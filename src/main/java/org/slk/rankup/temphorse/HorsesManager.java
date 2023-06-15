package org.slk.rankup.temphorse;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HorsesManager {
    static HashMap<Player, Horse> horseMap = new HashMap<>();

    public static HashMap<Player, Horse> getMap() { return horseMap; }
}