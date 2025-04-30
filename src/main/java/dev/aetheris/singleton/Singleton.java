package dev.aetheris.singleton;

import dev.aetheris.database.enums.InteractionType;
import dev.aetheris.database.models.Players;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class Singleton {

    public static Logger Logger;
    public static File DataFolder;

    public static BlockingQueue<Connection> ConnectionPool;

    public static HashMap<InteractionType, Integer> InteractionTypes;
    public static HashMap<UUID, Players> Players;

}
