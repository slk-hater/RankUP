package org.slk.rankup.listeners;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.slk.rankup.Core;
import org.slk.rankup.nametags.TagManager;
import org.slk.rankup.nametags.TeamAction;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ChatUtils;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage("");
        Player player = event.getPlayer();
        Rank rank = Rank.getRank(player);
        TagManager.changePlayerName(player, rank.getPrefix(), TeamAction.DESTROY);
        Bukkit.broadcastMessage(ChatUtils.colorize("&c" + player.getName() + " saiu do servidor."));

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(player.getName());
        if(team != null) {
            team.removeEntry(player.getName());
            team.unregister();
        }

        FastBoard board = Core.scoreboards.get(player);
        if(board != null){
            Core.scoreboards.remove(player, board);
            board.delete();
        }
    }
}