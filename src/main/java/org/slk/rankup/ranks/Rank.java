package org.slk.rankup.ranks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.slk.rankup.utils.ColorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Rank {
    BRONZE("BRONZE", "#CD7F32", 0.0D),
    SILVER("SILVER", "#C0C0C0", 15000.0D),
    GOLD("GOLD", "#D4AF37", 125000.0D),
    DIAMOND("DIAMOND", "#B9F2FF", 540000.0D);

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
    public Rank getPreviousRank(){
        Object[] vals = getRanks().toArray();
        Rank prevRank = null;
        try{
            prevRank = (Rank) vals[(this.ordinal() - 1)];
        } catch(Exception ignored) {}
        return prevRank;
    }
    public Rank getNextRank(){
        Object[] vals = getRanks().toArray();
        Rank nextRank = null;
        try {
            nextRank = (Rank) vals[(this.ordinal() + 1)];
        } catch(Exception ignored) {}
        return nextRank;
    }
    public boolean isGreaterThan(Rank rank){ return this.ordinal() > rank.ordinal(); }

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
    }
    public static Stream<Rank> getRanks() { return Stream.of(Rank.values()); }
    public static Rank getDefaultRank() { return getRanks().filter(Rank::isDefault).findFirst().orElse(getRanks().findFirst().orElse(null)); }
    public static int getNextRankProgressPercentage(Player player){
        double money = 7500;
        Rank nextRank = getRank(player).getNextRank();
        if(nextRank != null) { return (int) ((money*100)/nextRank.getPrice()); }
        return -1;
    }
    public static String getNextRankProgressSymbols(Player player){
        int percentage = getNextRankProgressPercentage(player);
        if(percentage == -1) { return ColorUtils.colorize("&aCOMPLETO"); }
        int chars = 0;
        StringBuilder sb = new StringBuilder();
        if(percentage > 10){
            sb.append("&a&l");
        }
        while(percentage >= 10){
            percentage -= 10;
            sb.append("∎");
            chars++;
        }
        sb.append("&7&l");
        while(chars < 10){
            sb.append("∎");
            chars++;
        }
        return ColorUtils.colorize(sb.toString());
    }
}