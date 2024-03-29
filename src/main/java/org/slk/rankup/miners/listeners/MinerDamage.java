package org.slk.rankup.miners.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.slk.rankup.miners.MinersManager;

public class MinerDamage implements Listener {
    @EventHandler
    public void onMinerDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof ArmorStand as)) return;

        event.setCancelled(MinersManager.hasMiner(as.getLocation()));
    }
}