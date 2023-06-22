package org.slk.rankup.stackentities.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.slk.rankup.stackentities.StackEntitiesManager;
import org.slk.rankup.utils.ColorUtils;

public class EntitySpawn implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        if(!(event.getEntity() instanceof Mob mob)) return;

        Location location = event.getLocation();
        assert location.getWorld() != null;
        for(Entity nearbyEnt : location.getWorld().getNearbyEntities(location, StackEntitiesManager.RADIUS, StackEntitiesManager.RADIUS, StackEntitiesManager.RADIUS)){
            if(!nearbyEnt.getClass().equals(mob.getClass())) continue;
            event.setCancelled(true);

            if(nearbyEnt.isCustomNameVisible() && nearbyEnt.getCustomName() != null){
                int quantity = Integer.parseInt(ChatColor.stripColor(nearbyEnt.getCustomName()).replace("[", "").replace("]", ""));
                nearbyEnt.setCustomName(ColorUtils.colorize("&7[" + (quantity+1) + "]"));
            }
            else{
                nearbyEnt.setCustomName(ColorUtils.colorize("&7[2]"));
                nearbyEnt.setCustomNameVisible(true);
            }
        }
    }
}