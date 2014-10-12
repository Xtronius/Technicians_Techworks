package mod.xtronius.ttm.handlers;

import mod.xtronius.ttm.lib.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init() {
//	 MyMod.network.sendToServer(new MyMessage("foobar"));
//	 MyMod.network.sendTo(new SomeMessage(), somePlayer);
    }
}
