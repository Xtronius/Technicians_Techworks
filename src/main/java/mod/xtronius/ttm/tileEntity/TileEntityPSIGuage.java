package mod.xtronius.ttm.tileEntity;

import java.util.ArrayList;

import mod.xtronius.ttm.util.IntervalUpdater;
import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.multipart.PipePart;
import mod.xtronius.ttm.packets.PacketPSI;
import mod.xtronius.ttm.tileEntity.psi.IConnectable;
import mod.xtronius.ttm.tileEntity.psi.IPSIContainer;
import mod.xtronius.ttm.util.Coord;
import mod.xtronius.ttm.util.DirHelper;
import mod.xtronius.ttm.util.ITimer;
import mod.xtronius.ttm.util.MultiPartHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.multipart.TCuboidPart;
import codechicken.multipart.TileMultipart;

public class TileEntityPSIGuage extends TileEntity implements IConnectable, ITimer {
	
	private double currentPSI = 0.0;
	private ArrayList<IPSIContainer> containers =  new ArrayList<IPSIContainer>();
	
	private IntervalUpdater timer;
	
	public TileEntityPSIGuage() {
		timer = new IntervalUpdater(this);
		timer.setDelay(0.5F);
	}
	
	@Override
	public void updateEntity() {
		timer.updateTimer();
	}
	
	@Override
	public void intervalUpdate(int currentTick) {
		if(!this.worldObj.isRemote) {
			this.getConnectedPipePSI();
			this.sendConnectedPipePSI();
		}
	}
	
	public void getConnectedPipePSI() {
		
		this.containers.clear();
		TileEntity tileEntity;
		Coord coord;
		
		for(int i = 0; i < 6; i++) {
			coord = DirHelper.getCoordInDir(ForgeDirection.getOrientation(i), this.xCoord, this.yCoord, this.zCoord);
			
			tileEntity = this.worldObj.getTileEntity(coord.getX(), coord.getY(), coord.getZ());
			
			if(tileEntity instanceof IPSIContainer)  
				containers.add((IPSIContainer) tileEntity);
			else if(tileEntity instanceof TileMultipart) {
				
				scala.collection.Iterator parts = MultiPartHelper.getParts(tileEntity);
				while(parts.hasNext()) {
					TCuboidPart part = (TCuboidPart) parts.next();
					if(part instanceof PipePart) {
						containers.add((IPSIContainer) part);
						break;
					}
				}
			}
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
