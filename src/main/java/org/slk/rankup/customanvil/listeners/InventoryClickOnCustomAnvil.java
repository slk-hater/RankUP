package org.slk.rankup.customanvil.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.customanvil.inventories.CustomAnvilInventory;

public class InventoryClickOnCustomAnvil implements Listener {
    @EventHandler
    public void onInventoryClickOnCustomAnvil(InventoryClickEvent event){
        Inventory clickedInventory = event.getClickedInventory();
        Inventory topInventory = event.getView().getTopInventory();
        ItemStack clickedItem = event.getCurrentItem();
        int clickedSlot = event.getSlot();

        assert clickedInventory != null;
        assert clickedInventory == topInventory;
        assert clickedItem != null;

        if(clickedItem.isSimilar(CustomAnvilInventory.FILL_ITEMSTACK))
            event.setCancelled(true);
        Bukkit.broadcastMessage(String.valueOf(clickedSlot));
    }
}