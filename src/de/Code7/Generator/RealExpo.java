package de.Code7.Generator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.util.Vector;

public class RealExpo implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityExplode(EntityExplodeEvent event){
		if(event.isCancelled() == false){
		if(event.getEntityType().equals(EntityType.CREEPER)){
			event.setCancelled(true);
		} else {
			for(Block b : event.blockList()){
				float x = (float) - 2 + (float) (Math.random() * ((2- -2)+1));
				float y = (float) - 3 + (float) (Math.random() * ((3- -3)+1));
				float z = (float) - 2 + (float) (Math.random() * ((2- -2)+1));
			
				FallingBlock fallingBlock = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
				fallingBlock.setDropItem(false);
				fallingBlock.setVelocity(new Vector(x,y,z));
				b.setType(Material.AIR);
			}
		}
		}
	}
}
