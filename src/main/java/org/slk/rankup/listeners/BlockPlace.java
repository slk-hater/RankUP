package org.slk.rankup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.placeables.IPlaceable;
import org.slk.rankup.placeables.PlaceableManager;

import java.util.Objects;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        ItemStack is = event.getItemInHand();
        if(Objects.requireNonNull(is.getItemMeta()).hasCustomModelData() && is.getItemMeta().getCustomModelData() == 1){ // is placeable
            return;
            /*Bukkit.broadcastMessage("placing placeable");
            PlaceableManager.addPlaceable(placeable, event.getBlockPlaced().getLocation());
            placeable.onPlaced();*/
        }
    }
}