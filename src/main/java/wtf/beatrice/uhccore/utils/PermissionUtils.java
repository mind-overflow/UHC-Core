package wtf.beatrice.uhccore.utils;

import wtf.beatrice.uhccore.UhcCore;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class PermissionUtils
{

    // Initialize the Debugger instance.
    private static Debugger debugger = new Debugger(PermissionUtils.class.getName());


    private static UhcCore plugin;
    public PermissionUtils(UhcCore givenPlugin)  { plugin = givenPlugin;  }

    // Method to get the permission string from the Permissions enum.
    public static boolean playerHasPermission(String username, Permission permission)
    {
        debugger.sendDebugMessage(Level.INFO, "Permission: " + permission.permission + "; Player name is: " + username);
        Player user = plugin.getServer().getPlayer(username);
        {
            if (user != null && user.hasPermission(permission.permission))
            {
                return true;
            }
        }
        return false;
    }
}
