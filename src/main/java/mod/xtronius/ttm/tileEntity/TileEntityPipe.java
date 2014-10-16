package mod.xtronius.ttm.tileEntity;

import java.util.ArrayList;

import mod.xtronius.ttm.util.Coord;
import mod.xtronius.ttm.util.DirHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPipe extends TickingTileEntity implements IConnectable{

	public ArrayList<TileEntityPipe> pipes = new  ArrayList<TileEntityPipe>();
	public ForgeDirection[] connections = new ForgeDirection[6];
	private double currentPSI = 0.0;
	private final double PSI_CAP = 1000000.0;
	
	public TileEntityPipe() {
		this.currentPSI = this.yCoord;
	}
	
	public void updateEntity() {super.updateEntity();}
	
	protected void intervalUpdate() {
		if(!this.worldObj.isRemote) {
			this.findLocalPipes();
			this.ballancePSI();
		}
	}
	
	public boolean canUpdate() {
        return true;
    }
	
	public float getDelay() {
		return 0.05f;
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
		
//		for(int i = 0; i < connections.length; i++) {
//			Coord coord = DirHelper.getCoordInDir(ForgeDirection.getOrientation(i), this.xCoord, this.yCoord, this.zCoord);
//			TileEntity tileEntity = this.worldObj.getTileEntity(coord.getX(), coord.getY(), coord.getZ());
//			if(tileEntity instanceof TileEntityPipe || tileEntity instanceof IInventory || tileEntity instanceof ISidedInventory)
//				this.connections[i] = ForgeDirection.getOrientation(i);
//			else this.connections[i] = null;
//		}
	}
	
	public boolean isOnlyConnectedOnOneAxis(ForgeDirection[] directions) {
		return DirHelper.doDirsOnlyPertainToOneAxis(directions);
	}
	
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection) {
		return DirHelper.isOpposite(firstDirection, secondDirection);
	}
	
	private void findLocalPipes() {
		for(int i = 0; i < connections.length; i++) {
			if(connections[i] != null) {
				Coord coord = DirHelper.getCoordInDir(connections[i], this.xCoord, this.yCoord, this.zCoord);
				TileEntity tileEntity = this.worldObj.getTileEntity(coord.getX(), coord.getY(), coord.getZ());
				TileEntityPipe pipe = tileEntity instanceof TileEntityPipe ? (TileEntityPipe) tileEntity : null;
				if(pipe != null);
					this.pipes.add(pipe);
			}
		}
	}

	public void ballancePSI() {
//		double ration = ((this.currentPSI - baseBuffer)/this.pipes.size());
		for(TileEntityPipe pipe : this.pipes) {
			if(pipe != null)
				this.transPSI(this, pipe, (Math.abs(this.getCurrentPSI()-pipe.getCurrentPSI())/2));
			
//			System.out.println("Y: " + this.yCoord + " " + this.getCurrentPSI() + " " + pipe);
		}
	}
	
	public boolean transPSI(TileEntityPipe pipe1, TileEntityPipe pipe2, double ammount) {
		if(this.canTransPSI(pipe1, pipe2, ammount)) {
			if(pipe1.getCurrentPSI() > pipe2.getCurrentPSI()) {
				pipe1.setCurrentPSI(pipe1.getCurrentPSI() - ammount);
				pipe2.setCurrentPSI(pipe2.getCurrentPSI() + ammount);
			} else if(pipe1.getCurrentPSI() < pipe2.getCurrentPSI()) {
				pipe2.setCurrentPSI(pipe2.getCurrentPSI() - ammount);
				pipe1.setCurrentPSI(pipe1.getCurrentPSI() + ammount);
			} else {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean canTransPSI(TileEntityPipe pipe1, TileEntityPipe pipe2, double ammount) {
		if(pipe1 != null && pipe2 != null) {
			double reducedPSI = Math.max(pipe1.getCurrentPSI(), pipe2.getCurrentPSI()) - ammount;
			double increasedPSI = Math.min(pipe1.getCurrentPSI(), pipe2.getCurrentPSI()) + ammount;
			return reducedPSI >= 0.0 && increasedPSI <= this.PSI_CAP;
		}
		return false;
	}
	
	public double getCurrentPSI() {
		return currentPSI;
	}

	public void setCurrentPSI(double currentPSI) {
		this.currentPSI = currentPSI;
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
