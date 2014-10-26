package mod.xtronius.ttm.tileEntity;

import java.util.ArrayList;

import mod.xtronius.ttm.multipart.PipePart;
import mod.xtronius.ttm.tileEntity.psi.IConnectable;
import mod.xtronius.ttm.tileEntity.psi.IPSIContainer;
import mod.xtronius.ttm.tileEntity.psi.IPipe;
import mod.xtronius.ttm.util.Coord;
import mod.xtronius.ttm.util.DirHelper;
import mod.xtronius.ttm.util.ITimer;
import mod.xtronius.ttm.util.IntervalUpdater;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.multipart.TCuboidPart;
import codechicken.multipart.TileMultipart;

public class TileEntityPipe extends TileEntity implements IPSIContainer, IPipe, ITimer {

	public ForgeDirection[] connections = new ForgeDirection[6];
	public ArrayList<IPSIContainer> containers = new  ArrayList<IPSIContainer>();
	
	private double currentPSI = 0.0;
	private final double PSI_CAP = 1000000000000.0;
	/** The rate at which the psi is transfered */
	private double transRate = 64.00;
	
	private IntervalUpdater timer;
	
	public TileEntityPipe() {
		timer = new IntervalUpdater(this);
		timer.setDelay(0.05F);
	}
	
	@Override
	public void updateEntity() {
		timer.updateTimer();
	}
	
	@Override
	public void intervalUpdate(int currentTick) {
		
		updateConnections();
		
		if(!this.worldObj.isRemote) {
			this.findLocalContainers();
			this.ballancePSI();
		}
	}
	
	@Override
	public void updateConnections() {
		for(int i = 0; i < connections.length; i++) {
			Coord coord = DirHelper.getCoordInDir(ForgeDirection.getOrientation(i), this.xCoord, this.yCoord, this.zCoord);
			TileEntity tileEntity = this.getWorldObj().getTileEntity(coord.getX(), coord.getY(), coord.getZ());
			if(tileEntity instanceof IPSIContainer || tileEntity instanceof IConnectable) {
				this.connections[i] = ForgeDirection.getOrientation(i);
			} else if (tileEntity instanceof TileMultipart) {
				
				TileMultipart part = (TileMultipart) tileEntity;
				scala.collection.Iterator parts = part.partList().iterator();
			    while (parts.hasNext()) {
			      if(parts.next() instanceof PipePart) {
			    	  this.connections[i] = ForgeDirection.getOrientation(i);
			    	  break;
			      }
			    }
				
			} else this.connections[i] = null;
		}
	}
	
	@Override
	public ForgeDirection[] getConnections() {
		return this.connections;
	}
	
	
	private void findLocalContainers() {
		for(int i = 0; i < connections.length; i++) {
			if(connections[i] != null) {
				Coord coord = DirHelper.getCoordInDir(connections[i], this.xCoord, this.yCoord, this.zCoord);
				TileEntity tileEntity = this.worldObj.getTileEntity(coord.getX(), coord.getY(), coord.getZ());
				IPSIContainer container = tileEntity instanceof IPSIContainer ? (IPSIContainer) tileEntity : null;
				if(container != null)
					this.containers.add(container);
				else if(tileEntity instanceof TileMultipart) {
					scala.collection.Iterator parts = ((TileMultipart) tileEntity).partList().iterator();
					
					while(parts.hasNext()) {
						TCuboidPart part = (TCuboidPart) parts.next();
						PipePart pipe = part instanceof PipePart ? (PipePart) part : null;
						if(pipe != null) {
							this.containers.add(pipe);
							break;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        
        NBTTagCompound blockDataTag = (NBTTagCompound) compound.getTag("BlockData");
        
        for(int i = 0; i < this.connections.length; i++) {
        	if(blockDataTag.hasKey(String.valueOf(i))) 
        		this.connections[i] = ForgeDirection.getOrientation(blockDataTag.getByte(String.valueOf(i)));
        }
    }

	@Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        
        NBTTagCompound blockDataTag = new NBTTagCompound();
        
        for(int i = 0; i < this.connections.length; i++) {
        	if(this.connections[i] != null)
        		blockDataTag.setByte(String.valueOf(i), (byte)this.connections[i].ordinal());
        }
        
        compound.setTag("BlockData", blockDataTag);
    }

	
	@Override
	public void ballancePSI() {
		for(IPSIContainer container : this.containers) {
			if(container != null) {
				double dPSI = Math.abs(this.getCurrentPSI() - container.getCurrentPSI());
				this.transPSI(this, container, dPSI/2.0);
			}
		}
	}
	
	@Override
	public boolean transPSI(IPSIContainer container1, IPSIContainer container2, double psi) {
		if(this.canTransPSI(container1, container2, psi)) {
			if(container1.getCurrentPSI() > container2.getCurrentPSI()) {
				container1.setCurrentPSI(container1.getCurrentPSI() - psi);
				container2.setCurrentPSI(container2.getCurrentPSI() + psi);
			} else if(container1.getCurrentPSI() < container2.getCurrentPSI()) {
				container2.setCurrentPSI(container2.getCurrentPSI() - psi);
				container1.setCurrentPSI(container1.getCurrentPSI() + psi);
			} else {
				return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canTransPSI(IPSIContainer container1, IPSIContainer container2, double ammount) {
		if(container1 != null && container2 != null) {
			double reducedPSI = Math.max(container1.getCurrentPSI(), container2.getCurrentPSI()) - ammount;
			double increasedPSI = Math.min(container1.getCurrentPSI(), container2.getCurrentPSI()) + ammount;
			return reducedPSI >= 0.0 && increasedPSI <= this.PSI_CAP;
		}
		return false;
	}
	
	@Override
	public double getCurrentPSI() {
		return currentPSI;
	}

	@Override
	public void setCurrentPSI(double currentPSI) {
		this.currentPSI = currentPSI;
	}

	@Override
	public void addPSI(double psi) {
		this.setCurrentPSI(this.getCurrentPSI() + psi);
	}
}
