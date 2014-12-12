package de.Code7.AdminMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BanMenu implements Listener{
	
	public static void openBan(Player p){
		int lines = 0;
		Player[] players = Bukkit.getOnlinePlayers();
		while(lines * 9 < players.length - 1){
			lines++;
		}
		if(lines > 6){
			lines = 6;
		}
		Inventory AdminBan = Bukkit.createInventory(null, lines * 9, ChatColor.RED + "§lAdmin Ban" + ChatColor.BLACK + " v0.7-Alpha");
		int slot = 0;
		for(int i = 0; i < players.length; i++){
			Player p_ = players[i];
			if(p_ != p){
				ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(p_.getName());
				item.setItemMeta(meta);
				AdminBan.setItem(slot, item);
				//p.sendMessage(slot + " " + item.getItemMeta().getDisplayName() + " " +lines);
				slot++;
			}
		
		}
		p.openInventory(AdminBan);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBan(InventoryClickEvent e){
		Player p = (Player)e.getWhoClicked();
		 if (e.getInventory().getName().equals(ChatColor.RED + "§lAdmin Ban" + ChatColor.BLACK + " v0.7-Alpha"))
	     {
			 if(e.getSlot() == e.getRawSlot()){
	    		  e.setCancelled(true);
	    		  p.updateInventory();
	    		  ItemStack item = e.getCurrentItem();
	    		  if(item != null){
	    			  String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
	    			  Player p_ = Bukkit.getPlayerExact(name);
	    			  if(p_ != null){
	    				  p_.kickPlayer(ChatColor.RED + "Banned by Admin!");
	    				  p_.setBanned(true);
	    				  e.getView().close();
	    			  } else {
	    				  p.sendMessage(ChatColor.RED + "Player not online!");
	    				  e.getView().close();
	    			  }
	    		  }
	    	 }
	     }
	}
	
}
