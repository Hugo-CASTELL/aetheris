package dev.aetheris.cache;

public final class Cache {

    private Cache() { }

    public static final PlayerCache PLAYER_CACHE = PlayerCache.getInstance();
    public static final InteractionTypeCache INTERACTION_TYPE_CACHE = InteractionTypeCache.getInstance();

}
