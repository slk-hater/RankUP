package org.slk.rankup.miners.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.slk.rankup.miners.inventories.MinerDashboardInventory;

public class InventoryClickOnMiner implements Listener {
    @EventHandler
    public void onInventoryClickOnMiner(InventoryClickEvent event){
        if(event.getClickedInventory() == null) return;
        InventoryView view = event.getView();
        if(!view.getTitle().equals(MinerDashboardInventory.NAME)) return;
    }
}