package org.slk.rankup.miners.commands;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.slk.rankup.miners.MinersManager;
import org.slk.rankup.miners.inventories.RemoteControlNoMinersInventory;
import org.slk.rankup.utils.ChatUtils;

import java.util.ArrayList;
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

        List<String> playerMiners = new ArrayList<>();
        YamlConfiguration config = MinersManager.getConfiguration();
        for(String key : config.getKeys(true)) {
            player.sendMessage(key);
            if(config.getString(key) == player.getName()) {
                playerMiners.add(key);
                player.sendMessage("toné");
            }
        }

        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 0.3f, 1f);
        player.openInventory(RemoteControlNoMinersInventory.cloneInventory());
        return true;
    }
}