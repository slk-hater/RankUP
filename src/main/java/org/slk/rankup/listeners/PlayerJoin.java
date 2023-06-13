package org.slk.rankup.listeners;

import com.nametagedit.plugin.NametagEdit;
import com.nametagedit.plugin.api.NametagAPI;
import com.nametagedit.plugin.api.data.Nametag;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.slk.rankup.Core;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ColorUtils;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage("");
        Player player = event.getPlayer();
        //Rank rank = Rank.getRank(player);
        //NametagEdit.getApi().setPrefix(player, rank.getPrefix() + " " + rank.getColor());
        Bukkit.broadcastMessage(ColorUtils.colorize("&e" + player.getName() + " entrou no servidor."));

        FastBoard board = new FastBoard(player);
        board.updateTitle(ColorUtils.colorize("&a&lRANK UP"));
        Core.scoreboards.put(player, board);
    }
}