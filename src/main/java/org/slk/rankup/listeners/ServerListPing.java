package org.slk.rankup.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.slk.rankup.utils.ColorUtils;

public class ServerListPing implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event){
        event.setMotd(ColorUtils.colorize(
                "&a&lRANK UP\n&dPalelo banana"
        ));
    }
}