package mod.xtronius.ttm.tileEntity;

import java.util.ArrayList;

import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.packets.PacketPSI;
import mod.xtronius.ttm.util.Coord;
import mod.xtronius.ttm.util.DirHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPSIGuage extends TickingTileEntity {
	
	private double currentPSI = 0.0;
	private ArrayList<IPSIContainer> containers =  new ArrayList<IPSIContainer>();
	
	protected void intervalUpdate() {
		if(!this.worldObj.isRemote) {
			this.getConnectedPipePSI();
			this.sendConnectedPipePSI();
		}
	}
	
	public boolean canUpdate() {
        return true;
    }
	
	public float getDelay() {
		return 0.05f;
	}
	
	public void getConnectedPipePSI() {
		
		this.containers.clear();
		TileEntity tileEntity;
		Coord coord;
		
		for(int i = 0; i < 6; i++) {
			coord = DirHelper.getCoordInDir(ForgeDirection.getOrientation(i), this.xCoord, this.yCoord, this.zCoord);
			
			tileEntity = this.worldObj.getTileEntity(coord.getX(), coord.getY(), coord.getZ());
			
			if(tileEntity instanceof IPSIContainer) containers.add((IPSIContainer) tileEntity);
		}
		
		int containers = 0;
		double subTotalPSI = 0;
	
		for(IPSIContainer container : this.containers) {
			if(container != null) {
				containers++;
				subTotalPSI += container.getCurrentPSI();
			}
		}
		if(containers != 0) this.setPSI(subTotalPSI/containers);
		else this.setPSI(0.0);
	}
	
	public void setPSI(double psi) {
		this.currentPSI = psi;
	}
	
	public double getPSI() {
		return this.currentPSI;
	}
	
	public void sendConnectedPipePSI() {
		TTM.ch.INSTANCE.sendToAll(new PacketPSI(this.currentPSI, this.xCoord, this.yCoord, this.zCoord));
	}
}
