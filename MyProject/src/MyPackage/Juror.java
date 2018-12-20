package MyPackage;

public class Juror {

	private int x;
	private int y;
	private int looksThreshold;

	Juror(int x, int y, int looksThreshold) {
		this.x = x;
		this.y = y;
		this.looksThreshold = looksThreshold;
	}

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
