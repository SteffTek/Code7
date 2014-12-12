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
public class Exchange implements Listener{
	@EventHandler
	public void onExchange(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof Villager){
			 e.setCancelled(true);
			 Villager v = (Villager) e.getRightClicked();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(true);
			 } else if (vName.equals("Exchange")) {
				 
				 //Initialisierung des Exchanges
					Inventory Exchange = p.getPlayer().getServer().createInventory(null, 9, ChatColor.GREEN + "§lExchange");
					
					Exchange.setItem(0, ItemsPublic.CreateItem("Sell Sulphur","Price: 2, Amount: 4", Material.SULPHUR, false, 4));
					Exchange.setItem(1, ItemsPublic.CreateItem("Sell String","Price: 1, Amount: 6", Material.STRING, false, 6));
					Exchange.setItem(2, ItemsPublic.CreateItem("Sell Bone","Price: 2, Amount: 2", Material.BONE, false, 2));
					Exchange.setItem(3, ItemsPublic.CreateItem("Sell Rotten Flesh","Price: 1, Amount: 6", Material.ROTTEN_FLESH, false, 6));
			        p.getPlayer().openInventory(Exchange);
				 
			 }
		}
	}
	
	@EventHandler
	public void onInvClickThree(InventoryClickEvent e){
		 try
		    {
		      Player p = (Player)e.getWhoClicked();
		      if (e.getInventory().getName().equals(ChatColor.GREEN + "§lExchange"))
		      {if(e.getSlot() == e.getRawSlot()){
		        e.setCancelled(true);
		        if (e.getCurrentItem().getType() == Material.SULPHUR)
		        {
		        	if(p.getItemInHand().getType() == Material.SULPHUR){
		        		if(p.getItemInHand().getAmount() >= 4){
		        		if(p.getItemInHand().getAmount() == 4){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 4);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 2));
		        	}
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough to exchange!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.STRING)
		        {
		        	if(p.getItemInHand().getType() == Material.STRING){
		        		if(p.getItemInHand().getAmount() >= 6){
		        		if(p.getItemInHand().getAmount() == 6){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 6);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 1));
		        	}
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough to exchange!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.BONE)
		        {
		        	if(p.getItemInHand().getType() == Material.BONE){
		        		if(p.getItemInHand().getAmount() >= 2){
		        		if(p.getItemInHand().getAmount() == 2){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 2);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 2));
		        	}
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough to exchange!");
		        	}
		        }
		        else if (e.getCurrentItem().getType() == Material.ROTTEN_FLESH)
		        {
		        	if(p.getItemInHand().getType() == Material.ROTTEN_FLESH){
		        		if(p.getItemInHand().getAmount() >= 6){
		        		if(p.getItemInHand().getAmount() == 6){
		        			p.setItemInHand(null);
		        		} else {
		        			p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 6);
		        		}
		        		p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 1));
		        	}
		        	} else {
		        		e.getView().close();
		        		p.sendMessage(ChatColor.RED + "Not enough to exchange!");
		        	}
		        } else {
		        	e.setCancelled(true);
		        }
		      }
		      }
		    }
		 catch (NullPointerException localNullPointerException) {}
	}
	
	@EventHandler
	public void damageExchange(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else if (vName.equals("Exchange")) {
				 e.setCancelled(true);
			 }
		}
	}
	@EventHandler
	public void damageExchangeTwo(EntityDamageEvent e){
		if(e.getEntity() instanceof Villager){
			 Villager v = (Villager) e.getEntity();
			 String vName = v.getCustomName();
			 if(vName == null){
				 e.setCancelled(false);
			 } else if (vName.equals("Exchange")) {
				 e.setCancelled(true);
			 }
		}
	}
}
