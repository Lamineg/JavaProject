package MyPackage;

public class BlindSpot {
	
	private int x;
	private int y;
	private int influenceRadius;
	
	BlindSpot(int x, int y, int radius){
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
