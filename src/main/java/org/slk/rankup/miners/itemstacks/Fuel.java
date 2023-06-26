package org.slk.rankup.miners.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.miners.MinersNamespacedKey;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.ItemStackBuilder;
import org.slk.rankup.utils.ItemStackUtils;
import org.slk.rankup.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class Fuel {
    static final int BASE_DURATION = 60;
    static String NAME = ChatColor.of("#36454F")+"Combustível fóssil";
    static String DESCRIPTION = "&7Produz &f%duration%";
    static ItemStack BASE_ITEMSTACK = ItemStackBuilder.build(Material.CHARCOAL, 1, NAME, DESCRIPTION);

    int duration;
    final ItemStack is;

    public Fuel(){
        this.is = BASE_ITEMSTACK;
        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        meta.setCustomModelData(CustomModelDataEnum.FUEL.get());
        meta.getPersistentDataContainer().set(MinersNamespacedKey.FUEL.get(), PersistentDataType.DOUBLE, 0.4D);
        setDuration(getBaseDuration());
    }

    public static int getBaseDuration() { return BASE_DURATION; }
    public static boolean isFuel(ItemStack is) { return is.getType().equals(BASE_ITEMSTACK.getType()) && is.getItemMeta() != null && is.getItemMeta().hasCustomModelData() && is.getItemMeta().getCustomModelData() == CustomModelDataEnum.FUEL.get(); }

    public int getDuration() { return this.duration; }
    public void setDuration(int seconds) {
        this.duration = seconds;
        ItemStackUtils.changeItemLore(is, DESCRIPTION.replace("%duration%", "&f" + NumberUtils.secondsToFormatted(seconds)));
    }
    public ItemStack getItemStack() { return this.is; }

}