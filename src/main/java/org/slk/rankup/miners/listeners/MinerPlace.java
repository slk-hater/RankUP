package org.slk.rankup.miners.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
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
        Location location = event.getBlock().getLocation();
        if(location.getWorld() == null) return;
        location.add(0, -1, 0).getBlock().setType(Material.RED_STAINED_GLASS);

        location.setDirection(player.getFacing().getOppositeFace().getDirection());
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location.add(0.5, 0.9, 0.5), EntityType.ARMOR_STAND);
        as.setSmall(true);
        as.setGravity(false);
        as.setVisible(true);
        as.setInvulnerable(true);
        as.setCollidable(false);
        as.setCanPickupItems(false);
        as.setCustomName(ColorUtils.colorize("&eMinerador"));
        as.setCustomNameVisible(true);
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

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        meta = (LeatherArmorMeta) boots.getItemMeta();
        meta.setColor(Color.fromRGB(44, 48, 101));
        boots.setItemMeta(meta);
        as.setBoots(boots);

        Miner.MinerType reverseType;
        if (ChatColor.stripColor(heldItemMeta.getDisplayName().split(" ")[1]).toUpperCase().equals("AVANÇADO")) reverseType = Miner.MinerType.ADVANCED;
        else reverseType = Miner.MinerType.BASIC;
        int speed = Integer.parseInt(ChatColor.stripColor(heldItem.getItemMeta().getLore().get(0)).replaceAll("[^0-9]", ""));

        YamlConfiguration configuration = MinersManager.getConfiguration();
        configuration.set("miners."+as.getLocation()+".owner", player.getName());
        configuration.set("miners."+as.getLocation()+".enabled", false);
        configuration.set("miners."+as.getLocation()+".type", reverseType.toString());
        configuration.set("miners."+as.getLocation()+".speed", speed);
        configuration.set("miners."+as.getLocation()+".fuel", 0);
        try{ configuration.save(MinersManager.getDataFile()); }
        catch(Exception e) { e.printStackTrace(); }
    }
}