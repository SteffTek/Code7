package de.Code7.Shops;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import de.Code7.Items.ItemsPublic;

public class FoodShop implements Listener{
	@EventHandler
	public void onFoodShop(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof Villager){
			 e.setCancelled(true);
			 Villager v = (Villager) e.getRightClicked();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(true);
			 } else 
			 if (vName.equals("FoodShop")) {
				 
				 //Initialisierung des FoodShops
					Inventory FoodShop = p.getPlayer().getServer().createInventory(null, 9, ChatColor.GREEN + "§lFoodShop");
					
					FoodShop.setItem(0, ItemsPublic.CreateItem("Cooked Beef","Price: 2, Amount 4", Material.COOKED_BEEF, false, 4));
					FoodShop.setItem(1, ItemsPublic.CreateItem("Chickenwings","Price: 3, Amount 4", Material.COOKED_CHICKEN, false, 4));
					FoodShop.setItem(2, ItemsPublic.CreateItem("Cake","Price: 10, Amount 1", Material.CAKE, false, 1));
					FoodShop.setItem(3, ItemsPublic.CreateItem("Bread","Price: 1, Amount: 5", Material.BREAD, false, 5));
			        p.getPlayer().openInventory(FoodShop);
				 
			 }
		}
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e){
		 try
		    {
		      Player p = (Player)e.getWhoClicked();
		      if (e.getInventory().getName().equals(ChatColor.GREEN + "§lFoodShop"))
		      {if(e.getSlot() == e.getRawSlot()){
		        e.setCancelled(true);
		        if((p.getItemInHand().getType() == Material.PAPER)){
		        if (e.getCurrentItem().getType() == Material.COOKED_BEEF)
		        {
		        	if(p.getItemInHand().getAmount() >= 2){
		        		if(p.getItemInHand().getAmount() == 2){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 2);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Cooked Beef","", Material.COOKED_BEEF, false, 4));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.COOKED_CHICKEN)
		        {
		        	if(p.getItemInHand().getAmount() >= 3){
		        		if(p.getItemInHand().getAmount() == 3){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 3);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Chickenwings","", Material.COOKED_CHICKEN, false, 4));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.CAKE)
		        {
		        	if(p.getItemInHand().getAmount() >= 10){
		        		if(p.getItemInHand().getAmount() == 10){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 10);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Cake","", Material.CAKE, false, 1));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.BREAD)
		        {
		        	if(p.getItemInHand().getAmount() >= 1){
		        		if(p.getItemInHand().getAmount() == 1){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Bread","", Material.BREAD, false, 5));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        } else {
		        	e.setCancelled(true);
		        }
		      }
		    }
		      }
		    }
		 catch (NullPointerException localNullPointerException) {}
	}
	
	@EventHandler
	public void damageFood(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else 
			 if (vName.equals("FoodShop")) {
				 e.setCancelled(true);
			 }
		}
	}
	@EventHandler
	public void damageFoodTwo(EntityDamageEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else 
			 if (vName.equals("FoodShop")) {
				 e.setCancelled(true);
			 }
		}
	}

}
