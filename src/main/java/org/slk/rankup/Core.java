package org.slk.rankup;

import fr.mrmicky.fastboard.FastBoard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.NumberUtils;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public final class Core extends JavaPlugin {
    private static Core instance;
    public static LocalDateTime uptimeStartDate;
    public static Map<Player, FastBoard> scoreboards = new HashMap<>();

    public static Core getInstance() { return instance; }
    public void onEnable() {
        super.onEnable();
        instance = this;
        uptimeStartDate = LocalDateTime.now();

        loadEvents();
        //loadEvents("org.slk.rankup.customanvil.listeners");
        loadCommands();

        (new BukkitRunnable() {
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.setPlayerListHeader(ColorUtils.colorize("\n&a&lRANK UP\n"));
                    LocalDateTime date = Core.uptimeStartDate;
                    Duration diff = Duration.between(date, LocalDateTime.now());
                    int hours = (int) Math.floor((double) diff.getSeconds()/3600);
                    int minutes = (int) Math.floor((double) diff.getSeconds()/60);
                    while(minutes > 60){
                        minutes-=60;
                        hours++;
                    }
                    int seconds = (int) diff.getSeconds()-(minutes*60)-(hours*3600);
                    player.setPlayerListFooter(ColorUtils.colorize(
                            "\n&fUptime &e"+hours+"h " + minutes + "m " + seconds +"s\n"));

                    Rank playerRank = Rank.getRank(player);
                    player.setPlayerListName(ColorUtils.colorize(playerRank.getPrefix() + " " + playerRank.getColor() + player.getName()));

                    if(!scoreboards.containsKey(player)){
                        FastBoard board = new FastBoard(player);
                        board.updateTitle(ColorUtils.colorize("&a&lRANK UP"));
                        Core.scoreboards.put(player, board);
                    }
                }
                for(FastBoard board : scoreboards.values()){ updateBoard(board); }
            }
        }).runTaskTimer(getInstance(), 20L, 20L);
    }
    public void onDisable() {
        /*for(Player player : Bukkit.getOnlinePlayers()){
            player.kickPlayer(ChatUtils.colorize("\n&a&lRANK UP\n\n&fO servidor está a reiniciar!"));
        }*/
    }

    private void updateBoard(FastBoard board) {
        if(board.isDeleted()) return;
        Player player = board.getPlayer();
        Rank rank = Rank.getRank(player);

        if(rank.getNextRank() == null) {
            board.updateLines(
                    "",
                    ColorUtils.colorize(" &fRank: " + rank.getPrefix()),
                    "",
                    ColorUtils.colorize(" &fDinheiro: " + ChatColor.of("#85bb65") + "$" + NumberUtils.format(7500)),
                    ColorUtils.colorize(" &fGemas: " + ChatColor.of("#625589") + "0"),
                    ColorUtils.colorize(" &fCristais: " + ChatColor.of("#5BB2FF") + "0"),
                    ""
            );
        }
        else{
            board.updateLines(
                    "",
                    ColorUtils.colorize(" &fRank: " + rank.getPrefix()),
                    ColorUtils.colorize(" &fProgresso (" + Rank.getNextRankProgressPercentage(player) + "%)"),
                    ColorUtils.colorize("      " + Rank.getNextRankProgressSymbols(player)),
                    "",
                    ColorUtils.colorize(" &fDinheiro: " + ChatColor.of("#85bb65") + "$" + NumberUtils.format(7500)),
                    ColorUtils.colorize(" &fGemas: " + ChatColor.of("#625589") + "0"),
                    ColorUtils.colorize(" &fCristais: " + ChatColor.of("#5BB2FF") + "0"),
                    ""
            );
        }
    }

    public void loadEvents(){
        List<String> packages = Arrays.stream(Package.getPackages())
                .map(Package::getName)
                .filter(p -> p.endsWith("listeners"))
                .collect(Collectors.toList());

        for(String pckg : packages){
            Bukkit.broadcastMessage(pckg);
            Reflections reflector = new Reflections(pckg);
            try{
                for(Class<? extends Listener> cls : reflector.getSubTypesOf(Listener.class)){
                    getServer().getPluginManager().registerEvents(cls.getDeclaredConstructor().newInstance(), this);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        /*
        Reflections reflector = new Reflections("org.slk.rankup.listeners");
        try{
            for(Class<? extends Listener> cls : reflector.getSubTypesOf(Listener.class)){
                getServer().getPluginManager().registerEvents(cls.getDeclaredConstructor().newInstance(), this);
            }
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        for(Class<? extends Listener> cls : events){
            try{
                getServer().getPluginManager().registerEvents(cls.getDeclaredConstructor().newInstance(), this);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        */
    }
    public void loadEvents(String packageName){
        Set<Class<? extends Listener>> events;
        Reflections reflector = new Reflections(packageName);
        try{
            events = reflector.getSubTypesOf(Listener.class);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

        for(Class<? extends Listener> cls : events){
            try{
                getServer().getPluginManager().registerEvents(cls.getDeclaredConstructor().newInstance(), this);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void loadCommands(){
        CommandMap map = null;
        try{
            Field cmdMap = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            cmdMap.setAccessible(true);
            map = (CommandMap) cmdMap.get(Bukkit.getPluginManager());
        }catch (Exception e){
            e.printStackTrace();
        }
        /*
        Reflections reflections = new Reflections("org.slk.rankup.commands");
        Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);
        for(Class<?extends Command> cls : commands){
            try{
                Command command = cls.getDeclaredConstructor(new Class[]{}).newInstance(new Object[]{});
                assert map != null;
                map.register(cls.getSimpleName().toLowerCase(), command);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        */
        List<String> packages = Arrays.stream(Package.getPackages())
                .map(Package::getName)
                .filter(p -> p.endsWith("commands"))
                .collect(Collectors.toList());
        for(String pckg : packages){
            Bukkit.broadcastMessage(pckg);
            Reflections reflector = new Reflections(pckg);
            try{
                for(Class<? extends Command> cls : reflector.getSubTypesOf(Command.class)){
                    Command command = cls.getDeclaredConstructor(new Class[]{}).newInstance();
                    assert map != null;
                    map.register(cls.getSimpleName().toLowerCase(), command);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}