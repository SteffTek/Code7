package de.Code7.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class TerrainGeneration extends ChunkGenerator{

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world){
		return new ArrayList<BlockPopulator>();
	}

	public Location getFixedSpawnLocation(World world, Random random){	
		return new Location(world, 0, 5, 0);
	}
	
	private int coordsToInt(int x, int y, int z){
		return (x * 16 + z) * 128 + y;
	}
	
    @Override
    public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
    byte[] result = new byte[32768];
    int y = 0;
    	for(int x=0; x<16; x++){
    		for(int z=0; z<16; z++) {
    			result[coordsToInt(x,y,z)] = (byte) Material.BEDROCK.compareTo(Material.BEDROCK);
    		}
    	}
    	return result;
    }
    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }
}