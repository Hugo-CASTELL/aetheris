package dev.aetheris.database.services;

import dev.aetheris.database.models.Player;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicePlayer extends Service<Player> {

    public ServicePlayer(Statement stmt) {
        super(stmt);
    }

    public void createTableIfNotExists() throws SQLException {
        this.statement.executeUpdate(Player.DEFAULT_SCHEME);
    }

    @Override
    public List<Player> getAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        try (var rs = this.statement.executeQuery("SELECT " + Player.COLUMNS + " FROM " + Player.TABLENAME)) {
            while (rs.next()) {
                players.add(new Player(rs));
            }
        }
        return players;
    }
}
