package org.slk.rankup.ranks.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.slk.rankup.itemstacks.EnchantingBook;
import org.slk.rankup.itemstacks.FireworkBox;
import org.slk.rankup.miners.itemstacks.Fuel;
import org.slk.rankup.miners.itemstacks.Miner;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.ranks.events.PlayerPrestigeEvent;
import org.slk.rankup.ranks.events.PlayerRankUPEvent;
import org.slk.rankup.utils.ChatUtils;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.WorldUtils;

public class PlayerPrestige implements Listener {
    @EventHandler
    public void onPlayerPrestige(PlayerPrestigeEvent event){
        Player player = event.getPlayer();
        int prestige = event.getPrestige();
        for(Player target : Bukkit.getOnlinePlayers()){
            target.sendTitle(
                    ColorUtils.colorize(player.getName()),
                    ColorUtils.colorize("Ganhou prestígio!"),
                    3, 30, 3);
        }
        player.sendMessage(ChatUtils.good("&fParabéns! Prestigias-te o teu rank, tens agora &l" + prestige + " &rprestígios."));
        WorldUtils.spawnFireworks(player.getLocation(), 6);

        player.getInventory().addItem(new FireworkBox().getItemStack());
        player.getInventory().addItem(new EnchantingBook(EnchantingBook.RarityType.COMMON, EnchantingBook.ModifierType.EFFICIENCY).getItemStack());
        player.getInventory().addItem(new EnchantingBook(EnchantingBook.RarityType.UNCOMMON, EnchantingBook.ModifierType.UNBREAKABLE).getItemStack());
        player.getInventory().addItem(new EnchantingBook(EnchantingBook.RarityType.RARE, EnchantingBook.ModifierType.FORTUNE).getItemStack());
        player.getInventory().addItem(new Miner().getItemStack());
        player.getInventory().addItem(new Fuel().getItemStack());
    }
}