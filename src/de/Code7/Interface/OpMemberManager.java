package de.Code7.Interface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.Code7.Main.PlayerManager;

public class OpMemberManager implements Listener, CommandExecutor{
	
	public static String getRank(Player p){
		File file = new File("plugins/Code7/Rank", "Users.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		String what;
		
		if(cfg.contains("Rank.Player." + PlayerManager.getUUID(p.getName()) + ".Member")){
			what = "member";
			return what;
		} else if(cfg.contains("Rank.Player." + PlayerManager.getUUID(p.getName()) + ".Admin")){
			what = "admin";
			return what;
		} else {
			cfg.set("Rank.Player." + PlayerManager.getUUID(p.getName()) + ".Noob", "true");
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "noob";
		}
	}
	
	public static String setRank(Player p, String what){
		File file = new File("plugins/Code7/Rank", "Users.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		ArrayList<String> check = new ArrayList<String>();
		check.add("Admin");
		check.add("Member");
		check.add("Noob");
		
		if(check.contains(what)){
			cfg.set("Rank.Player." + PlayerManager.getUUID(p.getName()), null);
			cfg.set("Rank.Player." + PlayerManager.getUUID(p.getName()) + "." + what, "true");
			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			p.sendMessage(ChatColor.GREEN + "You are now " + what);
			return "Succsess";
		} else {
			return "Fail";
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = null;
		if(cs instanceof Player){
			p = (Player) cs;
		} else {
			cs.sendMessage(ChatColor.RED + "You are not a human!");
			return false;
		}
		if(p != null){
			if(label.equalsIgnoreCase("setrank")){
				if(getRank(p) == "admin"){
					if(args.length == 2){
						if(Bukkit.getPlayerExact(args[0]) != null){
							if(setRank(Bukkit.getPlayerExact(args[0]),args[1]) == "Succsess"){
								p.sendMessage(ChatColor.GREEN + "Rank of " + args[0] + " set to " + args[1].toString());
								return true;
							} else {
								p.sendMessage(ChatColor.RED + "FAIL!");
								return false;
							}
						} else {
							p.sendMessage(ChatColor.RED + "Player not online!");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Wrong Count of Arguments!");
					}
				}
			}
		}
		return false;
	}

	
	@EventHandler
	public void onATMKlick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getClickedBlock().getType() == Material.IRON_BLOCK){
				Location loc1 = e.getClickedBlock().getLocation();
				Location loc2 = new Location(loc1.getWorld(), loc1.getBlockX(), loc1.getBlockY() -1, loc1.getBlockZ());
				if(loc2.getBlock().getType() == Material.SIGN_POST){
					Sign sign = (Sign) loc2.getBlock().getState();
					if(sign.getLine(1).equals("Mount")){
						if(p.isOp()){
							sign.setLine(1,ChatColor.RED + "Mount%%&&//");
							sign.update();
						}
					} else if(sign.getLine(1).equals(ChatColor.RED + "Mount%%&&//")){

						File file = new File("plugins/Code7/Rank", "Users.yml");
						FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						if((getRank(p) == "member") || (getRank(p) == "admin")){
							p.sendMessage(ChatColor.DARK_RED + "Already in Group Member/Admin");
						} else {
							cfg.set("Rank.Player." + PlayerManager.getUUID(p.getName()), "");
							cfg.set("Rank.Player." + PlayerManager.getUUID(p.getName()) + ".Member", true);
							p.sendMessage(ChatColor.GREEN + "You are now in group 'Member'");
							try {
								cfg.save(file);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							File port = new File("plugins/Code7/Spawn", "Spawn.yml");
				    		FileConfiguration portcheck = YamlConfiguration.loadConfiguration(port);
							
							double x = portcheck.getDouble("Spawn.X");
							double y = portcheck.getDouble("Spawn.Y");
							double z = portcheck.getDouble("Spawn.Z");
							String world = portcheck.getString("Spawn.World");
							float yaw = (float) portcheck.getDouble("Spawn.Yaw");
							float pitch = (float) portcheck.getDouble("Spawn.Pitch");
							
							World w = Bukkit.getServer().getWorld(world);
							
							Location loc = new Location(w, x, y, z, yaw, pitch);
							p.teleport(loc);
						}
					}
				}
			}
		}
	}
}
