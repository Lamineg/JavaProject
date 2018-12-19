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
