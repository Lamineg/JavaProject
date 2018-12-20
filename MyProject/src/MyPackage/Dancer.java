package MyPackage;

public class Dancer {

	//initializes variables for the position and threshold of the dancer
	private int x;
	private int y;
	private int danceThreshold;

	//creates an object dancer
	Dancer(int x, int y, int danceThreshold) {
		this.x = x;
		this.y = y;
		this.danceThreshold = danceThreshold;
	}
	
	//getters
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
