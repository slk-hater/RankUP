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
    public enum MinerType {BASIC, ADVANCED}

    static ItemStack SKULL = ItemStackBuilder.getSkull(
            "http://textures.minecraft.net/texture/fc82742b2a17eebdeaad2b95e566a5f0250c3557429f6a39a79b6ab8bf8",
            UUID.fromString("da05b761-b174-4ef6-b5e3-de5342f41fc8"));
    static int BASE_SPEED = 1;
    static int MAX_SPEED = 150;

    MinerType minerType;
    int speed;
    final ItemStack is;

    public Miner() {
        this.is = SKULL;
        setType(MinerType.BASIC);
        setSpeed(getBaseSpeed());

        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        meta.setCustomModelData(CustomModelDataEnum.MINER.get());
    }

    public static int getBaseSpeed() { return BASE_SPEED; }
    public static int getMaxSpeed() { return MAX_SPEED; }

    public MinerType getType() { return this.minerType; }
    public void setType(MinerType minerType) {
        this.minerType = minerType;
        ItemMeta meta = is.getItemMeta();
        if (meta == null) return;
        switch (minerType) {
            case ADVANCED -> meta.setDisplayName(ChatColor.of("#f5f190") + "Minerador avançado");
            default -> meta.setDisplayName(ChatColor.of("#f5f190") + "Minerador básico");
        }
    }
    public int getSpeed() { return this.speed; }
    public void setSpeed(int speed) {
        this.speed = Math.min(speed, MAX_SPEED);
        ItemMeta meta = getItemStack().getItemMeta();
        if(meta == null) return;
        String blocks = speed == 1 ? "bloco" : "blocos";
        String lore = "&7Velocidade &f" + speed + " " + blocks + "/s\n&8Clica com o botão direito no chão para colocar";
        List<String> loreRes = new ArrayList<>(List.of(lore.split("\n")));
        loreRes.replaceAll(ColorUtils::colorize);
        meta.setLore(loreRes);
    }
    public ItemStack getItemStack() { return this.is; }
}