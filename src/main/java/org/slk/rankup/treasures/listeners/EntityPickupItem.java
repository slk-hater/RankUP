package org.slk.rankup.treasures.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class EntityPickupItem implements Listener {
    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event){ event.setCancelled(event.getEntity().getWorld().equals(TreasuresManager.getWorld())); }
}