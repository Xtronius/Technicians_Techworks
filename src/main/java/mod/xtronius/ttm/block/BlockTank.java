package mod.xtronius.ttm.block;

import mod.xtronius.ttm.tileEntity.TileEntityTank;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTank extends TTMBlockContainer{

	public BlockTank() {
		super(Material.iron);
		this.setBlockTextureName("BlockTank");
		this.useNeighborBrightness = true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityTank();
	}
	
	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return false;
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 2) world.setBlockMetadataWithNotify(x, y, z, 2, 2);//NORTH
        if (l == 0) world.setBlockMetadataWithNotify(x, y, z, 3, 2);//SOUTH
        if (l == 3) world.setBlockMetadataWithNotify(x, y, z, 4, 2);//EAST
        if (l == 1) world.setBlockMetadataWithNotify(x, y, z, 5, 2);//WEST 
    }
}
