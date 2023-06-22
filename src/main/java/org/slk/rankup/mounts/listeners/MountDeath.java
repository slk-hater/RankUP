package org.slk.rankup.mounts.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MountDeath implements Listener {
    @EventHandler
    public void onMountDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Horse)) return;
        event.getDrops().clear();
    }
}