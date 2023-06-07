package org.slk.rankup.itemstacks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.interfaces.IPlaceable;
import org.slk.rankup.interfaces.IPlaceableComponents;

public class FireworkBox extends IPlaceableComponents implements IPlaceable {
    public ItemStack IS = new ItemStack(Material.PLAYER_HEAD, 1);
    public FireworkBox() {
        this.canBeDestroyed = true;
        this.dropOnDestroy = false;

        ItemMeta meta = IS.getItemMeta();
        meta.setCustomModelData(1); // placeable?!
        IS.setItemMeta(meta);
    }

    @Override
    public void onPlaced() { Bukkit.broadcastMessage("placed"); }

    @Override
    public void onDestroyed() {
        Bukkit.broadcastMessage("destroyed");
    }
}