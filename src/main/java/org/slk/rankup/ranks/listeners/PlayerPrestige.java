package org.slk.rankup.ranks.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.ranks.events.PlayerPrestigeEvent;
import org.slk.rankup.ranks.events.PlayerRankUPEvent;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.WorldUtils;

public class PlayerPrestige implements Listener {
    @EventHandler
    public void onPlayerPrestige(PlayerPrestigeEvent event){
        Player player = event.getPlayer();
        int prestige = event.getPrestige();
        for(Player target : Bukkit.getOnlinePlayers()){
            target.sendTitle(
                    ColorUtils.colorize(player.getName()),
                    ColorUtils.colorize("Ganhou prestígio!"),
                    3, 30, 3);
        }
        player.sendMessage(ChatUtils.good("&fParabéns! Prestigias-te o teu rank, tens agora &l" + prestige + " &rprestígios."));
        WorldUtils.spawnFireworks(player.getLocation(), 6);
    }
}