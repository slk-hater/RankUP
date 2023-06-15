package org.slk.rankup.temphorse.listeners;

import org.bukkit.entity.Entity;
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

        HorsesManager.getMap().remove(player);
        horse.setInvisible(true);
        horse.setHealth(0);
        horse.setSilent(true);
    }
}