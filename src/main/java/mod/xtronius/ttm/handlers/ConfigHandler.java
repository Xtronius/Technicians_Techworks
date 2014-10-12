package mod.xtronius.ttm.handlers;

import java.io.File;

import mod.xtronius.ttm.block.BlockIDs;
import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.item.ItemIDs;
import mod.xtronius.ttm.lib.ConfigCategories;
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
		
		ConfigValues.myConfigInteger = config.getInt("My Config Integer", Configuration.CATEGORY_GENERAL, ConfigValues.myConfigInteger, 0, Integer.MAX_VALUE, "An Integer!");
		ConfigValues.myConfigString = config.getString("My Config String", Configuration.CATEGORY_GENERAL, ConfigValues.myConfigString, "A String!");
		ConfigValues.myConfigBool = config.getBoolean("My Config Bool", Configuration.CATEGORY_GENERAL, ConfigValues.myConfigBool, "A Boolean!");

        if (config.hasChanged())
        	config.save();
    }
    
	private static void HandleConfigIDs() {
		int idI = 20000;
		int idB = 2750;
		int idGui = 1;

		for(String name : TTM.htsmBlock.blockNames) BlockIDs.setBlockID(name, config.get(ConfigCategories.BLOCKIDS, name + "ID", idB++).getInt());  
		for(String name : TTM.htsmItem.itemNames) ItemIDs.setItemID(name, config.get(ConfigCategories.ITEMIDS, name + "ID", idI++).getInt()); 	
	}

	private static void HandleConfigOptions() {
		config.addCustomCategoryComment(ConfigCategories.RENDERING, "These value decide wheather or not to render and obj or particle or they control how to render an object or particle");
	}

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
            loadConfiguration();
    }
}