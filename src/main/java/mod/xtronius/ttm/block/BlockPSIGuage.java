package mod.xtronius.ttm.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.xtronius.ttm.lib.Reference;
import mod.xtronius.ttm.lib.RenderTypes;
import mod.xtronius.ttm.proxy.ClientProxy;
import mod.xtronius.ttm.tileEntity.TileEntityPSIGuage;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPSIGuage extends TTMBlockContainer{

	public BlockPSIGuage() {
		super(Material.rock);
		this.setBlockTextureName("BlockPSIGuage");
		this.useNeighborBrightness = true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
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
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 2) world.setBlockMetadataWithNotify(x, y, z, 2, 2);//NORTH
        if (l == 0) world.setBlockMetadataWithNotify(x, y, z, 3, 2);//SOUTH
        if (l == 3) world.setBlockMetadataWithNotify(x, y, z, 4, 2);//EAST
        if (l == 1) world.setBlockMetadataWithNotify(x, y, z, 5, 2);//WEST 
    }
	
	@SideOnly(Side.CLIENT)
    private IIcon sides;
    @SideOnly(Side.CLIENT)
    private IIcon front;
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
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
