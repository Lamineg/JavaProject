package MyPackage;

public class Player {

	// username of the player, can be used later when we display the highscores
	private String username;

	// variable high score
	private int hs;

	// position of the player in t and in (t-1)
	private int x_pos;
	private int y_pos;

	// the skills of the player
	private int looksSkills;
	private int danceSkills;

	// energy
	private int energy;

	private int difficulty;
	private int input_skills;

	// constructor method for the object of class player, takes as argument a
	// username, the balance between looks and dancing skills
	// and the level of difficulty chosen by the player.
	Player(String username, int input_skills, int difficulty) {

		// adds a ":" to the username to correctly display it one the score board
		// afterwards
		this.username = username + ":";

		// initial position of the player
		x_pos = 0;
		y_pos = 0;

		// The neutral value for looks and dancing skills is set to 20 and the value
		// changes
		// with the value that the player inputs on a scale that goes from looks to
		// dancing skills.
		// If the player favors the left side of the scale, the looks will increase and
		// the dancing
		// will decrease. The scale goes from 0 (completely favors the looks) to 10
		// (completely
		// favors the dancing skills).

		looksSkills = 20 + 2 * (10 - input_skills);
		danceSkills = 20 + 2 * input_skills;

		// The neutral value for the energy is set to 30 and decreases as we increase
		// the difficulty
		// times two

		energy = 100 - (5 * difficulty);

		hs = 0;

		this.difficulty = difficulty;
		this.input_skills = input_skills;
	}

	// method that makes the player loose a certain amount of energy
	public void loseEnergy(int amount) {

		energy = energy - amount;
	}

	// method that makes the player gain a certain amount of dancing skills
	public void gainDancingSkills(int amount) {

		danceSkills = danceSkills + amount;
	}

	// method that makes the player gain a certain amount of looks
	public void gainLooks(int amount) {

		this.looksSkills = this.looksSkills + amount;
	}

	// getter for dancing skills
	public int getDance_skills() {
		return danceSkills;
	}

	// method that takes two distances as argument to make the player move. It first
	// updates the old position as the current position
	// before moving and then updates the new current position by summing the
	// distances x1 and y1. Finally, the player looses energy
	public void move(int x_new, int y_new) {

		x_pos = x_new;
		y_pos = y_new;
		loseEnergy(1);
	}

	// getter for looks

	public int getLooks() {
		return looksSkills;
	}

	//calculates the current score
	public int getScore() {
		return energy + looksSkills + danceSkills;
	}

	//getters and setters
	public int getHs() {
		return hs;
	}

	public void setHs() {
		this.hs = energy + looksSkills + danceSkills;
	}

	public int getX_pos() {
		return x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}
	
	
	//method to decide if the judge is impressed or not
	public boolean judgeWon(Juror myJuror, int n_blind) {
		
		int random;
		int looksThreshold = myJuror.getLooksThreshold();
		
		//The threshold is fixed for each juror and we add some randomness to it.
		//The number of points that we add to the threshold will depend on the difficulty, 
		//the higher the difficulty, the higher the variance of the randomness
		random = (int) (Math.random() * difficulty * 10);
		
		//if the looks of the player - the points that are decreased by the blinding spots are 
		//higher than the threshold of the juror + its randomness, the player wins
		if (looksSkills - n_blind * 10 >= looksThreshold + random) {
			return true;
		} else {
			return false;
		}
	}
	
	//method to decide if the dancer is beaten or not
	public boolean danceWon(Dancer myDancer, int n_spot) {
		
		
		int random;
		int danceThreshold = myDancer.getDanceThreshold();
		
		//The threshold is fixed for each dancer and we add some randomness to it.
		//The number of points that we add to the threshold will depend on the difficulty, 
		//the higher the difficulty, the higher the variance of the randomness
		random = (int) (Math.random() * difficulty * 10);
		
		//if the dancing skills of the player minus the penalties of the number of spotlights
		//is higher than the threshold + the randomness, the player wins
		if (danceSkills - n_spot * 10 >= danceThreshold + random) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//getters
	public int getEnergy() {
		return energy;
	}

	public String getUsername() {
		return username;
	}

	//to reset when the game restarts
	public void resetEnergy() {
		this.energy = 100 - (5 * difficulty);
	}

	//to reset when the game restarts
	public void resetLooksSkills() {
		this.looksSkills = 20 + 2 * (10 - input_skills);
	}

	//to reset when the game restarts
	public void resetDanceSkills() {
		this.danceSkills = 20 + 2 * input_skills;
	}
	
	//condition to catch the trophy
	public boolean canCatchTrophy() {
		int score = getScore();
		if (score > 100 + 2 * difficulty) {
			return true;
		} else {
			return false;
		}
	}

}
