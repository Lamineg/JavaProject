package MyPackage;

public class Juror {
	
	int id;
	boolean active;
	int x;
	int y;
	int looksThreshold;
	
	Juror(int x, int y, int id, int looksThreshold){
		this.x = x;
		this.y = y;
		this.id = id;
		this.looksThreshold = looksThreshold;
		active = true;
	}

}
