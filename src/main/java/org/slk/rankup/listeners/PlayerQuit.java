package org.slk.rankup.listeners;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.slk.rankup.Core;
import org.slk.rankup.utils.ChatUtils;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage("");
        Player player = event.getPlayer();
        Bukkit.broadcastMessage(ChatUtils.colorize("&c" + player.getName() + " saiu do servidor."));

        FastBoard board = Core.scoreboards.get(player);
        if(board != null){
            Core.scoreboards.remove(player, board);
            board.delete();
        }
    }
}