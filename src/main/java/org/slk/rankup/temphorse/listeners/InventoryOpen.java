package org.slk.rankup.temphorse.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.HorseInventory;
import org.slk.rankup.temphorse.HorsesManager;

public class InventoryOpen implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        if(!(event.getInventory() instanceof HorseInventory inventory)) return;

        event.setCancelled(HorsesManager.getMap().containsValue((Horse) inventory.getHolder()));
    }
}