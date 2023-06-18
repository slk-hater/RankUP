package org.slk.rankup.miners;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.checkerframework.checker.units.qual.A;
import org.slk.rankup.miners.itemstacks.Miner;
import org.slk.rankup.utils.ColorUtils;

import java.io.File;

public class MinersManager {
    static File DATA_FILE = new File("data/miners", "data.yml");

    public static void setup(){
        if(!DATA_FILE.exists()){
            try {
                YamlConfiguration config = new YamlConfiguration();
                config.save(DATA_FILE);
            } catch(Exception e) { e.printStackTrace(); }
        }
    }
    public static File getDataFile() { return DATA_FILE; }
    public static YamlConfiguration getConfiguration(){
        if(!DATA_FILE.exists()) setup();
        return YamlConfiguration.loadConfiguration(DATA_FILE);
    }

    public static ArmorStand spawnMiner(Location location){
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location.add(0.5, 0.9, 0.5), EntityType.ARMOR_STAND);
        as.setSmall(true);
        as.setGravity(false);
        as.setVisible(true);
        as.setInvulnerable(true);
        as.setCollidable(false);
        as.setCanPickupItems(false);
        as.setCustomName(ColorUtils.colorize("&eMinerador"));
        as.setCustomNameVisible(true);
        as.setHelmet(Miner.SKULL);
        as.setItemInHand(new ItemStack(Material.DIAMOND_PICKAXE));

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
        meta.setColor(Color.fromRGB(44, 48, 101));
        chestplate.setItemMeta(meta);
        as.setChestplate(chestplate);

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        meta = (LeatherArmorMeta) leggings.getItemMeta();
        meta.setColor(Color.fromRGB(44, 48, 101));
        leggings.setItemMeta(meta);
        as.setLeggings(leggings);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        meta = (LeatherArmorMeta) boots.getItemMeta();
        meta.setColor(Color.fromRGB(44, 48, 101));
        boots.setItemMeta(meta);
        as.setBoots(boots);

        as.getLocation().getWorld().spawnParticle(Particle.CLOUD, as.getLocation(), 100);
        return as;
    }
    public static void saveMiner(ArmorStand as, Player player, Miner.MinerType type, int speed){
        YamlConfiguration configuration = MinersManager.getConfiguration();
        configuration.set("miners."+as.getLocation()+".owner", player.getName());
        configuration.set("miners."+as.getLocation()+".enabled", false);
        configuration.set("miners."+as.getLocation()+".type", type.toString());
        configuration.set("miners."+as.getLocation()+".speed", speed);
        configuration.set("miners."+as.getLocation()+".fuel", 0);
        configuration.set("miners."+as.getLocation()+".remoteControl", false);
        try{ configuration.save(MinersManager.getDataFile()); }
        catch(Exception e) { e.printStackTrace(); }
    }
    public static boolean hasMiner(ArmorStand as){ return MinersManager.getConfiguration().get("miners."+as.getLocation()) != null; }
}