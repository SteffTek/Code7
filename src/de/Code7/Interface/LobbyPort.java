package de.Code7.Interface;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class LobbyPort implements CommandExecutor {


	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = null;
		if(cs instanceof Player){
			p = (Player) cs;
		} else {
			cs.sendMessage(ChatColor.RED + "You are not a human!");
			return false;
		}
		if(p != null){
			if(label.equalsIgnoreCase("firstport")){
				if(OpMemberManager.getRank(p) == "admin"){
				Location loc = p.getLocation();
				
				File port = new File("plugins/Code7/Port", "Lobby.yml");
				FileConfiguration portcheck = YamlConfiguration.loadConfiguration(port);
				
				String st = "Lobby.";
				
				portcheck.set(st + ".X", loc.getBlockX());
				portcheck.set(st + ".Y", loc.getBlockY());
				portcheck.set(st + ".Z", loc.getBlockZ());
				portcheck.set(st + ".YAW", loc.getYaw());
				portcheck.set(st + ".PITCH", loc.getPitch());
				portcheck.set(st + ".WORLD", loc.getWorld().getName());
				
				try {
					portcheck.save(port);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			}
		}
		return false;
	}
}
