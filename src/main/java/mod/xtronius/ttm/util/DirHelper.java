package mod.xtronius.ttm.util;

import net.minecraftforge.common.util.ForgeDirection;

public class DirHelper {

	/** Checks to see if the directions passed in the array only pertain to one axis */
	public static boolean doDirsOnlyPertainToOneAxis(ForgeDirection[] directions) {
		ForgeDirection mainDirection = null;
		boolean isOpposite = false;
		
		for(int i = 0; i < directions.length; i++) {
			if(mainDirection == null && directions[i] != null) mainDirection = directions[i];
			if(directions[i] != null && mainDirection != directions[i]) 
				if(!isOpposite(mainDirection, directions[i]))  return false;
				else isOpposite = true;
		}
		
		return isOpposite;
	}
	
	/** Checks to see if the passed directions are of opposite directions */
	public static boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection) {
		return secondDirection.equals(firstDirection.getOpposite());
	}
	
	public static Coord getCoordInDir(ForgeDirection dir, int x, int y, int z) {
		Coord result = new Coord(x, y, z);
		switch(dir) {
			case UP: return result.setY(y+1);
			case DOWN : return result.setY(y-1);
			case NORTH: return result.setZ(z-1);
			case SOUTH : return result.setZ(z+1);
			case EAST: return result.setX(x+1);
			case WEST : return result.setX(x-1);
			default : return result;
		}
	}
	
}
