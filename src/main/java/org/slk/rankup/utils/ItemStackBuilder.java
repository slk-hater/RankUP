package org.slk.rankup.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemStackBuilder {
    public static ItemStack build(Material material, int count, String name, String lore){
        ItemStack is = new ItemStack(material, count);
        ItemMeta im = is.getItemMeta();
        assert im != null;
        im.setDisplayName(ColorUtils.colorize(name));
        List<String> loreRes = new ArrayList<>(Collections.singletonList(ColorUtils.colorize(Arrays.toString(lore.split("\n")))));
        im.setLore(loreRes);
        is.setItemMeta(im);
        return is;
    }
}