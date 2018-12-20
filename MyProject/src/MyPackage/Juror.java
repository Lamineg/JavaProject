package MyPackage;

public class Juror {
	
	//initializes variables for the position of the juror and its threshold 
	private int x;
	private int y;
	private int looksThreshold;
	
	//creates object juror
	Juror(int x, int y, int looksThreshold) {
		this.x = x;
		this.y = y;
		this.looksThreshold = looksThreshold;
	}

	//getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLooksThreshold() {
		return looksThreshold;
	}

}
