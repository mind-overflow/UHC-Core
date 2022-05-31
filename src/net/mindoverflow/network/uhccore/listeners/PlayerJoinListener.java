package net.mindoverflow.network.uhccore.listeners;

import net.mindoverflow.network.uhccore.utils.Cache;
import net.mindoverflow.network.uhccore.utils.Debugger;
import net.mindoverflow.network.uhccore.utils.UhcUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener
{
    // Instantiate a Debugger for this class.
    private Debugger debugger = new Debugger(getClass().getName());



    // Call EventHandler and start listening to joining players.
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        // Initialize needed variables for performance improvements and to avoid continuous method calls.
        Player player = e.getPlayer();


        if(!(Cache.playerTeam.containsKey(player.getName())))
        {
            UhcUtils.tpSpawnAndGiveItem(player);
        }


    }
}
