package org.slk.rankup.nametags;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ColorUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TagManager {
    public void updateTag(Player player){
        for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
            if(onlinePlayer == player) continue;
            //REMOVES THE PLAYER
            ((CraftPlayer)onlinePlayer).getHandle().b.sendPacket(new ClientboundPlayerInfoUpdatePacket(EnumPlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer)player).getHandle()));
            //CHANGES THE PLAYER'S GAME PROFILE
            GameProfile gp = ((CraftPlayer)player).getProfile();
            try {
                Field nameField = GameProfile.class.getDeclaredField("name");
                nameField.setAccessible(true);

                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(nameField, nameField.getModifiers() & ~Modifier.FINAL);

                Rank rank = Rank.getRank(player);
                nameField.set(gp, ColorUtils.colorize(rank.getPrefix() + " " + rank.getColor() + player.getName()));
            } catch (IllegalAccessException | NoSuchFieldException ex) {
                throw new IllegalStateException(ex);
            }
            //ADDS THE PLAYER
            ((CraftPlayer)onlinePlayer).getHandle().b.sendPacket(new ClientboundPlayerInfoUpdatePacket(EnumPlayerInfoAction.ADD_PLAYER, ((CraftPlayer)player).getHandle()));
            ((CraftPlayer)onlinePlayer).getHandle().b.sendPacket(new PacketPlayOutEntityDestroy(player.getEntityId()));
            ((CraftPlayer)onlinePlayer).getHandle().b.sendPacket(new PacketPlayOutNamedEntitySpawn(((CraftPlayer)player).getHandle()));
        }
    }
}