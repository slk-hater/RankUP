package org.slk.rankup.miners.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.UUID;

public class Miner {
    public enum MinerType { BASIC, ADVANCED }
    static ItemStack SKULL = ItemStackBuilder.getSkull(
            "http://textures.minecraft.net/texture/fc82742b2a17eebdeaad2b95e566a5f0250c3557429f6a39a79b6ab8bf8",
            UUID.fromString("da05b761-b174-4ef6-b5e3-de5342f41fc8"));

    final MinerType minerType;
    final ItemStack is;

    public Miner(MinerType minerType){
        this.minerType = minerType;
        this.is = SKULL;
        ItemMeta meta = is.getItemMeta();
        if(meta != null) {
            switch (minerType) {
                case ADVANCED -> meta.setDisplayName(ChatColor.of("#f5f190") + "Minerador avançado");
                default -> meta.setDisplayName(ChatColor.of("#f5f190") + "Minerador básico");
            }
        }
    }
}