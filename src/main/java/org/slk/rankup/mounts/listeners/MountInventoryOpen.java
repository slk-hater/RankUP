package org.slk.rankup.mounts.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.HorseInventory;
import org.slk.rankup.mounts.MountsManager;

public class MountInventoryOpen implements Listener {
    @EventHandler
    public void onMountInventoryOpen(InventoryOpenEvent event){
        if(!(event.getInventory() instanceof HorseInventory inventory)) return;

        event.setCancelled(MountsManager.getMap().containsValue((Horse) inventory.getHolder()));
    }
}