package org.slk.rankup.temphorse.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.slk.rankup.temphorse.HorsesManager;
import org.slk.rankup.utils.ChatUtils;

import java.util.List;
import java.util.Random;

public class HorseCommand extends Command {
    public HorseCommand(){
        super("horse");
        setAliases(List.of("mount", "montada", "cavalo"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage(ChatUtils.error("Apenas jogadores podem utilizar este comando!"));
            return true;
        }

        Random random = new Random();
        player.playSound(player, Sound.ENTITY_HORSE_AMBIENT, 0.3f, 1f);
        Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
        horse.setColor(Horse.Color.values()[random.nextInt(Horse.Color.values().length)]);
        horse.setStyle(Horse.Style.values()[random.nextInt(Horse.Style.values().length)]);
        horse.setCustomName("Cavalo de " + player.getName());
        horse.setCustomNameVisible(true);
        horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue()+0.1);
        horse.setAI(false);
        horse.setAdult();
        horse.setTamed(true);
        horse.setBreed(false);
        horse.setCanPickupItems(false);
        horse.setOwner(player);
        horse.getInventory().setArmor(new ItemStack(Material.LEATHER_HORSE_ARMOR));
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.addPassenger(player);
        HorsesManager.getMap().put(player, horse);
        return true;
    }
}