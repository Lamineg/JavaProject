package MyPackage;

public class BlindSpot {
	
	//initializes variables for the position of the blinding spotlight and its influence radius
	private int x;
	private int y;
	private int influenceRadius;
	
	//constructor for the blinding spotlight object
	BlindSpot(int x, int y, int radius){
		this.x = x;
		this.y = y;
		this.influenceRadius = radius;
	}

	//getters
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
