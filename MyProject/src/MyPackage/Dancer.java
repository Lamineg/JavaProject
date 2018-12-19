package MyPackage;

public class Dancer {

	private int x;
	private int y;
	private int danceThreshold;

	Dancer(int x, int y, int id, int danceThreshold) {
		this.x = x;
		this.y = y;
		this.danceThreshold = danceThreshold;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDanceThreshold() {
		return danceThreshold;
	}

}
