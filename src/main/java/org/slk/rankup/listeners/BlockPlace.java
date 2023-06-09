package org.slk.rankup.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.itemstacks.CustomModelDataEnum;

import java.util.Objects;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        ItemStack is = event.getItemInHand();
        ItemMeta meta = is.getItemMeta();
        if(Objects.requireNonNull(is.getItemMeta()).hasCustomModelData()){
            /*Bukkit.broadcastMessage("placing placeable");
            PlaceableManager.addPlaceable(placeable, event.getBlockPlaced().getLocation());
            placeable.onPlaced();*/
            if(meta.getCustomModelData() == CustomModelDataEnum.FIREWORK_BOX.get()){

            }
            else if(meta.getCustomModelData() == CustomModelDataEnum.ENCHATING_BOOK.get()){
                event.setCancelled(true);
            }
        }
    }
}