package mod.xtronius.ttm.proxy;

import mod.xtronius.ttm.lib.RenderTypes;
import mod.xtronius.ttm.tileEntity.renderer.*;
import mod.xtronius.ttm.tileEntity.*;
import mod.xtronius.ttm.util.ClientSoundHelper;
import mod.xtronius.ttm.util.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	public static int renderPass;
	 
	 @Override
	 public void initRenderingAndTextures() {
		 RenderTypes.BLOCK_PSIGUAGE = RenderingRegistry.getNextAvailableRenderId();
		 RenderTypes.BLOCK_PIPE = RenderingRegistry.getNextAvailableRenderId();
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new RenderPipe());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPSIGuage.class, new RenderPSIGuage());
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new RenderPressureChamberCore());
	 }
	 
	 @Override
	 public void initSounds() {}
	 
	 @Override
	 public void registerKeybindings() { KeyBindings.init(); }
	 
	 @Override
    public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
        ClientSoundHelper.playSound(soundName, xCoord, yCoord, zCoord, volume, pitch);
    }
}
