package org.slk.rankup.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class GradientChatColor {

    public static void sendGradientMessage(Player player, String message) {
        TextComponent textComponent = new TextComponent(message);

        // Define the colors for the gradient
        ChatColor[] colors = {ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.BLUE};

        // Calculate the interval between color transitions
        float interval = (float) message.length() / colors.length;

        // Apply the gradient effect
        for (int i = 0; i < colors.length; i++) {
            int start = (int) (i * interval);
            int end = (int) ((i + 1) * interval);

            // Set the color for the text within the range
            for (int j = start; j < end; j++) {
                textComponent.setExtra(new TextComponent(String.valueOf(message.charAt(j))).getExtra());
                textComponent.setExtra(new TextComponent(String.valueOf(message.charAt(j))).getExtra());
                textComponent.getExtra().get(j).setColor(colors[i]);
            }
        }

        // Send the gradient message to the player
        player.spigot().sendMessage(textComponent);
    }
}
