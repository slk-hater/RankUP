package org.slk.rankup.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ChatUtils;

import java.util.List;
import java.util.Objects;

public class FlyCommand extends Command {
    //static List<World> ALLOWED_WORLDS = List.of(Bukkit.getWorld("Terrenos"));
    public FlyCommand(){
        super("fly");
        setLabel("fly");
        setDescription("Fly");
        setAliases(List.of("voar", "flight"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if(!(sender instanceof Player player)){
            sender.sendMessage(ChatUtils.error("Apenas jogadores podem utilizar este comando!"));
            return true;
        }
        if(!player.getGameMode().equals(GameMode.SURVIVAL)){
            player.sendMessage(ChatUtils.error("Comando apenas aplicavél a modo de jogador Sobrevivência"));
            return true;
        }
        if(!Rank.getRank(player).equals(Rank.getLastRank())){
            player.sendMessage(ChatUtils.error("Não tens permissão para usar este comando!"));
            return true;
        }
        /*if(!ALLOWED_WORLDS.contains(player.getWorld())){
            player.sendMessage(ChatUtils.error("Não podes usar este comando neste mundo!"));
            return true;
        }*/

        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_HARP, 0.3f, 1f);
        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
            player.setFlying(true);
        } else if (player.getAllowFlight()) {
            player.setAllowFlight(false);
        }
        if(player.isFlying()) player.sendMessage(ChatUtils.good("Voo ligado."));
        else player.sendMessage(ChatUtils.good("Voo desligado."));

        return true;
    }
}