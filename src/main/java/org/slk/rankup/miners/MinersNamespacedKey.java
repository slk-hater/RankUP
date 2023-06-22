package org.slk.rankup.miners;

import org.bukkit.NamespacedKey;
import org.slk.rankup.Core;

public enum MinersNamespacedKey {
    OWNERSHIP("minersOwnership"),
    FUEL("fuelProduction");

    private final String key;

    MinersNamespacedKey(String key){
        this.key = key;
    }

    public NamespacedKey get(){ return new NamespacedKey(Core.getInstance(), key); }
}