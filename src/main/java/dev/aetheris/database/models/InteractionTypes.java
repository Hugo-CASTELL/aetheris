package dev.aetheris.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InteractionTypes {

    public static final String DEFAULT_SCHEME = String.format(
        """
            CREATE TABLE IF NOT EXISTS %s (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                type TEXT NOT NULL UNIQUE
            );
        """, InteractionTypes.TABLENAME);
    public static final String TABLENAME = "interaction_types";
    public static final String COLUMNS = "id, type";

    private final int id;
    private final String type;

    public InteractionTypes(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.type = rs.getString("type");
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
