 package mod.xtronius.ttm.packets;

import io.netty.buffer.ByteBuf;
import mod.xtronius.ttm.tileEntity.psi.TileEntityPSIGuage;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketPSI implements IMessage{
	
	double psi;
	int x;
	int y;
	int z;
	
	public PacketPSI(){}
    
    public PacketPSI(double psi, int x, int y, int z) {
        this.psi = psi;
        this.x = x;
        this.y = y;
        this.z = z;
    }

	@Override
	public void fromBytes(ByteBuf bytes) {
		this.psi = bytes.readDouble();
		this.x = bytes.readInt();
		this.y = bytes.readInt();
		this.z = bytes.readInt();
	}

	@Override
	public void toBytes(ByteBuf bytes) {
		bytes.writeDouble(this.psi);
		bytes.writeInt(this.x);
	    bytes.writeInt(this.y);
		bytes.writeInt(this.z);
	}

	public static class Handler implements IMessageHandler<PacketPSI, IMessage> {
        
        @Override
        public IMessage onMessage(PacketPSI message, MessageContext ctx) {
        	
        	TileEntityPSIGuage tileEntity = (TileEntityPSIGuage) Minecraft.getMinecraft().theWorld.getTileEntity(message.x, message.y, message.z);
        	if(tileEntity != null) {
        		tileEntity.setPSI(message.psi);
        	}
            return null;
        }
    }
}
