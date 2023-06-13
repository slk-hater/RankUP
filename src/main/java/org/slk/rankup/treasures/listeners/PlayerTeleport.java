package org.slk.rankup.treasures.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class PlayerTeleport implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();
        if(to == null || to.getWorld() == null) return;
        if(from == null || from.getWorld() == null) return;

        if(to.getWorld().equals(TreasuresManager.getWorld())){
            player.getInventory().clear();
            player.getInventory().setItem(0, TreasuresManager.PICKAXE);
        }
        if(from.getWorld().equals(TreasuresManager.getWorld())){
            player.getInventory().clear();
        }
    }
}