package org.slk.rankup.temphorse.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.slk.rankup.temphorse.HorsesManager;

public class VehicleExit implements Listener {
    @EventHandler
    public void onVehicleExit(VehicleExitEvent event){
        if(!(event.getExited() instanceof Player player)) return;
        if(!(event.getVehicle() instanceof Horse horse)) return;
        if(!HorsesManager.getMap().containsValue(horse)) return;

        HorsesManager.getMap().remove(player);
        horse.teleport(horse.getLocation().subtract(0, 100, 0));
        horse.getInventory().clear();
        horse.setCustomNameVisible(false);
        horse.setInvisible(true);
        horse.setSilent(true);
        horse.setCollidable(false);
        horse.setHealth(0);
    }
}