package org.slk.rankup.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.slk.rankup.ranks.Rank;

public class PlayerRankUPEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    public static HandlerList getHandlerList() { return HANDLER_LIST; }

    private boolean cancelled;
    private final Player player;
    private final Rank rank;

    public PlayerRankUPEvent(Player player, Rank rank) {
        this.player = player;
        this.rank = rank;
    }

    @Override
    public HandlerList getHandlers() { return HANDLER_LIST; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancel) { cancelled = cancel; }
    public Player getPlayer() { return player; }
    public Rank getRank() { return rank; }
    public double getPrice() { return rank.getPrice(); }
}