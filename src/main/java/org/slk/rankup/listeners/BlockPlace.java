package org.slk.rankup.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.slk.rankup.Core;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.itemstacks.FireworkBox;
import org.slk.rankup.utils.WorldUtils;

import java.util.Random;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Block block = event.getBlock();
        ItemStack is = event.getItemInHand();
        ItemMeta meta = is.getItemMeta();

        if(meta == null) return;
        if(!meta.hasCustomModelData()) return;
        /*IPlaceable placeable = (IPlaceable) is;
        Bukkit.broadcastMessage(placeable.toString());*/
        if(meta.getCustomModelData() == CustomModelDataEnum.FIREWORK_BOX.get()){
            int duration = Integer.parseInt(ChatColor.stripColor(meta.getLore().get(0)).replaceAll("[^0-9]", ""));
            (new BukkitRunnable() {
                public int tick = 0;
                public void run() {
                    tick++;
                    if(block.getType() == Material.AIR) this.cancel();
                    if(tick >= duration) {
                        block.setType(Material.AIR);
                        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GUNPOWDER, new Random().nextInt(1,3)));
                        this.cancel();
                    }
                    WorldUtils.spawnFireworks(block.getLocation(), 4);
                }
            }).runTaskTimer(Core.getInstance(), 20L*4, 20L);
        }
        else if(meta.getCustomModelData() == CustomModelDataEnum.ENCHATING_BOOK.get())
            event.setCancelled(true);
    }
}