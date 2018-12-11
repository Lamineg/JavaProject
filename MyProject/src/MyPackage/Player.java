package MyPackage;

public class Player {
	
	int x_pos;
	int y_pos;
	
	// skill attributs
	
	int looksSkills;
	int danceSkills;
	
	// energy
	
	int energy;
	
	Player(){
		x_pos = 0;
		y_pos = 0;
		
		looksSkills = 10;
		danceSkills = 10;
		
		energy = 50;
		
	}
	
	void goUp() {
		
		y_pos = y_pos+1;
	
	}
	
	void move(int x1, int y1) {
		x_pos = x_pos + x1;
		y_pos = y_pos + y1;
		loseEnergy(x1,y1);
	}
	
	void loseEnergy(int x, int y) {
		
		energy = energy - x - y ;
	}

}
