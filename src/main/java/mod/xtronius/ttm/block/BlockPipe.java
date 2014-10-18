package mod.xtronius.ttm.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.xtronius.ttm.lib.ConfigValues;
import mod.xtronius.ttm.lib.RenderTypes;
import mod.xtronius.ttm.proxy.ClientProxy;
import mod.xtronius.ttm.tileEntity.TileEntityPipe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPipe extends TTMBlockContainer {

	public BlockPipe() {
		super(Material.ground);
		this.useNeighborBrightness = true;
		this.setTickRandomly(true);
		float pixel = 1F / 16F;
		this.setBlockBounds(10.5F * pixel / 2, 10.5F * pixel / 2, 10.5F * pixel / 2, 1 - 10.5F * pixel / 2, 1 - 10.5F * pixel / 2, 1 - 10.5F * pixel / 2);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		
		if(ConfigValues.refreshPipeConnectionListEveryTick) {
			TileEntityPipe tileEntity = (TileEntityPipe) blockAccess.getTileEntity(x, y, z);
			if(tileEntity != null)
				tileEntity.updateConnections();
		}
			

		float pixel = 1F / 16F;

		float minX = 11F * pixel / 2;
		float minY = 11F * pixel / 2;
		float minZ = 11F * pixel / 2;
		float maxX = 1 - 11F * pixel / 2;
		float maxY = 1 - 11F * pixel / 2;
		float maxZ = 1 - 11F * pixel / 2;

		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);

		TileEntityPipe container = (TileEntityPipe) blockAccess.getTileEntity(x, y, z);

		if(container != null) {
			boolean u = false;
			boolean d = false;
			boolean n = false;
			boolean s = false;
			boolean e = false;
			boolean w = false;
	
			for (int h = 0; h < container.connections.length; h++) {
				if (container.connections[h] != null) {
					if (container.connections[h].equals(ForgeDirection.UP))
						u = true;
					if (container.connections[h].equals(ForgeDirection.DOWN))
						d = true;
					if (container.connections[h].equals(ForgeDirection.NORTH))
						n = true;
					if (container.connections[h].equals(ForgeDirection.SOUTH))
						s = true;
					if (container.connections[h].equals(ForgeDirection.EAST))
						e = true;
					if (container.connections[h].equals(ForgeDirection.WEST))
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
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}
	
	public int tickRate(World world) {
        return 1;
    }
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
        this.updateConnections(world, x, y, z);
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		 this.updateConnections(world, x, y, z);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		this.updateConnections(world, x, y, z);
        return false;
    }
	
	public boolean onBlockEventReceived(World world, int x, int y, int z, int par1, int par2) {
		this.updateConnections(world, x, y, z);
        return super.onBlockEventReceived(world, x, y, z, par1, par2);
    }

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		this.updateConnections(world, x, y, z);
	}
	
	public void updateTick(World world, int x, int y, int z, Random rand) {
		this.updateConnections(world, x, y, z);
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		this.updateConnections(world, x, y, z);
    }
	
	private void updateConnections(World world, int x, int y, int z) {
		TileEntityPipe tileEntity = (TileEntityPipe) world.getTileEntity(x, y, z);
		if(tileEntity != null)
			tileEntity.updateConnections();
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
