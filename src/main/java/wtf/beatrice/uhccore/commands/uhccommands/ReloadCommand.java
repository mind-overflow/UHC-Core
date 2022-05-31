package wtf.beatrice.uhccore.commands.uhccommands;

import wtf.beatrice.uhccore.utils.Debugger;
import wtf.beatrice.uhccore.utils.configuration.FileUtils;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public class ReloadCommand
{
    private static Debugger debugger = new Debugger(ReloadCommand.class.getName());

    public static void reloadCommand(CommandSender commandSender)
    {
        debugger.sendDebugMessage(Level.INFO, "Reloading YAMLS...");
        commandSender.sendMessage("Reloading YAMLs...");
        FileUtils.checkFiles();
        FileUtils.reloadYamls();
        commandSender.sendMessage("Reloaded YAMLs!");
        debugger.sendDebugMessage(Level.INFO, "Reloaded YAMLs!");
    }
}
