package org.slk.rankup.treasures;

import org.bukkit.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.Core;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;

public class TreasuresManager {
    static String NAME = "Treasures";
    static World TREASURES_WORLD;

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
    public static List<ItemStack> getItems() {
        List<ItemStack> items = new ArrayList<>();

        ItemStack pickaxe = ItemStackBuilder.build(Material.NETHERITE_PICKAXE, 1, "&ePicareta", "");
        ItemMeta meta = pickaxe.getItemMeta();
        assert meta != null;
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxe.setItemMeta(meta);
        items.add(pickaxe);

        ItemStack compass = ItemStackBuilder.build(Material.COMPASS, 1, "&eBússola", "");
        items.add(compass);

        return items;
    }
}