package org.slk.rankup.treasures;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.Core;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ItemStackBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class TreasuresManager {
    static String NAME = "Treasures";
    static World TREASURES_WORLD;
    public static int DURATION_MINUTES = 10;
    public static double CHANCE_TREASURE = 0.35D; // 65%
    public static double CHANCE_GEM = 0.2D; // 80%

    public static HashMap<Player, LocalDateTime> TIME_MAP = new HashMap<>();
    public static HashMap<Player, Integer> CUSTOM_DURATION_MAP = new HashMap<>();
    static Timer TIMER;
    public static HashMap<Player, Location> LOCKED_TREASURE = new HashMap<>();

    public static ItemStack TICKET = ItemStackBuilder.build(Material.PAPER, 1, ChatColor.of("#BFFF40") + "Passagem", "&7Destino &fMundo dos Tesouros\n&7Duração &f" + DURATION_MINUTES + " minutos");
    static{
        ItemMeta meta = TICKET.getItemMeta();
        if(meta != null)
            meta.setCustomModelData(CustomModelDataEnum.TICKET.get());
        TICKET.setItemMeta(meta);
    }

    public static Duration getTimeLeft(Player player){
        if(!CUSTOM_DURATION_MAP.containsKey(player)) return Duration.between(LocalDateTime.now(), TIME_MAP.get(player).plusMinutes(DURATION_MINUTES));
        else return Duration.between(LocalDateTime.now(), TIME_MAP.get(player).plusMinutes(DURATION_MINUTES).minusMinutes(CUSTOM_DURATION_MAP.get(player)));
    }
    static void createWorld(){
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
    static void deleteWorld(){
        World world = Bukkit.getServer().getWorld(NAME);
        if(world == null) return;

        Core.getInstance().getLogger().info("A deletar o mundo de Tesouros...");
        Bukkit.unloadWorld(world, false);
        world.getWorldFolder().delete();
    }
    public static void setup(){
        deleteWorld();
        createWorld();
    }
    public static void setupTimer(){
        if(TIMER != null) return;

        TIMER = new Timer();
        TIMER.scheduleAtFixedRate(new TimerTask() {
            final Random rnd = new Random();
            @Override
            public void run() {
                if(getWorld().getPlayers().size() == 0) {
                    this.cancel();
                    TIMER = null;
                }

                // TODO : idk if this works
                getWorld().getPlayers().forEach(player -> {
                    player.sendMessage("debug: timer tick");

                    //region Check time left
                    Duration diff = getTimeLeft(player);

                    if(!CUSTOM_DURATION_MAP.containsKey(player)) {
                        if (diff.toMinutes() >= DURATION_MINUTES / 2 && diff.toMinutes() < DURATION_MINUTES)
                            player.sendMessage(ChatUtils.info(TreasuresMessages.TIME_LEFT.get(player)));
                        else if (diff.toMinutes() >= DURATION_MINUTES) {
                            TIME_MAP.remove(player);
                            player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
                            player.sendMessage(ChatUtils.info(TreasuresMessages.LEAVE_WORLD_TIME.getRaw()));
                        }
                    } else {
                        int minutes = CUSTOM_DURATION_MAP.get(player);
                        if (diff.toMinutes() >= minutes / 2 && diff.toMinutes() < minutes)
                            player.sendMessage(ChatUtils.info(TreasuresMessages.TIME_LEFT.get(player)));
                        else if (diff.toMinutes() >= minutes) {
                            TIME_MAP.remove(player);
                            CUSTOM_DURATION_MAP.remove(player);
                            player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
                            player.sendMessage(ChatUtils.info(TreasuresMessages.LEAVE_WORLD_TIME.getRaw()));
                        }
                    }
                    //endregion

                    //region Spawn treasure
                    if(!LOCKED_TREASURE.containsKey(player)){
                        double chance = Math.random();
                        if (chance > CHANCE_TREASURE) {
                            Location loc = new Location(getWorld(), rnd.nextInt((int) (player.getLocation().getX() + 400)), rnd.nextInt(18, 25), rnd.nextInt((int) (player.getLocation().getZ() + 400)));
                            double btwn = loc.distance(player.getLocation());
                            player.sendMessage("debug: " + btwn + " blocks far away");
                            getWorld().getBlockAt(loc).setType(Material.SAND);
                            LOCKED_TREASURE.put(player, loc);
                        }
                    }
                    //endregion
                });
            }
        }, 20L*5, 20L*5);
    }
    public static World getWorld(){ return TREASURES_WORLD; }
    public static List<ItemStack> getItems() {
        List<ItemStack> items = new ArrayList<>();

        ItemStack pickaxe = ItemStackBuilder.build(Material.NETHERITE_PICKAXE, 1, "&ePicareta", "&8Sem modificadores aplicados");
        ItemMeta meta = pickaxe.getItemMeta();
        assert meta != null;
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxe.setItemMeta(meta);
        items.add(pickaxe);

        items.add(ItemStackBuilder.build(Material.COMPASS, 1, "&eBússola", "&8Clica com o botão direito para localizar o tesouro"));

        for(int i=0; i<6; i++)
            items.add(new ItemStack(Material.RED_STAINED_GLASS_PANE, 1));

        items.add(ItemStackBuilder.build(Material.BARRIER, 1, "&cSair", "&8Clica com o botão direito para sair do mundo dos Tesouros\n&8Recebes uma passagem de volta com a restante duração"));

        return items;
    }
}