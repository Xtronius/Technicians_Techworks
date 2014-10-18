package mod.xtronius.ttm.tileEntity;

import java.util.ArrayList;

import mod.xtronius.ttm.util.Coord;
import mod.xtronius.ttm.util.DirHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPSIContainer extends TickingTileEntity implements IPSIContainer{
	
	public ForgeDirection[] connections = new ForgeDirection[6];
	public ArrayList<IPSIContainer> containers = new  ArrayList<IPSIContainer>();
	
	private double currentPSI = 0.0;
	private final double PSI_CAP = 1000000000000.0;

	protected void intervalUpdate() {
		if(!this.worldObj.isRemote) {
			this.findLocalContainers();
			this.ballancePSI();
		}
	}
	
	public boolean canUpdate() {
        return true;
    }
	
	public float getDelay() {
		return 0.05f;
	}
	
	public void updateConnections(ForgeDirection[] connections) {
		this.connections = connections;
	}
	
	private void findLocalContainers() {
		for(int i = 0; i < connections.length; i++) {
			if(connections[i] != null) {
				Coord coord = DirHelper.getCoordInDir(connections[i], this.xCoord, this.yCoord, this.zCoord);
				TileEntity tileEntity = this.worldObj.getTileEntity(coord.getX(), coord.getY(), coord.getZ());
				IPSIContainer container = tileEntity instanceof IPSIContainer ? (IPSIContainer) tileEntity : null;
				if(container != null);
					this.containers.add(container);
			}
		}
	}
	
	public void ballancePSI() {
		for(IPSIContainer container : this.containers) {
			if(container != null)
				this.transPSI(this, container, (Math.abs(this.getCurrentPSI()-container.getCurrentPSI())/2));
		}
	}
	
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
	
	public boolean canTransPSI(IPSIContainer container1, IPSIContainer container2, double ammount) {
		if(container1 != null && container2 != null) {
			double reducedPSI = Math.max(container1.getCurrentPSI(), container2.getCurrentPSI()) - ammount;
			double increasedPSI = Math.min(container1.getCurrentPSI(), container2.getCurrentPSI()) + ammount;
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

	@Override
	public void addPSI(double psi) {
		this.setCurrentPSI(this.getCurrentPSI() + psi);
	}
	
}
