package mod.xtronius.ttm.util;

public class Coord {

	private int x;
	private int y;
	private int z;
	
	public Coord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public Coord setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public Coord setY(int y) {
		this.y = y;
		return this;
	}

	public int getZ() {
		return z;
	}

	public Coord setZ(int z) {
		this.z = z;
		return this;
	}
}
