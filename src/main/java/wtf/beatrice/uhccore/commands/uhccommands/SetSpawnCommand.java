package wtf.beatrice.uhccore.commands.uhccommands;

import wtf.beatrice.uhccore.utils.Cache;
import wtf.beatrice.uhccore.utils.configuration.ConfigEntries;
import wtf.beatrice.uhccore.utils.configuration.FileUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawnCommand
{

    public static void setSpawn(CommandSender sender)
    {
        Player player = (Player) sender;
        Location playerLoc = player.getLocation();

        Cache.spawn = playerLoc;
        YamlConfiguration config = FileUtils.FileType.CONFIG_YAML.yaml;
        config.set(ConfigEntries.SPAWN_WORLD.path, playerLoc.getWorld().getName());
        config.set(ConfigEntries.SPAWN_X.path, playerLoc.getX());
        config.set(ConfigEntries.SPAWN_Y.path, playerLoc.getY());
        config.set(ConfigEntries.SPAWN_Z.path, playerLoc.getZ());
        config.set(ConfigEntries.SPAWN_YAW.path, playerLoc.getYaw());
        config.set(ConfigEntries.SPAWN_PITCH.path, playerLoc.getPitch());

        FileUtils.FileType.CONFIG_YAML.yaml = config;

        FileUtils.saveExistingYaml(FileUtils.FileType.CONFIG_YAML);
    }
}
