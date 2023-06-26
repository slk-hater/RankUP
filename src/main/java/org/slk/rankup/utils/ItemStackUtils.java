package org.slk.rankup.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackUtils {
    public static void changeItemName(ItemStack is, String name){
        ItemMeta meta = is.getItemMeta();
        if(meta == null) return;
        meta.setDisplayName(ColorUtils.colorize(name));
        is.setItemMeta(meta);
    }
    public static void changeItemLore(ItemStack is, String lore){
        ItemMeta meta = is.getItemMeta();
        if(meta == null) return;
        List<String> loreRes = new ArrayList<>(List.of(lore.split("\n")));
        loreRes.replaceAll(ColorUtils::colorize);
        meta.setLore(loreRes);
        is.setItemMeta(meta);
    }
}