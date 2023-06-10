package org.slk.rankup.customanvil.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.slk.rankup.customanvil.inventories.CustomAnvilInventory;

public class PlayerInteractOnAnvil implements Listener {
    @EventHandler
    public void onPlayerInteractOnAnvil(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block block = event.getClickedBlock();
        assert block != null;
        if(!(action.equals(Action.RIGHT_CLICK_BLOCK) && block.getType().equals(Material.ANVIL))) { return; }
        event.setCancelled(true);
        player.openInventory(CustomAnvilInventory.cloneInventory());
    }
}