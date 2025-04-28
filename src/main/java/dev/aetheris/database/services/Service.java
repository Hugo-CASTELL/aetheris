package dev.aetheris.database.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Service<T> {

    protected final Statement statement;

    public Service(Statement stmt) {
        this.statement = stmt;
    }

    public abstract void createTableIfNotExists() throws SQLException;

    public abstract List<T> getAll() throws SQLException;

}
