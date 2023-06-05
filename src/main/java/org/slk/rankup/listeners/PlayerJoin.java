package org.slk.rankup.listeners;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.slk.rankup.Core;
import org.slk.rankup.nametags.TagManager;
import org.slk.rankup.nametags.TeamAction;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ChatUtils;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage("");
        Player player = event.getPlayer();
        Rank rank = Rank.getRank(player);
        player.setDisplayName(player.getName());
        TagManager.changePlayerName(player, rank.getPrefix(), TeamAction.CREATE);
        Bukkit.broadcastMessage(ChatUtils.colorize("&e" + player.getName() + " entrou no servidor."));

        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatUtils.colorize("&a&lRANK UP"));
        Core.scoreboards.put(player, board);
    }
}