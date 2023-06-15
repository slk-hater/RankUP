package org.slk.rankup.treasures.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        Entity entity = event.getEntity();

        event.setCancelled(entity.getWorld().equals(TreasuresManager.getWorld()));
    }
}