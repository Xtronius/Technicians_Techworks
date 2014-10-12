package mod.xtronius.ttm.core;

import mod.xtronius.ttm.CreativeTab.CreativeTabHTSMBlocks;
import mod.xtronius.ttm.CreativeTab.CreativeTabHTSMItems;
import mod.xtronius.ttm.handlers.BlockInitializer;
import mod.xtronius.ttm.handlers.BlockRegistry;
import mod.xtronius.ttm.handlers.ConfigHandler;
import mod.xtronius.ttm.handlers.EventInitializer;
import mod.xtronius.ttm.handlers.GuiHandler;
import mod.xtronius.ttm.handlers.ItemInitializer;
import mod.xtronius.ttm.handlers.ItemRegistry;
import mod.xtronius.ttm.handlers.PacketHandler;
import mod.xtronius.ttm.lib.Reference;
import mod.xtronius.ttm.proxy.IProxy;
import mod.xtronius.ttm.util.Debug;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)

public class TTM {
	
	public TTM() {}
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	
	public static IProxy proxy;
	public static BlockInitializer htsmBlock = BlockInitializer.INSTANCE;
	public static ItemInitializer htsmItem = ItemInitializer.INSTANCE;
	
	public static CreativeTabs tabItems = new CreativeTabHTSMItems(CreativeTabs.getNextID(), " Items");
	public static CreativeTabs tabBlocks = new CreativeTabHTSMBlocks(CreativeTabs.getNextID(), " Blocks");
	
	public static PacketHandler ch = new PacketHandler();
	
	public static Debug debug = new Debug();
	
	@Instance(Reference.MOD_ID)
	public static TTM INSTANCE;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		
		proxy.initPacketInfo();
		proxy.registerEntities();
		
		new BlockInitializer();
		new ItemInitializer();
		ConfigHandler.inits(event.getSuggestedConfigurationFile());
		
		new EventInitializer();
    }   
    
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		new BlockRegistry();
		new ItemRegistry();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
    	
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.initRenderingAndTextures();
    	proxy.registerKeybindings();
    	proxy.initSounds();
    	proxy.initMiscInfo();
    }
    
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
    	 MinecraftServer server = MinecraftServer.getServer();
    	 ICommandManager command = server.getCommandManager();
    	 ServerCommandManager manager = (ServerCommandManager) command;
//    	 manager.registerCommand(new ResetLvlNBT());
    }
}

