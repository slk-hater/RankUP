package org.slk.rankup.treasures.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.treasures.TreasuresManager;
import org.slk.rankup.utils.ColorUtils;

public class PlayerInteractWithCompass implements Listener {
    @EventHandler
    public void onPlayerInteractWithCompass(PlayerInteractEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        ItemStack heldItem = event.getItem();

        if(!world.equals(TreasuresManager.getWorld())) return;
        if(heldItem == null) return;
        if(!heldItem.isSimilar(TreasuresManager.getItems().get(1))) return;

        Location loc = TreasuresManager.LOCKED_TREASURE.get(player);
        if(loc == null){
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorUtils.colorize("&cNenhum tesouro encontrado ainda...")));
            return;
        }
        player.setCompassTarget(loc);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorUtils.colorize("&aA apontar para o tesouro (" + Math.round(loc.distance(player.getLocation())) + " blocos)")));
    }
}