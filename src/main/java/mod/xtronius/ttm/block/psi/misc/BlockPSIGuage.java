package mod.xtronius.ttm.block.psi.misc;

import mod.xtronius.ttm.block.TTMBlockContainer;
import mod.xtronius.ttm.lib.Reference;
import mod.xtronius.ttm.tileEntity.psi.TileEntityPSIGuage;
import mod.xtronius.ttm.tileEntity.psi.TileEntityPipe;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPSIGuage extends TTMBlockContainer{

	public BlockPSIGuage() {
		super(Material.rock);
		this.setBlockTextureName("BlockPSIGuage");
		this.useNeighborBrightness = true;
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		if (meta == 0) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);//DOWN
		else if (meta == 1) this.setBlockBounds(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);//UP
		else if (meta == 2) this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);//NORTH
		else if (meta == 3) this.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);//SOUTH
		else if (meta == 4) this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);//EAST
		else if (meta == 5) this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 1.0F, 1.0F);//WEST
		
		else this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityPSIGuage();
	}
	
	public int getRenderType() {
		return 0;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return true;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return false;
    }
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
//		int k = MathHelper.floor_double((double)(player.rotationPitch * 3.0F / 360.0F) + 0.5D) & 3;
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

//        if(k == 0) {
	        if (l == 2) world.setBlockMetadataWithNotify(x, y, z, 2, 2);//NORTH
	        if (l == 0) world.setBlockMetadataWithNotify(x, y, z, 3, 2);//SOUTH
	        if (l == 3) world.setBlockMetadataWithNotify(x, y, z, 4, 2);//EAST
	        if (l == 1) world.setBlockMetadataWithNotify(x, y, z, 5, 2);//WEST 
//        } 
//        else if(k == 1) world.setBlockMetadataWithNotify(x, y, z, 0, 2);//DOWN 
//        else if(k == 3) world.setBlockMetadataWithNotify(x, y, z, 1, 2);//UP 
    }
	
	@SideOnly(Side.CLIENT)
    private IIcon sides;
    @SideOnly(Side.CLIENT)
    private IIcon front;
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
    	if(meta == 0 && side == 1) return this.front;
    	if(meta == 1 && side == 0) return this.front;
    	if(meta == 2 && side == 3) return this.front;
    	if(meta == 3 && side == 2) return this.front;
    	if(meta == 4 && side == 4) return this.front;
    	if(meta == 5 && side == 5) return this.front;
    	return this.sides;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.front = iconRegister.registerIcon(Reference.MOD_ASSET + ':' + this.textureName + "_Front");
        this.sides = iconRegister.registerIcon(Reference.MOD_ASSET + ':' + this.textureName + "_Side");
    }
}
