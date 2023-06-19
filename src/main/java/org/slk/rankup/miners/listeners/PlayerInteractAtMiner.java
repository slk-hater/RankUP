package org.slk.rankup.miners.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.slk.rankup.miners.MinersManager;
import org.slk.rankup.miners.inventories.DashboardMinerInventory;

public class PlayerInteractAtMiner implements Listener {
    @EventHandler
    public void onPlayerInteractAtMiner(PlayerInteractAtEntityEvent event){
        if(!(event.getRightClicked() instanceof ArmorStand as)) return;
        if(!MinersManager.hasMiner(as.getLocation())) return;

        // TODO : Upgrades (Speed, remote control, storage capacity, gem mining unlock)
        Player player = event.getPlayer();
        event.setCancelled(true);
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 0.3f, 1f);
        MinersManager.MANAGING_MAP.put(player, as.getLocation());

        Inventory inventory = DashboardMinerInventory.cloneInventory();
        if(MinersManager.getConfiguration().getBoolean("miners"+as.getLocation()+".enabled"))
            inventory.setItem(0, DashboardMinerInventory.ENABLED_ITEMSTACK);
        player.openInventory(inventory);
    }
}