package dev.aetheris.database.services;

import dev.aetheris.database.enums.EventType;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Service<T> {

    protected final Statement statement;

    protected Service(Statement stmt) {
        this.statement = stmt;
    }

    public abstract void createTableIfNotExists() throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    public abstract T get(int id) throws SQLException;

    public abstract int insert(T neverInsertedYet) throws SQLException;

    public abstract T insertGet(T neverInsertedYet) throws SQLException;

    public abstract boolean update(@NotNull T toUpdate, EventType event) throws SQLException;

    public abstract T updateGet(@NotNull T toUpdate, EventType event) throws SQLException;

}
