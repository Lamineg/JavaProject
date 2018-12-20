package MyPackage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 *  Class grid extends JPanel because a panel will be dedicated to the grid in the GUI
 */

public class Grid extends JPanel {

	// initialization of the graphical constants
	private static final int CELL_SIZE = 50;
	private static final int NUM_IMAGES = 8;
	private static final int GRID_SIZE = 10;

	private static final int IMAGE_BLINDSPOT = 1;
	private static final int IMAGE_SLIPTILE = 2;
	private static final int IMAGE_PLAYER = 3;
	private static final int IMAGE_DANCER = 4;

	private static final int IMAGE_JUROR = 5;
	private static final int IMAGE_TROPHY = 6;
	private static final int IMAGE_WHITE = 7;
	private static final int IMAGE_BLACK = 0;

	private JLabel statusBar;

	// matrix of Cells to represent the grid
	private Cell[][] cells;
	// array of images to store for the display of the GUI
	private Image[] img;

	// boolean value to inform if we are currently in a game
	private boolean inGame;

	// dimensions of the grid
	private int x_dim;
	private int y_dim;

	// high score value, stored at the end of game if won, zero else
	private int hs;

	// number of blinding spots, jurors, slippery tiles and dancers 
	private int n_Blind;
	private int n_Juror;
	private int n_Slip;
	private int n_Dancer;

	// storage of arrays of objects BlindSpots, etc.. 
	BlindSpot[] myBlindSpot;
	Juror[] myJuror;
	SlipperyTile[] mySlipTile;
	Dancer[] myDancer;

	// storage of the Player object
	Player myPlayer;

	// method to construct the grid using the difficulty chosen by the player.
	Grid(int difficulty, String username, int input_skills, JLabel statusBar) {

		// set the dimensions at 15, whatever the difficulty
		this.x_dim = GRID_SIZE;
		this.y_dim = GRID_SIZE;

		// the number of blinding spots and slippery tiles depends on the difficulty level
		this.n_Blind = difficulty + 1;
		this.n_Slip = difficulty + 1;

		// the number of opponents, aka dancers and jurors, is fixed to 10
		this.n_Dancer = 10;
		this.n_Juror = 10;
		
		// init of the arrays of objects, following the sizes necessary
		myBlindSpot = new BlindSpot[n_Blind];
		myJuror = new Juror[n_Juror];
		mySlipTile = new SlipperyTile[n_Slip];
		myDancer = new Dancer[n_Dancer];

		// creation of the player
		myPlayer = new Player(username, input_skills, difficulty);

		// statusbar will be useful to display messages on the GUI during the game
		this.statusBar = statusBar;
		// init of the array of images
		this.img = new Image[NUM_IMAGES];

		// set font to statusbar
		this.statusBar.setFont(new Font("Arial", Font.ITALIC, 20));

		// assign each graphical image to the array of images, with a number attributed
		for (int i = 0; i < NUM_IMAGES; i++) {
			String path = "img/m" + i + ".gif";
			img[i] = new ImageIcon(path).getImage();
		}

		this.setDoubleBuffered(true);
		
		// add a mouselistener to the panel in order to react to clicks of the mouse
		this.addMouseListener(new GameAdapter());

		// creation a new game
		this.newGame();

	}

	// getter for x_dim
	public int getX_dim() {
		return x_dim;
	}

	// getter for y_dim
	public int getY_dim() {
		return y_dim;
	}

	/*
	 *  Method to initialize the matrix of cells and create an object Cell for all tiles of the grid.
	 *  
	 */
	private void initCells() {
		this.cells = new Cell[y_dim][x_dim];

		for (int i = 0; i < this.y_dim; ++i) {
			for (int j = 0; j < this.x_dim; ++j) {
				this.cells[i][j] = new Cell(j, i);
			}
		}

		this.cells[0][0].setPlayer(true);
		// player position is set to (0,0) => upper left corner
	}

	public void newGame() {
		Random random;

		// use of a random number for the assignment of all the positions of the objects of interest
		// => blinding spots, slippery tiles, trophy, jurors, dancers
		random = new Random();

		this.inGame = true;

		this.initCells();

		// characteristics of the player are reset, in case it is the second game with the same user
		this.myPlayer.resetEnergy();
		this.myPlayer.resetDanceSkills();
		this.myPlayer.resetLooksSkills();
		this.myPlayer.move(0, 0);
		this.statusBar.setText("NEW GAME");

		
		// positioning of the 1 trophy, assure that position is not yet assigned
		int randX, randY;
		int remainder = 1;
		while (remainder > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setTrophy(true);
				remainder--;
			}
		}

