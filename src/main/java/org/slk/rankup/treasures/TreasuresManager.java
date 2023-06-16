package org.slk.rankup.treasures;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.slk.rankup.Core;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ItemStackBuilder;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class TreasuresManager {
    static String NAME = "Treasures";
    static World TREASURES_WORLD;
    public static int DURATION_MINUTES = 10;
    public static int WARNING_MINUTE = 3;
    public static double CHANCE_TREASURE = 0.4D; // 60%
    public static double CHANCE_GEM = 0.8D; // 20%

    public static HashMap<Player, LocalDateTime> TIME_MAP = new HashMap<>();
    public static HashMap<Player, Integer> CUSTOM_DURATION_MAP = new HashMap<>();
    static BukkitTask TIMER;
    public static HashMap<Player, Location> LOCKED_TREASURE = new HashMap<>();

    public static ItemStack TICKET = ItemStackBuilder.build(Material.PAPER, 1, ChatColor.of("#BFFF40") + "Passagem", "&7Destino &fMundo dos Tesouros\n&7Duração &f" + DURATION_MINUTES + " minutos\n\n&8Clica com o botão direito para usar");
    static{
        ItemMeta meta = TICKET.getItemMeta();
        if(meta != null)
            meta.setCustomModelData(CustomModelDataEnum.TICKET.get());
        TICKET.setItemMeta(meta);
    }

    public static Duration getTimeLeft(Player player){
        if(!CUSTOM_DURATION_MAP.containsKey(player)) return Duration.between(LocalDateTime.now(), TIME_MAP.get(player).plusMinutes(DURATION_MINUTES));
        else return Duration.between(LocalDateTime.now(), TIME_MAP.get(player).plusMinutes(CUSTOM_DURATION_MAP.get(player)));
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
        TREASURES_WORLD = Bukkit.getServer().createWorld(wc);
        if(TREASURES_WORLD == null) return;
        TREASURES_WORLD.setTime(1000L);
        TREASURES_WORLD.setDifficulty(Difficulty.PEACEFUL);
        TREASURES_WORLD.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        TREASURES_WORLD.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        TREASURES_WORLD.setGameRule(GameRule.DO_MOB_SPAWNING, false);
    }
    static void deleteWorld() {
        World world = Bukkit.getServer().getWorld(NAME);
        if(world != null) Bukkit.unloadWorld(world, false);

        Core.getInstance().getLogger().info("A deletar o mundo de Tesouros...");
        try {
            FileUtils.deleteDirectory(world.getWorldFolder());
        }catch(Exception ignored) { }
    }
    public static void setup(){
        deleteWorld();
        createWorld();
    }
    public static void setupTimer(){
        if(TIMER != null) return;

        TIMER = new BukkitRunnable() {
            final Random rnd = new Random();
            @Override
            public void run() {
                if(getWorld().getPlayers().size() == 0) {
                    this.cancel();
                    TIMER = null;
                }

                for(Player player : getWorld().getPlayers()){
                    //region Check time left
                    Duration diff = getTimeLeft(player);

                    int minutes;
                    if(!CUSTOM_DURATION_MAP.containsKey(player)) minutes = DURATION_MINUTES;
                    else  minutes = CUSTOM_DURATION_MAP.get(player);
                        /*if (diff.toMinutes() <= minutes / 2 && diff.toMinutes() < minutes)
                            player.sendMessage(ChatUtils.info(TreasuresMessages.TIME_LEFT.get(player)));
                        else*/
                    if (diff.toMinutes() >= minutes) {
                        TIME_MAP.remove(player);
                        CUSTOM_DURATION_MAP.remove(player);
                        player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
                        player.sendMessage(ChatUtils.info(TreasuresMessages.LEAVE_WORLD_TIME.getRaw()));
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 0.3f, 1f);
                    }
                    //endregion

                    //region Spawn treasure
                    if(!LOCKED_TREASURE.containsKey(player)){
                        double chance = Math.random();
                        if (chance > CHANCE_TREASURE) {
                            Location loc = new Location(getWorld(), rnd.nextInt((int) (player.getLocation().getX() + 200)), rnd.nextInt(92, 103), rnd.nextInt((int) (player.getLocation().getZ() + 200)));
                            getWorld().getBlockAt(loc).setType(Material.SAND);
                            LOCKED_TREASURE.put(player, loc);
                        }
                    }
                    //endregion
                }
            }
        }.runTaskTimer(Core.getInstance(), 20L*5, 20L*5);
    }
    public static World getWorld(){
        World world = Bukkit.getServer().getWorld(NAME);
        if(TREASURES_WORLD == null && world != null)
            TREASURES_WORLD = world;
        return TREASURES_WORLD;
    }
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

        items.add(ItemStackBuilder.build(Material.BARRIER, 1, "&cSair", "&8Clica com o botão direito para sair do mundo dos Tesouros\n&8Apenas receberás uma passagem de volta com a restante duração\n &8se o teu tempo restante for mais do que &l" + WARNING_MINUTE + "&8 minutos!"));

        return items;
    }
}