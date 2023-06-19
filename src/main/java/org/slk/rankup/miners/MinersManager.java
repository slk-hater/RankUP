package org.slk.rankup.miners;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
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
import java.util.HashMap;
import java.util.Map;

public class MinersManager {
    public static Map<Player, Location> MANAGING_MAP = new HashMap<>();
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
        as.setRemoveWhenFarAway(false);
        as.setHelmet(Miner.SKULL);
        as.setItemInHand(new ItemStack(Material.GOLDEN_PICKAXE));

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
    public static void createMinerConfiguration(Location location, Player player, Miner.MinerType type, int speed){
        YamlConfiguration configuration = MinersManager.getConfiguration();
        configuration.set("miners."+location+".owner", player.getName());
        configuration.set("miners."+location+".enabled", false);
        configuration.set("miners."+location+".type", type.toString());
        configuration.set("miners."+location+".speed", speed);
        configuration.set("miners."+location+".fuel", 0);
        configuration.set("miners."+location+".remoteControl", false);
        configuration.set("miners."+location+".storage", 0);
        configuration.set("miners."+location+".maxStorage", 500);
        try{ configuration.save(MinersManager.getDataFile()); }
        catch(Exception e) { e.printStackTrace(); }
    }
    public static boolean hasMiner(Location location){ return MinersManager.getConfiguration().get("miners."+location) != null; }
    public static ConfigurationSection getMinerConfiguration(Location location){
        YamlConfiguration configuration = MinersManager.getConfiguration();
        return configuration.getConfigurationSection("miners."+location);
    }
}