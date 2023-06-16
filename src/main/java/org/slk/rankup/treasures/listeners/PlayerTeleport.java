package org.slk.rankup.treasures.listeners;

import com.google.gson.stream.JsonReader;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.slk.rankup.Core;
import org.slk.rankup.treasures.TreasuresManager;
import org.slk.rankup.treasures.TreasuresMessages;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.Serialization;

import java.time.LocalDateTime;

public class PlayerTeleport implements Listener {
    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();

        if(to == null || to.getWorld() == null) return;
        if(from.getWorld() == null) return;

        // ?!
        if(from.getWorld().equals(TreasuresManager.getWorld()) && !to.getWorld().equals(from.getWorld())) {
            // TODO : Load previous inventory
            player.getInventory().clear();

            if(!player.getPersistentDataContainer().has(new NamespacedKey(Core.getInstance(), "inventoryBase64"), PersistentDataType.STRING)) return;
            try {
                String base64 = player.getPersistentDataContainer().get(new NamespacedKey(Core.getInstance(), "inventoryBase64"), PersistentDataType.STRING);
                player.sendMessage(" " + base64 + " ");
                Inventory inv = Serialization.fromBase64(base64);
                player.getInventory().setContents(inv.getContents());
            }
            catch (Exception e) { e.printStackTrace(); }
            return;
        }

        if(to.getWorld().equals(TreasuresManager.getWorld())) {
            if (to.getWorld().equals(from.getWorld())) return;
            TreasuresManager.setupTimer();
            TreasuresManager.JOINED_DATE_MAP.put(player, LocalDateTime.now());

            // TODO : Save inventory to load after
            String base64 = Serialization.toBase64(player.getInventory());
            player.sendMessage(" " + base64 + " ");
            player.getPersistentDataContainer().set(new NamespacedKey(Core.getInstance(), "inventoryBase64"), PersistentDataType.STRING, base64);
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