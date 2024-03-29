package org.slk.rankup.ranks.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slk.rankup.ranks.events.PlayerPrestigeEvent;
import org.slk.rankup.ranks.events.PlayerRankUPEvent;
import org.slk.rankup.itemstacks.EnchantingBook;
import org.slk.rankup.itemstacks.FireworkBox;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ChatUtils;

public class RankUPCommand extends Command {
    public RankUPCommand(){
        super("rankup");
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatUtils.error("Apenas jogadores podem utilizar este comando!"));
            return true;
        }
        Player player = (Player) sender;
        Rank rank = Rank.getRank(player);
        Rank nextRank = rank.getNextRank();
        if(nextRank == null){
            PlayerPrestigeEvent event = new PlayerPrestigeEvent(player, 1);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                // TODO
                Rank.setRank(player, Rank.getDefaultRank());
            }

            return true;
        }
        // TODO : Check player's money

        PlayerRankUPEvent event = new PlayerRankUPEvent(player, nextRank);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            // TODO
            Rank.setRank(player, nextRank);
        }

        return true;
    }
}