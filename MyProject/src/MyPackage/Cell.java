package MyPackage;

public class Cell {
	
	// set of boolean variables that give information about the state of the cell
	// note that because only one can be true at a time, this could be replaced by an int value between 0 and 5.
	private boolean dancer;
	private boolean juror;
	private boolean slipTile;
	private boolean blindSpot;
	private boolean trophy;
	private boolean player;

	// position x and y on the grid
	private int x;
	private int y;

	public Cell(int x, int y) {

		// all booleans are init to zero, the state will change when randomly assign positions to all opponents and obstacles
		this.dancer = false;
		this.juror = false;
		this.slipTile = false;
		this.blindSpot = false;
		this.trophy = false;
		this.player = false;

		this.x = x;
		this.y = y;

	}

	public boolean isBlindSpot() {
		return this.blindSpot;
	}

	public boolean isDancer() {
		return this.dancer;
	}

	public boolean isJuror() {
		return this.juror;
	}

	public boolean isSlipTile() {
		return this.slipTile;
	}

	public boolean isTrophy() {
		return this.trophy;
	}

	public boolean isPlayer() {
		return this.player;
	}

	/*
	 * Method that defines the visibility of the player on the map
	 * Here it is non-parametric, and only make the tiles up, down, left and right visible for any player
	 * It only depends on the position of the given player
	 */
	public boolean isVisible(Player myPlayer) {

		int playerX = myPlayer.getX_pos();
		int playerY = myPlayer.getY_pos();

		if (x == playerX && y == playerY + 1) {

			return true;

		} else if (x == playerX && y == playerY - 1) {

			return true;

		} else if (x == playerX + 1 && y == playerY) {

			return true;

		} else if (x == playerX - 1 && y == playerY) {

			return true;

		} else {

			return false;
		}

	}

	/*
	 * Method that defines the reachability of a tile with respect to a given player, depending on its position
	 * It is fixed for any game as the radius of 1, i.d. all the tiles neighboring the player
	 */
	public boolean isReachable(Player myPlayer) {

		int playerX = myPlayer.getX_pos();
		int playerY = myPlayer.getY_pos();

		if (Math.abs(x - playerX) <= 1 && Math.abs(y - playerY) <= 1) {
			return true;
		} else {
			return false;
		}

	}

	public void setBlindSpot(boolean blindSpot) {
		this.blindSpot = blindSpot;
	}

	public void setSlipTile(boolean slipTile) {
		this.slipTile = slipTile;
	}

	public void setDancer(boolean dancer) {
		this.dancer = dancer;
	}

	public void setJuror(boolean juror) {
		this.juror = juror;
	}

	public void setTrophy(boolean trophy) {
		this.trophy = trophy;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}

	/*
	 * Method that count the number of slippery tiles that have an influence on dance skills of a player on the given cell.
	 * The method is given the array of slip tiles which makes it more efficient that just look at all the cells.
	 * The slip tile is counted if the player is in its influence zone, depending on the influence radius.
	 */
	public int countSlipTile(SlipperyTile mySlipTile[], int n_Slip) {

		int n_s = 0;

		for (int j = 0; j < n_Slip; j++) {

			int radius = mySlipTile[j].getInfluenceRadius();
			int slipX = mySlipTile[j].getX();
			int slipY = mySlipTile[j].getY();

			if (Math.abs(slipX - x) <= radius && Math.abs(slipY - y) <= radius) {
				n_s++;
			}

		}

		return n_s;
	}

	/*
	 * Method that count the number of blinding spots that have an influence on looks skills of a player on the given cell.
	 * The method is given the array of blind spots which makes it more efficient that just look at all the cells.
	 * The blinding spot is counted if the player is in its influence zone, depending on the influence radius.
	 */
	public int countBlindSpot(BlindSpot myBlindSpot[], int n_Blind) {

		int n_b = 0;

		for (int j = 0; j < n_Blind; j++) {

			int radius = myBlindSpot[j].getInfluenceRadius();
			int blindX = myBlindSpot[j].getX();
			int blindY = myBlindSpot[j].getY();

			if (Math.abs(blindX - x) <= radius && Math.abs(blindY - y) <= radius) {
				n_b++;
			}
		}

		return n_b;
	}

	/*
	 * Method to find the index of the object juror concerned by the battle
	 * This is made in order to access the special features of that particular opponent
	 */
	public int getJurorId(Juror myJuror[], int n_Juror) {

		int id = 0;

		// verify for each juror, if the position corresponds to the one of the current cell
		for (int j = 0; j < n_Juror; j++) {

			id = j;
			int jurorX = myJuror[j].getX();
			int jurorY = myJuror[j].getY();

			if (jurorX == x && jurorY == y) {

				return id;

			}

		}

		return id;
	}

	/*
	 * Method to find the index of the object dancer concerned by the battle
	 * This is made in order to access the special features of that particular opponent
	 */
	public int getDancerId(Dancer myDancer[], int n_Dancer) {

		int id = 0;

		// verify for each dancer, if the position corresponds to the one of the current cell
		for (int j = 0; j < n_Dancer; j++) {

			id = j;
			int dancerX = myDancer[j].getX();
			int dancerY = myDancer[j].getY();

			if (dancerX == x && dancerY == y) {

				return id;

			}
		}

		return id;
	}

}
