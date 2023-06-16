package org.slk.rankup.treasures.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.slk.rankup.treasures.TreasuresManager;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ColorUtils;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        World world = event.getBlock().getWorld();
        Block block = event.getBlock();

        if(!world.equals(TreasuresManager.getWorld())) return;

        event.setDropItems(false);

        switch (block.getType()) {
            case SAND -> {
                Player owner = null;
                for (Player p : TreasuresManager.LOCKED_TREASURE.keySet())
                    if (TreasuresManager.LOCKED_TREASURE.get(p).equals(block.getLocation()))
                        owner = p;
                if (owner == null) {
                    block.setType(Material.AIR);
                    break;
                } else if (!owner.equals(player)) {
                    player.sendMessage(ChatUtils.error("Este tesouro não é teu!"));
                    break;
                }
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorUtils.colorize(ChatColor.of("#95D4FF") + "Encontras-te um tesouro!")));
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.3f, 1f);
                TreasuresManager.LOCKED_TREASURE.remove(player);
            }
            case AMETHYST_BLOCK -> {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorUtils.colorize(ChatColor.of("#7859b1") + "Encontras-te uma gema!")));
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 0.3f, 1f);
            }
            default -> {
                if (Math.random() < TreasuresManager.CHANCE_GEM) break;
                player.getTargetBlock(null, 4).setType(Material.AMETHYST_BLOCK);
            }
        }
    }
}