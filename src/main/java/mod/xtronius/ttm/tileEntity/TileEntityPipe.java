package mod.xtronius.ttm.tileEntity;

import java.util.ArrayList;

import mod.xtronius.ttm.util.Coord;
import mod.xtronius.ttm.util.DirHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPipe extends TileEntityPSIContainer implements IConnectable{

	public ForgeDirection[] connections = new ForgeDirection[6];
	
	public TileEntityPipe() {}
	
	public void updateEntity() {super.updateEntity();}
	
	public void updateConnections() {
//		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord) instanceof ISidedInventory) connections[0] = ForgeDirection.UP;
//		else connections[0] = null;
//		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord) instanceof ISidedInventory) connections[1] = ForgeDirection.DOWN;
//		else connections[1] = null;
//		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1) instanceof ISidedInventory) connections[2] = ForgeDirection.NORTH;
//		else connections[2] = null;
//		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1) instanceof ISidedInventory) connections[3] = ForgeDirection.SOUTH;
//		else connections[3] = null;
//		if(this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord) instanceof ISidedInventory) connections[4] = ForgeDirection.EAST;
//		else connections[4] = null;
//		if(this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord) instanceof ISidedInventory) connections[5] = ForgeDirection.WEST;
//		else connections[5] = null;
		
		
		
		for(int i = 0; i < connections.length; i++) {
			Coord coord = DirHelper.getCoordInDir(ForgeDirection.getOrientation(i), this.xCoord, this.yCoord, this.zCoord);
			TileEntity tileEntity = this.worldObj.getTileEntity(coord.getX(), coord.getY(), coord.getZ());
			if(tileEntity instanceof TileEntityPipe || tileEntity instanceof IInventory || tileEntity instanceof ISidedInventory || tileEntity instanceof TickingTileEntity) {
				this.connections[i] = ForgeDirection.getOrientation(i);
			}
			else this.connections[i] = null;
		}
		
		super.updateConnections(this.connections);
	}
	
	public boolean isOnlyConnectedOnOneAxis(ForgeDirection[] directions) {
		return DirHelper.doDirsOnlyPertainToOneAxis(directions);
	}
	
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection) {
		return DirHelper.isOpposite(firstDirection, secondDirection);
	}
	
	public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        
        NBTTagCompound blockDataTag = (NBTTagCompound) compound.getTag("BlockData");
        
        for(int i = 0; i < this.connections.length; i++) {
        	if(blockDataTag.hasKey(String.valueOf(i))) 
        		this.connections[i] = ForgeDirection.getOrientation(blockDataTag.getByte(String.valueOf(i)));
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        NBTTagCompound blockDataTag = new NBTTagCompound();
        
        for(int i = 0; i < this.connections.length; i++) {
        	if(this.connections[i] != null)
        		blockDataTag.setByte(String.valueOf(i), (byte)this.connections[i].ordinal());
        }
        
        compound.setTag("BlockData", blockDataTag);
    }
}
