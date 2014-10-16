package mod.xtronius.ttm.item;

import mod.xtronius.ttm.tileEntity.TileEntityPipe;
import mod.xtronius.ttm.util.ColorHelper;
import mod.xtronius.ttm.util.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemPSIMeter extends Item {

	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
		
		if(!world.isRemote) {
			TileEntity tileEntity = world.getTileEntity(x, y, z);
			
			if(tileEntity instanceof TileEntityPipe) {
				TileEntityPipe pipe = (TileEntityPipe) tileEntity;
				
				if(!player.isSneaking()) {
					PlayerHelper.sendPlayerMessage(player, "Currently Contained pressure: " + ColorHelper.GREEN + Math.round(pipe.getCurrentPSI()) + " PSI" + " (Rounded)");
				} else {
					pipe.setCurrentPSI(pipe.getCurrentPSI() + 10);
					PlayerHelper.sendPlayerMessage(player, "Currently Contained pressure: " + pipe.getCurrentPSI() + " PSI" + " (Exact)");
				}
			}
		}
		
		return false;
	}
	
	public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.block;
    }
}
