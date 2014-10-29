package mod.xtronius.ttm.block.psi.transport;

import mod.xtronius.ttm.block.TTMBlockContainer;
import mod.xtronius.ttm.lib.RenderTypes;
import mod.xtronius.ttm.tileEntity.psi.TileEntityPipe;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPipe extends TTMBlockContainer {

	public BlockPipe() {
		super(Material.ground);
		this.useNeighborBrightness = true;
		float pixel = 1F / 16F;
		this.setBlockBounds(10.5F * pixel / 2, 10.5F * pixel / 2, 10.5F * pixel / 2, 1 - 10.5F * pixel / 2, 1 - 10.5F * pixel / 2, 1 - 10.5F * pixel / 2);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		
		TileEntity tileEntity = null;
		
		try {
			tileEntity = blockAccess.getTileEntity(x, y, z);
		} catch(Exception e) {}
		
		TileEntityPipe pipe = null;
		
		if(tileEntity instanceof TileEntityPipe)
			pipe = (TileEntityPipe) tileEntity;
		
		if(pipe == null) return;

		float pixel = 1F / 16F;

		float minX = 11F * pixel / 2;
		float minY = 11F * pixel / 2;
		float minZ = 11F * pixel / 2;
		float maxX = 1 - 11F * pixel / 2;
		float maxY = 1 - 11F * pixel / 2;
		float maxZ = 1 - 11F * pixel / 2;

		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);

		boolean u = false;
		boolean d = false;
		boolean n = false;
		boolean s = false;
		boolean e = false;
		boolean w = false;

		for (int h = 0; h < pipe.connections.length; h++) {
			if (pipe.connections[h] != null) {
				if (pipe.connections[h].equals(ForgeDirection.UP))
					u = true;
				if (pipe.connections[h].equals(ForgeDirection.DOWN))
					d = true;
				if (pipe.connections[h].equals(ForgeDirection.NORTH))
					n = true;
				if (pipe.connections[h].equals(ForgeDirection.SOUTH))
					s = true;
				if (pipe.connections[h].equals(ForgeDirection.EAST))
					e = true;
				if (pipe.connections[h].equals(ForgeDirection.WEST))
					w = true;
			}
		}

		if (w)
			this.minX = 0;
		if (d)
			this.minY = 0;
		if (n)
			this.minZ = 0;
		if (e)
			this.maxX = 1;
		if (u)
			this.maxY = 1;
		if (s)
			this.maxZ = 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityPipe();
	}

	public int getRenderType() {
		return RenderTypes.BLOCK_PIPE;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
