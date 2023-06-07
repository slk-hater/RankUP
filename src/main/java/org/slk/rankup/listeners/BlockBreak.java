package org.slk.rankup.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.slk.rankup.interfaces.IPlaceable;
import org.slk.rankup.managers.PlaceableManager;

import java.util.Map;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Location location = event.getBlock().getLocation();
        if(PlaceableManager.getMap().containsValue(location)){ // placeable
            IPlaceable placeable = null;
            for(Map.Entry<IPlaceable, Location> placb : PlaceableManager.getMap().entrySet()){
                if(placb.getValue() == location) { placeable = placb.getKey(); }
            }
            assert placeable != null;
            PlaceableManager.removePlaceable(placeable);
            placeable.onDestroyed();
        }
    }
}