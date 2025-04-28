package dev.aetheris.utils;

import dev.aetheris.Rules;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static boolean isSoloPlayer(Player player) {
        return !ServerUtils.isUniquePlayerConnected() && ServerUtils.isPlayerAlone(player);
    }

    public static boolean isPlayer(Entity entity) {
        return entity.getType() == EntityType.PLAYER;
    }

    public static boolean isPVP(Entity damager, Entity attacked){
        return isPlayer(damager) && isPlayer(attacked);
    }

    public static boolean isPVE(Entity damager, Entity attacked){
        return isPlayer(damager) && !isPlayer(attacked);
    }

    public static boolean isEVP(Entity damager, Entity attacked){
        return !isPlayer(damager) && isPlayer(attacked);
    }

    public static boolean areTogether(Player player1, Player player2){
        Location loc1 = player1.getLocation();
        Location loc2 = player2.getLocation();

        return Math.sqrt(Math.pow(loc2.getBlockX() - loc1.getBlockX(), 2) + Math.pow(loc2.getBlockY() - loc1.getBlockY(), 2)) <= Rules.blocksRadiusToBeConsideredAlone;
    }

}
