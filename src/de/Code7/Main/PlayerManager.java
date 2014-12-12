package de.Code7.Main;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerManager {

	public static Player getPlayer(String playerName){
		Object[] playerObjects = Bukkit.getServer().getOnlinePlayers();
		
		for(Object playerObject : playerObjects){
			Player player = (Player) playerObject;
			if(player.getName().toLowerCase().equals(playerName.toLowerCase())){
				return player;
			}
		}
		
		return null;
	}
	

	public static OfflinePlayer getOfflinePlayer(String playerName){
		OfflinePlayer[] offlinePlayers = Bukkit.getServer().getOfflinePlayers();
		
		for(OfflinePlayer aktuellerPlayer : offlinePlayers){
			if(aktuellerPlayer.getName().toLowerCase().equals(playerName.toLowerCase())){
				return aktuellerPlayer;
			}
		}
		
		return null;
	}
	

	public static UUID getUUID(String playerName){
		Player p = getPlayer(playerName);
		
		if(p != null){
			return getPlayer(playerName).getUniqueId();
		}
		
		return null;
	}

}
