package wtf.beatrice.uhccore.listeners;

import wtf.beatrice.uhccore.UhcCore;
import wtf.beatrice.uhccore.utils.Cache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener
{

    private UhcCore plugin;
    public PlayerMoveListener(UhcCore givenPlugin)
    {
        plugin = givenPlugin;
    }

    //Debugger debugger = new Debugger(getClass().getName());

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if(Cache.allowMovement) return;
        event.setCancelled(true);
    }
}
