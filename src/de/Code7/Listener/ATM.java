package de.Code7.Listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import de.Code7.Items.ItemsPublic;
import de.Code7.Main.PlayerManager;

public class ATM implements Listener{
	
	private static File file = new File("plugins/Code7/ATM", "ATM.yml");
	private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	@EventHandler
	public void onATMKlick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.DIAMOND_BLOCK){
				Location loc1 = e.getClickedBlock().getLocation();
				Location loc2 = new Location(loc1.getWorld(), loc1.getBlockX(), loc1.getBlockY() -1, loc1.getBlockZ());
				if(loc2.getBlock().getType() == Material.SIGN_POST){
					Sign sign = (Sign) loc2.getBlock().getState();
					if(sign.getLine(1).equals("ATM")){
						if(p.isOp()){
							sign.setLine(1,ChatColor.RED + "ATM%%&&//");
							sign.update();
						}
					} else if(sign.getLine(1).equals(ChatColor.RED + "ATM%%&&//")){

						openATM(p);
						
					}
				}
			}
		}
	}
	
	public static void openATM(Player p){
		
		 int getmoney = 0;
		
		if(cfg.contains("Money." + PlayerManager.getUUID(p.getName()))){
			getmoney = cfg.getInt("Money." + PlayerManager.getUUID(p.getName()));
		} else if(!cfg.contains("Money." + PlayerManager.getUUID(p.getName()))){
			cfg.set("Money." + PlayerManager.getUUID(p.getName()), 0);
			p.sendMessage(ChatColor.WHITE + "[" + ChatColor.GRAY + "Code7" + ChatColor.WHITE + "]:" + ChatColor.YELLOW + " For going on a ATM for the first time, you get a free iBirne!");
			p.getInventory().addItem(ItemsPublic.CreateItem("iBirne", "Your personal SmartPhone", Material.ENCHANTED_BOOK, false, 1));
			try {
				cfg.save(file);
			} catch (IOException e1) {
				Bukkit.getServer().broadcast(null, ChatColor.DARK_RED + "FEHLER FEHLER FEHLER!!!");
			}
			getmoney = cfg.getInt("Money." + PlayerManager.getUUID(p.getName()));
		}
		
		//HIER ATM INTERFACE
		Inventory ATM = p.getPlayer().getServer().createInventory(null, 9, ChatColor.GREEN + "§lATM");
		
		ATM.setItem(0, ItemsPublic.CreateItem("Deposit","Deposit Money" + ChatColor.RED + "(Disabled on iBirne)", Material.GLOWSTONE_DUST, true, 1));
		ATM.setItem(2, ItemsPublic.CreateItem("Withdraw 1","Withdraw Money", Material.BED, true, 1));
		ATM.setItem(3, ItemsPublic.CreateItem("Withdraw 8","Withdraw Money", Material.BED, true, 1));
		ATM.setItem(4, ItemsPublic.CreateItem("Withdraw 32","Withdraw Money", Material.BED, true, 1));
		ATM.setItem(5, ItemsPublic.CreateItem("Withdraw 64","Withdraw Money", Material.BED, true, 1));
		ATM.setItem(8, ItemsPublic.CreateItem("Money at Bank","" + getmoney, Material.REDSTONE_BLOCK, false, 1));
        p.getPlayer().openInventory(ATM);
	}
	
	@EventHandler
	public void onATMInv(InventoryClickEvent e){
		int getmoney = 0;
		getmoney = cfg.getInt("Money." + PlayerManager.getUUID(e.getWhoClicked().getName()));
	    try
	    {
	      Player p = (Player)e.getWhoClicked();
	      if (e.getInventory().getName().equals(ChatColor.GREEN + "§lATM"))
	      {
	    	if(e.getSlot() == e.getRawSlot()){
	    		e.getInventory().setItem(8, ItemsPublic.CreateItem("Money at Bank",getmoney + "", Material.REDSTONE_BLOCK, false, 1));
	    		e.setCancelled(true);
	        if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Deposit","Deposit Money" + ChatColor.RED + "(Disabled on iBirne)", Material.GLOWSTONE_DUST, true, 1)))
	        {
	        	getmoney = cfg.getInt("Money." + PlayerManager.getUUID(p.getName()));
	        	if(p.getItemInHand().equals(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, p.getItemInHand().getAmount()))){
	        		cfg.set("Money." + PlayerManager.getUUID(p.getName()), getmoney + p.getItemInHand().getAmount());
	        		p.setItemInHand(null);
	        		e.setCancelled(true);
	        		try {
						cfg.save(file);
					} catch (IOException e1) {
						p.sendMessage(ChatColor.DARK_RED + "FEHLER FEHLER FEHLER!!!");
					}
	        		e.getView().close();

	        	} else {
	        		e.getView().close();
	        		p.sendMessage(ChatColor.RED + "Not enough money!");
	        	}
	        }
	        else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Withdraw 1","Withdraw Money", Material.BED, true, 1)))
	        {
	        	getmoney = cfg.getInt("Money." + PlayerManager.getUUID(p.getName()));
	        	if(cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) >= 1){
	        			p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 1));
	        			cfg.set("Money." + PlayerManager.getUUID(p.getName()), getmoney - 1);
	        			e.setCancelled(true);
	        			
	        		try {
						cfg.save(file);
					} catch (IOException e1) {
						p.sendMessage(ChatColor.DARK_RED + "FEHLER FEHLER FEHLER!!!");
					}

	        	} else {
	        		e.getView().close();
	        		p.sendMessage(ChatColor.RED + "Not enough money!");
	        	}
	        }
	        else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Withdraw 8","Withdraw Money", Material.BED, true, 1)))
	        {
	        	getmoney = cfg.getInt("Money." + PlayerManager.getUUID(p.getName()));
	        	if(cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) >= 8){
	        			p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 8));
	        			cfg.set("Money." + PlayerManager.getUUID(p.getName()), getmoney - 8);
	        			e.setCancelled(true);
	        		try {
						cfg.save(file);
					} catch (IOException e1) {
						p.sendMessage(ChatColor.DARK_RED + "FEHLER FEHLER FEHLER!!!");
					}

	        	} else {
	        		e.getView().close();
	        		p.sendMessage(ChatColor.RED + "Not enough money!");
	        	}
	        }
	        else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Withdraw 32","Withdraw Money", Material.BED, true, 1)))
	        {
	        	getmoney = cfg.getInt("Money." + PlayerManager.getUUID(p.getName()));
	        	if(cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) >= 32){
	        			p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 32));
	        			cfg.set("Money." + PlayerManager.getUUID(p.getName()), getmoney - 32);
	        			e.setCancelled(true);
	        		try {
						cfg.save(file);
					} catch (IOException e1) {
						p.sendMessage(ChatColor.DARK_RED + "FEHLER FEHLER FEHLER!!!");
					}

	        	} else {
	        		e.getView().close();
	        		p.sendMessage(ChatColor.RED + "Not enough money!");
	        	}
	        }
	        else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Withdraw 64","Withdraw Money", Material.BED, true, 1)))
	        {
	        	getmoney = cfg.getInt("Money." + PlayerManager.getUUID(p.getName()));
	        	if(cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) >= 64){
	        			p.getInventory().addItem(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 64));
	        			cfg.set("Money." + PlayerManager.getUUID(p.getName()), getmoney - 64);
	        			e.setCancelled(true);
	        		try {
						cfg.save(file);
					} catch (IOException e1) {
						p.sendMessage(ChatColor.DARK_RED + "FEHLER FEHLER FEHLER!!!");
					}

	        	} else {
	        		e.getView().close();
	        		p.sendMessage(ChatColor.RED + "Not enough money!");
	        	}
	        }
	        else{
	        	e.setCancelled(true);
	        }
	      }
	      }
	    }
	    catch (NullPointerException localNullPointerException) {}
	  }
}
