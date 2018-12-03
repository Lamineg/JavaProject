package MyPackage;

public class Player {
	
	int x;
	int y;
	
	// skill attributs
	
	// energy
	
	int energy;
	
	Player(){
		x = 0;
		y = 0;
		
	}
	
	void goUp() {
		
		y = y+1;
	
	}
	
	void move(int x1, int y1) {
		x = x + x1;
		y = y + y1;
	}

}
