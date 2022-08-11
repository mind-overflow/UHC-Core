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
        debugger.sendDebugMessage(Level.INFO, "Reloading configuration files...");
        commandSender.sendMessage("Reloading configuration files...");
        FileUtils.checkFiles();
        FileUtils.reloadYamls();
        commandSender.sendMessage("Reloaded configuration files!");
        debugger.sendDebugMessage(Level.INFO, "Reloaded configuration files!");
    }
}
