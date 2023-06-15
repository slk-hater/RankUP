package org.slk.rankup.utils;

public class ChatUtils {
    public static String good(String message) { return ColorUtils.colorize("&a&l# &r" + message); }
    public static String info(String message) { return ColorUtils.colorize("&e&l# &r" + message); }
    public static String error(String message) { return ColorUtils.colorize("&c&l# &r" + message); }
}