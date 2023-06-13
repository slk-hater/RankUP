package org.slk.rankup.treasures.listeners;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        World world = event.getBlock().getWorld();

        if(world.equals(TreasuresManager.getWorld()))
            event.setDropItems(false);
    }
}