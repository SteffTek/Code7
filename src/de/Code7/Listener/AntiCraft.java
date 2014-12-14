package de.Code7.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class AntiCraft implements Listener{
	@EventHandler
    public void craftItem(PrepareItemCraftEvent e) {
        Material itemType = e.getRecipe().getResult().getType();
        if(itemType==Material.TNT || itemType==Material.EXPLOSIVE_MINECART || itemType==Material.ENDER_CHEST) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
            for(HumanEntity he:e.getViewers()) {
                if(he instanceof Player) {
                    ((Player)he).sendMessage(ChatColor.RED+"You cannot craft this!");
                }
            }
        }
    }
}
