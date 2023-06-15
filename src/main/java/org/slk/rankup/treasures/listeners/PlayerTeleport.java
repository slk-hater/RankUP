package org.slk.rankup.treasures.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.treasures.TreasuresManager;
import org.slk.rankup.treasures.TreasuresMessages;
import org.slk.rankup.utils.ChatUtils;

import java.time.LocalDateTime;

public class PlayerTeleport implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();

        if(to == null || to.getWorld() == null) return;
        if(from.getWorld() == null) return;
        if(to.getWorld().equals(from.getWorld())) return;

        if(to.getWorld().equals(TreasuresManager.getWorld())){
            TreasuresManager.setupTimer();
            TreasuresManager.TIME_MAP.put(player, LocalDateTime.now());

            player.sendMessage("\n"+ChatUtils.good(TreasuresMessages.JOIN_WORLD.get(player))+"\n ");
            player.getInventory().clear();
            player.setFoodLevel(20);
            player.setHealth(20);
            for(int i=0; i<TreasuresManager.getItems().size(); i++)
                player.getInventory().setItem(i, TreasuresManager.getItems().get(i));
        }
        if(from.getWorld().equals(TreasuresManager.getWorld()))
            player.getInventory().clear();
    }
}