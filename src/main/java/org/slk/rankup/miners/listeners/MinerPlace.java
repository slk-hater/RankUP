package org.slk.rankup.miners.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.miners.MinersManager;
import org.slk.rankup.miners.itemstacks.Miner;
import org.slk.rankup.utils.ColorUtils;

public class MinerPlace implements Listener {
    @EventHandler
    public void onMinerPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        ItemStack heldItem = event.getItemInHand();
        ItemMeta heldItemMeta = heldItem.getItemMeta();

        if(heldItemMeta == null) return;
        if(!heldItemMeta.hasCustomModelData()) return;
        if(heldItemMeta.getCustomModelData() != CustomModelDataEnum.MINER.get()) return;

        event.setCancelled(true);
        if(player.getInventory().getItemInMainHand().getAmount()>1) player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
        else player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        Location location = event.getBlock().getLocation();
        if(location.getWorld() == null) return;
        location.add(0, -1, 0).getBlock().setType(Material.RED_STAINED_GLASS);

        location.setDirection(player.getFacing().getOppositeFace().getDirection());
        ArmorStand as = MinersManager.spawnMiner(location);
        player.playSound(player, Sound.ENTITY_VILLAGER_CELEBRATE, 0.3f, 1f);

        Miner.MinerType type;
        if (ChatColor.stripColor(heldItemMeta.getLore().get(1)).equalsIgnoreCase("AVANÇADO")) type = Miner.MinerType.ADVANCED;
        else type = Miner.MinerType.BASIC;
        int speed = Integer.parseInt(ChatColor.stripColor(heldItem.getItemMeta().getLore().get(1)).replaceAll("[^0-9]", ""));

        MinersManager.createMiner(as.getLocation(), player, type, speed);
    }
}