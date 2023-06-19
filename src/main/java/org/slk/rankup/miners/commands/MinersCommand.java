package org.slk.rankup.miners.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slk.rankup.miners.inventories.RemoteControlMinersInventory;
import org.slk.rankup.utils.ChatUtils;

import java.util.List;

public class MinersCommand extends Command {
    public MinersCommand(){
        super("miners");
        setLabel("miners");
        setDescription("Remote control miners");
        setAliases(List.of("mineradores"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage(ChatUtils.error("Apenas jogadores podem utilizar este comando!"));
            return true;
        }

        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 0.3f, 1f);
        player.openInventory(RemoteControlMinersInventory.cloneInventory());
        return true;
    }
}