		// positioning of the n_blind blind spots, assure that position is not yet assigned
		int remainderBlind = n_Blind;
		while (remainderBlind > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setBlindSpot(true);
				myBlindSpot[n_Blind - remainderBlind] = new BlindSpot(randX, randY, 1); 
				// the objects blindspot are created, with position as input, influence radius is only put to one, in all games
				remainderBlind--;
			}
		}

		// positioning of the n_slip sliptiles, assure that position is not yet assigned
		int remainderSlip = n_Slip;
		while (remainderSlip > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setSlipTile(true);
				mySlipTile[n_Slip - remainderSlip] = new SlipperyTile(randX, randY, 1);
				// the objects sliptiles are created, with position as input, influence radius is only put to one, in all games
				remainderSlip--;
			}
		}

		// positioning of the n_dancers, assure that position is not yet assigned
		int remainderDancer = n_Dancer;
		while (remainderDancer > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setDancer(true);
				myDancer[n_Dancer - remainderDancer] = new Dancer(randX, randY, 10);
				// the objects dancers are created, with position as input, dance threshold is only put to 10, in all games
				remainderDancer--;
			}
		}

		// positioning of the n_jurors, assure that position is not yet assigned
		int remainderJuror = n_Juror;
		while (remainderJuror > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setJuror(true);
				myJuror[n_Juror - remainderJuror] = new Juror(randX, randY, 10);
				// the objects jurors are created, with position as input, dance threshold is only put to 10, in all games
				remainderJuror--;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 * 
	 * Override of the paint method, called whenever the panel is paint of repaint
	 * Tells how to paint the GUI panel
	 */
	public void paint(Graphics g) {

		for (int i = 0; i < this.y_dim; i++) {
			for (int j = 0; j < this.x_dim; j++) {
				Cell cell = this.cells[i][j];
				int imageType;
				int xPosition, yPosition;

				// define the image type, only depending on the cell state
				imageType = this.decideImageType(cell);

				// tell the place on the panel to drax the image
				xPosition = (j * CELL_SIZE);
				yPosition = (i * CELL_SIZE);

				// method defined in the java library
				g.drawImage(img[imageType], xPosition, yPosition, this);
			}
		}

	}

	/*
	 * Method used to decide the image type, depending on the state of a cell
	 */
	private int decideImageType(Cell cell) {
		int imageType;

		// if not in game, i.d. when end of game because lost or won, you make everything visible
		if (!inGame) {

			// Whether a cell has a blind spot, slip tile, player, dancer, juror, trophy or nothing on it, the index of the right image is chosen
			// Note that only one type of image is possible on each cell
			if (cell.isBlindSpot()) { 
				imageType = IMAGE_BLINDSPOT;
			} else if (cell.isSlipTile()) {
				imageType = IMAGE_SLIPTILE;
			} else if (cell.isPlayer()) {
				imageType = IMAGE_PLAYER;
			} else if (cell.isDancer()) {
				imageType = IMAGE_DANCER;
			} else if (cell.isJuror()) {
				imageType = IMAGE_JUROR;
			} else if (cell.isTrophy()) {
				imageType = IMAGE_TROPHY;
			} else {
				imageType = IMAGE_WHITE;
			}

		// if still in the game, there is a difference between tiles that are visible or not
		} else {

			if (cell.isPlayer()) {
				imageType = IMAGE_PLAYER;
			} else if (cell.isVisible(myPlayer)) {

				if (cell.isBlindSpot()) { 
					imageType = IMAGE_BLINDSPOT;
				} else if (cell.isSlipTile()) {
					imageType = IMAGE_SLIPTILE;
				} else if (cell.isDancer()) {
					imageType = IMAGE_DANCER;
				} else if (cell.isJuror()) {
					imageType = IMAGE_JUROR;
				} else if (cell.isTrophy()) {
					imageType = IMAGE_TROPHY;
				} else {
					imageType = IMAGE_WHITE;
				}

			} else {
				imageType = IMAGE_BLACK;
			}
		}

		return imageType;
	}

	// access the high score value, for the writing of the .txt file
	public int getHs() {
		return hs;
	}

	// write the high score at the end of a successful game
	public void setHs(int hs) {
		this.hs = hs;
	}

	class GameAdapter extends MouseAdapter {

		/*
		 * (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
		 * Overrides a method of the java library
		 * The method tells the game how to react to the clicks of mouse.
		 * Hence, this is where the main body of the rules of the game is placed.
		 */
		public void mousePressed(MouseEvent e) {
			
			// get the position of the pressed cell
			int pressedCol = e.getX() / CELL_SIZE;
			int pressedRow = e.getY() / CELL_SIZE;
			
			// position of the cell of the player
			int x = myPlayer.getX_pos();
			int y = myPlayer.getY_pos();

			// init of a boolean, that will specify if the panel needs to be repainted, i.e. if positions of elements have changed
			boolean doRepaint = false;
			
			Cell pressedCell;
			Cell currentCell;

			// if not in a game, begin a new game and refresh => typically at the end of a previous game (won or lost)
			if (!inGame) {
				newGame();
				repaint();
			}

			// if out of bounds, do not do anything, return directly
			if ((pressedCol < 0 || pressedCol >= x_dim) || (pressedRow < 0 || pressedRow >= y_dim)) {
				return;
			}

			// init of the objects cell
			pressedCell = cells[pressedRow][pressedCol];
			currentCell = cells[y][x];

			// if right click, no impact
			if (e.getButton() == MouseEvent.BUTTON3) {

				return;

			} else {
				int energy, danceSkills, looksSkills, score;

				// if no more energy to move => end of game, and refresh, to reveal all the map
				if (myPlayer.getEnergy() <= 0) {
					inGame = false;

					statusBar.setText("You ran out of energy !");

					doRepaint = true;

				} else {

					// if invalid move, because of 1. blindspot position, 2. sliptile position 3. unreachable cell
					// does not do anything, return directly
					// Message displaying invalid move
					if (pressedCell.isBlindSpot() || pressedCell.isSlipTile() || !pressedCell.isReachable(myPlayer)) {

						// possibility of varying the message depending on the object
						if (pressedCell.isBlindSpot()) {
							statusBar.setText("Invalid move");
						} else if (pressedCell.isSlipTile()) {
							statusBar.setText("Invalid move");
						} else {
							statusBar.setText("Invalid move : unreachable");
						}
						
						return;

					// if pressedCell is the same as the currentCell, does not do anything.
					// Display message of the score of the player
					} else if (pressedCell.equals(currentCell)) {

						energy = myPlayer.getEnergy();
						looksSkills = myPlayer.getLooks();
						danceSkills = myPlayer.getDance_skills();
						score = myPlayer.getScore();

						statusBar.setText("energy = " + energy + "; looks = " + looksSkills + "; dance = " + danceSkills
								+ "=> score : " + score);
						return;

					// if juror on pressedCell, 
					} else if (pressedCell.isJuror()) {

						// find the juror id, to find its features
						int id = pressedCell.getJurorId(myJuror, n_Juror);
						// count the blind spots, that could influence the looks of the player
						int n_b = currentCell.countBlindSpot(myBlindSpot, n_Blind);

						// if judge is convinced (Depending on the threshold of the juror, the number of blinding spots, and some randomness)
						if (myPlayer.judgeWon(myJuror[id], n_b)) {

							// Win 10 points of looks : fixed for all games, independent of difficulty
							myPlayer.gainLooks(10);
							
							// Juror disapear from the map, as it has been convinced by the player's looks
							pressedCell.setJuror(false);

							// place the player on the cell where the juror was before
							pressedCell.setPlayer(true);
							// withdraw the player from the previous cell
							currentCell.setPlayer(false);
							// update position in object player
							myPlayer.move(pressedCol, pressedRow);

							statusBar.setText("You convinced a juror");

							// the panel needs repainting
							// no return, in order to access "repaint();" at the end of the method
							doRepaint = true;

						// if lost battle, still lose 1 point energy, and return, without refreshing
						} else {

							statusBar.setText("You did not convinced a juror");

							myPlayer.loseEnergy(1);
							return;

						}

					// if dancer, same as juror
					} else if (pressedCell.isDancer()) {

						int id = pressedCell.getDancerId(myDancer, n_Dancer);
						int n_s = currentCell.countSlipTile(mySlipTile, n_Slip);

						if (myPlayer.danceWon(myDancer[id], n_s)) {

							myPlayer.gainDancingSkills(10);
							pressedCell.setDancer(false);

							pressedCell.setPlayer(true);
							currentCell.setPlayer(false);
							myPlayer.move(pressedCol, pressedRow);

							statusBar.setText("You won a dance battle");

							doRepaint = true;

						} else {

							statusBar.setText("You lost a dance battle");

							myPlayer.loseEnergy(1);
							return;

						}

					// if trophy, 
					} else if (pressedCell.isTrophy()) { 

						// if enough points to catch the trophy : end of game and win 
						// Depends on the difficulty level and the current score of the player
						if (myPlayer.canCatchTrophy()) {

							// end of game
							inGame = false;
							
							// remove trophy from cell
							pressedCell.setTrophy(false);
							
							score = myPlayer.getScore();

							// put the player where the trophy was
							pressedCell.setPlayer(true);
							currentCell.setPlayer(false);
							myPlayer.move(pressedCol, pressedRow);

							// display message + score of the game
							statusBar.setText("You won ! Score : " + score);

							// set the score of the player
							myPlayer.setHs();

							// update highscore in the .txt file
							try {
								Highscore.UpdateHighScore(myPlayer);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}

							// needs repainting, revealing all the map when won
							doRepaint = true;

						// if not enough points, can not take the trophy
						} else {

							statusBar.setText("Invalid move");

							// still lose 1 point of energy
							myPlayer.loseEnergy(1);
							return;

						}

					// if not any obstacle or opponent on the cell, just update position
					} else {

						pressedCell.setPlayer(true);
						currentCell.setPlayer(false);
						myPlayer.move(pressedCol, pressedRow);

						energy = myPlayer.getEnergy();
						looksSkills = myPlayer.getLooks();
						danceSkills = myPlayer.getDance_skills();
						score = myPlayer.getScore();

						// display message of the current score
						statusBar.setText("energy = " + energy + "; looks = " + looksSkills + "; dance = " + danceSkills + "=> score : " + score);

						// needs refreshing
						doRepaint = true;
					}
				}

			}

			// block that will be entered if the panel needs refreshing : repaint(); the panel.
			if (doRepaint) {
				repaint();
			}
		}
	}

}
