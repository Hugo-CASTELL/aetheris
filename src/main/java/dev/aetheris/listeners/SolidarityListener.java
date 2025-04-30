package dev.aetheris.listeners;

import dev.aetheris.Rules;
import dev.aetheris.utils.AetherisUtils;
import dev.aetheris.utils.PlayerUtils;
import dev.aetheris.utils.ServerUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class SolidarityListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onSoloPlayerEVPNerf(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity defender = event.getEntity();

        if(PlayerUtils.isEVP(damager, defender) &&
           PlayerUtils.isSoloPlayer((Player) defender)){
            event.setDamage(AetherisUtils.increase(event.getDamage(), Rules.NERF_SOLIDARITY_SOLO_PLAYER_EVP_DAMAGE_PERCENTAGE_IGNORED));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMultiPlayerPVEBuff(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity defender = event.getEntity();

        if(PlayerUtils.isPVE(damager, defender) &&
           !ServerUtils.isUniquePlayerConnected()) {
            event.setDamage(AetherisUtils.increase(event.getDamage(), Math.min(Rules.BUFF_SOLIDARITY_MULTI_PLAYER_PVE_DAMAGE_PERCENTAGE_PER_NEARBY_ALLY * ServerUtils.countPlayersAroundPlayer((Player) damager), Rules.BUFF_SOLIDARITY_MULTI_PLAYER_PVE_DAMAGE_PERCENTAGE_PER_NEARBY_ALLY_MAX)));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRegainHealthSoloPlayerNerf(EntityRegainHealthEvent event) {
        Entity entity = event.getEntity();

        if(PlayerUtils.isPlayer(entity) &&
           PlayerUtils.isSoloPlayer((Player) entity)){
            event.setAmount(AetherisUtils.decrease(event.getAmount(), Rules.NERF_SOLIDARITY_SOLO_PLAYER_REGAIN_HEALTH_PERCENTAGE_IGNORED));
        }

    }

}