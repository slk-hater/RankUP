package org.slk.rankup.treasures.listeners;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        World world = event.getBlock().getWorld();

        event.setCancelled(world.equals(TreasuresManager.getWorld()));
    }
}