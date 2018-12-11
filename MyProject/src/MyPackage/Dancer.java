package MyPackage;

public class Dancer {
	
	int id;
	boolean active;
	int x;
	int y;
	int danceThreshold;
	
	Dancer(int x, int y, int id, int danceThreshold){
		this.x = x;
		this.y = y;
		this.id = id;
		this.danceThreshold = danceThreshold;
		active = true;
	}
	

}
