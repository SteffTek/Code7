package de.Code7.EntityManipulation;


import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.Code7.Main.Instructor;

public class SpawnManipulation implements Listener{
	
	public Instructor plugin;
	public SpawnManipulation(Instructor plugin){
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerRespawnEvent e){
		
		Player p = e.getPlayer();
		
		if(p.getBedSpawnLocation() == null){
			Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable(){
				@Override
				public void run() {
					
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
					
					p.teleport(loc);	
				}
			},20L);
		} else {
			Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable(){
				@Override
				public void run() {
					p.teleport(p.getBedSpawnLocation());	
				}
			},20L);
		}
	}
}
