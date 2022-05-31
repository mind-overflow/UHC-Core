package wtf.beatrice.uhccore.utils;

import wtf.beatrice.uhccore.UhcCore;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class UhcUtils {

    // Function to check how many players a team has.
    // This function returns the total number of alive teams.
    public static void updatePlayersPerTeam()
    {
        // Integer to check how many teams are left alive.
        int playingTeams = 0;

        // Iterate through every existing team.
        for(int i = 0; i < Cache.totalTeams; i++)
        {
            // Int to store the players number for each team.
            int playersNumber = 0;

            // Iterate through every player and...
            for(String s : Cache.playerTeam.keySet())
            {
                //if his team is the current checked one...
                if(Cache.playerTeam.get(s) == i)
                {
                    //increase the playersNumber by 1.
                    playersNumber++;
                }
            }

            // Finally, put the team number and his player count in the playersPerTeam HashMap.
             Cache.playersPerTeam.put(i, playersNumber);

            // If there is at least 1 player in this team, then count this as an "alive team".
            if(playersNumber != 0) playingTeams++;
        }

        // Return the number of alive teams.
        Cache.playingTeams = playingTeams;
    }

    public static void giveTeamsSelectorItem(Player player)
    {

        player.getInventory().clear();
        player.getInventory().setItem(4, Cache.teamsItem);
    }

    public static void spawnFirework(Location location, long detonateDelay) {

        Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.setPower(100);
        fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

        firework.setFireworkMeta(fireworkMeta);

        UhcCore plugin = UhcCore.getInstance();
        plugin.getServer().getScheduler().runTaskLater(plugin, firework::detonate, detonateDelay);
    }

    public static void tpSpawnAndGiveItem(Player player)
    {

        player.teleport(Cache.spawn);

        // Clear the player's inventory and give hims the Teams selector item.
        UhcUtils.giveTeamsSelectorItem(player);
    }
}
