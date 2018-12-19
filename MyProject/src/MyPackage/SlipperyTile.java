package MyPackage;

public class SlipperyTile {

	// x and y represent the position of the slippery tile, influence Radius
	// represents how far the penalty can influence the player
	private int x;
	private int y;
	private int influenceRadius;

	// constructor, instantiates the object SlipperyTile
	SlipperyTile(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.influenceRadius = radius;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getInfluenceRadius() {
		return influenceRadius;
	}

}
