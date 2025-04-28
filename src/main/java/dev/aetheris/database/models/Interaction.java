package dev.aetheris.database.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Interaction {

    public static final String DEFAULT_SCHEME =
        """
            CREATE TABLE IF NOT EXSISTS interactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                interaction_type_id INTEGER NOT NULL,
                timestamp INTEGER NOT NULL,               -- UNIX timestamp in millis
                player_initiator_id INTEGER,              -- initiator
                player_recipient_id INTEGER,              -- recipient (optional)
                context_json TEXT,                        -- JSON blob for context (optional)
                FOREIGN KEY (player_a_id) REFERENCES players(id),
                FOREIGN KEY (player_b_id) REFERENCES players(id),
                FOREIGN KEY (interaction_type_id) REFERENCES interaction_types(id)
            );
        """;
    public static final String TABLENAME = "interactions";
    public static final String COLUMNS = "id, interaction_type_id, timestamp, player_initiator_id, player_recipient_id, context_json";

    private final int id;
    private final int interaction_type_id;
    private final long timestamp;
    private final int player_initiator_id;
    private final int player_recipient_id;
    private final String context_json;

    public Interaction(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.interaction_type_id = rs.getInt("interaction_type_id");
        this.timestamp = rs.getLong("timestamp");
        this.player_initiator_id = rs.getInt("player_initiator_id");
        this.player_recipient_id = rs.getInt("player_recipient_id");
        this.context_json = rs.getString("context_json");
    }
}
