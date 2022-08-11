package wtf.beatrice.uhccore.commands.uhccommands;

import wtf.beatrice.uhccore.utils.Cache;
import wtf.beatrice.uhccore.utils.configuration.ConfigEntry;
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
        config.set(ConfigEntry.SPAWN_WORLD.path, playerLoc.getWorld().getName());
        config.set(ConfigEntry.SPAWN_X.path, playerLoc.getX());
        config.set(ConfigEntry.SPAWN_Y.path, playerLoc.getY());
        config.set(ConfigEntry.SPAWN_Z.path, playerLoc.getZ());
        config.set(ConfigEntry.SPAWN_YAW.path, playerLoc.getYaw());
        config.set(ConfigEntry.SPAWN_PITCH.path, playerLoc.getPitch());

        FileUtils.FileType.CONFIG_YAML.yaml = config;

        FileUtils.saveExistingYaml(FileUtils.FileType.CONFIG_YAML);
    }
}
