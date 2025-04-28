package dev.aetheris.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ServerUtils {

    public static Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getServer().getOnlinePlayers();
    }

    public static int countConnectedPlayers(){
        return getOnlinePlayers().size();
    }

    public static boolean isUniquePlayerConnected(){
        return countConnectedPlayers() == 1;
    }

    public static int countPlayersAroundPlayer(Player player){
        if(isUniquePlayerConnected()){
            return 0;
        }
        else {
            int playerCount = 0;

            for(Player otherPlayer : getOnlinePlayers()){
                if(!otherPlayer.getUniqueId().equals(player.getUniqueId())){
                    if(PlayerUtils.areTogether(player, otherPlayer)){
                        playerCount++;
                    }
                }
            }

            return playerCount;
        }
    }

    public static boolean isPlayerAlone(Player player){
        return countPlayersAroundPlayer(player) == 0;
    }

    public static boolean isPlayerWithAtLeast(Player player, int amount){
        return countPlayersAroundPlayer(player) >= amount;
    }

}
