package org.slk.rankup.treasures.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        World world = event.getBlock().getWorld();

        event.setDropItems(world.equals(TreasuresManager.getWorld()));

        double chance = Math.random();
        if(chance > 0.2d){ // 80% chance
            player.getTargetBlock(null, 4).setType(Material.AMETHYST_BLOCK);
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 0.3f, 1f);
            // TODO : Gem break check
        }
    }
}