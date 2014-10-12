package mod.xtronius.ttm.proxy;

public interface IProxy {
	
	public abstract void initMiscInfo();

    public abstract void initRenderingAndTextures();
    
    public abstract void initSounds();

    public abstract void registerKeybindings();

    public abstract void playSound(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch);
    
    public abstract void initPacketInfo();
    
    public abstract void registerEntities();
}
