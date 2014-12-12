package de.Code7.PvP;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import de.Code7.Main.Instructor;

public class DontHurtInRegion implements Listener{
	
	@EventHandler
	public void onHurt(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Instructor.IVA.contains(p)){
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}
		}
	}
	@EventHandler
	public void onHurtTwo(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Instructor.IVA.contains(p)){
				e.setCancelled(true);
			} else {
				if(Instructor.IVA.contains(e.getDamager())){
					e.setCancelled(true);
				} else {
					e.setCancelled(false);
				}
			}
		}
	}
}
