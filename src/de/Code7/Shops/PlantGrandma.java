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

public class PlantGrandma implements Listener{
	@EventHandler
	public void onPlantOma(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof Villager){
			 e.setCancelled(true);
			 Villager v = (Villager) e.getRightClicked();
			 String vName = v.getCustomName();
			 
			 if(vName == null){
					int i = (int) (Math.random()*4+1);
					
					if(i == 1){
						v.setCustomName("Gunter");
					}
					if(i == 2){
						v.setCustomName("Jakob");
					}
					if(i == 3){
						v.setCustomName("Petra");
					}
					if(i == 4){
						v.setCustomName("Ingeborg");
					}
			 } else if (vName.equals("PlantGrandma")) {
				 
				 //Initialisierung des PlantGrandmas
					Inventory PlantGrandma = p.getPlayer().getServer().createInventory(null, 9, ChatColor.GREEN + "§lPlant Grandma");
					
					PlantGrandma.setItem(0, ItemsPublic.CreateItem("Seeds","Price: 2, Amount: 4", Material.SEEDS, false, 4));
					PlantGrandma.setItem(1, ItemsPublic.CreateItem("Carrots","Price: 1, Amount: 6", Material.CARROT_ITEM, false, 6));
					PlantGrandma.setItem(2, ItemsPublic.CreateItem("Melon","Price: 2, Amount: 2", Material.MELON_BLOCK, false, 2));
					PlantGrandma.setItem(3, ItemsPublic.CreateItem("Potato","Price: 1, Amount: 6", Material.POTATO_ITEM, false, 6));
			        p.getPlayer().openInventory(PlantGrandma);
				 
			 }
		}
	}
	
	@EventHandler
	public void onInvClickFour(InventoryClickEvent e){
		 try
		    {
		      Player p = (Player)e.getWhoClicked();
		      if (e.getInventory().getName().equals(ChatColor.GREEN + "§lPlant Grandma"))
		      {if(e.getSlot() == e.getRawSlot()){
		        e.setCancelled(true);
		        if((p.getItemInHand().getType() == Material.PAPER)){
		        if (e.getCurrentItem().getType() == Material.SEEDS)
		        {
		        	if(p.getItemInHand().getAmount() >= 2){
		        		if(p.getItemInHand().getAmount() == 2){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 2);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Seeds","", Material.SEEDS, false, 4));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.CARROT_ITEM)
		        {
		        	if(p.getItemInHand().getAmount() >= 1){
		        		if(p.getItemInHand().getAmount() == 1){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Carrots","", Material.CARROT_ITEM, false, 6));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.MELON_BLOCK)
		        {
		        	if(p.getItemInHand().getAmount() >= 2){
		        		if(p.getItemInHand().getAmount() == 2){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 2);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Melon","", Material.MELON_BLOCK, false, 2));
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough money!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.POTATO_ITEM)
		        {
		        	if(p.getItemInHand().getAmount() >= 1){
		        		if(p.getItemInHand().getAmount() == 1){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Potato","", Material.POTATO_ITEM, false, 6));
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
	public void damagePlantGrandma(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else 
			 if (vName.equals("PlantGrandma")) {
				 e.setCancelled(true);
			 }
		}
	}
	@EventHandler
	public void damagePlantGrandmaTwo(EntityDamageEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else 
			 if (vName.equals("PlantGrandma")) {
				 e.setCancelled(true);
			 }
		}
	}

}
