package mod.xtronius.ttm.util;

import scala.actors.threadpool.Arrays;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.ForgeDirection;

public class MultiBlock {

	private String id;
	private BlockData[] blocks;
	private ForgeDirection dir;
	
	/** width, height, and depth must be odd numbers */
	public MultiBlock(String id, ForgeDirection dir, int width, int height, int depth) {
		this.id = id;
		this.dir = dir;
		blocks = new BlockData[(width*height*depth) + 1];
	}
	
	public boolean addBlock(int index, int x, int y, int z, Block block, int meta) {
		if(blocks[index] == null) { 
			blocks[index] = new BlockData(x, y, z, block, meta);
			return true;
		}
		else return false;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BlockData[] getBlocks() {
		return blocks;
	}

	public void setBlocks(BlockData[] blocks) {
		this.blocks = blocks;
	}

	public ForgeDirection getDir() {
		return dir;
	}

	public void setDir(ForgeDirection dir) {
		this.dir = dir;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MultiBlock) {
			MultiBlock multiBlock = (MultiBlock) obj;
			if(multiBlock.getId().equals(this.getId())) 
				if(multiBlock.getDir().equals(this.getDir()))
					if(Arrays.equals(multiBlock.getBlocks(), this.getBlocks()))
						return true;
		}
		return false;
	}
}
