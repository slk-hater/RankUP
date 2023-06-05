package org.slk.rankup.nametags;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.slk.rankup.utils.ChatUtils;

import java.util.Objects;

public class TagManager {

    private static Team team;
    private static Scoreboard scoreboard;

    @SuppressWarnings("deprecation")
    public static void changePlayerName(Player player, String prefix, TeamAction action) {
        prefix = prefix.length() > 16 ? prefix.substring(0, 16) : prefix;

        scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();

        if (scoreboard.getTeam("TAG" + player.getName()) == null){
            scoreboard.registerNewTeam("TAG" + player.getName());
        }

        team = scoreboard.getTeam("TAG" + player.getName());
        team.setPrefix(ChatUtils.colorize(prefix));
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

        switch (action) {
            case CREATE:
                team.addPlayer(player);
                break;
            case UPDATE:
                team.unregister();
                scoreboard.registerNewTeam("TAG" + player.getName());
                team = scoreboard.getTeam("TAG" + player.getName());
                team.setPrefix(ChatUtils.colorize(prefix));
                team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
                team.addPlayer(player);
                break;
            case DESTROY:
                team.unregister();
                break;
        }
    }
}