package org.slk.rankup.stackentities.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.slk.rankup.utils.ColorUtils;

public class EntityDeath implements Listener {
    @EventHandler
    public void onEnitityDeath(EntityDeathEvent event){
        Entity entity = event.getEntity();
        if(!(entity instanceof Mob)) return;
        if(!entity.isCustomNameVisible() || entity.getCustomName() == null) return;

        Location location = entity.getLocation();
        assert location.getWorld() != null;
        int quantity = Integer.parseInt(ChatColor.stripColor(entity.getCustomName()).replace("[", "").replace("]", ""));
        Entity newEnt = location.getWorld().spawnEntity(location, entity.getType());
        newEnt.setCustomName(ColorUtils.colorize("&7[" + (quantity-1) + "]"));
        newEnt.setCustomNameVisible(true);
    }
}