package mod.xtronius.ttm.proxy;

import mod.xtronius.ttm.handlers.PacketHandler;
import mod.xtronius.ttm.util.KeyBindings;

public abstract class CommonProxy implements IProxy{

	public void initMiscInfo() {}

	public void initRenderingAndTextures() {}

	public void registerKeybindings() { KeyBindings.init(); }

	public void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {}

	public void initPacketInfo() { PacketHandler.init(); }

	public void initSounds() {}
	
	public void registerEntities() {}
	
}
