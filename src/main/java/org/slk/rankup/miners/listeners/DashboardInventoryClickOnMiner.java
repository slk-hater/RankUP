package org.slk.rankup.miners.listeners;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.miners.MinersManager;
import org.slk.rankup.miners.inventories.DashboardMinerInventory;
import org.slk.rankup.utils.ChatUtils;

public class DashboardInventoryClickOnMiner implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onDashboardInventoryClickOnMiner(InventoryClickEvent event){
        if(event.getClickedInventory() == null) return;
        if(!event.getView().getTitle().equals(DashboardMinerInventory.NAME)) return;
        if(!event.getClickedInventory().equals(event.getView().getTopInventory())) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack clickedItem = inventory.getItem(event.getSlot());
        assert clickedItem != null;

        Location asLoc = MinersManager.MANAGING_MAP.get(player);
        YamlConfiguration config = MinersManager.getConfiguration();

        switch(clickedItem.getType()){
            case GREEN_STAINED_GLASS_PANE -> {
                if (config.getInt("miners." + asLoc + ".fuel") <= 0) {
                    player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.3f, 1f);
                    player.sendMessage(ChatUtils.error("Este minerador não tem combustível suficiente!"));
                    return;
                }
                player.playSound(player, Sound.ENTITY_VILLAGER_YES, 0.3f, 1f);
                config.set("miners." + asLoc + ".enabled", true);
                try { config.save(MinersManager.getDataFile()); }
                catch (Exception ignored) { }
                inventory.setItem(0, DashboardMinerInventory.ENABLED_ITEMSTACK);
            }
            case RED_STAINED_GLASS_PANE -> {
                player.playSound(player, Sound.ENTITY_VILLAGER_YES, 0.3f, 1f);
                config.set("miners." + asLoc + ".enabled", false);
                try { config.save(MinersManager.getDataFile()); }
                catch (Exception ignored) { }
                inventory.setItem(0, DashboardMinerInventory.DISABLED_ITEMSTACK);
            }
        }
    }
}