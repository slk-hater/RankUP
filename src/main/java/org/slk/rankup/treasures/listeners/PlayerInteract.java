package org.slk.rankup.treasures.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
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
        if(!heldItem.getItemMeta().hasCustomModelData() || heldItem.getItemMeta().getCustomModelData() != CustomModelDataEnum.TICKET.get()) return;

        String duration = ChatColor.stripColor(heldItem.getItemMeta().getLore().get(1).replace("Duração ", "").replace(" minutos", ""));
        player.sendMessage("duration of ticket is " + duration);
        int d = Integer.parseInt(duration);
        if(d != TreasuresManager.DURATION_MINUTES)
            TreasuresManager.CUSTOM_DURATION_MAP.put(player, d);

        Random rnd = new Random();
        Location loc = new Location(TreasuresManager.getWorld(), rnd.nextInt(0, 4000), 0, rnd.nextInt(0, 4000));
        player.teleport(loc.add(0, TreasuresManager.getWorld().getHighestBlockYAt(loc),0));
        if(player.getInventory().getItemInMainHand().getAmount()>1) player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
        else player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
    }
}