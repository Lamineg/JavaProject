package MyPackage;

public class Player {
		
	//player position
	int x_pos;
	int y_pos;
	
	// skill attributs
	
	int looks;
	int danceSkills;
	
	// energy
	
	int energy;
	
	Player(int input_skills, int difficulty){
		x_pos = 0;
		y_pos = 0;
		
		//The neutral value for looks and dancing skills is set to 30 and the value changes
		//with the value that the player inputs on a scale that goes from looks to dancing skills.
		//If the player favors the left side of the scale, the looks will increase and the dancing
		//will decrease. The scale goes from 0 (completely favors the looks) to 10 (completely 
		//favors the dancing skills.
		
		looks = 30 + (10 - input_skills);
		danceSkills = 30 - input_skills;
		
		//The neutral value for the energy is set to 30 and decreases as we increase the difficulty
		//times two
		energy = 30 - (2*difficulty) ;
		
	}
	
	void move(int x1, int y1) {
		x_pos = x_pos + x1;
		y_pos = y_pos + y1;
	}
	
	void loseEnergy() {
		
		energy--;
	}

}
