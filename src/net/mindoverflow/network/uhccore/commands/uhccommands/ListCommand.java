package net.mindoverflow.network.uhccore.commands.uhccommands;

import net.mindoverflow.network.uhccore.utils.Cache;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCommand
{
    public static void listCommand(CommandSender commandSender)
    {

        HashMap<Integer, ArrayList<String>>playersPerTeam = new HashMap<>();
        for(int i = 0; i < Cache.totalTeams; i++)
        {
            ArrayList<String>playersInThisTeam = new ArrayList<>();
            for(String playerName : Cache.playerTeam.keySet())
            {
                if(Cache.playerTeam.get(playerName).equals(i))
                {
                    playersInThisTeam.add(playerName);
                }
            }
            playersPerTeam.put(i, playersInThisTeam);
        }

        commandSender.sendMessage("§6-------[ UHC: Lista Team ]-------");
        for(Integer i : playersPerTeam.keySet())
        {
            String teamName = Cache.teamNames.get(i);
            int playersInThisTeam = playersPerTeam.get(i).size();
            ArrayList<String>playersNames = playersPerTeam.get(i);
            String playersList = playersNames.toString().replace("[", "").replace("]", "").replace(",", "§7,§r");
            commandSender.sendMessage("" + teamName + " §7(§f" + playersInThisTeam + "§7): §f" + playersList);
        }

        /*// Check if player has permissions to see this message.
        if(PermissionUtils.playerHasPermission(commandSender.getName(), Permissions.TEXTCOMPONENT_COMMAND))
        {
            // Create the TextComponent and give it an initial text.
            TextComponent mainComponent = new TextComponent("Hello! ");
            // Set the text color for the mainComponent.
            mainComponent.setColor(ChatColor.GRAY);
            // Create another TextComponent with its own text.
            TextComponent subComponent = new TextComponent ("This is clickable!");
            // Set the color for the subComponent.
            subComponent.setColor(ChatColor.AQUA);
            /// Give the subComponent an HoverEvent that displays a text when the mouse is hovering the TextComponent.
            subComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here").create()));
            // Give the subComponent a ClickEvent that takes action when the TextComponent is clicked.
            subComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://google.com/"));
            // Add the subComponent as a second part to the mainComponent. They will simply appear one after the other in a single line.
            mainComponent.addExtra(subComponent);
            // Add another final text after the subComponent, inheriting the mainComponent's characteristics (ChatColor, HoverEvent, ClickEvent...)
            mainComponent.addExtra(" <- click");
            // Pass the Bungeecord message as a Spigot message and send it to the user (probably via packets?)
            commandSender.spigot().sendMessage(mainComponent);
        }*/
    }
}
