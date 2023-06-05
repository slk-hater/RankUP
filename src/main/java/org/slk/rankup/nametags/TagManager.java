package org.slk.rankup.nametags;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.slk.rankup.utils.ChatUtils;

public class TagManager {

    private static Team team;
    private static Scoreboard scoreboard;

    @SuppressWarnings("deprecation")
    public static void changePlayerName(Player player, String prefix, TeamAction action) {
        prefix = prefix.length() > 16 ? prefix.substring(0, 16) : prefix;

        scoreboard = player.getScoreboard();

        if (scoreboard.getTeam("LTAGS" + player.getName()) ==  null){
            scoreboard.registerNewTeam("LTAGS" + player.getName());
        }

        team = scoreboard.getTeam("LTAGS" + player.getName());
        team.setPrefix(ChatUtils.colorize(prefix));
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

        switch (action) {
            case CREATE:
                team.addPlayer(player);
                break;
            case UPDATE:
                team.unregister();
                scoreboard.registerNewTeam("LTAGS" + player.getName());
                team = scoreboard.getTeam("LTAGS" + player.getName());
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