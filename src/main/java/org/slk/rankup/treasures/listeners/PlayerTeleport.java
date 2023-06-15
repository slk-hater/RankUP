package org.slk.rankup.treasures.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.treasures.TreasuresManager;

public class PlayerTeleport implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();

        if(to == null || to.getWorld() == null) return;
        if(from.getWorld() == null) return;

        if(to.getWorld().equals(TreasuresManager.getWorld())){
            player.sendMessage("\nBem-vindo ao mundo de Tesouros, aqui vais encontrar diversas recompensas. Tens apenas &e10 minutos&r para encontrar o máximo de tesouros que conseguires e lembra-te que a &eBússola&r é a chave para o teu sucesso, boa sorte.\n");
            player.getInventory().clear();
            for(ItemStack item : TreasuresManager.getItems())
                player.getInventory().addItem(item);
        }
        if(from.getWorld().equals(TreasuresManager.getWorld()))
            player.getInventory().clear();
    }
}