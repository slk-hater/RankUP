package org.slk.rankup.treasures.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.treasures.TreasuresManager;
import org.slk.rankup.treasures.TreasuresMessages;
import org.slk.rankup.utils.ChatUtils;

public class PlayerInteractWithBarrier implements Listener {
    @EventHandler
    public void onPlayerInteractWithBarrier(PlayerInteractEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        ItemStack heldItem = event.getItem();

        if(!world.equals(TreasuresManager.getWorld())) return;
        if(heldItem == null) return;
        if(!heldItem.isSimilar(TreasuresManager.getItems().get(8))) return;

        if(TreasuresManager.getTimeLeft(player).toMinutes() < 1){
            player.sendMessage(ChatUtils.error(TreasuresMessages.LEAVE_WORLD_OPTIONAL.getRaw()));
            player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());

            ItemStack ticketClone = TreasuresManager.TICKET.clone();
            ItemMeta meta = ticketClone.getItemMeta();
            if(meta == null || meta.getLore() == null) return;
            meta.getLore().set(1, meta.getLore().get(1).replace(
                    String.valueOf(TreasuresManager.DURATION_MINUTES),
                    String.valueOf(Math.round(TreasuresManager.getTimeLeft(player).toMinutes()))
            ));
            ticketClone.setItemMeta(meta);
            player.getInventory().addItem(ticketClone);
        }
    }
}