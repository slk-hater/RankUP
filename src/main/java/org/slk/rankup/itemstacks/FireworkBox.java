package org.slk.rankup.itemstacks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.interfaces.IPlaceable;

public class FireworkBox extends IPlaceable {
    public ItemStack IS = new ItemStack(Material.PLAYER_HEAD, 1);
    public FireworkBox() {
        this.canBeDestroyed = true;
        this.dropOnDestroy = false;
    }
}