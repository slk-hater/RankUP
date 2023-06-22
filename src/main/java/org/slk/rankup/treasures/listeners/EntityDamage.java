package org.slk.rankup.treasures.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){ event.setCancelled(event.getEntity().getWorld().equals(TreasuresManager.getWorld())); }
}