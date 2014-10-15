package mod.xtronius.ttm.proxy;

import mod.xtronius.ttm.tileEntity.renderer.RenderPipe;
import mod.xtronius.ttm.tileEntity.TileEntityPipe;
import mod.xtronius.ttm.util.ClientSoundHelper;
import mod.xtronius.ttm.util.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	public static int renderPass;
	
	public static int pipeID;
	 
	 @Override
	 public void initRenderingAndTextures() {
		 pipeID = RenderingRegistry.getNextAvailableRenderId();
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new RenderPipe());
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
