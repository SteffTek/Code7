package de.Code7.Home;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.Code7.Main.PlayerManager;

public class HomeConfig{

	public static void Home(Player p, String sld){
		if(sld == "CreateHome") CreateHome(p);
		if(sld == "LoadHome") LoadHome(p);
		if(sld == "DeleteHome") DeleteHome(p);
	}
	
	public static void CreateHome(Player p){
		File file = new File("plugins/Code7/Home", "Home.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		cfg.set("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.world", p.getLocation().getWorld().getName());
		cfg.set("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.x", p.getLocation().getBlockX());
		cfg.set("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.z", p.getLocation().getBlockZ());
		cfg.set("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.y", p.getLocation().getBlockY());
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void LoadHome(Player p){
		File file = new File("plugins/Code7/Home", "Home.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		String w = cfg.getString("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.world");
		double x = cfg.getDouble("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.x");
		double y = cfg.getDouble("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.y");
		double z = cfg.getDouble("Home.User." + PlayerManager.getUUID(p.getName()) + ".loc.z");
		
		World world = Bukkit.getServer().getWorld(w);
		
		Location loc = new Location(world, x, y, z);
		p.teleport(loc);
	}	
	public static void DeleteHome(Player p){
		File file = new File("plugins/Code7/Home", "Home.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		cfg.set("Home.User." + PlayerManager.getUUID(p.getName()), "");
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
