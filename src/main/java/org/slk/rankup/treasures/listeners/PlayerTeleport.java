package org.slk.rankup.treasures.listeners;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.slk.rankup.treasures.TreasuresManager;
import org.slk.rankup.treasures.TreasuresMessages;
import org.slk.rankup.treasures.TreasuresNamespacedKey;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.Serialization;

import java.time.LocalDateTime;
import java.util.Arrays;

public class PlayerTeleport implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();

        if(to == null || to.getWorld() == null) return;
        if(from.getWorld() == null) return;

        if(from.getWorld().equals(TreasuresManager.getWorld()) && !to.getWorld().equals(from.getWorld())) {
            player.getInventory().clear();
            if(!player.getPersistentDataContainer().has(TreasuresNamespacedKey.INVENTORY.get(), PersistentDataType.STRING)) return;
            try {
                String base64 = player.getPersistentDataContainer().get(TreasuresNamespacedKey.INVENTORY.get(), PersistentDataType.STRING);
                assert base64 != null;
                ItemStack[] items = Serialization.itemStackArrayFromBase64(base64.split("//")[0]);
                ItemStack[] armor = Serialization.itemStackArrayFromBase64(base64.split("//")[1]);
                player.getInventory().setContents(items);
                player.getInventory().setArmorContents(armor);
            }
            catch (Exception e) { e.printStackTrace(); }
            return;
        }

        if(to.getWorld().equals(TreasuresManager.getWorld()) && !from.getWorld().equals(to.getWorld())) {
            TreasuresManager.setupTimer();
            TreasuresManager.JOINED_DATE_MAP.put(player, LocalDateTime.now());

            String[] base64 = Serialization.playerInventoryToBase64(player.getInventory());
            player.getPersistentDataContainer().set(TreasuresNamespacedKey.INVENTORY.get(), PersistentDataType.STRING, base64[0] + "//" + base64[1]);
            player.getInventory().clear();

            player.sendMessage("\n" + ChatUtils.good(TreasuresMessages.JOIN_WORLD.get(player)) + "\n ");
            player.setFoodLevel((int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            player.setHealth((int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, 0.3f, 1f);
            for (int i = 0; i < TreasuresManager.getItems().size(); i++)
                player.getInventory().setItem(i, TreasuresManager.getItems().get(i));
        }
    }
}