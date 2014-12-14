package de.Code7.Interface;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.Code7.Main.PlayerManager;


public class ChatManager implements Listener{

	@EventHandler (priority = EventPriority.HIGHEST)
	public void atChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		
		File clans = new File("plugins/Code7/Clan", "Clans.yml");
		FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);
		
		for(String key : clancheck.getConfigurationSection("Clans.").getKeys(false)){
			

			//if(clancheck.contains("Clans." + key)){
				/*String clanpath = "Clans." + key;
				if(clancheck.getString(clanpath + ".Owner").equals(p.getPlayer().getUniqueId().toString())){
					p.setDisplayName(ChatColor.RED + "[" + key + "] " + ChatColor.GREEN + p.getName());
				} else 
				if(clancheck.getString(PlayerManager.getUUID(p.getName()) + "") == key){//clancheck.contains("Clans." + key + "." + p.getPlayer().getUniqueId().toString())){
					p.setDisplayName(ChatColor.RED + "[" + key + "] " + ChatColor.GREEN + p.getName());
				*/
				
				if(clancheck.get("Clans." + getClan(p)) == null){
					clancheck.set(PlayerManager.getUUID(p.getName()) + "", null);
					
					try {
						clancheck.save(clans);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				if(key.equals(getClan(p))){
					p.setDisplayName(ChatColor.RED + "[" + key + "] " + ChatColor.GREEN + p.getName());
				} else {
					p.setDisplayName(p.getName());
				}
			//}
		}
		
		if(OpMemberManager.getRank(p) == "member"){
			e.setFormat(ChatColor.GOLD + "[" + ChatColor.GREEN + p.getDisplayName() + ChatColor.GOLD + "]" + " " + ChatColor.LIGHT_PURPLE + e.getMessage());
		} else if(OpMemberManager.getRank(p) == "admin"){
			e.setFormat(ChatColor.DARK_RED + "[" + ChatColor.RED + p.getDisplayName() + ChatColor.DARK_RED + "]" + " " + ChatColor.AQUA + e.getMessage());
		} else if(OpMemberManager.getRank(p) == "noob"){
			e.setFormat(ChatColor.BLUE + "[" + ChatColor.YELLOW + "Noobie: " + p.getDisplayName() + ChatColor.BLUE + "]" + " " + ChatColor.MAGIC + e.getMessage());
		}
	}
	
	public static String getClan(Player p){
		File clans = new File("plugins/Code7/Clan", "Clans.yml");
		FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);
		
		String clan = clancheck.getString(PlayerManager.getUUID(p.getName()) + "");
		
		if(clan != ""){
			return clan;
		} else {
			return null;
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		

		
		if(p.hasPlayedBefore() == false){
			
			Bukkit.getServer().broadcastMessage(ChatColor.DARK_GREEN + "Player: " + ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.DARK_GREEN + " joined for the first time!");
		}
		if(OpMemberManager.getRank(p) == "member"){
			e.setJoinMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + p.getDisplayName() + ChatColor.GOLD + "]" + " " + ChatColor.LIGHT_PURPLE + " joined!");
		} else if(OpMemberManager.getRank(p) == "admin"){
			e.setJoinMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + p.getDisplayName() + ChatColor.DARK_RED + "]" + " " + ChatColor.AQUA + " joined!");
		} else if(OpMemberManager.getRank(p) == "noob"){
			/*File file = new File("plugins/Code7/Spawn", "Spawn.yml");
    		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			double x = cfg.getDouble("Spawn.X");
			double y = cfg.getDouble("Spawn.Y");
			double z = cfg.getDouble("Spawn.Z");
			String world = cfg.getString("Spawn.World");
			float yaw = (float) cfg.getDouble("Spawn.Yaw");
			float pitch = (float) cfg.getDouble("Spawn.Pitch");
			
			World w = Bukkit.getServer().getWorld(world);
			
			Location loc = new Location(w, x, y, z, yaw, pitch);
			*/
			
			File file = new File("plugins/Code7/Port", "Lobby.yml");
    		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			
			double x = cfg.getDouble("Lobby.X");
			double y = cfg.getDouble("Lobby.Y");
			double z = cfg.getDouble("Lobby.Z");
			String world = cfg.getString("Lobby.WORLD");
			float yaw = (float) cfg.getDouble("Lobby.YAW");
			float pitch = (float) cfg.getDouble("Lobby.PITCH");
			
			World w = Bukkit.getServer().getWorld(world);
			
			Location loc = new Location(w, x, y, z, yaw, pitch);
			
			p.teleport(loc);
			e.setJoinMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "Noobie: " + p.getDisplayName() + ChatColor.BLUE + "]" + " " + ChatColor.BLUE + " joined!");
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();
		

		
		if(OpMemberManager.getRank(p) == "member"){
			e.setQuitMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + p.getDisplayName() + ChatColor.GOLD + "]" + " " + ChatColor.LIGHT_PURPLE + " left!");
		} else if(OpMemberManager.getRank(p) == "admin"){
			e.setQuitMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + p.getDisplayName() + ChatColor.DARK_RED + "]" + " " + ChatColor.AQUA + " left!");
		} else if(OpMemberManager.getRank(p) == "noob"){
			e.setQuitMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "Noobie: " + p.getDisplayName() + ChatColor.BLUE + "]" + " " + ChatColor.BLUE + " left!");
		}
	}
}