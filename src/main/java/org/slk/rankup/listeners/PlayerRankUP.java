package org.slk.rankup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.slk.rankup.events.PlayerRankUPEvent;
import org.slk.rankup.ranks.Rank;
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
                    ColorUtils.colorize("&fUpou para " + rank.getPrefix()),
                    3, 3, 3);
        }
        player.sendMessage(ColorUtils.colorize(" &a&l# &fUpas-te para " + rank.getPrefix() + " &f, parabéns."));
        WorldUtils.spawnFireworks(player.getLocation().add(0, 2, 0), 6);
    }
}