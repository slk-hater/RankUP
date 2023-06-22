package org.slk.rankup.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ColorUtils {
    public static String colorize(String message){ return ChatColor.translateAlternateColorCodes('&', message); }
    public static void colorize(Player player, String message){ player.sendMessage(colorize(message)); }
}