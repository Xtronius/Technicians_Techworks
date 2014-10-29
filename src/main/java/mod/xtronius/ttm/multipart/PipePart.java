package mod.xtronius.ttm.multipart;

import java.util.ArrayList;
import java.util.Arrays;

import mod.xtronius.ttm.block.psi.transport.BlockPipe;
import mod.xtronius.ttm.core.TTM;
import mod.xtronius.ttm.tileEntity.psi.type.IConnectable;
import mod.xtronius.ttm.tileEntity.psi.type.IPSIContainer;
import mod.xtronius.ttm.tileEntity.psi.type.IPipe;
import mod.xtronius.ttm.tileEntity.renderer.RenderPipe;
import mod.xtronius.ttm.util.Coord;
import mod.xtronius.ttm.util.DirHelper;
import mod.xtronius.ttm.util.ITimer;
import mod.xtronius.ttm.util.IntervalUpdater;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.TCuboidPart;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.minecraft.McMetaPart;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PipePart extends McMetaPart implements IPipe, IPSIContainer, ITimer {
	
    public static BlockPipe pipe = (BlockPipe) TTM.Blocks.getBlockByName("BlockPipe");
    
    public ForgeDirection[] connections = new ForgeDirection[6];
    public ArrayList<IPSIContainer> containers = new  ArrayList<IPSIContainer>();
    
    private double currentPSI = 0.0;
	private final double PSI_CAP = 1000000000000.0;
	
	/** The rate at which the psi is transfered */
	private double transRate = 64.00;
    
    private final RenderPipe renderer;
    private IntervalUpdater timer;
    
    public PipePart() {
    	renderer = RenderPipe.instance;
    	timer = new IntervalUpdater(this);
		timer.setDelay(0.05F);
    }
    	
	@Override
	public void update() {
		timer.updateTimer();
	}
	
	@Override
	public boolean doesTick() {
		return true;
	}
	
	@Override
	public void intervalUpdate(int currentTick) {
		updateConnections();
		if(!this.getWorld().isRemote) {
			this.findLocalContainers();
			this.ballancePSI();
		}
	}
     
    @Override
    public Block getBlock() {
        return pipe;
    }
    
    @Override
    public String getType() {
        return "ttm_pipe";
    }
    
    @Override
    public Iterable<Cuboid6> getCollisionBoxes() {
      return Arrays.asList(new Cuboid6[] { getBounds()});
    } 

	@Override
	public Cuboid6 getBounds() {

		float pixel = 1F / 16F;

		float minX = 11F * pixel / 2;
		float minY = 11F * pixel / 2;
		float minZ = 11F * pixel / 2;
		float maxX = 1 - 11F * pixel / 2;
		float maxY = 1 - 11F * pixel / 2;
		float maxZ = 1 - 11F * pixel / 2;

		Cuboid6 blockBounds = new Cuboid6(minX, minY, minZ, maxX, maxY, maxZ);

		boolean u = false;
		boolean d = false;
		boolean n = false;
		boolean s = false;
		boolean e = false;
		boolean w = false;

		for (int h = 0; h < this.getConnections().length; h++) {
			if (this.getConnections()[h] != null) {
				if (this.getConnections()[h].equals(ForgeDirection.UP))
					u = true;
				if (this.getConnections()[h].equals(ForgeDirection.DOWN))
					d = true;
				if (this.getConnections()[h].equals(ForgeDirection.NORTH))
					n = true;
				if (this.getConnections()[h].equals(ForgeDirection.SOUTH))
					s = true;
				if (this.getConnections()[h].equals(ForgeDirection.EAST))
					e = true;
				if (this.getConnections()[h].equals(ForgeDirection.WEST))
					w = true;
			}
		}

		if (w)
			blockBounds.min.x = 0;
		if (d)
			blockBounds.min.y = 0;
		if (n)
			blockBounds.min.z = 0;
		if (e)
			blockBounds.max.x = 1;
		if (u)
			blockBounds.max.y = 1;
		if (s)
			blockBounds.max.z = 1;
		
		return blockBounds;
	}
	
	/**Finds ajacent psi containers and adds them to the containers list*/
	private void findLocalContainers() {
		for(int i = 0; i < connections.length; i++) {
			if(connections[i] != null) {
				Coord coord = DirHelper.getCoordInDir(connections[i], this.x(), this.y(), this.z());
				TileEntity tileEntity = this.getWorld().getTileEntity(coord.getX(), coord.getY(), coord.getZ());
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
	
	/**Finds connectable objects to connect to*/
	public void updateConnections() {
		for(int i = 0; i < connections.length; i++) {
			Coord coord = DirHelper.getCoordInDir(ForgeDirection.getOrientation(i), this.x(), this.y(), this.z());
			TileEntity tileEntity = this.getWorld().getTileEntity(coord.getX(), coord.getY(), coord.getZ());
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
	
	@SideOnly(Side.CLIENT)
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		renderPipe(pos.copy(), frame, pass);
	}
	
	public void renderPipe(Vector3 pos, float frame, int pass) {
		if(renderer != null && pass == 0) {
			renderer.renderTileEntityAt(this, pos.x, pos.y, pos.z, frame);
		}
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

	@Override
	public ForgeDirection[] getConnections() {
		return this.connections;
	}
	
//	public void load(NBTTagCompound compound) {
//        super.load(compound);
//        
//        NBTTagCompound blockDataTag = (NBTTagCompound) compound.getTag("BlockData");
//        
//        for(int i = 0; i < this.connections.length; i++) {
//        	if(blockDataTag.hasKey(String.valueOf(i))) 
//        		this.connections[i] = ForgeDirection.getOrientation(blockDataTag.getByte(String.valueOf(i)));
//        }
//    }
//
//    public void save(NBTTagCompound compound) {
//        super.save(compound);
//        
//        NBTTagCompound blockDataTag = new NBTTagCompound();
//        
//        for(int i = 0; i < this.connections.length; i++) {
//        	if(this.connections[i] != null)
//        		blockDataTag.setByte(String.valueOf(i), (byte)this.connections[i].ordinal());
//        }
//        
//        compound.setTag("BlockData", blockDataTag);
//    }
}
