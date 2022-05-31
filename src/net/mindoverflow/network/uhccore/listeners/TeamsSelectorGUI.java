package net.mindoverflow.network.uhccore.listeners;

import net.mindoverflow.network.uhccore.utils.Cache;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamsSelectorGUI implements InventoryHolder
{

    private final Inventory inv;

    public TeamsSelectorGUI()
    {
        inv = Bukkit.createInventory(this, 18, "Team");
    }

    public Inventory getInventory()
    {
        return inv;
    }

    public void initializeInv()
    {

        for(int i = 0; i < Cache.totalTeams; i++)
        {
            String teamName = Cache.teamNames.get(i);
            Material itemMat = Cache.teamItemsMaterials.get(i);
            inv.setItem(i, createItem(teamName, itemMat));
        }


        inv.setItem(17, Cache.quitTeamItem);

    }

    private ItemStack createItem(String name, Material material)
    {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        item.setItemMeta(im);

        return item;
    }

}
