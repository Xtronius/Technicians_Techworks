package mod.xtronius.ttm.tileEntity;

public interface IPSIContainer {

	public void ballancePSI();
	public boolean transPSI(IPSIContainer container1, IPSIContainer container2, double psi);
	public boolean canTransPSI(IPSIContainer container1, IPSIContainer container2, double psi);
	public double getCurrentPSI();
	public void setCurrentPSI(double psi);
	public void addPSI(double psi);
}
