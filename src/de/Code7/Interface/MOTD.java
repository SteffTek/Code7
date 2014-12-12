package de.Code7.Interface;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener{

	List<String> list = new ArrayList<String> ();
	
	@EventHandler
	public void listPing(ServerListPingEvent e){
		
		int i = (int) (Math.random()*4+1);
		
		if(i == 1){
			e.setMotd(ChatColor.WHITE + "[" + ChatColor.GRAY + "Code7" + ChatColor.WHITE + "]:" + ChatColor.YELLOW + " Welcome to Code7 RPG GameMode!");
		}
		if(i == 2){
			e.setMotd(ChatColor.WHITE + "[" + ChatColor.GRAY + "Code7" + ChatColor.WHITE + "]:" + ChatColor.YELLOW + " Check out our ATM- and Shopsystem!");
		}
		if(i == 3){
			e.setMotd(ChatColor.WHITE + "[" + ChatColor.GRAY + "Code7" + ChatColor.WHITE + "]:" + ChatColor.YELLOW + " Join and have FUN!");
		}
		if(i == 4){
			e.setMotd(ChatColor.WHITE + "[" + ChatColor.GRAY + "Code7" + ChatColor.WHITE + "]:" + ChatColor.YELLOW + " There are " + (e.getMaxPlayers() - e.getNumPlayers()) + " Slots free. Join now!");
		}
	}
}
