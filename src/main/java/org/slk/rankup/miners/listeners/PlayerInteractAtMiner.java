package org.slk.rankup.miners.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.slk.rankup.miners.MinersManager;

public class PlayerInteractAtMiner implements Listener {
    @EventHandler
    public void onPlayerInteractAtMiner(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof ArmorStand as)) return;
        if(MinersManager.getConfiguration().get("miners."+as.getLocation()) == null) return;

        Player player = event.getPlayer();
        event.setCancelled(true);
        player.openInventory(Bukkit.createInventory(player, 9*1, "Minerador"));
    }
}