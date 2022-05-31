package net.mindoverflow.network.uhccore.listeners;

import net.mindoverflow.network.uhccore.UhcCore;
import net.mindoverflow.network.uhccore.utils.Cache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    private UhcCore plugin;

    public PlayerChatListener(UhcCore givenPlugin)
    {
        plugin = givenPlugin;
    }


    // Event called whenever a player sends a chat message.
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {

        // Store the player's name, his display name, and the sent message.
        String playerName = event.getPlayer().getName();
        String displayName = event.getPlayer().getDisplayName();
        String message;

        // Check if the player is in a team.
        if(Cache.playerTeam.containsKey(playerName))
        {
            // Load the team number and the team name from that.
            int teamNumber = Cache.playerTeam.get(playerName);
            String teamName = Cache.teamNames.get(teamNumber);

            // Build the chat message.
            message = "§7[" + teamName + "§7] §f" + displayName + "§7: " + event.getMessage();
        } else // Else, if the player is not in any team...
        {
            // Just fomat the message.
            message = "§f" + displayName + "§7: " + event.getMessage();
        }

        // cancel the event (we want to send our own custom message)
        event.setCancelled(true);

        // and finally broadcast the message.
        plugin.getServer().broadcastMessage(message);
    }
}
