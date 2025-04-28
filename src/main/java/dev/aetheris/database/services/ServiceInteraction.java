package dev.aetheris.database.services;

import dev.aetheris.database.models.Interaction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceInteraction extends Service<Interaction> {

    public ServiceInteraction(Statement stmt) {
        super(stmt);
    }

    @Override
    public void createTableIfNotExists() throws SQLException {
        this.statement.executeUpdate(Interaction.DEFAULT_SCHEME);
    }

    @Override
    public List<Interaction> getAll() throws SQLException {
        List<Interaction> interactionTypes = new ArrayList<>();
        try (var rs = this.statement.executeQuery("SELECT " + Interaction.COLUMNS + " FROM " + Interaction.TABLENAME)) {
            while (rs.next()) {
                interactionTypes.add(new Interaction(rs));
            }
        }
        return interactionTypes;
    }
}
