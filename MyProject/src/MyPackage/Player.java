package MyPackage;


public class Player {
	
	//username of the player, can be used later when we display the highscores
	String username;
	
	//variable high score
	int hs;
	
	//position of the player in t and in (t-1)
	int x_pos;
	int y_pos;
	int x_old;
	int y_old;
	
	// the skills of the player 
	int looksSkills;
	int danceSkills;
	
	// energy
	int energy;
	
	//amount that will be lost or gain when encountering a penalty/bonus
	int amount;
	
	//constructor method for the object of class player, takes as argument a username, the balance between looks and dancing skills
	// and the level of difficulty chosen by the player. 
	Player(String username, int input_skills, int difficulty){
		
		//adds a ":" to the username to correctly display it one the score board afterwards
		this.username = username + ":";
		
		//initial position of the player
		x_pos = 0;
		y_pos = 0;
		x_old = 0;
		y_old = 0;
		

		//The neutral value for looks and dancing skills is set to 20 and the value changes
		//with the value that the player inputs on a scale that goes from looks to dancing skills.
		//If the player favors the left side of the scale, the looks will increase and the dancing
		//will decrease. The scale goes from 0 (completely favors the looks) to 10 (completely 
		//favors the dancing skills).

		
		looksSkills = 20 + 2*(10 - input_skills);
		danceSkills = 20 + 2*input_skills;
		
		//The neutral value for the energy is set to 30 and decreases as we increase the difficulty
		//times two
		energy = 30 - (2*difficulty) ;
	}
	
	
	//method that makes the player gain a certain amount of energy
	void gainEnergy(int amount) {
		energy = energy + amount;
	}
	 
	//method that makes the player loose a certain amount of energy
	void loseEnergy(int amount) {
		
		energy= energy - amount;
	}
	
	//method that makes the player loose a certain amount of dancing skills
	void loseDancingSkills(int amount) {
		
		danceSkills = danceSkills + amount;
	}
	
	//method that makes the player gain a certain amount of dancing skills
	void gainDancingSkills(int amount) {
		
		danceSkills= danceSkills + amount;
	}
	
	//method that makes the player loose a certain amount of looks
	void loseLooks(int amount) {
		
		looksSkills = looksSkills - amount;
	}
	
	//method that makes the player gain a certain amount of looks
	void gainLooks(int amount) {
		
		looksSkills = looksSkills + amount;
	}
	

	//getter for dancing skills
	int getDance_skills() {
		return danceSkills;
	}
	
	//method that takes two distances as argument to make the player move. It first updates the old position as the current position
	//before moving and then updates the new current position by summing the distances x1 and y1. Finally, the player looses energy
	void move1(int x1, int y1) {
		x_old = x_pos;
		y_old = y_pos;

		x_pos = x_pos + x1;
		y_pos = y_pos + y1;
		//the player looses energy, elsewhere we have implemented a way for the player to regain its energy if the move 
		//is not valid
		loseEnergy(1);
	}
	
	//getter for looks
	int getLooks() {
		return looksSkills;
	}
	
	//method to calculate the current score
	void currentScore() {
		hs = energy + looksSkills + danceSkills;
	}
	
}	


