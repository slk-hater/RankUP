package org.slk.rankup.customanvil.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClickOnCustomAnvil implements Listener {
    @EventHandler
    public void onInventoryClickOnCustomAnvil(InventoryClickEvent event){
        Inventory clickedInventory = event.getClickedInventory();
        Inventory topInventory = event.getView().getTopInventory();
        assert clickedInventory != null;

        if(!clickedInventory.equals(topInventory)) return;
    }
}