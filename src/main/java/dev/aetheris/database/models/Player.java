package dev.aetheris.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Player {

    public static final String DEFAULT_SCHEME = String.format(
        """
            CREATE TABLE IF NOT EXISTS %s (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                uuid TEXT NOT NULL UNIQUE,
                username TEXT,
                login_count INTEGER
            );
        """, Player.TABLENAME);

    public static final String TABLENAME = "players";
    public static final String COLUMNS = "id, uuid, username, login_count";

    private final int id;
    private final UUID uuid;
    private final String username;
    private final int loginCount;

    public Player(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.username = rs.getString("username");
        this.loginCount = rs.getInt("login_count");
    }

    public UUID getUuid() {
        return uuid;
    }
}
