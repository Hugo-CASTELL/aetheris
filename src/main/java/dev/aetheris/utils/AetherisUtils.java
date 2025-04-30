package dev.aetheris.utils;

import dev.aetheris.Rules;
import dev.aetheris.singleton.Singleton;

import java.util.logging.Level;

public class AetherisUtils {

    private AetherisUtils() { }

    public static String getDatabaseFilename() {
        return Rules.AETHERIS_DB;
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
            Singleton.getInstance().getLogger().log(level, message, e);
        }
        else {
            Singleton.getInstance().getLogger().log(level, message);
        }
    }

    public static double increase(double initial, int percentage) {
        return initial + (initial * percentage / 100);
    }

    public static double decrease(double initial, int percentage) {
        return initial - (initial * percentage / 100);
    }

}
