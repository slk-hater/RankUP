package org.slk.rankup.miners.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.utils.ItemStackBuilder;

public class DashboardMinerInventory {
    public static int SIZE = 9*3;
    public static String NAME = "Minerador - Painel de controlo";
    public static ItemStack FILL_ITEMSTACK = ItemStackBuilder.build(Material.GRAY_STAINED_GLASS_PANE, 1, " ", "");

    public static ItemStack ENABLED_ITEMSTACK = ItemStackBuilder.build(Material.RED_STAINED_GLASS_PANE, 1, "&c&lDESLIGAR", "&8Clica para desligar o minerador");
    public static ItemStack DISABLED_ITEMSTACK = ItemStackBuilder.build(Material.GREEN_STAINED_GLASS_PANE, 1, "&a&lLIGAR", "&8Clica para ligar o minerador");

    public static ItemStack UPGRADES_ITEMSTACK = ItemStackBuilder.build(Material.KNOWLEDGE_BOOK, 1, "&eAprimoramentos", "&8Clica para ir para a página de aprimoramentos");
    public static ItemStack FUEL_ITEMSTACK = ItemStackBuilder.build(Material.CHARCOAL, 1, "&eCombustível", "&8Clica para ir para a página de combustível");
    public static ItemStack RESOURCES_ITEMSTACK = ItemStackBuilder.build(Material.CHEST_MINECART, 1, "&eRecolher recursos", "&7Minérios (0/500)\n&7Gemas (0/100)\n\n&8Clica para recolher os recursos");
    public static ItemStack DESTRUCTION_ITEMSTACK = ItemStackBuilder.build(Material.BARRIER, 1, "&cRecolher minerador", "&8Clica para recolher o minerador");

    static Inventory inv = Bukkit.createInventory(null, SIZE, NAME);
    static{
        for(int i=0; i<SIZE; i++)
            inv.setItem(i, FILL_ITEMSTACK);
        //https://minecraft-heads.com/custom-heads/decoration/17952-spruce-crate
        //https://minecraft-heads.com/custom-heads/blocks/23062-coal-ore
        inv.setItem(0, DISABLED_ITEMSTACK);
        inv.setItem(10, UPGRADES_ITEMSTACK);
        inv.setItem(12, FUEL_ITEMSTACK);
        inv.setItem(14, RESOURCES_ITEMSTACK);
        inv.setItem(16, DESTRUCTION_ITEMSTACK);
    }

    public static Inventory cloneInventory() {
        Inventory newInv = Bukkit.createInventory(null, SIZE, NAME);
        newInv.setContents(inv.getContents());
        return newInv;
    }
    public static Inventory getInventory() { return inv; }
}