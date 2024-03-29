package org.slk.rankup.ranks.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.slk.rankup.ranks.events.PlayerRankUPEvent;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.WorldUtils;

public class PlayerRankUP implements Listener {
    @EventHandler
    public void onPlayerRankUP(PlayerRankUPEvent event){
        Player player = event.getPlayer();
        Rank rank = event.getRank();
        for(Player target : Bukkit.getOnlinePlayers()){
            target.sendTitle(
                    ColorUtils.colorize(rank.getColor() + player.getName()),
                    ColorUtils.colorize("&fSubiu para o rank " + rank.getPrefix() + "&f!"),
                    3, 30, 3);
        }
        player.sendMessage(ChatUtils.good("&fParabéns! Subis-te para o rank " + rank.getPrefix() + "&r."));
        WorldUtils.spawnFireworks(player.getLocation(), 6);
    }
}