package org.slk.rankup.treasures.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class FoodLevelChange implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){
        Player player = (Player) event.getEntity();

        event.setCancelled(player.getWorld().equals(TreasuresManager.getWorld()));
    }
}