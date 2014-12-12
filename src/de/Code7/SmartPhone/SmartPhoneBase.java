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
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import de.Code7.Home.HomeInv;
import de.Code7.Items.ItemsPublic;
import de.Code7.Listener.ATM;
import de.Code7.Main.PlayerManager;

public class SmartPhoneBase implements Listener{
	
	@EventHandler
	public void onSmartPhoneKlick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)){
			if(p.getItemInHand().equals(ItemsPublic.CreateItem("iBirne", "Your personal SmartPhone", Material.ENCHANTED_BOOK, false, 1))){
				
				File AppConfig = new File("plugins/Code7/SmartPhone", "AppConfig.yml");
				FileConfiguration config = YamlConfiguration.loadConfiguration(AppConfig);
				
				if((!config.contains("Users." + PlayerManager.getUUID(p.getName())))){
					config.set("Users." + PlayerManager.getUUID(p.getName()) + ".Using", true);
					try {
						config.save(AppConfig);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
					openPhone(p);
			}
		}
	}
	@SuppressWarnings("deprecation")
	public static void openPhone(Player p){
		Inventory SmartPhone = p.getPlayer().getServer().createInventory(null, 54, ChatColor.GOLD + "§liBirne" + ChatColor.BLACK + " v0.7-Alpha");
		
		
		SmartPhone.setItem(0, ItemsPublic.CreateItem("AppStore","Buy Apps", Material.REDSTONE, false, 1));
		SmartPhone.setItem(4, ItemsPublic.CreateItem("Mobile ATM","Deposit/Withdraw Money", Material.GLOWSTONE_DUST, false, 1));
		SmartPhone.setItem(8, ItemsPublic.CreateItem("Home Manager","Manage your Home", Material.BED, false, 1));

		ItemStack istack = new ItemStack(Material.STAINED_GLASS_PANE);
		istack.setData (new MaterialData(15));
		ItemMeta imet = istack.getItemMeta();
		imet.setDisplayName(" ");
		istack.setItemMeta(imet);
		
		SmartPhone.setItem(45, istack);
		SmartPhone.setItem(46, istack);
		SmartPhone.setItem(47, istack);
		SmartPhone.setItem(48, istack);
		SmartPhone.setItem(49, ItemsPublic.CreateItem("Home","", Material.GOLD_BLOCK, false, 1));
		SmartPhone.setItem(50, istack);
		SmartPhone.setItem(51, istack);
		SmartPhone.setItem(52, istack);
		SmartPhone.setItem(53, istack);
        p.getPlayer().openInventory(SmartPhone);
	}
	
	@EventHandler
	public void onAppStoreInv(InventoryClickEvent e){
		
		File AppConfig = new File("plugins/Code7/SmartPhone", "AppConfig.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(AppConfig);
		
	    try
	    {
	      Player p = (Player)e.getWhoClicked();
	      if (e.getInventory().getName().equals(ChatColor.GOLD + "§liBirne" + ChatColor.BLACK + " v0.7-Alpha"))
	      {
	    	if(e.getSlot() == e.getRawSlot()){
	        e.setCancelled(true);
	        if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Mobile ATM","Deposit/Withdraw Money", Material.GLOWSTONE_DUST, false, 1)))
	        {
	        	if(config.getBoolean("Users." + PlayerManager.getUUID(p.getName()) + ".ATM")){
	        		e.getView().close();
	        		ATM.openATM(p);
	        	}
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Home Manager","Manage your Home", Material.BED, false, 1)))
	        {
	        	if(config.getBoolean("Users." + PlayerManager.getUUID(p.getName()) + ".HomeManager")){
	        		e.getView().close();
	        		HomeInv.onHome(p);
	        	}
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("AppStore","Buy Apps", Material.REDSTONE, false, 1)))
	        {
	        	e.getView().close();
	        	AppStore.onAppStore(p);
	        } else {
	        	e.setCancelled(true);
	        }
	      }
	      }
	    }
	    catch (NullPointerException localNullPointerException) {}
	  }
}
