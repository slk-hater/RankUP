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
import java.util.Random;

public class WorldUtils {
    public static void spawnFireworks(Location location, int amount) {
        if (location == null || amount <= 0) return;
        int diameter = 4;
        Random random = new Random();
        for (int i = 0; i < amount; i++)
        {
            Firework fw = (Firework) location.getWorld().spawnEntity(location.add(new Vector(Math.random()-0.5, 1, Math.random()-0.5).multiply(diameter)), EntityType.FIREWORK);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.setPower(1);
            fwm.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255))).flicker(true).build());
            fwm.addEffect(FireworkEffect.builder().withColor(Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255))).flicker(true).build());
            fw.setFireworkMeta(fwm);
        }
    }
}