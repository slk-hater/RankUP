package org.slk.rankup.treasures.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class InventoryClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){ event.setCancelled(event.getWhoClicked().getWorld().equals(TreasuresManager.getWorld())); }
}