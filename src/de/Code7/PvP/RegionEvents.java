package de.Code7.PvP;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.mewin.WGRegionEvents.events.RegionLeaveEvent;

import de.Code7.Main.Instructor;

public class RegionEvents implements Listener{
	
	public Instructor plugin;
	public RegionEvents(Instructor plugin){
		this.plugin = plugin;
	}
	
	
	public boolean getRegion(String region, Player p, String what){
		
		File file = new File("plugins/Code7/Region", "SafeZone.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if(cfg.getBoolean("Regions.SafeZone." + region)){
			p.sendMessage(ChatColor.GOLD + "SafeZone: " + ChatColor.GREEN + region + " " + ChatColor.GOLD + what);
			return true;
		} else {
			return false;
		}
	}
	
	@EventHandler
	public void OnRegionEnter(RegionEnterEvent e){
		if(getRegion(e.getRegion().getId(), e.getPlayer(), "entered!")){
			if(!Instructor.IVA.contains(e.getPlayer())){
				Instructor.IVA.add(e.getPlayer());
			}
		}
	}
	@EventHandler
	public void OnRegionLeave(RegionLeaveEvent e){
		if(getRegion(e.getRegion().getId(), e.getPlayer(), "left!")){
			if(Instructor.IVA.contains(e.getPlayer())){
				Instructor.IVA.remove(e.getPlayer());
			}
		}
	}
}
