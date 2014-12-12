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

public class ToolStore implements Listener{
	
	@EventHandler
	public void onToolStore(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof Villager){
			 e.setCancelled(true);
			 Villager v = (Villager) e.getRightClicked();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(true);
			 } else 
			 if (vName.equals("ToolStore")) {
				 
				 //Initialisierung des ToolStores
				 	Inventory ToolStore = p.getPlayer().getServer().createInventory(null, 9, ChatColor.GREEN + "§lToolStore");
					
					ToolStore.setItem(0, ItemsPublic.CreateItem("Pickaxe","Price: 6", Material.IRON_PICKAXE, false, 1));
					ToolStore.setItem(1, ItemsPublic.CreateItem("Axe","Price: 5", Material.IRON_AXE, false, 1));
					ToolStore.setItem(2, ItemsPublic.CreateItem("Spade","Price: 6", Material.IRON_SPADE, false, 1));
					ToolStore.setItem(3, ItemsPublic.CreateItem("Sword","Price: 7", Material.IRON_SWORD, false, 1));
					ToolStore.setItem(5, ItemsPublic.CreateItem("TNT","Price: 64", Material.TNT, false, 1));
					ToolStore.setItem(6, ItemsPublic.CreateItem("iBirne", "Price: 50", Material.ENCHANTED_BOOK, false, 1));
			        p.getPlayer().openInventory(ToolStore);
				 
			 }
		}
	}
	
	@EventHandler
	public void onInvClickTwo(InventoryClickEvent e){
		 try
		    {
		      Player p = (Player)e.getWhoClicked();
		      if (e.getInventory().getName().equals(ChatColor.GREEN + "§lToolStore"))
		      {if(e.getSlot() == e.getRawSlot()){
		        e.setCancelled(true);
		        if((p.getItemInHand().getType() == Material.PAPER)){
		        if (e.getCurrentItem().getType() == Material.IRON_PICKAXE)
		        {
		        	if(p.getItemInHand().getAmount() >= 6){
		        		if(p.getItemInHand().getAmount() == 6){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 6);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Pickaxe","", Material.IRON_PICKAXE, false, 1));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.IRON_AXE)
		        {
		        	if(p.getItemInHand().getAmount() >= 5){
		        		if(p.getItemInHand().getAmount() == 5){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 5);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Axe","", Material.IRON_AXE, false, 1));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.IRON_SPADE)
		        {
		        	if(p.getItemInHand().getAmount() >= 6){
		        		if(p.getItemInHand().getAmount() == 6){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 6);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Spade","", Material.IRON_SPADE, false, 1));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.IRON_SWORD)
		        {
		        	if(p.getItemInHand().getAmount() >= 7){
		        		if(p.getItemInHand().getAmount() == 7){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 7);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Sword","", Material.IRON_SWORD, false, 1));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.TNT)
		        {
		        	if(p.getItemInHand().getAmount() >= 64){
		        		if(p.getItemInHand().getAmount() == 64){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 64);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("TNT","", Material.TNT, false, 1));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }else if (e.getCurrentItem().getType() == Material.ENCHANTED_BOOK)
		        {
		        	if(p.getItemInHand().getAmount() >= 50){
		        		if(p.getItemInHand().getAmount() == 50){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 50);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("iBirne", "Your personal SmartPhone", Material.ENCHANTED_BOOK, false, 1));
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
	public void damageStore(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else 
			 if (vName.equals("ToolStore")) {
				 e.setCancelled(true);
			 }
		}
	}
	@EventHandler
	public void damageStoreTwo(EntityDamageEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else 
			 if (vName.equals("ToolStore")) {
				 e.setCancelled(true);
			 }
		}
	}
}
