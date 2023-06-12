package org.slk.rankup.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.List;
import java.util.UUID;

public class FireworkBox {
    static ItemStack IS = ItemStackBuilder.getSkull("http://textures.minecraft.net/texture/b55ea43e592785d016acdeea9a4a6f9cf22c2753e695405e8c83d2e09ebcf647", UUID.fromString("4db1d6a8-0975-4b1c-bc5e-ea61edf2a11b"));
    public static final int DURATION = 25;

    public FireworkBox() {
        ItemMeta meta = IS.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ColorUtils.colorize(ChatColor.of("#F46D75") + "Caixa de foguetes"));
        String lore = "&7Duração &f" + DURATION + "s";
        List<String> loreRes = new List<>(List.of(lore.split("\n")));
        loreRes.replaceAll(ColorUtils::colorize);
        meta.setLore(loreRes);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setCustomModelData(CustomModelDataEnum.FIREWORK_BOX.get());
        IS.setItemMeta(meta);
    }

    public ItemStack getItemStack() { return IS; }
}