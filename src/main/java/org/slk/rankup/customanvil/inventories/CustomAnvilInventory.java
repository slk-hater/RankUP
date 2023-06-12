package org.slk.rankup.customanvil.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.slk.rankup.utils.ItemStackBuilder;

import java.util.ArrayList;
import java.util.List;

public class CustomAnvilInventory {
    public static int SIZE = 9*5;
    public static String NAME = "Forja";
    public static List<Integer> KEEP_SLOTS = new List<>(List.of(19, 22));
    public static List<Integer> EMPTY_SLOTS = new List<>(List.of(24));
    public static ItemStack FILL_ITEMSTACK = ItemStackBuilder.build(Material.GRAY_STAINED_GLASS_PANE, 1, "", "");
    static Inventory inv = Bukkit.createInventory(null, SIZE, NAME);
    static{
        for(int i=0; i<SIZE; i++) {
            if (!KEEP_SLOTS.contains(i) && !EMPTY_SLOTS.contains(i)) {
                inv.setItem(i, FILL_ITEMSTACK);
            }
        }

        //inv.setItem(3, ItemStackBuilder.build(Material.PAPER, 1, "&eLimpar encantamentos", "&8Clica para limpar os encantamentos do item"));
        //inv.setItem(4, ItemStackBuilder.build(Material.NAME_TAG, 1, "&eAlterar nome", "&8Clica para alterar o nome do item"));
        //inv.setItem(5, ItemStackBuilder.build(Material.TNT_MINECART, 1, "&eDestruir", "&8Clica para destruir o item"));
        //inv.setItem(40, ItemStackBuilder.build(Material.GREEN_CONCRETE, 1, "&aForjar", ""));
    }

    public static Inventory cloneInventory() {
        Inventory newInv = Bukkit.createInventory(null, SIZE, NAME);
        newInv.setContents(inv.getContents());
        return newInv;
    }
    public static Inventory getInventory() { return inv; }
}