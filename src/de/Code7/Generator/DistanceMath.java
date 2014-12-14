package de.Code7.Generator;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;



public class DistanceMath {

	public static int DisToSpawn(Location loc2){
		
		
		File file = new File("plugins/Code7/Spawn", "Spawn.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		double x = cfg.getDouble("Spawn.X");
		double y = cfg.getDouble("Spawn.Y");
		double z = cfg.getDouble("Spawn.Z");
		String world = cfg.getString("Spawn.World");
		float yaw = (float) cfg.getDouble("Spawn.Yaw");
		float pitch = (float) cfg.getDouble("Spawn.Pitch");
		
		World w = Bukkit.getServer().getWorld(world);
		
		Location loc = new Location(w, x, y, z, yaw, pitch);
		
		int locdis = (int) loc.distance(loc2);
		
		return locdis;
	}
}
