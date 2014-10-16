package mod.xtronius.ttm.tileEntity;

import net.minecraftforge.common.util.ForgeDirection;

public interface IConnectable {
	
	/** Updates the list of connections */
	public void updateConnections();
	/** Checks to see if the connection only on one axis */
	public boolean isOnlyConnectedOnOneAxis(ForgeDirection[] directions);
	/** Checks to see if the passed pars are of opposite directions */
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection);
}
