package org.slk.rankup.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slk.rankup.events.PlayerRankUPEvent;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ColorUtils;

public class RankUPCommand extends Command {
    public RankUPCommand(){
        super("rankup");
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ColorUtils.colorize("&cApenas jogadores podem utilizar este comando!"));
            return true;
        }
        Player player = (Player) sender;
        Rank rank = Rank.getRank(player);
        Rank nextRank = rank.getNextRank();
        if(nextRank == null){
            player.sendMessage(ColorUtils.colorize("&c&l# &fJá estás no último rank!"));
            return true;
        }

        PlayerRankUPEvent event = new PlayerRankUPEvent(player, nextRank);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            // TODO
            Rank.setRank(player, nextRank);
        }

        return true;
    }
}