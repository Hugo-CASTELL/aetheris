package dev.aetheris.database;

import dev.aetheris.Rules;
import dev.aetheris.database.enums.InteractionType;
import dev.aetheris.database.models.InteractionTypes;
import dev.aetheris.database.models.Players;
import dev.aetheris.database.services.ServiceInteraction;
import dev.aetheris.database.services.ServiceInteractionType;
import dev.aetheris.database.services.ServicePlayer;
import dev.aetheris.singleton.Singleton;
import dev.aetheris.utils.DatabaseUtils;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class DatabaseManager {

    private DatabaseManager() { }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseUtils.getDatabaseConnectionString(Singleton.getInstance().getDataFolder()));
    }

    public static void createConnectionPool() throws SQLException {
        Singleton.getInstance().setConnectionPool(new ArrayBlockingQueue<>(Rules.MAX_CONNECTION_POOL_SIZE));
        int maxRetryAttempts = Rules.MAX_CONNECTION_POOL_SIZE;
        while (Singleton.getInstance().getConnectionPool().remainingCapacity() != 0 && maxRetryAttempts > 0) {
            boolean added = Singleton.getInstance().getConnectionPool().offer(createConnection());
            if(!added) {
                maxRetryAttempts--;
            }
        }
    }

    public static Connection getConnection() throws InterruptedException, SQLException {
        Connection take = Singleton.getInstance().getConnectionPool().take();
        if(!take.isClosed()) {
            return take;
        } else {
          return createConnection();
        }
    }

    public static void releaseConnection(Connection connection) {
        boolean added = false;
        int maxRetryAttempts = Rules.MAX_CONNECTION_POOL_SIZE;
        while (!added && maxRetryAttempts > 0) {
            added = Singleton.getInstance().getConnectionPool().offer(connection);
            maxRetryAttempts--;
        }
    }

    public static void releaseConnectionPool() throws SQLException {
        boolean wentWrong = false;
        for(Connection c : Singleton.getInstance().getConnectionPool()) {
            if(c != null && !c.isClosed()) {
                c.close();
                boolean removed = Singleton.getInstance().getConnectionPool().remove(c);
                if(!removed) wentWrong = true;
            }
        }
        if(wentWrong) {
            Singleton.getInstance().getConnectionPool().clear();
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
            Singleton.getInstance().setPlayers(new HashMap<>());
            for(Players player : new ServicePlayer(statement).getAll()) {
                Singleton.getInstance().getPlayers().put(player.getUuid(), player);
            }
            Singleton.getInstance().setInteractionTypes(new EnumMap<>(InteractionType.class));
            for(InteractionTypes interactionType : new ServiceInteractionType(statement).getAll()) {
                InteractionType correspondingEnum = InteractionType.valueOf(interactionType.getType());
                Singleton.getInstance().getInteractionTypes().put(correspondingEnum, interactionType.getId());
            }
        } finally {
            releaseConnection(connection);
        }
    }

}
