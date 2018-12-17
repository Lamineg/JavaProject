package MyPackage;

public class Dancer {
	
	private int id;
	private boolean active;
	private int x;
	private int y;
	private int danceThreshold;
	
	Dancer(int x, int y, int id, int danceThreshold){
		this.setX(x);
		this.setY(y);
		this.setId(id);
		this.setDanceThreshold(danceThreshold);
		setActive(true);
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}
