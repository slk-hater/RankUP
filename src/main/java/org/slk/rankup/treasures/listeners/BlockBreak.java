package org.slk.rankup.treasures.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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

        event.setDropItems(world.equals(TreasuresManager.getWorld()));

        switch(block.getType()){
            case SAND:
                Player owner = null;
                for(Player p : TreasuresManager.LOCKED_TREASURE.keySet())
                    if(TreasuresManager.LOCKED_TREASURE.get(p).equals(block.getLocation()))
                        owner = p;
                if(owner == null || !owner.equals(player)){
                    player.sendMessage(ChatUtils.error("Este tesouro não é teu!"));
                    return;
                }
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorUtils.colorize(ChatColor.of("#95D4FF") + "Encontras-te um tesouro!")));
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.3f, 1f);
                break;
            case AMETHYST_BLOCK:
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorUtils.colorize(ChatColor.of("#625589") + "Encontras-te uma gema!")));
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 0.3f, 1f);
                break;
            default:
                double chance = Math.random();
                if(chance > 0.2D) // 80% chance to spawn gem
                    player.getTargetBlock(null, 4).setType(Material.AMETHYST_BLOCK);
                break;
        }
    }
}