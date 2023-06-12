package org.slk.rankup.ranks.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ItemStackBuilder;
import org.slk.rankup.utils.NumberUtils;

public class RanksCommand extends Command {
    public RanksCommand(){
        super("ranks");
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatUtils.error("Apenas jogadores podem utilizar este comando!"));
            return true;
        }
        Player player = (Player) sender;
        Rank rank = Rank.getRank(player);

        Inventory inv = Bukkit.createInventory(player, 9*5, "Ranks");
        int index = 10;
        for(Rank rankVal : Rank.values()){
            //int count=0;
            //if(rank.isGreaterThan(rankVal)) count=1;
            inv.setItem(index, ItemStackBuilder.build(Material.COAL_BLOCK, 1,
                    rankVal.getPrefix(),
                    "&fPreço " + ChatColor.of("#85bb65") + "$" + NumberUtils.format((long)rankVal.getPrice()))
            );
            index++;
        }
        inv.setItem(40, ItemStackBuilder.build(Material.ARROW, 1, "&cFechar", ""));
        player.openInventory(inv);

        return true;
    }
}