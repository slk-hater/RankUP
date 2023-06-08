package org.slk.rankup.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import java.util.Objects;

public class WorldUtils {
    public static void spawnFireworks(Location location, int amount) {
        // TODO : Check if code works ;-;
        if (location == null || amount <= 0) return;
        int diameter = 10; //Diameter of the circle centered on loc

        for (int i = 0; i < 3; i++)
        {
            Location newLocation = location.add(new Vector(Math.random()-0.5, 0, Math.random()-0.5).multiply(diameter));
            location.getWorld().spawnEntity(newLocation, EntityType.FIREWORK);
        }

        /*Firework fw = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        for (int i = 0; i < amount; i++) {
            Firework fw2 = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }*/
    }
}