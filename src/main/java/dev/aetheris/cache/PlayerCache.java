package dev.aetheris.cache;

import dev.aetheris.database.models.Players;
import dev.aetheris.exception.AetherisRuntimeException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerCache {

    private final Map<UUID, Players> players;

    private PlayerCache() {
        players = new ConcurrentHashMap<>();
    }

    private static final class PlayerCacheHolder {
        private static final PlayerCache INSTANCE = new PlayerCache();
    }

    public static PlayerCache getInstance() {
        return PlayerCacheHolder.INSTANCE;
    }

    public Players get(UUID uuid) {
        return players.get(uuid);
    }

    public void put(Players player) {
        if(player == null) throw new AetherisRuntimeException("Aborted tentative to add a null player to cache");
        players.put(player.getUuid(), player);
    }

    public void putMultiple(List<Players> addPlayers) {
        for(Players player : addPlayers) {
            put(player);
        }
    }

}
