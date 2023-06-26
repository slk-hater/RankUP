package org.slk.rankup.playerdata.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.slk.rankup.playerdata.PlayerDataManager;
import org.slk.rankup.ranks.Rank;

import java.util.Dictionary;
import java.util.Hashtable;
public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(PlayerDataManager.getPlayerData(player) == null) PlayerDataManager.getCache().put(player, new Hashtable<>());

        Dictionary<String, Object> playerData = PlayerDataManager.getPlayerData(player);
        playerData.put("rank", Rank.getDefaultRank());
        playerData.put("prestiges", 0);
        playerData.put("money", (long)5000);
        playerData.put("crystals", 0);
        playerData.put("gems", 0);
        playerData.put("totalTreasures", 0);
    }
}