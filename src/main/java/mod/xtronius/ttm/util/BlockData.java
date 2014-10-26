package mod.xtronius.ttm.util;

import net.minecraft.block.Block;

public class BlockData {
	
	private Coord coord;
	private Block block;
	private int meta;

	public BlockData(int x, int y, int z, Block block, int meta) {
		coord = new Coord(x, y, z);
		this.block = block;
		this.meta = meta;
	}
	
	public Coord getCoord() {
		return coord;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public int getX() {
		return coord.getX();
	}
	
	public void setX(int x) {
		this.coord.setX(x);
	}
	
	public int getY() {
		return coord.getY();
	}
	
	public void setY(int y) {
		this.coord.setY(y);
	}
	
	public int getZ() {
		return coord.getZ();
	}
	
	public void setZ(int z) {
		this.coord.setZ(z);
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public int getMeta() {
		return meta;
	}

	public void setMeta(int meta) {
		this.meta = meta;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BlockData) {
			BlockData data = (BlockData) obj;
			
			if(data.getCoord().equals(this.getCoord()))
				if(data.getBlock().equals(this.getBlock()))
					if(data.getMeta() == this.getMeta())
						return true;
		}
		return false;
	}
}
