package org.slk.rankup.ranks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.slk.rankup.nametags.TagManager;
import org.slk.rankup.nametags.TeamAction;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Rank {
    BRONZE("BRONZE", "#CD7F32", 0.0D),
    SILVER("SILVER", "#C0C0C0", 15000.0D),
    GOLD("GOLD", "#D4AF37", 125000.0D),
    DIAMOND("DIAMOND", "#b9f2ff", 540000.0D);

    private final String prefix;
    private final String color;
    private final double price;

    Rank(String prefix, String color, double price) {
        this.prefix = prefix;
        this.color = color;
        this.price = price;
    }

    public String getPrefix() { return String.valueOf(getColor()) + ChatColor.BOLD + prefix; }
    public ChatColor getColor() { return ChatColor.of(color); }
    public double getPrice() { return this.price; }
    public boolean isDefault() { return getPrice() == 0; }

    static final Map<Player, Rank> ranksMap = new HashMap<>();
    public static Rank getRank(Player player) {
        Rank rank = ranksMap.get(player);
        if(rank == null) {
            setRank(player, getDefaultRank());
            return ranksMap.get(player);
        }
        return rank;
    }
    public static void setRank(Player player, Rank rank) {
        ranksMap.put(player, rank);
        TagManager.changePlayerName(player, rank.getPrefix(), TeamAction.UPDATE);
    }
    public static Stream<Rank> getRanks() { return Stream.of(Rank.values()); }
    public static Rank getNextRank(Player player) {
        Object[] vals = getRanks().toArray();
        return (Rank) vals[(getRank(player).ordinal() + 1) % vals.length];
    }
    public static Rank getDefaultRank() { return getRanks().filter(Rank::isDefault).findFirst().orElse(getRanks().findFirst().orElse(null)); }
    public static int getNextRankProgress(Player player, int money){
        Rank nextRank = getNextRank(player);
        if(nextRank != null) { return (int) ((money*100)/nextRank.getPrice()); }
        return -1;
    }
}