package org.slk.rankup.listeners;

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

import java.util.Objects;
import java.util.Random;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Block block = event.getBlock();
        ItemStack is = event.getItemInHand();
        ItemMeta meta = is.getItemMeta();
        if(Objects.requireNonNull(meta).hasCustomModelData()){
            if(meta.getCustomModelData() == CustomModelDataEnum.FIREWORK_BOX.get()){
                (new BukkitRunnable() {
                    public int tick = 0;
                    public void run() {
                        tick++;
                        if(block.getType() == Material.AIR){ this.cancel(); }
                        if(tick >= FireworkBox.DURATION) {
                            block.setType(Material.AIR);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GUNPOWDER, new Random().nextInt(3)));
                            this.cancel();
                        }

                        WorldUtils.spawnFireworks(block.getLocation(), 4);
                    }
                }).runTaskTimer(Core.getInstance(), 20L*4, 20L);
            }
            else if(meta.getCustomModelData() == CustomModelDataEnum.ENCHATING_BOOK.get()){
                event.setCancelled(true);
            }
        }
    }
}