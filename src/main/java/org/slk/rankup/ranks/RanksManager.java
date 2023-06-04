package org.slk.rankup.ranks;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RanksManager {
    static Map<Player, Rank> ranks = new HashMap<>();

    public static void setRank(Player player, Rank rank) {
        ranks.put(player, rank);
    }

    public static Rank getRank(Player player) {
        Rank rank = ranks.get(player);
        if(rank == null) { return Rank.BRONZE; }
        return rank;
    }
}