package dev.aetheris.listeners;

import dev.aetheris.database.DatabaseManager;
import dev.aetheris.database.enums.EventType;
import dev.aetheris.database.models.Players;
import dev.aetheris.database.services.ServicePlayer;
import dev.aetheris.singleton.Singleton;
import dev.aetheris.utils.AetherisUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InteractionListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Players savedPlayer = Singleton.getInstance().getPlayers().get(player.getUniqueId());

        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();

            try (Statement statement = connection.createStatement()) {
                ServicePlayer servicePlayer = new ServicePlayer(statement);

                Players playerInDb = savedPlayer == null ? servicePlayer.insertGet(new Players(player)) :
                                                           servicePlayer.updateGet(savedPlayer, EventType.PLAYER_LOGIN);

                Singleton.getInstance().getPlayers().put(player.getUniqueId(), playerInDb);
            }

        } catch (InterruptedException | SQLException e){
            AetherisUtils.logWarn(String.format("Failed to handle player %s login event", player.getName()), e);
        } finally {
            if(connection != null){
                try{
                    DatabaseManager.releaseConnection(connection);
                } catch (Exception e) {
                    AetherisUtils.logWarn("Failed to release connection", e);
                }
            }
        }
    }

    // # NOTE TO DO NOT FORGET ABOUT BUKKIT RUNNABLE #
}
