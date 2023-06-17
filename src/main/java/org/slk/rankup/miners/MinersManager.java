package org.slk.rankup.miners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MinersManager {
    static File DATA_FILE = new File("data/miners", "data.yml");

    public static void setup(){
        if(!DATA_FILE.exists()){
            try {
                YamlConfiguration config = new YamlConfiguration();
                config.save(DATA_FILE);
            } catch(Exception e) { e.printStackTrace(); }
        }
    }
    public static File getDataFile() { return DATA_FILE; }
    public static YamlConfiguration getConfiguration(){
        if(!DATA_FILE.exists()) setup();
        return YamlConfiguration.loadConfiguration(DATA_FILE);
    }
}