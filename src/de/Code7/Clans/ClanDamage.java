package de.Code7.Clans;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.Code7.Interface.ChatManager;

public class ClanDamage implements Listener{
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void ClanDMG(EntityDamageByEntityEvent e){
		
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
			Player ent = (Player) e.getEntity();
			Player dmg = (Player) e.getDamager();
			
			if(ChatManager.getClan(ent) == ChatManager.getClan(dmg)){
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}
		}
	}
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void ClanDeath(PlayerDeathEvent e){
		
		
		if(e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player){
			Player ent = (Player) e.getEntity();
			Player dmg = (Player) e.getEntity().getKiller();
			
			File clans = new File("plugins/Code7/Clan", "Clans.yml");
			FileConfiguration clancheck = YamlConfiguration.loadConfiguration(clans);
			if(ChatManager.getClan(dmg) != null){
			clancheck.set("Clans." + ChatManager.getClan(dmg) + ".Kills", clancheck.getInt("Clans." + ChatManager.getClan(dmg) + ".Kills") + 1);
			e.setDeathMessage(ChatColor.GREEN + "Player " + ChatColor.BLUE + ent.getName() + ChatColor.GREEN + " has been killed by a Member of " + ChatColor.BLUE + ChatManager.getClan(dmg) + "=>" + dmg.getName());
			} else {
				e.setDeathMessage(ChatColor.GREEN + "Player " + ChatColor.BLUE + ent.getName() + ChatColor.GREEN + " has been killed by " + dmg.getName());
			}
			
			if(ChatManager.getClan(ent) != null){
			
			//String str = ChatManager.getClan(dmg);
			String entstr = ChatManager.getClan(ent);
			
			//clancheck.set("Clans." + str + ".Kills", clancheck.getInt("Clans." + str + ".Kills") + 1);
			
			if(clancheck.getInt("Clans." + entstr + ".Kills") > 0){
				clancheck.set("Clans." + entstr + ".Kills", clancheck.getInt("Clans." + entstr + ".Kills") - 1);
			}
			
			try {
				clancheck.save(clans);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			}
		} else {
			e.setDeathMessage(ChatColor.BLUE + e.getEntity().getName() + " died!");
		}
	}
}
