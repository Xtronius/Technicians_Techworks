package mod.xtronius.ttm.tileEntity.psi;

import mod.xtronius.ttm.tileEntity.psi.type.IPSIContainer;
import mod.xtronius.ttm.util.ITimer;
import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.util.DimensionalPattern;
import mod.xtronius.ttm.util.DimensionalPattern.BlockState;
import mod.xtronius.ttm.util.DimensionalPattern.Layer;
import mod.xtronius.ttm.util.DimensionalPattern.Row;
import mod.xtronius.ttm.util.IntervalUpdater;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTank extends TileEntity implements IPSIContainer, ITimer {
	
	private double currentPSI = 0.0;
	public DimensionalPattern dimensionalPattern;
	private IntervalUpdater timer;
	
	public TileEntityTank() {
		timer = new IntervalUpdater(this);
		timer.setDelay(0.05F);
	}
	
	public void updateEntity() {
		timer.updateTimer();
		if(this.dimensionalPattern == null) {
			
		 	Row row1 = DimensionalPattern.createRow("WWW");
	        Row row2 = DimensionalPattern.createRow("WVW");
	        Row row3 = DimensionalPattern.createRow("WWW");
	 
	        Row row4 = DimensionalPattern.createRow("WPW");
	        Row row5 = DimensionalPattern.createRow("PCP");
	        Row row6 = DimensionalPattern.createRow("WPW");
	 
	        Layer layer1 = DimensionalPattern.createLayer(row1, row2, row3);
	        Layer layer2 = DimensionalPattern.createLayer(row4, row5, row6);
	        Layer layer3 = DimensionalPattern.createLayer(row1, row2, row3);
	 
	        BlockState Frame = new BlockState(Character.valueOf('W'), Blocks.iron_block, 0, false);
	        BlockState Glass = new BlockState(Character.valueOf('V'), Blocks.iron_bars, 0, false);
	        BlockState Core = new BlockState(Character.valueOf('C'), TTM.Blocks.getBlockByName("BlockTank"), 0, false);
	        BlockState PSIGuage = new BlockState(Character.valueOf('P'), TTM.Blocks.getBlockByName("BlockPSIGuage"), 0, false);
	 
	        dimensionalPattern = DimensionalPattern.createPattern("Tank", layer1, layer2, layer3, Frame, Glass, Core, PSIGuage);
	        
	        this.dimensionalPattern.updatePattern("Tank", layer1, layer2, layer3, Frame, Glass, Core, PSIGuage);
		}
	}
	
	@Override
	public void intervalUpdate(int currentTick) {
		if(!this.worldObj.isRemote) {
			if(this.dimensionalPattern != null) {
				
				int x = this.xCoord-1;
				int y = this.yCoord-1;
				int z = this.zCoord-1;
				
				if (this.dimensionalPattern.hasFormed(this.worldObj, x, y, z)) 
					if(this.getCurrentPSI() < Double.MAX_VALUE) 
						this.setCurrentPSI(this.getCurrentPSI() + 1);
				else {
					this.setCurrentPSI(0.0);
				}
			}
		}
	}

	@Override
	public void ballancePSI() {}

	@Override
	public boolean transPSI(IPSIContainer container1, IPSIContainer container2, double psi) {
		return false;}

	@Override
	public boolean canTransPSI(IPSIContainer container1, IPSIContainer container2, double psi) {
		return false;
	}

	@Override
	public double getCurrentPSI() {
		return this.currentPSI;
	}

	@Override
	public void setCurrentPSI(double psi) {
		this.currentPSI = psi;
		
	}

	@Override
	public void addPSI(double psi) {
		this.setCurrentPSI(this.getCurrentPSI() + psi);
	}
}
