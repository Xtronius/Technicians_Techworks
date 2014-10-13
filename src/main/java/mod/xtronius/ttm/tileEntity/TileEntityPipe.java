package mod.xtronius.ttm.tileEntity;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPipe extends TileEntity {

	public ForgeDirection[] connections = new ForgeDirection[6];
	
	public TileEntityPipe() {}
	
	public void updateEntity() {
		this.updateConnections();
	}
	
	public void updateConnections() {
		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord+1, this.zCoord) instanceof ISidedInventory) connections[0] = ForgeDirection.UP;
		else connections[0] = null;
		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord-1, this.zCoord) instanceof ISidedInventory) connections[1] = ForgeDirection.DOWN;
		else connections[1] = null;
		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord-1) instanceof ISidedInventory) connections[2] = ForgeDirection.NORTH;
		else connections[2] = null;
		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord+1) instanceof ISidedInventory) connections[3] = ForgeDirection.SOUTH;
		else connections[3] = null;
		if(this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord+1, this.yCoord, this.zCoord) instanceof ISidedInventory) connections[4] = ForgeDirection.EAST;
		else connections[4] = null;
		if(this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord) instanceof TileEntityPipe || this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord) instanceof IInventory || this.worldObj.getTileEntity(this.xCoord-1, this.yCoord, this.zCoord) instanceof ISidedInventory) connections[5] = ForgeDirection.WEST;
		else connections[5] = null;
	}
	
	public boolean onlyOneOpposite(ForgeDirection[] directions) {
		ForgeDirection mainDirection = null;
		boolean isOpposite = false;
		
		for(int i = 0; i < directions.length; i++) {
			
			if(mainDirection == null && directions[i] != null) mainDirection = directions[i];
			
			if(directions[i] != null && mainDirection != directions[i]) {
				if(!isOpposite(mainDirection, directions[i])) return false;
				else isOpposite = true;
			}
		}
		
		return isOpposite;
	}
	
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection) {
		if(secondDirection.equals(firstDirection.getOpposite())) return true;
		if(firstDirection.equals(secondDirection.getOpposite())) return true;
		return false;
	}
	
	public ForgeDirection getSingleConnection(ForgeDirection[] directions) {
		ForgeDirection result = null;
		for(int i = 0; i < directions.length; i++) {
			if(directions[i] != null && result == null) result = directions[i];
			else if(directions[i] != null && result != null) return null;
		}
		return result;
	}
	
	public boolean isConnectedOnAllSides(ForgeDirection[] directions) {
		int connections = 0;
		for(int i = 0; i < directions.length; i++) {
			if(directions[i] != null) connections++;
		}
		return connections == 6;
	}
}
