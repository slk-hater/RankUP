package org.slk.rankup.customanvil.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.customanvil.inventories.CustomAnvilInventory;

import java.util.List;

public class InventoryCloseInCustomAnvil implements Listener {
    @EventHandler
    public void onInventoryCloseInCustomAnvil(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        InventoryView view = event.getView();

        if(!view.getTitle().equals(CustomAnvilInventory.NAME)) return;

        List<Integer> keepSlots = CustomAnvilInventory.KEEP_SLOTS;

        for(int keepSlot : keepSlots){
            ItemStack keepIS = view.getTopInventory().getItem(keepSlot);
            if(keepIS == null || keepIS.getType().equals(Material.AIR)) continue;

            if(player.getInventory().firstEmpty() != -1) player.getInventory().addItem(keepIS);
            else player.getWorld().dropItemNaturally(player.getLocation(), keepIS);
        }
    }
}