package org.slk.rankup.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnchantingBook {
    public enum RarityType { COMMON, UNCOMMON, RARE }
    public enum ModifierType { EFFICIENCY, FORTUNE, UNBREAKABLE, EXPLOSION }
    static ItemStack COMMON_SKULL = ItemStackBuilder.getSkull("http://textures.minecraft.net/texture/d53fa1b57e4f784d16e5a2daa2f746b2ecfe624ccd74a4d4acc6a2e6a083f54e", UUID.fromString("0041a162-ad10-4dfc-906d-014c797abaeb"));
    static ItemStack UNCOMMON_SKULL = ItemStackBuilder.getSkull("http://textures.minecraft.net/texture/1c030cc35fde0218ded5ba5d557cca9e03dcbc84f9e672911da54fe07c5fdbbb", UUID.fromString("7a6f8374-8915-43db-acdf-932da0dbcd25"));
    static ItemStack RARE_SKULL = ItemStackBuilder.getSkull("http://textures.minecraft.net/texture/93a69c3caa31304e9952328c72cee0b57b2a2bd46ce9c5cb88c07d1266277d6a", UUID.fromString("a71300d1-7286-4f84-aee1-fce55c7bb172"));

    final RarityType bookRarity;
    final ModifierType bookModifier;
    final ItemStack is;

    public EnchantingBook(RarityType bookRarity, ModifierType bookModifier){
        this.bookRarity = bookRarity;
        this.bookModifier = bookModifier;
        String translatedRarity;
        switch (bookRarity) {
            case UNCOMMON -> {
                this.is = UNCOMMON_SKULL;
                translatedRarity = "INCOMUM";
            }
            case RARE -> {
                this.is = RARE_SKULL;
                translatedRarity = "RARO";
            }
            default -> {
                this.is = COMMON_SKULL;
                translatedRarity = "COMUM";
            }
        }
        String translatedModifier = switch (bookModifier) {
            case FORTUNE -> "FORTUNA";
            case UNBREAKABLE -> "INQUEBRÁVEL";
            default -> "EFICIÊNCIA";
        };
        ItemMeta meta = is.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ColorUtils.colorize(ChatColor.of("#00DFA2") + "Livro de encantamento"));
        String lore = "&7Raridade &f" + translatedRarity + "\n&7Modificador &f" + translatedModifier;
        List<String> loreRes = new ArrayList<>(List.of(lore.split("\n")));
        loreRes.replaceAll(ColorUtils::colorize);
        meta.setLore(loreRes);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setCustomModelData(CustomModelDataEnum.ENCHATING_BOOK.get());
        is.setItemMeta(meta);
    }

    public RarityType getBookRarity(){ return bookRarity; }
    public ItemStack getItemStack(){ return is; }
}