package org.slk.rankup.treasures.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.slk.rankup.treasures.TreasuresManager;

public class FoodLevelChange implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){ event.setCancelled(event.getEntity().getWorld().equals(TreasuresManager.getWorld())); }
}