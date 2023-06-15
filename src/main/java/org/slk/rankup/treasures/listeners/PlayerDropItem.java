package org.slk.rankup.treasures.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class PlayerDropItem implements Listener {
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event){
        Player player = event.getPlayer();

        event.setCancelled(player.getWorld().equals(TreasuresManager.getWorld()));
    }
}