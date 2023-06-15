package org.slk.rankup.treasures.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.treasures.TreasuresManager;

import java.util.Random;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack heldItem = event.getItem();

        if(heldItem == null) return;
        if(!action.toString().contains("RIGHT_CLICK")) return;
        if(!heldItem.isSimilar(TreasuresManager.TICKET)) return;

        Random rnd = new Random();
        Location loc = new Location(TreasuresManager.getWorld(), rnd.nextInt(0, 4000), 0, rnd.nextInt(0, 4000));
        player.teleport(loc.add(0, TreasuresManager.getWorld().getHighestBlockYAt(loc),0));
        if(player.getInventory().getItemInMainHand().getAmount()>1) player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
        else player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
    }
}