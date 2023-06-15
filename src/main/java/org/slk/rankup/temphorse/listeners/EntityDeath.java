package org.slk.rankup.temphorse.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Horse)) return;
        event.getDrops().clear();
    }
}