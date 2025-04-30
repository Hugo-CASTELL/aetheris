package dev.aetheris.singleton;

import dev.aetheris.database.enums.InteractionType;
import dev.aetheris.database.models.Players;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.Connection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Singleton {

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Plugin plugin;
    private Logger logger;
    private File dataFolder;

    private BlockingQueue<Connection> connectionPool;

    private Map<InteractionType, Integer> interactionTypes;
    private Map<UUID, Players> players;

    private Singleton() { }

    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public void setDataFolder(File dataFolder) {
        this.dataFolder = dataFolder;
    }

    public BlockingQueue<Connection> getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(BlockingQueue<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Map<InteractionType, Integer> getInteractionTypes() {
        return interactionTypes;
    }

    public void setInteractionTypes(Map<InteractionType, Integer> interactionTypes) {
        this.interactionTypes = interactionTypes;
    }

    public Map<UUID, Players> getPlayers() {
        return players;
    }

    public void setPlayers(Map<UUID, Players> players) {
        this.players = players;
    }

}
