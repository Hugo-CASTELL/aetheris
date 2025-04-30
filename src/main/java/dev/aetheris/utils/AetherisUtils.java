package dev.aetheris.utils;

import dev.aetheris.singleton.Singleton;

import java.util.logging.Level;

public class AetherisUtils {

    public static String getDatabaseFilename() {
        return "aetheris.db";
    }

    public static void logInfo(String message) {
        log(message, Level.INFO);
    }

    public static void logWarn(String message) {
        log(message, Level.WARNING);
    }

    public static void logWarn(String message, Exception e) {
        log(message, Level.WARNING, e);
    }

    public static void log(String message, Level level) {
        log(message, level, null);
    }

    public static void log(String message, Level level, Exception e) {
        if(e != null) {
            Singleton.Logger.log(level, message, e);
        }
        else {
            Singleton.Logger.log(level, message);
        }
    }

    public static double increase(double initial, int percentage) {
        return initial + (initial * percentage / 100);
    }

    public static double decrease(double initial, int percentage) {
        return initial - (initial * percentage / 100);
    }

}
