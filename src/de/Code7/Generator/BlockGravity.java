package de.Code7.Generator;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.Code7.Interface.OpMemberManager;
import de.Code7.Main.Instructor;

public class BlockGravity implements Listener{

	ArrayList<Material> mat = new ArrayList<Material>();
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlace(BlockPlaceEvent e){
		if(!Instructor.IVA.contains(e.getPlayer()) || OpMemberManager.getRank(e.getPlayer()) == "admin"){
		if(e.isCancelled() == false){
		mat.add(Material.DIRT);
		mat.add(Material.GRASS);
		mat.add(Material.TNT);
		mat.add(Material.WEB);
		mat.add(Material.ICE);
		mat.add(Material.PACKED_ICE);
		mat.add(Material.SNOW_BLOCK);
		mat.add(Material.LEAVES);
		mat.add(Material.WOOL);
		mat.add(Material.NOTE_BLOCK);

		for(Material mati : mat){
			if((e.getBlockPlaced().getType() == mati)){
				Block block = e.getBlock();
			      block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
			      block.setType(Material.AIR);
			      block.getState().update(true);
		}
		}
		}
		} else {
			e.setCancelled(true);
		}
	}
}
