package org.slk.rankup;

import fr.mrmicky.fastboard.FastBoard;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;
import org.slk.rankup.miners.MinersManager;
import org.slk.rankup.playerdata.PlayerDataManager;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.treasures.TreasuresManager;
import org.slk.rankup.treasures.TreasuresMessages;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.NumberUtils;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public final class Core extends JavaPlugin {
    private static Core instance;
    public static LocalDateTime uptimeStartDate = LocalDateTime.now();
    public static Map<Player, FastBoard> scoreboards = new HashMap<>();

    public static Core getInstance() { return instance; }

    public void onEnable() {
        super.onEnable();
        instance = this;

        loadEvents();
        loadCommands();

        // --> TreasuresManager.setup();
        MinersManager.setup();

        //region Runnables
        (new BukkitRunnable() {
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.setPlayerListHeader(ColorUtils.colorize(
                            "\n&a&lRANK UP\n"
                    ));
                    Duration duration = Duration.between(uptimeStartDate, LocalDateTime.now());
                    player.setPlayerListFooter(ColorUtils.colorize(
                            "\n&fUptime &e" + duration.toHoursPart() + "h " + duration.toMinutesPart() + "m " + duration.toSecondsPart() +"s\n"
                    ));

                    Rank playerRank = Rank.getRank(player);
                    player.setPlayerListName(ColorUtils.colorize(playerRank.getPrefix() + " " + playerRank.getColor() + player.getName()));

                    if(!scoreboards.containsKey(player)){
                        FastBoard board = new FastBoard(player);
                        Core.scoreboards.put(player, board);
                    }
                }
                for(FastBoard board : scoreboards.values()) updateBoard(board);
            }
        }).runTaskTimer(getInstance(), 20L, 20L);
        //endregion
    }
    public void onDisable() {
        super.onDisable();

        TreasuresManager.kickPlayers();
    }

    private void updateBoard(FastBoard board) {
        if(board.isDeleted()) return;
        Player player = board.getPlayer();
        Rank rank = Rank.getRank(player);

        if(player.getWorld().equals(TreasuresManager.getWorld())){
            board.updateTitle(ColorUtils.colorize("&a&lRANK UP - TESOUROS"));
            String color = TreasuresManager.getTimeLeft(player).toMinutesPart() > 3 ? "&e" : "&c";
            board.updateLines(
                    "",
                    ColorUtils.colorize(" &fTempo restante:"),
                    ColorUtils.colorize("     " + color + TreasuresManager.getTimeLeft(player).toMinutesPart() + "m e " + TreasuresManager.getTimeLeft(player).toSecondsPart() + "s"),
                    "",
                    ColorUtils.colorize(" &fT. Tesouros: " + ChatColor.of("#95D4FF") + "☘ " + PlayerDataManager.getPlayerData(player).get("totalTreasures")),
                    ColorUtils.colorize(" &fGemas: " + ChatColor.of("#7859b1") + "♦ " + PlayerDataManager.getPlayerData(player).get("gems")),
                    ""
            );
            return;
        }

        if(rank.getNextRank() == null) {
            board.updateTitle(ColorUtils.colorize("&a&lRANK UP"));
            board.updateLines(
                    "",
                    ColorUtils.colorize(" &fPrestígio: " + ChatColor.of("#17A589") + "★ " + PlayerDataManager.getPlayerData(player).get("prestiges")),
                    ColorUtils.colorize(" &fRank: " + rank.getPrefix()),
                    "",
                    ColorUtils.colorize(" &fDinheiro: " + ChatColor.of("#85bb65") + "$" + NumberUtils.format((long) PlayerDataManager.getPlayerData(player).get("money"))),
                    ColorUtils.colorize(" &fCristais: " + ChatColor.of("#5BB2FF") + "♦ " + PlayerDataManager.getPlayerData(player).get("crystals")),
                    ColorUtils.colorize(" &fGemas: " + ChatColor.of("#7859b1") + "♦ " + PlayerDataManager.getPlayerData(player).get("gems")),
                    ""
            );
        }
        else{
            board.updateTitle(ColorUtils.colorize("&a&lRANK UP"));
            board.updateLines(
                    "",
                    ColorUtils.colorize(" &fPrestígio: " + ChatColor.of("#17A589") + "★ " + PlayerDataManager.getPlayerData(player).get("prestiges")),
                    ColorUtils.colorize(" &fRank: " + rank.getPrefix()),
                    ColorUtils.colorize(" &fProgresso (" + Rank.getNextRankProgressPercentage(player) + "%)"),
                    ColorUtils.colorize("       " + Rank.getNextRankProgressSymbols(player)),
                    "",
                    ColorUtils.colorize(" &fDinheiro: " + ChatColor.of("#85bb65") + "$" + NumberUtils.format((long) PlayerDataManager.getPlayerData(player).get("money"))),
                    ColorUtils.colorize(" &fCristais: " + ChatColor.of("#5BB2FF") + "♦ " + PlayerDataManager.getPlayerData(player).get("crystals")),
                    ColorUtils.colorize(" &fGemas: " + ChatColor.of("#7859b1") + "♦ " + PlayerDataManager.getPlayerData(player).get("gems")),
                    ""
            );
        }
    }

    public void loadEvents(){
        Reflections reflector = new Reflections("org.slk.rankup");
        try{
            for(Class<? extends Listener> cls : reflector.getSubTypesOf(Listener.class)){
                getServer().getPluginManager().registerEvents(cls.getDeclaredConstructor().newInstance(), this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadCommands(){
        CommandMap map = null;
        try{
            Field cmdMap = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            cmdMap.setAccessible(true);
            map = (CommandMap) cmdMap.get(Bukkit.getPluginManager());
        }catch (Exception e) { e.printStackTrace(); }

        Reflections reflector = new Reflections("org.slk.rankup");
        try{
            for(Class<? extends Command> cls : reflector.getSubTypesOf(Command.class)){
                Command command = cls.getDeclaredConstructor(new Class[]{}).newInstance();
                assert map != null;
                map.register(cls.getSimpleName().toLowerCase(), command);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}