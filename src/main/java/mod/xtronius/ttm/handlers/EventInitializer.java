package mod.xtronius.ttm.handlers;

import cpw.mods.fml.common.FMLCommonHandler;

public class EventInitializer {
	
	public EventInitializer(){
//		FMLCommonHandler.instance().bus().register(new RCTickHandler());
		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
	}
}
