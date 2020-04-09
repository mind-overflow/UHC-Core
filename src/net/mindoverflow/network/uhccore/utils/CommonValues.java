package net.mindoverflow.network.uhccore.utils;

import net.mindoverflow.network.uhccore.UhcCore;
import net.mindoverflow.network.uhccore.listeners.TeamsSelectorGUI;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonValues {

    // Values to save the uhc and lobby world names.
    public static List<String> uhcWorlds = new ArrayList<>(), lobbyWorlds = new ArrayList<>();

    // HashMap <TeamNumber, teamPlayers> containing the number of players teamPlayers for every team teamNumber.
    public static HashMap<Integer, Integer> playersPerTeam = new HashMap<>();

    // Integer to store how many teams are alive and playing.
    public static int playingTeams;

    // Boolean to know if friendly fire is enabled, and to know if movement is allowed.
    public static boolean friendlyFire, allowMovement = true;

    // ItemStacks to store the "team selector" and "quit team" items + data.
    public static ItemStack teamsItem, quitTeamItem;

    // Int to know how many teams are enabled.
    public static int totalTeams;

    // Instance of the Teams Selector GUI, so we can access it from anywhere.
    public static TeamsSelectorGUI teamsSelectorGUI;

    // ArrayList with all teams' names, ordered.
    public static List<String> teamNames = new ArrayList<>();

    // ArrayList with all teams' representative items, ordered.
    public static List<Material> teamItemsMaterials = new ArrayList<>();

    // HashMap to know in which team number is a certain player
    // <PlayerName, TeamNumber>
    // Team number is the teamNames[n] number, starting from 0.
    public static HashMap<String, Integer> playerTeam = new HashMap<>();

    // Location to store the UHC spawn/win location.
    public static Location spawn;

    public static List<Location>fireworksLocations = new ArrayList<>();


    public static int borderX, borderZ, borderSize;

    // Function to check how many players a team has.
    // This function returns the total number of alive teams.
    public static void updatePlayersPerTeam()
    {
        // Integer to check how many teams are left alive.
        int playingTeams = 0;

        // Iterate through every existing team.
        for(int i = 0; i < totalTeams; i++)
        {
            // Int to store the players number for each team.
            int playersNumber = 0;

            // Iterate through every player and...
            for(String s : playerTeam.keySet())
            {
                //if his team is the current checked one...
                if(playerTeam.get(s) == i)
                {
                    //increase the playersNumber by 1.
                    playersNumber++;
                }
            }

            // Finally, put the team number and his player count in the playersPerTeam HashMap.
            playersPerTeam.put(i, playersNumber);

            // If there is at least 1 player in this team, then count this as an "alive team".
            if(playersNumber != 0) playingTeams++;
        }

        // Return the number of alive teams.
        CommonValues.playingTeams = playingTeams;
    }

    public static void giveTeamsSelectorItem(Player player)
    {

        player.getInventory().clear();
        player.getInventory().setItem(4, CommonValues.teamsItem);
    }

    public static void spawnFirework(UhcCore plugin, Location location, long detonateDelay) {

        Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        fireworkMeta.setPower(100);
        fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

        firework.setFireworkMeta(fireworkMeta);
        plugin.getServer().getScheduler().runTaskLater(plugin, firework::detonate, detonateDelay);
    }

    public static void tpSpawnAndGiveItem(Player player)
    {

        player.teleport(spawn);

        // Clear the player's inventory and give hims the Teams selector item.
        giveTeamsSelectorItem(player);
    }
}
