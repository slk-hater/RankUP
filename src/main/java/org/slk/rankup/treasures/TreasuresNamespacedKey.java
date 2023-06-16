package org.slk.rankup.treasures;

import org.bukkit.NamespacedKey;
import org.slk.rankup.Core;

public enum TreasuresNamespacedKey {
    INVENTORY("beforeTreasureInventoryBase64");

    private final String key;

    TreasuresNamespacedKey(String key){
        this.key = key;
    }

    public NamespacedKey get(){ return new NamespacedKey(Core.getInstance(), key); }
}