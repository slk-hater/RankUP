package org.slk.rankup.playerdata;

import org.bukkit.entity.Player;

import java.util.Dictionary;
import java.util.Hashtable;

public class PlayerDataManager {
    static Dictionary<Player, Dictionary<String, Object>> cache = new Hashtable<>();

    public static Dictionary<Player, Dictionary<String, Object>> getCache() { return cache; }
    public static Dictionary<String, Object> getPlayerData(Player player) { return getCache().get(player); }
}