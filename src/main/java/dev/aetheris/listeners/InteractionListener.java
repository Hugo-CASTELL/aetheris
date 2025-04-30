package dev.aetheris.listeners;

import dev.aetheris.database.enums.EventType;
import dev.aetheris.database.models.Players;
import dev.aetheris.database.services.ServicePlayer;
import dev.aetheris.singleton.Singleton;
import dev.aetheris.utils.AetherisUtils;
import dev.aetheris.utils.DatabaseUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class InteractionListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        Players savedPlayer = Singleton.getInstance().getPlayers().get(player.getUniqueId());

        DatabaseUtils.runAsynchronously(String.format("Failed to handle player %s login event", player.getName()), statement -> {
            ServicePlayer servicePlayer = new ServicePlayer(statement);

            Players playerInDb = savedPlayer == null ? servicePlayer.insertGet(new Players(player)) :
                                                       servicePlayer.updateGet(savedPlayer, EventType.PLAYER_LOGIN);

            AetherisUtils.runSyncronously(() -> Singleton.getInstance().getPlayers().put(player.getUniqueId(), playerInDb));
        });
    }
}
