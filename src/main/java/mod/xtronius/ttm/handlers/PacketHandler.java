package mod.xtronius.ttm.handlers;

import mod.xtronius.ttm.packets.PacketPSI;
import mod.xtronius.ttm.lib.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init() {
//	 MyMod.network.sendToServer(new MyMessage("foobar"));
//	 MyMod.network.sendTo(new SomeMessage(), somePlayer);
    	INSTANCE.registerMessage(PacketPSI.Handler.class, PacketPSI.class, 0, Side.CLIENT);
    }
}
