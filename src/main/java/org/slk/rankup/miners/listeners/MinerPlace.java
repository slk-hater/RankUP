package org.slk.rankup.miners.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.slk.rankup.Core;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.miners.itemstacks.Miner;

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
        Location location = event.getBlock().getLocation();
        if(location.getWorld() == null) return;
        location.add(0, -1, 0).getBlock().setType(Material.RED_STAINED_GLASS);

        location.setDirection(player.getFacing().getOppositeFace().getDirection());
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location.add(0.5, 0.9, 0.5), EntityType.ARMOR_STAND);
        as.setSmall(true);
        as.setGravity(false);
        as.setHelmet(Miner.SKULL);
        as.setItemInHand(new ItemStack(Material.NETHERITE_PICKAXE));

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) chestplate.getItemMeta();
        meta.setColor(Color.fromRGB(44, 48, 101));
        chestplate.setItemMeta(meta);
        as.setChestplate(chestplate);

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        meta = (LeatherArmorMeta) leggings.getItemMeta();
        meta.setColor(Color.fromRGB(44, 48, 101));
        leggings.setItemMeta(meta);
        as.setLeggings(leggings);

        ItemStack boots = new ItemStack(Material.LEATHER_CHESTPLATE);
        meta = (LeatherArmorMeta) boots.getItemMeta();
        meta.setColor(Color.fromRGB(44, 48, 101));
        boots.setItemMeta(meta);
        as.setBoots(boots);

        as.setVisible(true);

        /*new BukkitRunnable() {
            @Override
            public void run() {
                as.setHeadPose(as.getHeadPose().add(0, 0.17, 0));
            }

        }.runTaskTimer(Core.getInstance(), 0L, 1L);*/
    }
}