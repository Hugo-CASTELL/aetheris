package dev.aetheris.cache;

import dev.aetheris.database.enums.InteractionType;
import dev.aetheris.database.models.InteractionTypes;
import dev.aetheris.exception.AetherisRuntimeException;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class InteractionTypeCache {

    private final Map<InteractionType, Integer> interactionTypes;

    private InteractionTypeCache() {
        interactionTypes = new EnumMap<>(InteractionType.class);
    }

    private static final class InteractionTypeCacheHolder {
        private static final InteractionTypeCache INSTANCE = new InteractionTypeCache();
    }

    public static InteractionTypeCache getInstance() {
        return InteractionTypeCacheHolder.INSTANCE;
    }

    public Integer get(InteractionType interactionType) {
        return interactionTypes.get(interactionType);
    }

    private void put(InteractionTypes interactionType) {
        if(interactionType == null) throw new AetherisRuntimeException("Aborted tentative to add a null interactionType to cache");
        InteractionType correspondingEnum = InteractionType.valueOf(interactionType.getType());
        interactionTypes.put(correspondingEnum, interactionType.getId());
    }

    public void putMultiple(List<InteractionTypes> interactionTypes) {
        for(InteractionTypes interactionType : interactionTypes) {
            put(interactionType);
        }
    }

}
