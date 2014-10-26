package mod.xtronius.ttm.handlers;

import mod.xtronius.ttm.tileEntity.*;
import mod.xtronius.ttm.core.TTM;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;


public class BlockRegistry {
	
	public BlockRegistry() {
		for(String name : TTM.Blocks.blockNames) { 
			Block block = TTM.Blocks.blocks.get(name);
			regBlockAuto(block, name);
		}
		
		regBlockManual();
	}

	private void regBlockAuto(Block block, String name) {	
		GameRegistry.registerBlock(block, name);
	}
	
	private void regBlockManual() {
//		GameRegistry.registerTileEntity(TileEntityExample.class, "BlockExample");
		GameRegistry.registerTileEntity(TileEntityPipe.class,  "BlockPipe");
		GameRegistry.registerTileEntity(TileEntityPSIGuage.class,  "BlockPSIGuage");
		GameRegistry.registerTileEntity(TileEntityTank.class,  "BlockTank");
	}
}
