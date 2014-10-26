package mod.xtronius.ttm.handlers;

import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.multipart.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;

import java.util.Arrays;

public class MultiPartHandler implements IPartFactory, IPartConverter {
	
    @Override
    public TMultiPart createPart(String name, boolean client) {
        if(name.equals("ttm_pipe")) return new PipePart();
        return null;
    }
    
    public void init() {
        MultiPartRegistry.registerConverter(this);
        MultiPartRegistry.registerParts(this, new String[]{
                "ttm_pipe"
            });
    }

    @Override
    public Iterable<Block> blockTypes() {
        return Arrays.asList(TTM.Blocks.getBlockByName("BlockPipe"));
    }

    @Override
    public TMultiPart convert(World world, BlockCoord pos) {
        Block b = world.getBlock(pos.x, pos.y, pos.z);
        if(b == TTM.Blocks.getBlockByName("BlockPipe"))
            return new PipePart();   
        return null;
    }
}
