package dev.aetheris.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Interactions {

    public static final String TABLENAME = "interactions";

    public static final class Columns {
        private Columns() {}
        public static final String ID = "id";
        public static final String INTERACTION_TYPE_ID = "interaction_type_id";
        public static final String TIMESTAMP = "timestamp";
        public static final String PLAYER_INITIATOR_ID = "player_initiator_id";
        public static final String PLAYER_RECIPIENT_ID = "player_recipient_id";
        public static final String CONTEXT_JSON = "context_json";
    }

    public static final List<String> COLUMNS_LIST = List.of(
        Columns.ID,
        Columns.INTERACTION_TYPE_ID,
        Columns.TIMESTAMP,
        Columns.PLAYER_INITIATOR_ID,
        Columns.PLAYER_RECIPIENT_ID,
        Columns.CONTEXT_JSON
    );

    public static final String COLUMNS = String.join(", ", COLUMNS_LIST);

    public static final String DEFAULT_SCHEME = String.format(
        """
            CREATE TABLE IF NOT EXISTS %s (
                %s INTEGER PRIMARY KEY AUTOINCREMENT,
                %s INTEGER NOT NULL,
                %s INTEGER NOT NULL,
                %s INTEGER,
                %s INTEGER,
                %s TEXT,
                FOREIGN KEY (%s) REFERENCES %s(%s),
                FOREIGN KEY (%s) REFERENCES %s(%s),
                FOREIGN KEY (%s) REFERENCES %s(%s)
            );
        """,
        TABLENAME,
        Columns.ID,
        Columns.INTERACTION_TYPE_ID,
        Columns.TIMESTAMP,
        Columns.PLAYER_INITIATOR_ID,
        Columns.PLAYER_RECIPIENT_ID,
        Columns.CONTEXT_JSON,
        Columns.PLAYER_INITIATOR_ID, Players.TABLENAME, Players.Columns.ID,
        Columns.PLAYER_RECIPIENT_ID, Players.TABLENAME, Players.Columns.ID,
        Columns.INTERACTION_TYPE_ID, InteractionTypes.TABLENAME, Interactions.Columns.ID
    );

    private final int id;
    private final int interactionTypeId;
    private final long timestamp;
    private final int playerInitiatorId;
    private final int playerRecipientId;
    private final String contextJson;

    public Interactions(ResultSet rs) throws SQLException {
        this(rs.getInt(Columns.ID), rs.getInt(Columns.INTERACTION_TYPE_ID), rs.getLong(Columns.TIMESTAMP), rs.getInt(Columns.PLAYER_INITIATOR_ID), rs.getInt(Columns.PLAYER_RECIPIENT_ID), rs.getString(Columns.CONTEXT_JSON));
    }

    private Interactions(int id, int interactionTypeId, long timestamp, int playerInitiatorId, int playerRecipientId, String contextJson) {
        this.id = id;
        this.interactionTypeId = interactionTypeId;
        this.timestamp = timestamp;
        this.playerInitiatorId = playerInitiatorId;
        this.playerRecipientId = playerRecipientId;
        this.contextJson = contextJson;
    }
}
