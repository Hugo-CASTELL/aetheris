package dev.aetheris.database.services;

import dev.aetheris.database.enums.InteractionTypes;
import dev.aetheris.database.models.InteractionType;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceInteractionType extends Service<InteractionType> {

    public ServiceInteractionType(Statement stmt) {
        super(stmt);
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        this.statement.executeUpdate(InteractionType.DEFAULT_SCHEME);
    }

    public void updateInteractionTypes() throws SQLException {
        for (InteractionTypes type : InteractionTypes.values()) {
            statement.executeUpdate(
                "INSERT OR IGNORE INTO " + InteractionType.TABLENAME + " (type) VALUES ('" + type.name() + "')"
            );
        }
    }

    @Override
    public List<InteractionType> getAll() throws SQLException {
        List<InteractionType> interactionTypes = new ArrayList<>();
        try (var rs = this.statement.executeQuery("SELECT " + InteractionType.COLUMNS + " FROM " + InteractionType.TABLENAME)) {
            while (rs.next()) {
                interactionTypes.add(new InteractionType(rs));
            }
        }
        return interactionTypes;
    }
}
