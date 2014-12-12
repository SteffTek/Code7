package de.Code7.SmartPhone;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import de.Code7.Items.ItemsPublic;
import de.Code7.Main.PlayerManager;

public class AppStore implements Listener{

	
	@SuppressWarnings("deprecation")
	public static void onAppStore(Player p){
		
						Inventory AppStore = p.getPlayer().getServer().createInventory(null, 54, ChatColor.GOLD + "§liBirne" + ChatColor.BLACK + " v0.7-Alpha");
						
						
						AppStore.setItem(0, ItemsPublic.CreateItem("Buy Mobile ATM","Price: 25", Material.GLOWSTONE_DUST, false, 1));
						AppStore.setItem(1, ItemsPublic.CreateItem("Buy Home-Manager","Price: 100", Material.BED, false, 1));
						
						ItemStack istack = new ItemStack(Material.STAINED_GLASS_PANE);
						istack.setData (new MaterialData(15));
						ItemMeta imet = istack.getItemMeta();
						imet.setDisplayName(" ");
						istack.setItemMeta(imet);
						
						AppStore.setItem(45, istack);
						AppStore.setItem(46, istack);
						AppStore.setItem(47, istack);
						AppStore.setItem(48, istack);
						AppStore.setItem(49, ItemsPublic.CreateItem("Home","", Material.GOLD_BLOCK, false, 1));
						AppStore.setItem(50, istack);
						AppStore.setItem(51, istack);
						AppStore.setItem(52, istack);
						AppStore.setItem(53, istack);
				        p.getPlayer().openInventory(AppStore);
	}
	@EventHandler
	public void onSmartInv(InventoryClickEvent e){
		
		File file = new File("plugins/Code7/ATM", "ATM.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		File AppConfig = new File("plugins/Code7/SmartPhone", "AppConfig.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(AppConfig);
		
	    try
	    {
	      Player p = (Player)e.getWhoClicked();
	      if (e.getInventory().getName().equals(ChatColor.GOLD + "§liBirne" + ChatColor.BLACK + " v0.7-Alpha"))
	      {
	    	  if(e.getSlot() == e.getRawSlot()){
	        e.setCancelled(true);
	        if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Buy Mobile ATM","Price: 25", Material.GLOWSTONE_DUST, false, 1))){
	        	if(config.getBoolean("Users." + PlayerManager.getUUID(p.getName()) + ".ATM") == false)
	        	if(cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) >= 25){
	        		cfg.set("Money." + PlayerManager.getUUID(p.getName()), cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) - 25);
	        		config.set("Users." + PlayerManager.getUUID(p.getName()) + ".ATM", true);
	        		try {
						config.save(AppConfig);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        		try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        	}
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Buy Home-Manager","Price: 100", Material.BED, false, 1))){
	        	if(config.getBoolean("Users." + PlayerManager.getUUID(p.getName()) + ".HomeManager") == false)
	        	if(cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) >= 100){
	        		cfg.set("Money." + PlayerManager.getUUID(p.getName()), cfg.getInt("Money." + PlayerManager.getUUID(p.getName())) - 100);
	        		config.set("Users." + PlayerManager.getUUID(p.getName()) + ".HomeManager", true);
	        		try {
						config.save(AppConfig);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        		try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        	}
	        } else if(e.getCurrentItem().equals(ItemsPublic.CreateItem("Home","", Material.GOLD_BLOCK, false, 1))){
	        	e.getView().close();
	        	SmartPhoneBase.openPhone(p);
	        } else {
	        	e.setCancelled(true);
	        }
	    	  }
	      }
	    }
	    catch (NullPointerException localNullPointerException) {}
	  }
}
