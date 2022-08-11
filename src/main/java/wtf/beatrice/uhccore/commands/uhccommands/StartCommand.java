package wtf.beatrice.uhccore.commands.uhccommands;

import wtf.beatrice.uhccore.UhcCore;
import wtf.beatrice.uhccore.utils.Cache;
import wtf.beatrice.uhccore.utils.Debugger;
import wtf.beatrice.uhccore.utils.math.NumberUtils;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Level;


public class StartCommand {

    private static Debugger debugger = new Debugger(StartCommand.class.getName());

    private static int loadDelay = 10;

    public static void startUhcCommand(CommandSender sender, UhcCore plugin)
    {

        HashMap<Integer, Location> spawnPerTeam = new HashMap<>();

        World spawnWorld = plugin.getServer().getWorld(Cache.uhcWorlds.get(0));

        int borderX = Cache.borderX;
        int borderZ = Cache.borderZ;
        int borderSize = Cache.borderSize;
        int range = borderSize / 2;

        Location borderCenter = new Location(spawnWorld, borderX, 64, borderZ);


        for(String playerName : Cache.playerTeam.keySet())
        {
            Player player = plugin.getServer().getPlayer(playerName);
            player.sendTitle("La §dUHC§r inizierà a breve!", "ricerca degli spawnpoint...", 20, 70, 10);
        }


        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, ()->
        {
            for(int i = 0; i < Cache.totalTeams; i++)
            {

                double x = NumberUtils.getRandomNumberInRange(borderX - range + 1, borderX + range - 1) + 0.5;
                double z = NumberUtils.getRandomNumberInRange(borderZ - range + 1, borderZ + range - 1) + 0.5;
                int y = spawnWorld.getHighestBlockYAt((int) x, (int) z); // todo: this method is shit, use the one i already implemented in Factions...

                Location loc = new Location(spawnWorld, x, y + 1, z);

                Location standingBlockLoc = new Location(spawnWorld, x, y, z);
                Location upperBlockLoc = new Location(spawnWorld, x, y + 2, z);
                Material standingBlockType = standingBlockLoc.getBlock().getType();
                Material upperBlockType = upperBlockLoc.getBlock().getType();

                spawnPerTeam.put(i, loc);
                debugger.sendDebugMessage(Level.INFO, "found block! " + loc);
                if(standingBlockType.equals(Material.WATER) || standingBlockType.equals(Material.LAVA) || standingBlockType.equals(Material.AIR)
                || !upperBlockType.equals(Material.AIR) || !loc.getBlock().getType().equals(Material.AIR))
                {
                    debugger.sendDebugMessage(Level.WARNING, "block is not valid: " + standingBlockType + ", " + loc.getBlock().getType() + ", " + upperBlockType);
                    i--;
                }
            }
            plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, ()->
            {
                Cache.allowMovement = false;
            }, 20L);

            plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, ()->
            {
                Cache.allowMovement = true;
            }, loadDelay * 20L);


            for(String playerName : Cache.playerTeam.keySet())
            {
                int teamNumber = Cache.playerTeam.get(playerName);

                Player player = plugin.getServer().getPlayer(playerName);
                Location hisTeamLoc = spawnPerTeam.get(teamNumber);



                plugin.getServer().getScheduler().runTask(plugin, () ->
                {
                    player.teleport(hisTeamLoc);
                    player.getInventory().clear();
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    player.sendTitle("Caricamento...", "§7attendi un attimo", 20, 70, 10);



                    plugin.getServer().getScheduler().runTaskLater(plugin, ()->
                    {
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        player.sendTitle("La §dUHC§r è iniziata!", "buona fortuna!", 20, 70, 10);
                        player.setHealth(20);
                        player.setFoodLevel(22);
                        player.setGameMode(GameMode.SURVIVAL);
                    }, loadDelay * 20L);
                });
            }

            plugin.getServer().getScheduler().runTask(plugin, ()->
            {
                spawnWorld.setTime(0L);
                spawnWorld.setGameRule(GameRule.NATURAL_REGENERATION, false);
                spawnWorld.setDifficulty(Difficulty.NORMAL);
                spawnWorld.getWorldBorder().setCenter(borderCenter);
                spawnWorld.getWorldBorder().setSize(borderSize);
                plugin.getLogger().log(Level.INFO,"UHC Started!");
            });
        });
    }

}
