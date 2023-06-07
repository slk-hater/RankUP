package org.slk.rankup.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.slk.rankup.managers.PlaceableManager;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Location location = event.getBlock().getLocation();
        if(PlaceableManager.getMap().containsValue(location)){ // placeable

        }
    }
}