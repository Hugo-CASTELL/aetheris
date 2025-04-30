package dev.aetheris.database.services;

import dev.aetheris.database.enums.EventType;
import dev.aetheris.database.models.Players;
import dev.aetheris.utils.AetherisUtils;
import dev.aetheris.utils.DatabaseUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicePlayer extends Service<Players> {

    public ServicePlayer(Statement stmt) {
        super(stmt);
    }

    @Override
    public boolean update(@NotNull Players player, EventType event) throws SQLException {
        if(player.getId() <= 0) {
            return false;
        } else {
            String query = switch (event){
                case PLAYER_LOGIN -> "UPDATE " + Players.TABLENAME + " SET login_count = login_count + 1, username = '" + player.getUsername() + "' WHERE id = " + player.getId();
            };
            return this.statement.executeUpdate(query) > 0;
        }
    }

    @Override
    public Players updateGet(@NotNull Players player, EventType event) throws SQLException {
        boolean updated = update(player, event);

        if (!updated && player.getId() <= 0) {
            // Only insert if update failed (player missing in DB)
            AetherisUtils.logWarn(String.format("Player %s not found in DB when trying to update, inserting new player", player.getUsername()));
            return insertGet(player);
        }

        return get(player.getId());
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        this.statement.executeUpdate(Players.DEFAULT_SCHEME);
    }

    @Override
    public List<Players> getAll() throws SQLException {
        List<Players> players = new ArrayList<>();
        try (var rs = this.statement.executeQuery("SELECT " + Players.COLUMNS + " FROM " + Players.TABLENAME)) {
            while (rs.next()) {
                players.add(new Players(rs));
            }
        }
        return players;
    }

    @Override
    public Players get(int id) throws SQLException {
        try (var rs = this.statement.executeQuery("SELECT " + Players.COLUMNS + " FROM " + Players.TABLENAME + " WHERE id = " + id)) {
            if (rs.next()) {
                return new Players(rs);
            }
        }
        return null;
    }

    @Override
    public int insert(Players newPlayer) throws SQLException {
        return this.statement.executeUpdate(DatabaseUtils.createInsertQuery(newPlayer), Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    public Players insertGet(Players newPlayer) throws SQLException {
        return get(insert(newPlayer));
    }
}
