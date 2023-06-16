package org.slk.rankup.miners.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.utils.ItemStackBuilder;

public class Fuel {
    static int BASE_DURATION = 60;

    int duration;
    final ItemStack is;

    public Fuel(){
        setDuration(BASE_DURATION);
        String durationStr = "&e";
        int hours = getDuration()/3600;
        int remainder = getDuration()-hours * 3600;
        int minutes = remainder/60;
        remainder = remainder - minutes * 60;
        int seconds = remainder;
        if(hours > 0)
            durationStr += hours + "h ";
        if(minutes > 0)
            durationStr += minutes + "m ";
        if(seconds > 0)
            durationStr += seconds + "s ";
        this.is = ItemStackBuilder.build(Material.COAL,
                1,
                ChatColor.of("#36454F")+"Combustível",
                "&7Aplicavél a &fMineradores\n&7Duração &f"+durationStr);

        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        meta.setCustomModelData(CustomModelDataEnum.FUEL.get());
    }

    public static int getBaseDuration() { return BASE_DURATION; }

    public int getDuration() { return this.duration; }
    public void setDuration(int seconds) { this.duration = seconds; }
    public ItemStack getItemStack() { return this.is; }
}