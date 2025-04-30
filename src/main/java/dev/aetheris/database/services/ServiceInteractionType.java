package dev.aetheris.database.services;

import dev.aetheris.database.enums.EventType;
import dev.aetheris.database.enums.InteractionType;
import dev.aetheris.database.models.InteractionTypes;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceInteractionType extends Service<InteractionTypes> {

    public ServiceInteractionType(Statement stmt) {
        super(stmt);
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        this.statement.executeUpdate(InteractionTypes.DEFAULT_SCHEME);
    }

    public void updateInteractionTypes() throws SQLException {
        for (InteractionType type : InteractionType.values()) {
            statement.executeUpdate(
                "INSERT OR IGNORE INTO " + InteractionTypes.TABLENAME + " (type) VALUES ('" + type.name() + "')"
            );
        }
    }

    @Override
    public List<InteractionTypes> getAll() throws SQLException {
        List<InteractionTypes> interactionTypes = new ArrayList<>();
        try (var rs = this.statement.executeQuery("SELECT " + InteractionTypes.COLUMNS + " FROM " + InteractionTypes.TABLENAME)) {
            while (rs.next()) {
                interactionTypes.add(new InteractionTypes(rs));
            }
        }
        return interactionTypes;
    }

    @Override
    public InteractionTypes get(int id) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int insert(InteractionTypes neverInsertedYet) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InteractionTypes insertGet(InteractionTypes neverInsertedYet) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean update(@NotNull InteractionTypes toUpdate, EventType event) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public InteractionTypes updateGet(@NotNull InteractionTypes toUpdate, EventType event) throws SQLException {
        throw new NotImplementedException();
    }
}
