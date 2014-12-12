package de.Code7.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.Code7.AdminMenu.*;
import de.Code7.Clans.CreateClan;
import de.Code7.EntityManipulation.*;
import de.Code7.Generator.*;
import de.Code7.Home.*;
import de.Code7.Interface.*;
import de.Code7.Items.*;
import de.Code7.Listener.*;
import de.Code7.PvP.*;
import de.Code7.Shops.*;
import de.Code7.SmartPhone.*;

public class Instructor extends JavaPlugin implements Listener{

	public static ArrayList<Player> IVA = new ArrayList<Player>();
	
	private Logger log = Logger.getLogger("Minecraft");
	public static HashMap<String, String> invite = new HashMap<String, String>();
	
	public void onEnable (){
		this.logMsg("Enabled");
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new ATM(), this);
		pm.registerEvents(new BlockGravity(), this);
		pm.registerEvents(new RealExpo(), this);
		pm.registerEvents(new DeathItem(), this);
		pm.registerEvents(new FoodShop(), this);
		pm.registerEvents(new ToolStore(), this);
		pm.registerEvents(new Exchange(), this);
		pm.registerEvents(new PlantGrandma(), this);
		pm.registerEvents(new MOTD(), this);
		pm.registerEvents(new DontHurtInRegion(), this);
		pm.registerEvents(new RegionEvents(this), this);
		pm.registerEvents(new SmartPhoneBase(), this);
		pm.registerEvents(new AppStore(), this);
		pm.registerEvents(new HomeInv(), this);
		pm.registerEvents(new ChatManager(), this);
		pm.registerEvents(new MenuManager(), this);
		pm.registerEvents(new KickMenu(), this);
		pm.registerEvents(new BanMenu(), this);
		pm.registerEvents(new TeleMenu(), this);
		pm.registerEvents(new TeleFrom(), this);
		pm.registerEvents(new SpawnManipulation(this), this);
		pm.registerEvents(new OpMemberManager(), this);
		pm.registerEvents(new AntiCraft(), this);
		pm.registerEvents(this, this);
		
		getCommand("clancreate").setExecutor(new CreateClan());
		getCommand("clandelete").setExecutor(new CreateClan());
		getCommand("claninvite").setExecutor(new CreateClan());
		getCommand("clanaccept").setExecutor(new CreateClan());
		getCommand("clanleave").setExecutor(new CreateClan());
		getCommand("firstport").setExecutor(new LobbyPort());
		getCommand("setrank").setExecutor(new OpMemberManager());
	}
	public void logMsg(String msg){
		PluginDescriptionFile pdFile = this.getDescription();
		this.log.info(pdFile.getName() + " " + pdFile.getVersion() + ": " + msg);
	}

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(p.getInventory().contains(ItemsPublic.CreateItem("Admin Menu", "ONLY ADMINS", Material.BEDROCK, false, 1)) == false){
			if(OpMemberManager.getRank(p) == "admin"){
				p.getInventory().addItem(ItemsPublic.CreateItem("Admin Menu", "ONLY ADMINS", Material.BEDROCK, false, 1));
			}
		}
		//p.getInventory().addItem(ItemsPublic.CreateItem("iBirne", "Your personal SmartPhone", Material.ENCHANTED_BOOK, false, 1));
	}

	
	/*public ChunkGenerator getDefaultWorldGenerator(String worldName,String uid){
		return new TerrainGeneration();
	}*/
}
