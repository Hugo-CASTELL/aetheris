package dev.aetheris;

public class Rules {

    public static boolean activateSolidarityTweaks = true;

    public static int chunksRadiusToBeConsideredAlone = 6;

    public static int NERF_solidarity_soloPlayerEVPDamagePercentageIgnored = 30;
    public static int NERF_solidarity_soloPlayerRegainHealthPercentageIgnored = 30;
    public static int BUFF_solidarity_multiPlayerPVEDamagePercentagePerNearbyAlly = 3;
    public static int BUFF_solidarity_multiPlayerPVEDamagePercentagePerNearbyAllyMax = 9;

    public static int maxConnectionPoolSize = 20;

    public static int blocksRadiusToBeConsideredAlone = chunksRadiusToBeConsideredAlone * 16;
}
