package org.slk.rankup.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.interfaces.IPlaceable;
import org.slk.rankup.managers.PlaceableManager;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        ItemStack is = event.getItemInHand();
        if(is.getItemMeta().getCustomModelData() == 1){ // is placeable
            IPlaceable placeable = (IPlaceable) is;
            PlaceableManager.addPlaceable(placeable, event.getBlockPlaced().getLocation());
            placeable.onPlaced();
        }
    }
}