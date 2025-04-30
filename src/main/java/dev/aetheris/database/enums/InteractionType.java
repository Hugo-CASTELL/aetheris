package dev.aetheris.database.enums;

public enum InteractionType {
    PICKUP_ITEM,
    DROP_ITEM,
    DAMAGE_PLAYER,
    HEAL_PLAYER,
    CHAT_MESSAGE,
    BLOCK_BREAK,
    BLOCK_PLACE;

    public String getDatabaseValue() {
        return this.name().toLowerCase();
    }
}
