package dev.aetheris.database.models;

import dev.aetheris.utils.DatabaseUtils;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Players {

    public static final String TABLENAME = "players";

    public static final class Columns {
        private Columns() { }
        public static final String ID = "id";
        public static final String UUID = "uuid";
        public static final String USERNAME = "username";
        public static final String LOGIN_COUNT = "login_count";
    }

    public static final List<String> COLUMNS_LIST = List.of(
        Columns.ID,
        Columns.UUID,
        Columns.USERNAME,
        Columns.LOGIN_COUNT
    );

    public static final String COLUMNS = DatabaseUtils.joinColumns(COLUMNS_LIST);

    public static final String DEFAULT_SCHEME = String.format(
        """
            CREATE TABLE IF NOT EXISTS %s (
                %s INTEGER PRIMARY KEY AUTOINCREMENT,
                %s TEXT NOT NULL UNIQUE,
                %s TEXT,
                %s INTEGER
            );
        """,
        TABLENAME,
        Columns.ID,
        Columns.UUID,
        Columns.USERNAME,
        Columns.LOGIN_COUNT
    );

    private final int id;
    private final UUID uuid;
    private final String username;
    private final int loginCount;

    public Players(ResultSet rs) throws SQLException {
        this(rs.getInt(Columns.ID), UUID.fromString(rs.getString(Columns.UUID)), rs.getString(Columns.USERNAME), rs.getInt(Columns.LOGIN_COUNT));
    }

    public Players(Player player) {
        this(-1, player.getUniqueId(), player.getName(), 1 );
    }

    private Players(int id, UUID uuid, String username, int loginCount) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.loginCount = loginCount;
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
            Columns.ID, id,
            Columns.UUID, uuid.toString(),
            Columns.USERNAME, username,
            Columns.LOGIN_COUNT, loginCount
        );
    }
}
