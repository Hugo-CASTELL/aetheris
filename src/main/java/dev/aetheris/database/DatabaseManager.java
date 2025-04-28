package dev.aetheris.database;

import dev.aetheris.Rules;
import dev.aetheris.database.enums.InteractionTypes;
import dev.aetheris.database.models.InteractionType;
import dev.aetheris.database.models.Player;
import dev.aetheris.database.services.ServiceInteraction;
import dev.aetheris.database.services.ServiceInteractionType;
import dev.aetheris.database.services.ServicePlayer;
import dev.aetheris.singleton.Singleton;
import dev.aetheris.utils.DatabaseUtils;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class DatabaseManager {

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseUtils.getDatabaseConnectionString(Singleton.DataFolder));
    }

    public static void createConnectionPool() throws SQLException {
        Singleton.ConnectionPool = new ArrayBlockingQueue<>(Rules.maxConnectionPoolSize);
        int MAX_RETRY_ATTEMPTS = Rules.maxConnectionPoolSize;
        for(int i = 0; i < Rules.maxConnectionPoolSize; i++) {
            boolean added = Singleton.ConnectionPool.offer(createConnection());
            if(!added && MAX_RETRY_ATTEMPTS > 0) {
                i--;
                MAX_RETRY_ATTEMPTS--;
            }
        }
    }

    public static Connection getConnection() throws InterruptedException, SQLException {
        Connection take = Singleton.ConnectionPool.take();
        if(!take.isClosed()) {
            return take;
        } else {
          return createConnection();
        }
    }

    public static void releaseConnection(Connection connection) {
        boolean added = false;
        int MAX_RETRY_ATTEMPTS = Rules.maxConnectionPoolSize;
        while (!added && MAX_RETRY_ATTEMPTS > 0) {
            added = Singleton.ConnectionPool.offer(connection);
            MAX_RETRY_ATTEMPTS--;
        }
    }

    public static void releaseConnectionPool() throws SQLException {
        boolean wentWrong = false;
        for(Connection c : Singleton.ConnectionPool) {
            if(c != null && !c.isClosed()) {
                c.close();
                boolean removed = Singleton.ConnectionPool.remove(c);
                if(!removed) wentWrong = true;
            }
        }
        if(wentWrong) {
            Singleton.ConnectionPool.clear();
        }
    }

    public static void initializeDatabase() throws SQLException {
        Connection connection = createConnection();

        connection.setAutoCommit(false);
        try(Statement statement = connection.createStatement()) {
            new ServicePlayer(statement).createTableIfNotExists();
            new ServiceInteractionType(statement).createTableIfNotExists();
            new ServiceInteraction(statement).createTableIfNotExists();

            connection.commit();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

    public static void populateDatabase() throws SQLException, InterruptedException {
        Connection connection = getConnection();

        try(Statement statement = connection.createStatement()) {
            new ServiceInteractionType(statement).updateInteractionTypes();
        } finally {
            releaseConnection(connection);
        }
    }

    public static void retrieveData() throws InterruptedException, SQLException {
        Connection connection = getConnection();

        try(Statement statement = connection.createStatement()){
            Singleton.Players = new HashMap<>();
            for(Player player : new ServicePlayer(statement).getAll()) {
                Singleton.Players.put(player.getUuid(), player);
            }
            Singleton.InteractionTypes = new HashMap<>();
            for(InteractionType interactionType : new ServiceInteractionType(statement).getAll()) {
                InteractionTypes correspondingEnum = InteractionTypes.valueOf(interactionType.getType());
                Singleton.InteractionTypes.put(correspondingEnum, interactionType.getId());
            }
        } finally {
            releaseConnection(connection);
        }
    }

}
