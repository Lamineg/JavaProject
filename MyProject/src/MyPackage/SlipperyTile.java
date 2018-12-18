package MyPackage;

public class SlipperyTile {
	
	//x and y represent the position of the slippery tile, influence Radius represents how far the penalty can influence the player
	private int x;
	private int y;
	private int influenceRadius;
	
	//constructor, instantiates the object SlipperyTile
	SlipperyTile(int x, int y, int radius){
		this.setX(x);
		this.setY(y);
		setInfluenceRadius(radius);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getInfluenceRadius() {
		return influenceRadius;
	}

	public void setInfluenceRadius(int influenceRadius) {
		this.influenceRadius = influenceRadius;
	}

}
