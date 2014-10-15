package mod.xtronius.ttm.handlers;

import java.io.File;

import mod.xtronius.ttm.block.BlockIDs;
import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.item.ItemIDs;
import mod.xtronius.ttm.lib.ConfigValues;
import mod.xtronius.ttm.lib.Reference;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {
    public static Configuration config;

    public static void inits(File configFile) {
        if (config == null) {
        	config = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
		HandleConfigIDs();
		HandleConfigOptions();

        if (config.hasChanged())
        	config.save();
    }
    
	private static void HandleConfigIDs() {
		int idI = 20000;
		int idB = 2750;
		int idGui = 1;

		for(String name : TTM.htsmBlock.blockNames) BlockIDs.setBlockID(name, config.get("block-ids", name + "ID", idB++).getInt());  
		for(String name : TTM.htsmItem.itemNames) ItemIDs.setItemID(name, config.get("item-ids", name + "ID", idI++).getInt()); 	
	}

	private static void HandleConfigOptions() {
		ConfigValues.refreshPipeConnectionListEveryTick = config.getBoolean("Resfresh Pipe Connections List Every Tick", Reference.MOD_OPTIONS, true, "This controls the rendering of how fast the pipe block will cange its block bounds and render direction. NOTE: This will not effect the server-side list or any functionality of the pipe block, it is mainly an option for users that have computers that cannot handle updating the connections list for all of the pipes in their world every tick!");
	}

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
            loadConfiguration();
    }
}