package de.Code7.EntityManipulation;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import de.Code7.Items.ItemsPublic;

public class DeathItem implements Listener{

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
		Entity ent = e.getEntity();
		if(!(ent.getType() == EntityType.PLAYER)){
			e.getDrops().add(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 2));
		} else {
			e.getDrops().add(ItemsPublic.CreateItem("Money","Buy nice things!", Material.PAPER, false, 4));
		}
	}
}
