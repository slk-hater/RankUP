package org.slk.rankup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slk.rankup.Core;
import org.slk.rankup.utils.ChatUtils;

public class RankUPCommand extends Command {
    public RankUPCommand(){
        super("rankup");
    }
    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player)){ sender.sendMessage(ChatUtils.colorize("&cApenas jogadores podem utilizar este comando!")); }

        return true;
    }
}