package org.slk.rankup.miners.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;

public class MinerDashboardInventory {
    public static int SIZE = 9*3;
    public static String NAME = "Minerador - Painel de controlo";
    public static List<Integer> EMPTY_SLOTS = new ArrayList<>(List.of(0));
    public static ItemStack FILL_ITEMSTACK = ItemStackBuilder.build(Material.GRAY_STAINED_GLASS_PANE, 1, " ", "");
    static Inventory inv = Bukkit.createInventory(null, SIZE, NAME);
    static{
        for(int i=0; i<SIZE; i++) {
            if (!EMPTY_SLOTS.contains(i)) {
                inv.setItem(i, FILL_ITEMSTACK);
            }
        }
        inv.setItem(0, ItemStackBuilder.build(Material.GREEN_STAINED_GLASS_PANE, 1, "&aLigar", "&8Clica para ligar o minerador"));
        inv.setItem(10, ItemStackBuilder.build(Material.NETHER_STAR, 1, "&eAprimoramentos", "&8Página de aprimoramentos"));
        inv.setItem(12, ItemStackBuilder.build(Material.COAL, 1, "&eCombustível", "&8Página de manuseio de combustível"));
        inv.setItem(14, ItemStackBuilder.build(Material.CHEST_MINECART, 1, "&eRecolher recursos", "&8Clica para recolher os recursos"));
        inv.setItem(16, ItemStackBuilder.build(Material.BARRIER, 1, "&cRecolher minerador", "&8Clica para recolher o minerador"));
    }

    public static Inventory cloneInventory() {
        Inventory newInv = Bukkit.createInventory(null, SIZE, NAME);
        newInv.setContents(inv.getContents());
        return newInv;
    }
    public static Inventory getInventory() { return inv; }
}