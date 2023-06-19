package org.slk.rankup.miners.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.slk.rankup.utils.ItemStackBuilder;

public class RemoteControlNoMinersInventory {
    public static int SIZE = 9*3;
    public static String NAME = "Mineradores - Controlo remoto";
    static Inventory inv = Bukkit.createInventory(null, SIZE, NAME);
    static{
        inv.setItem(13, ItemStackBuilder.build(Material.COBWEB, 1, "&cSem mineradores", "&8Nenhum minerador foi encontrado"));
    }

    public static Inventory cloneInventory() {
        Inventory newInv = Bukkit.createInventory(null, SIZE, NAME);
        newInv.setContents(inv.getContents());
        return newInv;
    }
    public static Inventory getInventory() { return inv; }
}