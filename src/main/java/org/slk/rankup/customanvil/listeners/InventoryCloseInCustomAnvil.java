package org.slk.rankup.customanvil.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryCloseInCustomAnvil implements Listener {
    @EventHandler
    public void onInventoryCloseInCustomAnvil(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        Inventory topInventory = event.getView().getTopInventory();
        int[] keepSlots = {19, 22, 24};
        for(int keepSlot : keepSlots){
            ItemStack keepIS = topInventory.getItem(keepSlot);
            if(keepIS == null || keepIS.getType().equals(Material.AIR)) { continue; }

            if(player.getInventory().getSize() != -1){ player.getInventory().addItem(keepIS); }
            else { player.getWorld().dropItemNaturally(player.getLocation(), keepIS); }
        }
    }
}