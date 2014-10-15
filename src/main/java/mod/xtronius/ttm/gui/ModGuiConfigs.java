package mod.xtronius.ttm.gui;

import mod.xtronius.ttm.handlers.ConfigHandler;
import mod.xtronius.ttm.lib.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;

public class ModGuiConfigs extends GuiConfig {
    public ModGuiConfigs(GuiScreen guiScreen) {
        super(guiScreen,
                new ConfigElement(ConfigHandler.config.getCategory(Reference.MOD_OPTIONS)).getChildElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
