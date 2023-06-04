package org.slk.rankup.listeners;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.slk.rankup.Core;
import org.slk.rankup.utils.ChatUtils;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage("");
        Player player = event.getPlayer();
        Bukkit.broadcastMessage(ChatUtils.colorize("&e" + player.getName() + " entrou no servidor."));

        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatUtils.colorize("&a&lRANK UP"));
        Core.scoreboards.put(player, board);
    }
}