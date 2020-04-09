package net.mindoverflow.network.uhccore.commands.uhccommands;

import net.mindoverflow.network.uhccore.utils.CommonValues;
import net.mindoverflow.network.uhccore.utils.ConfigEntries;
import net.mindoverflow.network.uhccore.utils.Debugger;
import net.mindoverflow.network.uhccore.utils.FileUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SetFireworkCommand
{

    private static Debugger debugger = new Debugger(SetFireworkCommand.class.getName());

    public static void setFireworkCommand(CommandSender sender)
    {
        Player player = (Player) sender;
        Location fireworkLoc = player.getLocation();

        YamlConfiguration config = FileUtils.FileType.CONFIG_YAML.yaml;

        int listPos = CommonValues.fireworksLocations.size() + 1;

        if(CommonValues.fireworksLocations.get(0).getWorld() == null)
        {
            debugger.sendDebugMessage(Level.SEVERE, "WORLD IS NULL!");
            listPos = 1;
            CommonValues.fireworksLocations.set(0, fireworkLoc);
        }
        else
        {
            CommonValues.fireworksLocations.add(fireworkLoc);
        }

        String currentPath = ConfigEntries.FIREWORK_POS.path + "." + listPos;

        config.set(currentPath + ".x", fireworkLoc.getX());
        config.set(currentPath + ".y", fireworkLoc.getY());
        config.set(currentPath + ".z", fireworkLoc.getZ());
        config.set(currentPath + ".world", fireworkLoc.getWorld().getName());

        FileUtils.FileType.CONFIG_YAML.yaml = config;
        FileUtils.saveExistingYaml(FileUtils.FileType.CONFIG_YAML);
    }
}
