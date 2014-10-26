package mod.xtronius.ttm.tileEntity.psi;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPipe {
	
	public void updateConnections();
	
	public ForgeDirection[] getConnections();
}
