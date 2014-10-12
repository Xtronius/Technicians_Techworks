package mod.xtronius.ttm.handlers;

import mod.xtronius.ttm.core.TTM;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;


public class BlockRegistry {
	
	public BlockRegistry() {
		for(String name : TTM.htsmBlock.blockNames) { 
			Block block = TTM.htsmBlock.blocks.get(name);
			regBlockAuto(block, name);
		}
		
		regBlockManual();
	}

	private void regBlockAuto(Block block, String name) {	
		GameRegistry.registerBlock(block, name);
	}
	
	private void regBlockManual() {
//		GameRegistry.registerTileEntity(TileEntityExample.class, "BlockExample");
	}
}
