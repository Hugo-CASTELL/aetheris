package dev.aetheris.database.services;

import dev.aetheris.database.enums.EventType;
import dev.aetheris.database.models.Interactions;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceInteraction extends Service<Interactions> {

    public ServiceInteraction(Statement stmt) {
        super(stmt);
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        this.statement.executeUpdate(Interactions.DEFAULT_SCHEME);
    }

    @Override
    public List<Interactions> getAll() throws SQLException {
        List<Interactions> interactionTypes = new ArrayList<>();
        try (var rs = this.statement.executeQuery("SELECT " + Interactions.COLUMNS + " FROM " + Interactions.TABLENAME)) {
            while (rs.next()) {
                interactionTypes.add(new Interactions(rs));
            }
        }
        return interactionTypes;
    }

    @Override
    public Interactions get(int id) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int insert(Interactions neverInsertedYet) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Interactions insertGet(Interactions neverInsertedYet) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public boolean update(@NotNull Interactions toUpdate, EventType event) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Interactions updateGet(@NotNull Interactions toUpdate, EventType event) throws SQLException {
        throw new NotImplementedException();
    }
}
