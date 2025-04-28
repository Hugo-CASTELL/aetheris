package dev.aetheris.utils;

import dev.aetheris.singleton.Singleton;

import java.util.logging.Level;

public class AetherisUtils {

    public static String getDatabaseFilename() {
        return "aetheris.db";
    }

    public static void logInfo(String message) {
        log(message, Level.INFO, null);
    }

    public static void log(String message, Level level, Exception e) {
        String aetherisLog = "[Aetheris] " + message;
        if(e != null) {
            Singleton.Logger.log(level, aetherisLog, e);
        }
        else {
            Singleton.Logger.info(aetherisLog);
        }
    }

    public static double increase(double initial, int percentage) {
        return initial + (initial * percentage / 100);
    }

    public static double decrease(double initial, int percentage) {
        return initial - (initial * percentage / 100);
    }

}
