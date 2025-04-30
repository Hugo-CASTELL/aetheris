package dev.aetheris;

import dev.aetheris.database.DatabaseManager;
import dev.aetheris.listeners.InteractionListener;
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
        Singleton.getInstance().setPlugin(this);
        Singleton.getInstance().setLogger(this.getLogger());
        Singleton.getInstance().setDataFolder(this.getDataFolder());

        // #----------------------------------#
        // # Plugin folder creation or update #
        // #----------------------------------#
        if(!Singleton.getInstance().getDataFolder().exists() || !Singleton.getInstance().getDataFolder().isDirectory()){
            if(Singleton.getInstance().getDataFolder().mkdirs()) {
                AetherisUtils.logInfo("Plugin folder created successfully");
            } else {
                AetherisUtils.log("Failed to create plugin folder. Enable aborted.", Level.SEVERE);
                return;
            }
        }

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
            AetherisUtils.logInfo("Database updated and retrieved data in memory for performance successfully");
        } catch (SQLException | InterruptedException e) {
            AetherisUtils.log("Failed to populate or retrieve data from SQLite database. Enable aborted.", Level.SEVERE, e);
            return;
        }

        // #--------------------------------#
        // # Register interaction listening #
        // #--------------------------------#
        registerListener(new InteractionListener());

        // #---------------#
        // # Plugin tweaks #
        // #---------------#
        if(Rules.ACTIVATE_SOLIDARITY_TWEAKS) registerListener(new SolidarityListener());

        // #----------------#
        // # Plugin enabled #
        // #----------------#
        AetherisUtils.logInfo("Plugin enabled successfully");
    }

    @Override
    public void onDisable() {
        try {
            DatabaseManager.releaseConnectionPool();
            AetherisUtils.logInfo("Plugin disabled successfully");
        } catch (SQLException e) {
            AetherisUtils.log("Failed to close database", Level.SEVERE, e);
        }
    }

    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
}
