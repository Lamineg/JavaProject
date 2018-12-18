package MyPackage;

public class Juror {
	
	private int id;
	private boolean active;
	private int x;
	private int y;
	private int looksThreshold;
	
	Juror(int x, int y, int id, int looksThreshold){
		this.setX(x);
		this.setY(y);
		this.setId(id);
		this.setLooksThreshold(looksThreshold);
		setActive(true);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public int getLooksThreshold() {
		return looksThreshold;
	}

	public void setLooksThreshold(int looksThreshold) {
		this.looksThreshold = looksThreshold;
	}

}
