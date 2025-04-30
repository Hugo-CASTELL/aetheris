package dev.aetheris;

public class Rules {

    private Rules() { }

    public static final boolean ACTIVATE_SOLIDARITY_TWEAKS = true;

    public static final int CHUNKS_RADIUS_TO_BE_CONSIDERED_ALONE = 6;

    public static final int NERF_SOLIDARITY_SOLO_PLAYER_EVP_DAMAGE_PERCENTAGE_IGNORED = 30;
    public static final int NERF_SOLIDARITY_SOLO_PLAYER_REGAIN_HEALTH_PERCENTAGE_IGNORED = 30;
    public static final int BUFF_SOLIDARITY_MULTI_PLAYER_PVE_DAMAGE_PERCENTAGE_PER_NEARBY_ALLY = 3;
    public static final int BUFF_SOLIDARITY_MULTI_PLAYER_PVE_DAMAGE_PERCENTAGE_PER_NEARBY_ALLY_MAX = 9;

    public static final String AETHERIS_DB = "aetheris.db";
    public static final int MAX_CONNECTION_POOL_SIZE = 20;

    public static final int BLOCKS_RADIUS_TO_BE_CONSIDERED_ALONE = CHUNKS_RADIUS_TO_BE_CONSIDERED_ALONE * 16;
}
