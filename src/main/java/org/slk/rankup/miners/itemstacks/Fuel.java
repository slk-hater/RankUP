package org.slk.rankup.miners.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;

public class Fuel {
    static final int BASE_DURATION = 60;
    static String DESCRIPTION = "&7Energia &f%duration%";

    int duration;
    final ItemStack is;

    public Fuel(){
        this.is = ItemStackBuilder.build(Material.CHARCOAL,
                1,
                ChatColor.of("#36454F")+"Combustível fóssil",
                DESCRIPTION.replace("%duration%", String.valueOf(getDuration())));
        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        meta.setCustomModelData(CustomModelDataEnum.FUEL.get());
        setDuration(BASE_DURATION);
    }

    public static int getBaseDuration() { return BASE_DURATION; }

    public int getDuration() { return this.duration; }
    public void setDuration(int seconds) {
        this.duration = seconds;
        String durationStr = ChatColor.of("#36454F")+"0,4J";
        /*int h = getDuration()/3600;
        int r = getDuration()-h * 3600;
        int m = r/60;
        r = r - m * 60;
        int s = r;
        if(h > 0) durationStr += h + "h ";
        if(m > 0) durationStr += m + "m ";
        if(s > 0) durationStr += s + "s ";*/

        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        String lore = DESCRIPTION.replace("%duration%", durationStr);
        List<String> loreRes = new ArrayList<>(List.of(lore.split("\n")));
        loreRes.replaceAll(ColorUtils::colorize);
        meta.setLore(loreRes);
        getItemStack().setItemMeta(meta);
    }
    public ItemStack getItemStack() { return this.is; }
}