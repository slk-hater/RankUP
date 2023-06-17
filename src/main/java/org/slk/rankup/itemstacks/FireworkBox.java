package org.slk.rankup.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.placeables.IPlaceable;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FireworkBox {
    static ItemStack IS = ItemStackBuilder.getSkull("http://textures.minecraft.net/texture/b55ea43e592785d016acdeea9a4a6f9cf22c2753e695405e8c83d2e09ebcf647", UUID.fromString("4db1d6a8-0975-4b1c-bc5e-ea61edf2a11b"));
    static final int BASE_DURATION = 25;
    static final int MAX_DURATION = 300;

    int duration;
    final ItemStack is;

    public FireworkBox() {
        this.is = IS.clone();

        ItemMeta meta = this.is.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ColorUtils.colorize(ChatColor.of("#F46D75") + "Caixa de foguetes"));
        meta.setCustomModelData(CustomModelDataEnum.FIREWORK_BOX.get());
        this.is.setItemMeta(meta);

        setDuration(BASE_DURATION);
    }
    public static int getBaseDuration() { return BASE_DURATION; }
    public static int getMaxDuration() { return MAX_DURATION; }

    public int getDuration() { return this.duration; }
    public void setDuration(int seconds) {
        this.duration = Math.min(seconds, MAX_DURATION);
        String durationStr = "&e";
        int h = getDuration()/3600;
        int r = getDuration()-h * 3600;
        int m = r/60;
        r = r - m * 60;
        int s = r;
        if(h > 0) durationStr += h + "h ";
        if(m > 0) durationStr += m + "m ";
        if(s > 0) durationStr += s + "s ";
        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        String lore = "&7Duração &f" + durationStr + "\n\n&8Botão direito no chão para posicionar";
        List<String> loreRes = new ArrayList<>(List.of(lore.split("\n")));
        loreRes.replaceAll(ColorUtils::colorize);
        meta.setLore(loreRes);
        getItemStack().setItemMeta(meta);
    }
    public ItemStack getItemStack() { return this.is; }
}