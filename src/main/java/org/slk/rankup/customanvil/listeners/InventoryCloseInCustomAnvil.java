package org.slk.rankup.customanvil.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.customanvil.inventories.CustomAnvilInventory;

import java.util.List;

public class InventoryCloseInCustomAnvil implements Listener {
    @EventHandler
    public void onInventoryCloseInCustomAnvil(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        Inventory topInventory = event.getView().getTopInventory();
        List<Integer> keepSlots = CustomAnvilInventory.KEEP_SLOTS;

        for(int keepSlot : keepSlots){
            ItemStack keepIS = topInventory.getItem(keepSlot);
            assert keepIS != null && !keepIS.getType().equals(Material.AIR);

            if(player.getInventory().firstEmpty() != -1) player.getInventory().addItem(keepIS);
            else player.getWorld().dropItemNaturally(player.getLocation(), keepIS);
        }
    }
}