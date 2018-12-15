package MyPackage;

public class Highscore {
	
	String username;
	int hs;
	
	public Highscore (String username, int hs) {
		this.username = username;
		this.hs = hs;
		
		
	}
	int getScore() {
		return hs;
	}
	
	
}
