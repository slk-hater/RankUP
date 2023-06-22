package org.slk.rankup.mounts.listeners;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.slk.rankup.mounts.MountsManager;

public class MountExit implements Listener {
    @EventHandler
    public void onMountExit(VehicleExitEvent event){
        if(!(event.getExited() instanceof Player player)) return;
        if(!(event.getVehicle() instanceof Horse horse)) return;
        if(!MountsManager.getMap().containsValue(horse)) return;

        MountsManager.getMap().remove(player);
        horse.teleport(horse.getLocation().subtract(0, 100, 0));
        horse.getInventory().clear();
        horse.setCustomNameVisible(false);
        horse.setInvisible(true);
        horse.setSilent(true);
        horse.setCollidable(false);
        horse.setHealth(0);
    }
}