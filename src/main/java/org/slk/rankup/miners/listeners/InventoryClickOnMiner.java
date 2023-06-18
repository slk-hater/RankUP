package org.slk.rankup.miners.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.slk.rankup.miners.MinersManager;
import org.slk.rankup.miners.inventories.MinerDashboardInventory;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ItemStackBuilder;

public class InventoryClickOnMiner implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onInventoryClickOnMiner(InventoryClickEvent event){
        if(event.getClickedInventory() == null) return;
        if(!event.getView().getTitle().equals(MinerDashboardInventory.NAME)) return;
        if(!event.getClickedInventory().equals(event.getView().getTopInventory())) return;
        event.setCancelled(true);

        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        Location asLoc = MinersManager.MANAGING_MAP.get(player);
        ConfigurationSection minerConfiguration = MinersManager.getMinerConfiguration(asLoc);
        int clickedSlot = event.getSlot();

        switch(clickedSlot){
            case 0 -> {
                if(minerConfiguration.getInt("fuel") <= 0){
                    player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.3f, 1f);
                    player.sendMessage(ChatUtils.error("Este minerador não tem combustível!"));
                    return;
                }
                player.playSound(player, Sound.ENTITY_VILLAGER_YES, 0.3f, 1f);
                minerConfiguration.set("enabled", true);
                //MinersManager.saveConfiguration();
                inventory.setItem(0, ItemStackBuilder.build(Material.RED_STAINED_GLASS_PANE, 1, "&cDesligar", "&8Clica para desligar o minerador"));
            }
        }
    }
}