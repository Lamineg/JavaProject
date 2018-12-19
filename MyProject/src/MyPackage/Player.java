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

	// amount that will be lost or gain when encountering a penalty/bonus
	int amount;

	private int difficulty;
	private int input_skills;

	// constructor method for the object of class player, takes as argument a
	// username, the balance between looks and dancing skills
	// and the level of difficulty chosen by the player.
	Player(String username, int input_skills, int difficulty) {

		// adds a ":" to the username to correctly display it one the score board
		// afterwards
		this.setUsername(username + ":");

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
		energy = 30 - (2 * difficulty);
		hs = 0;

		this.difficulty = difficulty;
		this.input_skills = input_skills;
	}

	// method to move the player in any direction but maximum of 1 step
	void move2(int x1, int y1) {

		if (Math.abs(x1) > 1 || (Math.abs(y1) > 1)) {
			System.out.println("Unreachable");
		} else {
			x_pos = x_pos + x1;
			y_pos = y_pos + y1;

		}
		loseEnergy(1);

	}

	// method that makes the player gain a certain amount of energy
	void gainEnergy(int amount) {
		energy = energy + amount;
	}

	// method that makes the player loose a certain amount of energy
	void loseEnergy(int amount) {

		energy = energy - amount;
	}

	// method that makes the player loose a certain amount of dancing skills
	void loseDancingSkills(int amount) {

		danceSkills = danceSkills + amount;
	}

	// method that makes the player gain a certain amount of dancing skills
	void gainDancingSkills(int amount) {

		danceSkills = danceSkills + amount;
	}

	// method that makes the player loose a certain amount of looks
	void loseLooks(int amount) {

		looksSkills = looksSkills - amount;
	}

	// method that makes the player gain a certain amount of looks
	void gainLooks(int amount) {

		looksSkills = looksSkills + amount;
	}

	// getter for dancing skills
	int getDance_skills() {
		return danceSkills;
	}

	// method that takes two distances as argument to make the player move. It first
	// updates the old position as the current position
	// before moving and then updates the new current position by summing the
	// distances x1 and y1. Finally, the player looses energy
	void move1(int x1, int y1) {

		x_pos = x_pos + x1;
		y_pos = y_pos + y1;
		// the player looses energy, elsewhere we have implemented a way for the player
		// to regain its energy if the move
		// is not valid
		loseEnergy(1);
	}

	void move(int x_new, int y_new) {

		x_pos = x_new;
		y_pos = y_new;
		loseEnergy(1);
	}

	void loseEnergy1(int x, int y) {

		energy = energy - x - y;

	}

	// getter for looks

	int getLooks() {
		return looksSkills;
	}

	// method to calculate the current score

	public int getScore() {
		return energy + looksSkills + danceSkills;
	}

	public int getHs() {
		return hs;
	}

	public void setHs() {
		this.hs = energy + looksSkills + danceSkills;
	}

	public int getX_pos() {
		return x_pos;
	}

	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}

	public boolean judgeWon(Juror myJuror, int n_blind) {
		// add number of blindspots

		int random;
		int looksThreshold = myJuror.getLooksThreshold();
		random = (int) (Math.random()*(FirstScreen.getDifficulty())*10);
		if (random<20) {
			random = 20;
		}
		
		if (looksSkills - n_blind >= looksThreshold + random) {
			return true;
		} else {
			return false;
		}
	}
		

	public boolean danceWon(Dancer myDancer, int n_spot) {
		// add number of slips

		int random;
		int danceThreshold = myDancer.getDanceThreshold();
		random = (int) (Math.random()*(FirstScreen.getDifficulty())*10);
		if (random<20) {
			random = 20;
		}
		

		if (danceSkills - n_spot >= danceThreshold + random) {
			return true;
		} else {
			return false;
		}
	}
	

	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void resetEnergy() {
		this.energy = 30 - (2 * difficulty);
	}

	public void resetLooksSkills() {
		this.looksSkills = 20 + 2 * (10 - input_skills);
	}

	public void resetDanceSkills() {
		this.danceSkills = 20 + 2 * input_skills;
	}

	/*
	 * public int getInputSkills() { return input_skills; }
	 * 
	 * public int getDifficulty() { return difficulty; }
	 */

}
