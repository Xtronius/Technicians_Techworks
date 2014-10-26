package mod.xtronius.ttm.util;

import java.util.ArrayList;

import mod.xtronius.ttm.core.TTM;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;

public class MultiBlockHelper {

	private static ArrayList<MultiBlock> multiBlocks = new ArrayList<MultiBlock>();
	
	
	public static void initMultiBlocks() {
		MultiBlock mbNorth = new MultiBlock("Test", ForgeDirection.NORTH, 3, 3, 3);
		{
			mbNorth.addBlock(1, -1, 0, -1, Blocks.wool, 0);
			mbNorth.addBlock(2, 0, 0, -1, TTM.Blocks.getBlockByName("BlockPSIGuage"), 1);
			mbNorth.addBlock(3, 1, 0, -1, Blocks.wool, 2);
			mbNorth.addBlock(4, -1, 0, 0, Blocks.wool, 3);
			mbNorth.addBlock(5, 0, 0, 0, Blocks.wool, 4);
			mbNorth.addBlock(6, 1, 0, 0, Blocks.wool, 5);
			mbNorth.addBlock(7, -1, 0, 1, Blocks.wool, 6);
			mbNorth.addBlock(8, 0, 0, 1, Blocks.wool, 7);
			mbNorth.addBlock(9, 1, 0, 1, Blocks.wool, 8);
		}
		
		MultiBlock mbSouth = new MultiBlock("Test", ForgeDirection.SOUTH, 3, 1, 3);
		{
			mbSouth.addBlock(1, 1, 0, 1, Blocks.wool, 0);
			mbSouth.addBlock(2, 0, 0, 1, TTM.Blocks.getBlockByName("BlockPSIGuage"), 7);
			mbSouth.addBlock(3, -1, 0, 1, Blocks.wool, 1);
			mbSouth.addBlock(4, 1, 0, 0, Blocks.wool, 2);
			mbSouth.addBlock(5, 0, 0, 0, Blocks.wool, 3);
			mbSouth.addBlock(6, -1, 0, 0, Blocks.wool, 4);
			mbSouth.addBlock(7, 1, 0, -1, Blocks.wool, 5);
			mbSouth.addBlock(8, 0, 0, -1, Blocks.wool, 6);
			mbSouth.addBlock(9, -1, 0, -1, Blocks.wool, 7);
		}
		
		MultiBlock mbWest = new MultiBlock("Test", ForgeDirection.WEST, 3, 1, 3);
		{
			mbWest.addBlock(1, 1, 0, 1, Blocks.wool, 0);
			mbWest.addBlock(2, 1, 0, 0, TTM.Blocks.getBlockByName("BlockPSIGuage"), 1);
			mbWest.addBlock(3, 1, 0, -1, Blocks.wool, 2);
			mbWest.addBlock(4, 0, 0, 1, Blocks.wool, 3);
			mbWest.addBlock(5, 0, 0, 0, Blocks.wool, 4);
			mbWest.addBlock(6, 0, 0, -1, Blocks.wool, 5);
			mbWest.addBlock(7, -1, 0, 1, Blocks.wool, 6);
			mbWest.addBlock(8, -1, 0, 0, Blocks.wool, 7);
			mbWest.addBlock(9, -1, 0, -1, Blocks.wool, 8);
		}
		
		MultiBlock mbEast = new MultiBlock("Test", ForgeDirection.EAST, 3, 1, 3);
		{
			mbNorth.addBlock(7, -1, 0, 1, Blocks.wool, 6);
			mbNorth.addBlock(4, -1, 0, 0, Blocks.wool, 3);
			mbNorth.addBlock(1, -1, 0, -1, Blocks.wool, 0);
			mbNorth.addBlock(8, 0, 0, 1, Blocks.wool, 7);
			mbNorth.addBlock(5, 0, 0, 0, Blocks.wool, 4);
			mbNorth.addBlock(2, 0, 0, -1, TTM.Blocks.getBlockByName("BlockPSIGuage"), 1);
			mbNorth.addBlock(9, 1, 0, 1, Blocks.wool, 8);
			mbNorth.addBlock(6, 1, 0, 0, Blocks.wool, 5);
			mbNorth.addBlock(3, 1, 0, -1, Blocks.wool, 2);	
		}
		
		addMultiBlock(mbNorth, 0);
		addMultiBlock(mbSouth, 1);
		addMultiBlock(mbEast, 2);
		addMultiBlock(mbWest, 3);
	}
	
	public static void addMultiBlock(MultiBlock multiBlock, int discriminator) {
			multiBlocks.add(discriminator, multiBlock);
	}

	public static ArrayList<MultiBlock> getMultiBlocks() {
		return multiBlocks;
	}

	public static void setMultiBlocks(ArrayList<MultiBlock> multiBlocks) {
		MultiBlockHelper.multiBlocks = multiBlocks;
	}
}
