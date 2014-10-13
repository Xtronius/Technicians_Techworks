package mod.xtronius.ttm.proxy;

import mod.xtronius.ttm.tileEntity.renderer.RenderPipe;
import mod.xtronius.ttm.tileEntity.TileEntityPipe;
import mod.xtronius.ttm.util.ClientSoundHelper;
import mod.xtronius.ttm.util.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {
	 
	 @Override
	 public void initRenderingAndTextures() {
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
