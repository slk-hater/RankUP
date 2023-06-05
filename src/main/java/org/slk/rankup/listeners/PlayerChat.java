package org.slk.rankup.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.slk.rankup.ranks.Rank;

public class PlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        Rank rank = Rank.getRank(player);
        String format = "{PREFIX} %s: {MESSAGE}";
        format = format.replace("{PREFIX}", rank.getPrefix() + ChatColor.RESET + rank.getColor());
        format = format.replace("{MESSAGE}", ChatColor.of(rank.getColor().getColor().brighter()) + event.getMessage());
        event.setFormat(format);
    }
}