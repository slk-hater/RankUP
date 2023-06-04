package org.slk.rankup.ranks;

import net.md_5.bungee.api.ChatColor;

public enum Rank {
    BRONZE("BRONZE", "#CD7F32"),
    SILVER("SILVER", "#C0C0C0"),
    GOLD("GOLD", "#D4AF37"),
    DIAMOND("DIAMOND", "#b9f2ff");

    private final String Prefix;
    private final String Color;

    Rank(String Prefix, String Color) {
        this.Prefix = Prefix;
        this.Color = Color;
    }

    public String getPrefix() { return String.valueOf(getColor()) + ChatColor.BOLD + Prefix; }
    public ChatColor getColor() { return ChatColor.of(Color); }
}