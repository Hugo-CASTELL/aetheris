package dev.aetheris.utils;

import dev.aetheris.database.models.Players;

import java.io.File;
import java.util.*;

public class DatabaseUtils {

    private DatabaseUtils() { }

    public static String getDatabaseConnectionString(File dataFolder) {
        return "jdbc:sqlite:" + dataFolder + "/" + AetherisUtils.getDatabaseFilename();
    }

    public static String joinColumns(Iterable<String> columns) {
        return String.join(", ", columns);
    }

    private static String joinValues(Iterable<String> values) {
        return String.join(", ", values);
    }

    private static List<String> orderValuesFromColumns(Iterable<String> columns, Map<String, Object> orderedValues) {
        List<String> orderedValuesList = new ArrayList<>();
        for (String column : columns) {
            Object value = orderedValues.get(column);
            if(value == null) {
                orderedValuesList.add("NULL");
                continue;
            }
            String valueString = switch (value) {
                case String s    -> s.isBlank() ? "NULL" : "'" + s + "'";
                case Character c -> "'" + c + "'";
                case Boolean b   -> Boolean.TRUE.equals(b) ? "1" : "0";
                case UUID uuid   -> "'" + uuid + "'";
                case Date date   -> "'" + date + "'";
                default          -> String.valueOf(value);
            };
            orderedValuesList.add(valueString);
        }
        return orderedValuesList;
    }

    private static List<String> getColumnsWithoutId(Collection<String> columns) {
        ArrayList<String> columnsWithoutId = new ArrayList<>(columns);
        columnsWithoutId.remove("id");
        return columnsWithoutId;
    }

    public static String createInsertQuery(Players player) {
        return createInsertQuery(Players.TABLENAME, Players.COLUMNS_LIST, player.toMap());
    }

    private static String createInsertQuery(String tableName, Collection<String> orderedColumns, Map<String, Object> orderedValues) {
        List<String> columnsWithoutId = getColumnsWithoutId(orderedColumns);
        return String.format(
            "INSERT INTO %s (%s) VALUES (%s);",
            tableName,
            joinColumns(columnsWithoutId),
            joinValues(orderValuesFromColumns(columnsWithoutId, orderedValues))
        );
    }

}
