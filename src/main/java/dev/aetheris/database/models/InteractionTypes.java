package dev.aetheris.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InteractionTypes {

    public static final String TABLENAME = "interaction_types";

    public static final class Columns {
        private Columns() {}
        public static final String ID = "id";
        public static final String TYPE = "type";
    }

    public static final List<String> COLUMNS_LIST = List.of(
        Columns.ID,
        Columns.TYPE
    );

    public static final String COLUMNS = String.join(", ", COLUMNS_LIST);

    public static final String DEFAULT_SCHEME = String.format(
        """
            CREATE TABLE IF NOT EXISTS %s (
                %s INTEGER PRIMARY KEY AUTOINCREMENT,
                %s TEXT NOT NULL UNIQUE
            );
        """,
        TABLENAME,
        Columns.ID,
        Columns.TYPE
    );

    private final int id;
    private final String type;

    public InteractionTypes(ResultSet rs) throws SQLException {
        this(rs.getInt(Columns.ID), rs.getString(Columns.TYPE));
    }

    private InteractionTypes(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
