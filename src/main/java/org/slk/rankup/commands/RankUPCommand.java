package org.slk.rankup.commands;

import org.bukkit.Bukkit;
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

        PlayerRankUPEvent event = new PlayerRankUPEvent(player, rank.getNextRank());
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            // TODO
            Rank.setRank(player, rank.getNextRank());
        }

        return true;
    }
}