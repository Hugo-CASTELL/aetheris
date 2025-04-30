package dev.aetheris.database.models;

import dev.aetheris.utils.DatabaseUtils;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Players {

    public static final String DEFAULT_SCHEME = String.format(
        """
            CREATE TABLE IF NOT EXISTS %s (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                uuid TEXT NOT NULL UNIQUE,
                username TEXT,
                login_count INTEGER
            );
        """, Players.TABLENAME);

    public static final String TABLENAME = "players";
    public static final List<String> COLUMNS_LIST = Arrays.asList(
            "id",
            "uuid",
            "username",
            "login_count"
    );
    public static final String COLUMNS = DatabaseUtils.joinColumns(COLUMNS_LIST);

    private final int id;
    private final UUID uuid;
    private final String username;
    private int loginCount;

    public Players(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.uuid = UUID.fromString(rs.getString("uuid"));
        this.username = rs.getString("username");
        this.loginCount = rs.getInt("login_count");
    }

    public Players(Player player) {
        this.id = -1; // Not set yet
        this.uuid = player.getUniqueId();
        this.username = player.getName();
        this.loginCount = 1; // Default value
    }

    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername(){
        return username;
    }

    public Map<String, Object> toMap() {
        return Map.of(
            "id", id,
            "uuid", uuid.toString(),
            "username", username,
            "login_count", loginCount
        );
    }
}
