package org.slk.rankup.mounts;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MountsManager {
    static HashMap<Player, Horse> horseMap = new HashMap<>();

    public static HashMap<Player, Horse> getMap() { return horseMap; }
}