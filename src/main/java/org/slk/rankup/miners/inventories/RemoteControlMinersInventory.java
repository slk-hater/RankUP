package org.slk.rankup.miners.inventories;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slk.rankup.miners.MinersManager;
import org.slk.rankup.miners.itemstacks.Miner;
import org.slk.rankup.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class RemoteControlMinersInventory {
    public static int SIZE;
    public static String NAME = "Mineradores - Controlo remoto";

    public static Inventory build(List<ArmorStand> miners){
        if(miners.size() <= 7) SIZE = 9*3;
        else if(miners.size() <= 7*2) SIZE = 9*4;
        else if(miners.size() <= 7*3) SIZE = 9*5;
        else if(miners.size() <= 7*4) SIZE = 9*6;

        YamlConfiguration config = MinersManager.getConfiguration();
        Inventory inventory = Bukkit.createInventory(null, SIZE, NAME);
        for(int i=0; i<miners.size(); i++){
            ArmorStand as = miners.get(i);

            String onOrOff;
            if(config.getBoolean("miners."+as.getLocation()+".enabled")) onOrOff = "&a&lLIGADO";
            else onOrOff = "&c&lDESLIGADO";
            String lore = "\n" + onOrOff +
                    "\n&7Velocidade &r" + config.getInt("miners."+as.getLocation()+".speed") +
                    "\n&7Combustível &r" + config.getInt("miners."+as.getLocation()+".fuel") +
                    "\n&7Recursos &r(" + config.getInt("miners."+as.getLocation()+".storage") + "/" + config.getInt("miners."+as.getLocation()+".maxStorage") + ")" +
                    "\n&7Coordenadas:\n &f" + as.getLocation();

            ItemStack skull = Miner.SKULL;
            ItemMeta meta = skull.getItemMeta();
            assert meta != null;
            meta.setDisplayName(ColorUtils.colorize("&eMinerador"));
            List<String> loreRes = new ArrayList<>(List.of(lore.split("\n")));
            loreRes.replaceAll(ColorUtils::colorize);
            meta.setLore(loreRes);
            skull.setItemMeta(meta);

            inventory.setItem(i, skull);
        }
        return inventory;
    }
}