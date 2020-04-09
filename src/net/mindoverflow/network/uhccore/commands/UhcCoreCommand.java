package net.mindoverflow.network.uhccore.commands;

import net.mindoverflow.network.uhccore.UhcCore;
import net.mindoverflow.network.uhccore.commands.uhccommands.*;
import net.mindoverflow.network.uhccore.utils.Debugger;
import net.mindoverflow.network.uhccore.utils.MessageUtils;
import net.mindoverflow.network.uhccore.utils.LocalizedMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class UhcCoreCommand implements CommandExecutor
{

    // Initialize the plugin variable so we can access all of the plugin's data.
    private UhcCore plugin;

    // Initialize the debugger so I can debug the plugin.
    private Debugger debugger = new Debugger(getClass().getName());

    // Constructor to actually give "plugin" a value.
    public UhcCoreCommand(UhcCore givenPlugin)
    {
        plugin = givenPlugin;
    }



    // Override the default command. Set the instructions for this particular command (registered in the main class).
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        // Log who is using the command.
        debugger.sendDebugMessage(Level.INFO, "Sender is instance of: " + commandSender.getClass().getName());

        // If the command comes from Console, give a warning.
        boolean senderIsConsole = (commandSender instanceof ConsoleCommandSender);
        if(senderIsConsole)
        {
            MessageUtils.sendLocalizedMessage(commandSender.getName(), LocalizedMessages.WARNING_CONSOLE_ACCESS);
            // Only uncomment the following line if the command should not be able to run this command.
        }

        // Check if there are any args.
        if(args.length == 0 || args[0].equalsIgnoreCase("help"))
        {
            HelpCommand.infoCommand(commandSender, plugin);
        }
        // Check if there is a single argument after the command itself.
        else if (args.length == 1)
        {
            // Check if the args are "reload" and in case, do it.
            if(args[0].equalsIgnoreCase("reload"))
            {
                ReloadCommand.reloadCommand(commandSender);
            }
            else if(args[0].equalsIgnoreCase("setspawn"))
            {
                if(!(commandSender instanceof Player))
                {
                    MessageUtils.sendLocalizedMessage(commandSender.getName(), LocalizedMessages.ERROR_CONSOLE_ACCESS_BLOCKED);
                }
                else
                {
                    SetSpawnCommand.setSpawn(commandSender);
                }
            }
            else if(args[0].equalsIgnoreCase("setfirework"))
            {
                if(!(commandSender instanceof Player))
                {
                    MessageUtils.sendLocalizedMessage(commandSender.getName(), LocalizedMessages.ERROR_CONSOLE_ACCESS_BLOCKED);
                }
                else
                {
                    SetFireworkCommand.setFireworkCommand(commandSender);
                }
            }
            else if(args[0].equalsIgnoreCase("list"))
            {
                ListCommand.listCommand(commandSender);
            }
            else if(args[0].equalsIgnoreCase("start"))
            {
                StartUhcCommand.startUhcCommand(commandSender, plugin);
            }

            // TODO: PERMISSIONS! CONFIG!

            // Check if the args are "textcomponent" and run that command.
            /*else if (args[0].equalsIgnoreCase("textcomponent"))
            {
                // We do not want the console to receive the TextComponent, so we're gonna disable it.
                if(senderIsConsole)
                {
                    MessageUtils.sendLocalizedMessage(commandSender.getName(), LocalizedMessages.ERROR_CONSOLE_ACCESS_BLOCKED);
                    return true;
                }
                // Everyone else will be able to run the TextComponent message.
                TextComponentCommand.textComponentCommand(commandSender);
            }*/
        }
        return true;
    }
}
