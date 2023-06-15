package org.slk.rankup.treasures.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();

        if(!world.equals(TreasuresManager.getWorld())) return;

        player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
    }
}