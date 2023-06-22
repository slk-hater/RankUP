package org.slk.rankup.treasures.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class PlayerDropItem implements Listener {
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event){ event.setCancelled(event.getPlayer().getWorld().equals(TreasuresManager.getWorld())); }
}