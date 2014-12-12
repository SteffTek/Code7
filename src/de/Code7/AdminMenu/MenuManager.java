package de.Code7.AdminMenu;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
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

import de.Code7.Interface.OpMemberManager;
import de.Code7.Items.ItemsPublic;

public class MenuManager implements Listener{
	@EventHandler
	public void onAdminMainKlick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)){
			if(p.getItemInHand().equals(ItemsPublic.CreateItem("Admin Menu", "ONLY ADMINS", Material.BEDROCK, false, 1))){
				
				e.setCancelled(true);
				
				if(OpMemberManager.getRank(p) == "admin"){
					openAdmin(p);
				}
			}
		}
	}
	public static void openAdmin(Player p){
		Inventory AdminMain = p.getPlayer().getServer().createInventory(null, 18, ChatColor.RED + "§lAdmin Menu" + ChatColor.BLACK + " v0.7-Alpha");
		
		
		AdminMain.setItem(0, ItemsPublic.CreateItem("Ban Menu","", Material.BEDROCK, false, 1));
		AdminMain.setItem(1, ItemsPublic.CreateItem("Kick Menu","", Material.STONE, false, 1));
		AdminMain.setItem(2, ItemsPublic.CreateItem("Teleport Menu","", Material.SKULL_ITEM, false, 1));
		AdminMain.setItem(3, ItemsPublic.CreateItem("Teleport Menu From","", Material.SKULL_ITEM, false, 1));
		
		AdminMain.setItem(6, ItemsPublic.CreateItem("Set Spawn","", Material.BEACON, false, 1));
		AdminMain.setItem(8, ItemsPublic.CreateItem("Heal","", Material.CAKE, false, 1));
		AdminMain.setItem(9, ItemsPublic.CreateItem("Gamemode Survival","", Material.COMPASS, false, 1));
		AdminMain.setItem(10, ItemsPublic.CreateItem("Gamemode Creative","", Material.COMPASS, false, 1));
		AdminMain.setItem(12, ItemsPublic.CreateItem("Toggle downfall","", Material.WATER_BUCKET, false, 1));
		AdminMain.setItem(14, ItemsPublic.CreateItem("Day","", Material.GLOWSTONE, false, 1));
		AdminMain.setItem(15, ItemsPublic.CreateItem("Night","", Material.REDSTONE_BLOCK, false, 1));
		AdminMain.setItem(17, ItemsPublic.CreateItem("PvP On/Off","", Material.IRON_SWORD, false, 1));
		
        p.getPlayer().openInventory(AdminMain);
	}
	@EventHandler
	public void onAdminMainInv(InventoryClickEvent e){
	    try
	    {
	      Player p = (Player)e.getWhoClicked();
	      if (e.getInventory().getName().equals(ChatColor.RED + "§lAdmin Menu" + ChatColor.BLACK + " v0.7-Alpha"))
	      {
	    	  if(e.getSlot() == e.getRawSlot()){
	        e.setCancelled(true);
	        if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Ban Menu","", Material.BEDROCK, false, 1))){
	        	e.getView().close();
	        	BanMenu.openBan(p);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Kick Menu","", Material.STONE, false, 1))){
	        	e.getView().close();
	        	KickMenu.openKick(p);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Teleport Menu","", Material.SKULL_ITEM, false, 1))){
	        	e.getView().close();
	        	TeleMenu.openTele(p);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Teleport Menu From","", Material.SKULL_ITEM, false, 1))){
	        	e.getView().close();
	        	TeleFrom.openTeleFrom(p);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Gamemode Survival","", Material.COMPASS, false, 1))){
	        	e.getView().close();
	        	p.setGameMode(GameMode.SURVIVAL);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Gamemode Creative","", Material.COMPASS, false, 1))){
	        	e.getView().close();
	        	p.setGameMode(GameMode.CREATIVE);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Toggle downfall","", Material.WATER_BUCKET, false, 1))){
	        	e.getView().close();
	        	p.getLocation().getWorld().setStorm(!p.getLocation().getWorld().hasStorm());
	        	p.sendMessage(ChatColor.RED + "Rain: " + p.getLocation().getWorld().hasStorm());
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Day","", Material.GLOWSTONE, false, 1))){
	        	e.getView().close();
	        	p.getLocation().getWorld().setTime(0);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Night","", Material.REDSTONE_BLOCK, false, 1))){
	        	e.getView().close();
	        	p.getLocation().getWorld().setTime(16000);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("PvP On/Off","", Material.IRON_SWORD, false, 1))){
	        	e.getView().close();
	        	p.getLocation().getWorld().setPVP(!p.getLocation().getWorld().getPVP());
	        	p.sendMessage(ChatColor.RED + "PvP: " + p.getLocation().getWorld().getPVP());
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Heal","", Material.CAKE, false, 1))){
	        	e.getView().close();
	        	p.setHealth(20);
	        	p.setFoodLevel(20);
	        } else if (e.getCurrentItem().equals(ItemsPublic.CreateItem("Set Spawn","", Material.BEACON, false, 1))){
	        	e.getView().close();
	        	
	    		File file = new File("plugins/Code7/Spawn", "Spawn.yml");
	    		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	        	
	        	Location loc = p.getLocation();
	        	loc.setYaw(p.getLocation().getYaw());
	        	loc.setPitch(p.getLocation().getPitch());
	        	
	        	cfg.set("Spawn.X", loc.getX());
	        	cfg.set("Spawn.Y", loc.getY());
	        	cfg.set("Spawn.Z", loc.getZ());
	        	cfg.set("Spawn.Yaw", loc.getYaw());
	        	cfg.set("Spawn.Pitch", loc.getPitch());
	        	cfg.set("Spawn.World", loc.getWorld().getName());
	        	
	        	try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        	
	        } else {
	        	e.setCancelled(true);
	        }
	      }
	    }
	    }
	    catch (NullPointerException localNullPointerException) {}
	  }
}
