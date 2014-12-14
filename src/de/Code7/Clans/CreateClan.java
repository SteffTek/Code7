package de.Code7.Clans;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.Code7.Interface.ChatManager;
import de.Code7.Main.Instructor;
import de.Code7.Main.PlayerManager;

public class CreateClan implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = null;
		if(cs instanceof Player){
			p = (Player) cs;
		} else {
			cs.sendMessage(ChatColor.RED + "You are not a human!");
			return false;
		}
		if(p != null){
		if(label.equalsIgnoreCase("clancreate")){
			if(args.length == 1){
				File clans = new File("plugins/Code7/Clan", "Clans.yml");
				FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);

				File money = new File("plugins/Code7/ATM", "ATM.yml");
				FileConfiguration getmoney = YamlConfiguration.loadConfiguration(money);
			
				int pmoney = getmoney.getInt("Money." + PlayerManager.getUUID(p.getName()));
				if(!clancheck.contains("Clans." + args[0])){
					if(pmoney > 500){
						if(ChatManager.getClan(p) == null){
						args[0] = args[0].replace(".", "");
						args[0] = args[0].replace(",", "");
						args[0] = args[0].replace("?", "");
						getmoney.set("Money." + PlayerManager.getUUID(p.getName()), pmoney - 500);
						clancheck.set("Clans." + args[0] + ".Owner", PlayerManager.getUUID(p.getName()).toString());
						//clancheck.set(("Clans." + args[0] + "." + p.getPlayer().getUniqueId().toString()), "on");
						clancheck.set(PlayerManager.getUUID(p.getName()) + "", args[0]);
						p.sendMessage(ChatColor.GREEN + "You are now Owner of clan " + ChatColor.RED + args[0]);
						p.setDisplayName(ChatColor.RED + "[" +args[0] + "] " + ChatColor.GREEN + p.getName());
						try {
							clancheck.save(clans);
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							getmoney.save(money);
						} catch (IOException e) {
							e.printStackTrace();
						}
						return true;
						} else {
							p.sendMessage(ChatColor.RED + "You are already in Clan: " + ChatManager.getClan(p));
						}
					} else {
							p.sendMessage(ChatColor.RED + "You need 500 Money at your ATM Account!");
					}
				} else {
					p.sendMessage(ChatColor.RED + "Clan already exists!");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "Wrong count of Arguments!");
				return true;
			}
		}
		
		else if(label.equalsIgnoreCase("clandelete")){
			if(args.length == 1){
				File clans = new File("plugins/Code7/Clan", "Clans.yml");
				FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);
			
				if(clancheck.contains("Clans." + args[0])){
					if(clancheck.getString("Clans." + args[0] + ".Owner").equals(PlayerManager.getUUID(p.getName()).toString())){
						clancheck.set("Clans." + args[0], null);
						clancheck.set(PlayerManager.getUUID(p.getName()) + "", null);
						p.sendMessage(ChatColor.GREEN + "Clan removed: " + ChatColor.RED + args[0]);
						p.setDisplayName(p.getName());
						try {
							clancheck.save(clans);
						} catch (IOException e) {
							e.printStackTrace();
						}
						return true;
					}
				} else {
					p.sendMessage(ChatColor.RED + "Clan did not exists!");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "Wrong count of Arguments!");
				return true;
			}
		}
		else if(label.equalsIgnoreCase("claninvite")){
			if(args.length == 2){
				File clans = new File("plugins/Code7/Clan", "Clans.yml");
				FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);
			
				if(clancheck.contains("Clans." + args[0])){
					if(clancheck.getString("Clans." + args[0] + ".Owner").equals(PlayerManager.getUUID(p.getName()).toString())){
						if(Bukkit.getServer().getPlayer(args[1]) != null){
							p.sendMessage(ChatColor.GREEN + "You invited: " + ChatColor.RED + args[1]);
							Player other = Bukkit.getServer().getPlayer(args[1]);
							other.sendMessage(ChatColor.GREEN + "You have been invited to Clan: " + ChatColor.RED + args[0] + ChatColor.GREEN + "! Accept with /clanaccept [clanname]");
							Instructor.invite.put(other.getName(), args[0]);
							return true;
						} else {
							return false;
						}
					}
				} else {
					p.sendMessage(ChatColor.RED + "Clan did not exists!");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "Wrong count of Arguments!");
				return true;
			}
		}
		
		else if(label.equalsIgnoreCase("clanaccept")){
			if(args.length == 1){
				File clans = new File("plugins/Code7/Clan", "Clans.yml");
				FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);
				
				if(clancheck.contains("Clans." + args[0])){
							if(args[0].equals(Instructor.invite.get(p.getName()))){
								if(ChatManager.getClan(p) == null){
								p.sendMessage(ChatColor.GREEN + "You are now in Clan " + ChatColor.RED + args[0]);
								//clancheck.set(("Clans." + args[0] + "." + p.getPlayer().getUniqueId().toString()), "on");
								clancheck.set(PlayerManager.getUUID(p.getName()) + "", args[0]);
								p.setDisplayName(ChatColor.RED + "[" +args[0] + "] " + ChatColor.GREEN + p.getName());
								Instructor.invite.remove(p.getName());
								try {
									clancheck.save(clans);
								} catch (IOException e) {
									e.printStackTrace();
								}
								return true;
							} else {
								p.sendMessage(ChatColor.RED + "You are already in Clan: " + ChatManager.getClan(p));
							}
							} else {
								p.sendMessage(ChatColor.RED + "You havent been invited by " + args[0]);
								return true;  
							}
				} else {
					p.sendMessage(ChatColor.RED + "Clan did not exists!");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "Wrong count of Arguments!");
				return true;
			}
		}
		else if(label.equalsIgnoreCase("clanleave")){
			if(args.length == 1){
				File clans = new File("plugins/Code7/Clan", "Clans.yml");
				FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);
				
				if(clancheck.contains("Clans." + args[0])){
				if(clancheck.getString("Clans." + args[0] + ".Owner").equals(p.getPlayer().getUniqueId().toString())){
					clancheck.set("Clans." + args[0], null);
					clancheck.set(PlayerManager.getUUID(p.getName()) + "", null);
					p.sendMessage(ChatColor.GREEN + "Clan removed: " + ChatColor.RED + args[0]);
					p.setDisplayName(p.getName());
					try {
						clancheck.save(clans);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
				} 
				if(clancheck.contains(PlayerManager.getUUID(p.getName()) + "")){
						if(ChatManager.getClan(p).equals(args[0])){
							//clancheck.set("Clans." + args[0] + "." + PlayerManager.getUUID(p.getName()).toString(), null);
							clancheck.set(PlayerManager.getUUID(p.getName()) + "", null);
							p.sendMessage(ChatColor.GREEN + "Clan left: " + ChatColor.RED + args[0]);
							p.setDisplayName(p.getName());
							try {
								clancheck.save(clans);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						} 
				} else {
					p.sendMessage(ChatColor.RED + "Clan did not exists!");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "Wrong count of Arguments!");
				return true;
			}
		}
		}
		return false;
	}
	
}
