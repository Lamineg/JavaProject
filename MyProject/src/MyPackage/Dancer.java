package MyPackage;

public class Dancer {

	private int id;
	private int x;
	private int y;
	private int danceThreshold;

	Dancer(int x, int y, int id, int danceThreshold) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.danceThreshold = danceThreshold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getDanceThreshold() {
		return danceThreshold;
	}

	public void setDanceThreshold(int danceThreshold) {
		this.danceThreshold = danceThreshold;
	}

}
