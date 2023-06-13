package org.slk.rankup.treasures;

import net.minecraft.FileUtils;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.FileUtil;
import org.slk.rankup.Core;
import org.slk.rankup.utils.ItemStackBuilder;

public class TreasuresManager {
    static String NAME = "Treasures";
    static World TREASURES_WORLD;

    public static ItemStack PICKAXE = ItemStackBuilder.build(Material.NETHERITE_PICKAXE, 1, "&ePicareta", "");

    public static void createWorld(){
        World world = Bukkit.getServer().getWorld(NAME);
        if(world != null){
            TREASURES_WORLD = world;
            return;
        }

        Core.getInstance().getLogger().info("A criar o mundo de Tesouros...");
        WorldCreator wc = new WorldCreator(NAME);
        wc.environment(World.Environment.NORMAL);
        wc.generateStructures(false);
        wc.type(WorldType.FLAT);
        wc.generatorSettings("{\"layers\": [{\"block\": \"bedrock\", \"height\": 1}, {\"block\": \"packed_mud\", \"height\": 180}], \"biome\":\"plains\"}");
        Bukkit.getServer().createWorld(wc);
    }
    public static void deleteWorld(){
        World world = Bukkit.getServer().getWorld(NAME);
        if(world == null) return;

        Core.getInstance().getLogger().info("A deletar o mundo de Tesouros...");
        Bukkit.unloadWorld(world, false);
        world.getWorldFolder().delete();
    }
    public static void resetWorld(){
        deleteWorld();
        createWorld();
    }

    public static World getWorld(){ return TREASURES_WORLD; }
}