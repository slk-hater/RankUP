package org.slk.rankup.ranks.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerPrestigeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    public static HandlerList getHandlerList() { return HANDLERS; }

    private boolean isCancelled;
    private final Player player;
    private int prestige;

    public PlayerPrestigeEvent(Player player, int prestige) {
        this.player = player;
        this.prestige = prestige;
    }

    @Override
    public HandlerList getHandlers() { return HANDLERS; }
    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean cancel) { isCancelled = cancel; }
    public Player getPlayer() { return player; }
    public int getPrestige() { return prestige; }
}