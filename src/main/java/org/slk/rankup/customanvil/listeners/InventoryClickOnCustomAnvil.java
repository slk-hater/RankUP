package org.slk.rankup.customanvil.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.customanvil.inventories.CustomAnvilInventory;

public class InventoryClickOnCustomAnvil implements Listener {
    @EventHandler
    public void onInventoryClickOnCustomAnvil(InventoryClickEvent event){
        InventoryView view = event.getView();
        Inventory clickedInventory = event.getClickedInventory();
        Inventory topInventory = view.getTopInventory();
        ItemStack clickedItem = event.getCurrentItem();
        int clickedSlot = event.getSlot();

        if(clickedInventory == null) return;
        if(clickedInventory != topInventory) return;
        Bukkit.broadcastMessage("is custom anvil inventory? " + view.getTitle().equals(CustomAnvilInventory.NAME));
        if(!view.getTitle().equals(CustomAnvilInventory.NAME)) return;
        if(clickedItem == null) return;

        if(clickedItem.isSimilar(CustomAnvilInventory.FILL_ITEMSTACK))
            event.setCancelled(true);
        Bukkit.broadcastMessage(String.valueOf(clickedSlot));
    }
}