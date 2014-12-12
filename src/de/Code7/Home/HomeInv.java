package de.Code7.Home;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import de.Code7.Items.ItemsPublic;

public class HomeInv implements Listener{
	public static void onHome(Player p){
		
						Inventory Home = p.getPlayer().getServer().createInventory(null, 9, ChatColor.GREEN + "§lHome-Manager");
						
						
						Home.setItem(0, ItemsPublic.CreateItem(ChatColor.RED + "Set Home","", Material.BED, false, 1));
						Home.setItem(4, ItemsPublic.CreateItem(ChatColor.RED + "Load Home","", Material.RED_ROSE, false, 1));
						Home.setItem(8, ItemsPublic.CreateItem(ChatColor.RED + "Delete Home","", Material.BEDROCK, false, 1));


				        p.getPlayer().openInventory(Home);
	}
	
	@EventHandler
	public void onHomeInv(InventoryClickEvent e){
	    try
	    {
	      Player p = (Player)e.getWhoClicked();
	      if (e.getInventory().getName().equals(ChatColor.GREEN + "§lHome-Manager"))
	      {if(e.getSlot() == e.getRawSlot()){
	        e.setCancelled(true);
	        if (e.getCurrentItem().equals(ItemsPublic.CreateItem(ChatColor.RED + "Set Home","", Material.BED, false, 1)))
	        {
	        	e.getView().close();
	        	HomeConfig.Home(p, "CreateHome");
	        }
	        else if (e.getCurrentItem().equals(ItemsPublic.CreateItem(ChatColor.RED + "Load Home","", Material.RED_ROSE, false, 1)))
	        {
	        	e.getView().close();
	        	HomeConfig.Home(p, "LoadHome");
	        }
	        else if (e.getCurrentItem().equals(ItemsPublic.CreateItem(ChatColor.RED + "Delete Home","", Material.BEDROCK, false, 1)))
	        {
	        	e.getView().close();
	        	HomeConfig.Home(p, "DeleteHome");
	        } else {
	        	e.setCancelled(true);
	        }
	      }
	    }
	    }
	    catch (NullPointerException localNullPointerException) {}
	  }
	
}
