package org.slk.rankup.nametags;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.*;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.slk.rankup.Core;
import org.slk.rankup.ranks.Rank;
import org.slk.rankup.utils.ColorUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class TagManager {
    private static final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    public static void updateTag(Player player){
        /*for(Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()){
            if(onlinePlayer == player) continue;
            //REMOVES THE PLAYER
            //((CraftPlayer)onlinePlayer).getHandle().b.sendPacket(new ClientboundPlayerInfoUpdatePacket(EnumWrappers.PlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer)player).getHandle()));
            EnumWrappers.PlayerInfoAction action = EnumWrappers.PlayerInfoAction.REMOVE_PLAYER; // or REMOVE_PLAYER
            PacketContainer packetPlayOutPlayerInfo = getPacketPlayerInfo(player, action);
            ProtocolLibrary.getProtocolManager().sendServerPacket(onlinePlayer, packetPlayOutPlayerInfo);
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
        }*/
        Rank rank = Rank.getRank(player);
        /*PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.PLAYER_INFO);
        packet.getPlayerInfoAction().write(0, EnumWrappers.PlayerInfoAction.UPDATE_DISPLAY_NAME);
        List<WrappedWatchableObject> dataWatcher = Collections.singletonList(new WrappedWatchableObject(
                new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true)),
                WrappedChatComponent.fromText(ColorUtils.colorize(rank.getPrefix() + " " + rank.getColor() + player.getName()))
        ));
        packet.getWatchableCollectionModifier().write(0, dataWatcher);

        protocolManager.sendServerPacket(player, packet);*/
    }
}