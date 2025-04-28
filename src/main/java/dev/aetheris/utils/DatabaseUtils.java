package dev.aetheris.utils;

import java.io.File;

public class DatabaseUtils {

    public static String getDatabaseConnectionString(File dataFolder) {
        return "jdbc:sqlite:" + dataFolder + "/" + AetherisUtils.getDatabaseFilename();
    }

}
