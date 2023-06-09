package org.slk.rankup.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.placeables.IPlaceable;
import org.slk.rankup.placeables.IPlaceableComponents;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.ItemStackBuilder;

public class FireworkBox extends IPlaceableComponents implements IPlaceable {
    public ItemStack IS = ItemStackBuilder.getSkull("http://textures.minecraft.net/texture/b55ea43e592785d016acdeea9a4a6f9cf22c2753e695405e8c83d2e09ebcf647");

    public FireworkBox() {
        this.canBeDestroyed = true;
        this.dropOnDestroy = false;

        // TODO : Define as a placeable item
        ItemMeta meta = IS.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ColorUtils.colorize(ChatColor.of("#F46D75") + "Caixa de foguetes"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setCustomModelData(CustomModelDataEnum.FIREWORK_BOX.get()); // placeable?!
        IS.setItemMeta(meta);
    }

    @Override
    public void onPlaced() { Bukkit.broadcastMessage("placed"); }

    @Override
    public void onDestroyed() {
        Bukkit.broadcastMessage("destroyed");
    }
}