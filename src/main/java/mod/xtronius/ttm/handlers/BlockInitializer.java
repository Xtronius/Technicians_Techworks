package mod.xtronius.ttm.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import mod.xtronius.ttm.block.*;
import mod.xtronius.ttm.block.BlockIDs;
import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockInitializer {
	
	public static ArrayList<String> blockNames = new ArrayList<String>();
	public static HashMap<String, Block> blocks = new HashMap<String, Block>();
	
	public static BlockInitializer INSTANCE;

	public BlockInitializer() {
		INSTANCE = this;
		init();
	}
	
	private void init() {
		addBlock(new BlockPipe(), "BlockPipe", true);
		addBlock(new BlockPSIGuage(), "BlockPSIGuage", true);
		addBlock(new BlockTank(), "BlockTank", true);
		addBlock(new BlockTankGlass(), "BlockTankGlass", true);
		addBlock(new BlockWoodTrimmedGlass(), "BlockWoodTrimmedGlass", true);
	}
	
	private void addBlock(Block block, String name, boolean addBlockToCreativeTab) { 
		block.setBlockName(name); 
		BlockIDs.genNewBlockIDObj(name); 
		if(addBlockToCreativeTab) block.setCreativeTab(TTM.tabBlocks);
		blockNames.add(name); 
		blocks.put(name, block); 
	}
	
	public static Block getBlockByName(String name) { return blocks.get(name); }
	public static Item getBlockAsItemByName(String name) { return GameRegistry.findItem(Reference.MOD_ID, name); }
	public static Item getBlockAsItemByBlock(Block block) { return GameRegistry.findItem(Reference.MOD_ID, block.getUnlocalizedName().substring(block.getUnlocalizedName().indexOf(".") + 1)); }
}