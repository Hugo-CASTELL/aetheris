package dev.aetheris;

import dev.aetheris.database.DatabaseManager;
import dev.aetheris.listeners.SolidarityListener;
import dev.aetheris.singleton.Singleton;
import dev.aetheris.utils.AetherisUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.logging.Level;

public class Aetheris extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // #---------------------------------#
        // # Initialize some Singleton parts #
        // #---------------------------------#
        Singleton.Logger = getLogger();
        Singleton.DataFolder = getDataFolder();

        // #---------------------------------#
        // # Database creation or connection #
        // #---------------------------------#
        try {
            DatabaseManager.initializeDatabase();
            DatabaseManager.createConnectionPool();
            AetherisUtils.logInfo("Database and scheme created/updated successfully");
        } catch (SQLException e) {
            AetherisUtils.log("Failed to connect to SQLite database. Enable aborted.", Level.SEVERE, e);
            return;
        }

        // #-----------------------------------------#
        // # Database population and retrieving data #
        // #-----------------------------------------#
        try {
            DatabaseManager.populateDatabase();
            DatabaseManager.retrieveData();
            AetherisUtils.logInfo("Database updated and retrieving data successfully");
        } catch (SQLException | InterruptedException e) {
            AetherisUtils.log("Failed to populate or retrieve data from SQLite database. Enable aborted.", Level.SEVERE, e);
            return;
        }

        // #-------------------------------#
        // # Register interaction analysis #
        // #-------------------------------#

        // #---------------#
        // # Plugin tweaks #
        // #---------------#
        if(Rules.activateSolidarityTweaks) {
            Bukkit.getPluginManager().registerEvents(new SolidarityListener(), this);
        }
    }

    @Override
    public void onDisable() {
        try {
            DatabaseManager.releaseConnectionPool();
        } catch (SQLException e) {
            AetherisUtils.log("Failed to close database", Level.SEVERE, e);
        }
    }
}
