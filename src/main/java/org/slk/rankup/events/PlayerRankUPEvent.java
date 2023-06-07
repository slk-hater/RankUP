package org.slk.rankup.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.slk.rankup.ranks.Rank;

public class PlayerRankUPEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private boolean isCancelled;
    private final Player player;
    private final Rank rank;

    public PlayerRankUPEvent(Player player, Rank rank) {
        this.player = player;
        this.rank = rank;
    }

    @Override
    public HandlerList getHandlers() { return HANDLERS; }
    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean cancel) { isCancelled = cancel; }
    public Player getPlayer() { return player; }
    public Rank getRank() { return rank; }
}