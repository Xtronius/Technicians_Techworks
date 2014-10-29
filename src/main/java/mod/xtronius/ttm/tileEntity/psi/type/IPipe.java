package mod.xtronius.ttm.tileEntity.psi.type;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPipe {
	
	public void updateConnections();
	
	public ForgeDirection[] getConnections();
}
