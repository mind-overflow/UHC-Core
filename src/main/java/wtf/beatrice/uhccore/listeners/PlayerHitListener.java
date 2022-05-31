package wtf.beatrice.uhccore.listeners;

import wtf.beatrice.uhccore.utils.Cache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHitListener implements Listener
{

    // Event called when someone hits someone else.
    @EventHandler
    public void onPlayerHitPlayer(EntityDamageByEntityEvent event)
    {
        // if friendly fire is enabled, then skip everything that follows and let the hit happen.
        if(Cache.friendlyFire) return;

        // check if the damaged and damager entities are both players. If they're not, stop.
        if(!(event.getDamager() instanceof Player && event.getEntity() instanceof Player))
        {
            return;
        }

        // Load the damaged and damager's names.
        String damagedName = event.getEntity().getName();
        String damagerName = event.getDamager().getName();

        // Check if they're in a team. if they're not, stop.
        if(!(Cache.playerTeam.containsKey(damagedName) && Cache.playerTeam.containsKey(damagerName)))
        {
            return;
        }

        // check if they're both in the same team.
        if(Cache.playerTeam.get(damagedName).equals(Cache.playerTeam.get(damagerName)))
        {
            // cancel the event.
            event.setCancelled(true);
            event.getDamager().sendMessage("§cNon puoi colpire un tuo amico!");
        }
    }
}
