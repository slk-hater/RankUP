package org.slk.rankup.miners.itemstacks;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.itemstacks.CustomModelDataEnum;
import org.slk.rankup.utils.ColorUtils;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Miner {
    public static ItemStack SKULL = ItemStackBuilder.getSkull(
            "http://textures.minecraft.net/texture/9908235a1cbecc03a72cdf710f4ed519d65b4da62b54a4ef98a7408fcf51b83b",
            UUID.fromString("da05b761-b174-4ef6-b5e3-de5342f41fc8"));
    static int BASE_SPEED = 1;
    static int MAX_SPEED = 150;
    static String DESCRIPTION = "&7Velocidade &f%speed%/s\n\n&8Botão direito no chão para posicionar";

    int speed;
    final ItemStack is;

    public Miner() {
        this.is = SKULL;
        setSpeed(getBaseSpeed());

        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        meta.setDisplayName(ChatColor.of("#f5f190") + "Minion minerador");
        meta.setCustomModelData(CustomModelDataEnum.MINER.get());
        getItemStack().setItemMeta(meta);
    }

    public static int getBaseSpeed() { return BASE_SPEED; }
    public static int getMaxSpeed() { return MAX_SPEED; }

    public int getSpeed() { return this.speed; }
    public void setSpeed(int speed) {
        this.speed = Math.min(speed, MAX_SPEED);
        String blocks = speed == 1 ? "bloco" : "blocos";

        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        String lore = DESCRIPTION.replace("%speed%", getSpeed()+" "+blocks);
        List<String> loreRes = new ArrayList<>(List.of(lore.split("\n")));
        loreRes.replaceAll(ColorUtils::colorize);
        meta.setLore(loreRes);
        getItemStack().setItemMeta(meta);
    }
    public ItemStack getItemStack() { return this.is; }
}