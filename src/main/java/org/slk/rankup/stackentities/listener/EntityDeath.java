package org.slk.rankup.stackentities.listener;

import org.bukkit.Bukkit;
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
        if(!(event.getEntity() instanceof Mob mob)) return;
        if(!mob.isCustomNameVisible() || mob.getCustomName() == null) return;

        Location location = mob.getLocation();
        assert location.getWorld() != null;
        int quantity = Integer.parseInt(ChatColor.stripColor(mob.getCustomName()).replace("[", "").replace("]", ""));
        if(quantity >= 2) {
            mob.setHealth(mob.getMaxHealth());
            mob.setInvulnerable(false);
            /*Entity newEnt = location.getWorld().spawnEntity(location.add(5,0,0), mob.getType(), true);
            newEnt.setCustomName(ColorUtils.colorize("&7[" + (quantity-1) + "]"));
            newEnt.setCustomNameVisible(true);*/
        }
    }
}