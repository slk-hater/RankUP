package org.slk.rankup.treasures.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.treasures.TreasuresManager;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();

        if(!world.equals(TreasuresManager.getWorld())) return;

        player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
        ItemStack ticketClone = TreasuresManager.TICKET.clone();
        ItemMeta meta = ticketClone.getItemMeta();
        if(meta == null || meta.getLore() == null) return;
        meta.getLore().set(1, meta.getLore().get(1).replace(
                String.valueOf(TreasuresManager.DURATION_MINUTES),
                String.valueOf(TreasuresManager.getTimeLeft(player).toMinutes())
        ));
        ticketClone.setItemMeta(meta);
        player.getInventory().addItem(ticketClone);
    }
}