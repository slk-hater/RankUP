package org.slk.rankup.customanvil.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.customanvil.inventories.CustomAnvilInventory;
import org.slk.rankup.itemstacks.CustomModelDataEnum;

public class InventoryClickOnCustomAnvil implements Listener {
    @EventHandler
    public void onInventoryClickOnCustomAnvil(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();
        Inventory clickedInventory = event.getClickedInventory();
        Inventory topInventory = view.getTopInventory();
        ItemStack clickedItem = event.getCurrentItem();
        ItemStack cursorItem = event.getCursor();
        InventoryAction action = event.getAction();
        int clickedSlot = event.getSlot();

        if(clickedInventory == null) return;
        //if(clickedInventory != topInventory) return;
        //if(!view.getTitle().equals(CustomAnvilInventory.NAME)) return;
        if(event.isShiftClick()) {
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.3f, 1f);
            event.setCancelled(true);
        }
        if(clickedItem != null && clickedItem.isSimilar(CustomAnvilInventory.FILL_ITEMSTACK)) {
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.3f, 1f);
            event.setCancelled(true);
        }

        if(clickedInventory == topInventory && action.toString().contains("PLACE_")){
            if(clickedSlot == CustomAnvilInventory.KEEP_SLOTS.get(1) && (!cursorItem.getItemMeta().hasCustomModelData() || (cursorItem.getItemMeta().hasCustomModelData() && cursorItem.getItemMeta().getCustomModelData() != CustomModelDataEnum.ENCHATING_BOOK.get()))) {
                player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.3f, 1f);
                event.setCancelled(true);
            }
            if(clickedSlot == CustomAnvilInventory.EMPTY_SLOTS.get(0)){
                player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.3f, 1f);
                event.setCancelled(true);
            }
        }
    }
}