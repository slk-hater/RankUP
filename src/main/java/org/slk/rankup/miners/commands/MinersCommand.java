package org.slk.rankup.miners.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.slk.rankup.miners.MinersNamespacedKey;
import org.slk.rankup.miners.inventories.RemoteControlMinersInventory;
import org.slk.rankup.miners.inventories.RemoteControlNoMinersInventory;
import org.slk.rankup.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;

public class MinersCommand extends Command {
    public MinersCommand(){
        super("miners");
        setLabel("miners");
        setDescription("Remote control miners");
        setAliases(List.of("mineradores", "minions"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage(ChatUtils.error("Apenas jogadores podem utilizar este comando!"));
            return true;
        }

        List<ArmorStand> playerMiners = new ArrayList<>();
        for(World world : Bukkit.getWorlds()) {
            for (ArmorStand as : world.getEntitiesByClass(ArmorStand.class)) {
                if (!as.getPersistentDataContainer().has(MinersNamespacedKey.OWNERSHIP.get(), PersistentDataType.STRING)) continue;
                else if (as.getPersistentDataContainer().get(MinersNamespacedKey.OWNERSHIP.get(), PersistentDataType.STRING) != player.getName()) continue;
                playerMiners.add(as);
            }
        }

        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 0.3f, 1f);
        if(playerMiners.size() == 0) player.openInventory(RemoteControlNoMinersInventory.cloneInventory());
        else player.openInventory(RemoteControlMinersInventory.build(playerMiners));

        return true;
    }
}