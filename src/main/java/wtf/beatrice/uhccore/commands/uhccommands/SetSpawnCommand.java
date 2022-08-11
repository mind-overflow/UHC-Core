package wtf.beatrice.uhccore.commands.uhccommands;

import wtf.beatrice.uhccore.utils.Cache;
import wtf.beatrice.uhccore.utils.MessageUtils;
import wtf.beatrice.uhccore.utils.configuration.ConfigEntry;
import wtf.beatrice.uhccore.utils.configuration.FileUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import wtf.beatrice.uhccore.utils.configuration.LocalizedMessage;

public class SetSpawnCommand
{

    public static void setSpawn(CommandSender sender)
    {
        Player player = (Player) sender;
        Location playerLoc = player.getLocation();

        String worldName = playerLoc.getWorld().getName();

        if(!Cache.lobbyWorlds.contains(worldName))
        {
            MessageUtils.sendLocalizedMessage(sender, LocalizedMessage.ERROR_NOT_IN_LOBBY_WORLD);
            return;
        }

        Cache.spawn = playerLoc;
        YamlConfiguration config = FileUtils.FileType.CONFIG_YAML.yaml;
        config.set(ConfigEntry.SPAWN_WORLD.path, worldName);
        config.set(ConfigEntry.SPAWN_X.path, playerLoc.getX());
        config.set(ConfigEntry.SPAWN_Y.path, playerLoc.getY());
        config.set(ConfigEntry.SPAWN_Z.path, playerLoc.getZ());
        config.set(ConfigEntry.SPAWN_YAW.path, playerLoc.getYaw());
        config.set(ConfigEntry.SPAWN_PITCH.path, playerLoc.getPitch());

        FileUtils.FileType.CONFIG_YAML.yaml = config;

        FileUtils.saveExistingYaml(FileUtils.FileType.CONFIG_YAML);
    }
}
