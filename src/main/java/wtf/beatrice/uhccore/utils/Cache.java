package wtf.beatrice.uhccore.utils;

import wtf.beatrice.uhccore.listeners.TeamsSelectorGUI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cache {

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

    // boolean to store whether the server has been set up for gameplay or not.
    // TODO: we haven't implemented any checks for this boolean in the loading phase.
    // we should implement it and use it eg. when people are joining the server, starting the game, etc...
    public static boolean isServerReady = false;

}
