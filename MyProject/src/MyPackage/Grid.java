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

public class Grid extends JPanel {

	private final int CELL_SIZE = 50;
	private final int NUM_IMAGES = 8;

	private final int IMAGE_BLINDSPOT = 1;
	private final int IMAGE_SLIPTILE = 2;
	private final int IMAGE_PLAYER = 3;
	private final int IMAGE_DANCER = 4;

	private final int IMAGE_JUROR = 5;
	private final int IMAGE_TROPHY = 6;
	private final int IMAGE_WHITE = 7;
	private final int IMAGE_BLACK = 0;

	private JLabel statusBar;

	private Cell[][] cells;
	private Image[] img;

	private boolean inGame;

	// dimensions of the grid
	private int x_dim;
	private int y_dim;

	// position of the trophy
	// private int trophy_x;
	// private int trophy_y;

	private static int hs;

	// number of objects for the penalties and bonuses
	private int n_Blind;
	private int n_Juror;
	private int n_Slip;
	private int n_Dancer;

	// lists of objects
	BlindSpot[] myBlindSpot;
	Juror[] myJuror;
	SlipperyTile[] mySlipTile;
	Dancer[] myDancer;

	Player myPlayer;

	// method to construct the grid using the difficulty chosen by the player.
	Grid(int difficulty, String username, int input_skills, JLabel statusBar) {

		// set the dimensions at 15, whatever the difficulty
		this.x_dim = 10;// 16
		this.y_dim = 10;// 16

		// set the number of blinding spotlights at difficulty + 1
		this.n_Blind = difficulty + 1;

		// set the number of jurors at difficulty + 1
		this.n_Juror = 10;

		// set the number of slippery tiles at difficulty +1
		this.n_Slip = difficulty + 1;

		// set the number of dancers at difficulty + 1
		this.n_Dancer = 10;

		// create list of objects of size equal to the number of objects of that class
		myBlindSpot = new BlindSpot[n_Blind];
		myJuror = new Juror[n_Juror];
		mySlipTile = new SlipperyTile[n_Slip];
		myDancer = new Dancer[n_Dancer];

		myPlayer = new Player(username, input_skills, difficulty);

		this.statusBar = statusBar;
		this.img = new Image[NUM_IMAGES];

		this.statusBar.setFont(new Font("Arial", Font.ITALIC, 20));

		for (int i = 0; i < NUM_IMAGES; i++) {
			String path = "img/m" + i + ".gif";
			img[i] = new ImageIcon(path).getImage();
		}

		this.setDoubleBuffered(true);
		this.addMouseListener(new GameAdapter());

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

	private void initCells() {
		this.cells = new Cell[y_dim][x_dim];

		for (int i = 0; i < this.y_dim; ++i) {
			for (int j = 0; j < this.x_dim; ++j) {
				this.cells[i][j] = new Cell(j, i);
			}
		}

		this.cells[0][0].setPlayer(true);
	}

	public void newGame() {
		Random random;

		random = new Random();

		this.inGame = true;

		this.initCells();

		this.myPlayer.resetEnergy();
		this.myPlayer.resetDanceSkills();
		this.myPlayer.resetLooksSkills();
		this.myPlayer.move(0, 0);
		this.statusBar.setText("NEW GAME");

		int randX, randY;
		int remainder = 1; // 1 trophy
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

		int remainderBlind = n_Blind;
		while (remainderBlind > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setBlindSpot(true);
				myBlindSpot[n_Blind - remainderBlind] = new BlindSpot(randX, randY, 1);
				remainderBlind--;
			}
		}

		int remainderSlip = n_Slip;
		while (remainderSlip > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setSlipTile(true);
				mySlipTile[n_Slip - remainderSlip] = new SlipperyTile(randX, randY, 1);
				remainderSlip--;
			}
		}

		int remainderDancer = n_Dancer;
		while (remainderDancer > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setDancer(true);
				myDancer[n_Dancer - remainderDancer] = new Dancer(randX, randY, n_Dancer - remainderDancer, 10);
				remainderDancer--;
			}
		}

		int remainderJuror = n_Juror;
		while (remainderJuror > 0) {
			randY = random.nextInt(this.y_dim);
			randX = random.nextInt(this.x_dim);

			Cell cell = this.cells[randY][randX];
			if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()
					&& !cell.isPlayer()) {
				cell.setJuror(true);
				myJuror[n_Juror - remainderJuror] = new Juror(randX, randY, n_Juror - remainderJuror, 10);
				remainderJuror--;
			}
		}
	}

	public void paint(Graphics g) {

		for (int i = 0; i < this.y_dim; i++) {
			for (int j = 0; j < this.x_dim; j++) {
				Cell cell = this.cells[i][j];
				int imageType;
				int xPosition, yPosition;

				imageType = this.decideImageType(cell);

				xPosition = (j * CELL_SIZE);
				yPosition = (i * CELL_SIZE);

				g.drawImage(img[imageType], xPosition, yPosition, this);
			}
		}

	}

	private int decideImageType(Cell cell) {
		int imageType;

		if (!inGame) {

			if (cell.isBlindSpot()) { // replace by a case ?
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

		} else {

			if (cell.isPlayer()) {
				imageType = IMAGE_PLAYER;
			} else if (cell.isVisible(myPlayer)) {

				if (cell.isBlindSpot()) { // replace by a case ?
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

	/*
	 * public int getTrophy_x() { return trophy_x; }
	 */

	/*
	 * public void setTrophy_x(int trophy_x) { this.trophy_x = trophy_x; }
	 */

	/*
	 * public int getTrophy_y() { return trophy_y; }
	 */

	/*
	 * public void setTrophy_y(int trophy_y) { this.trophy_y = trophy_y; }
	 */

	public void setX_dim(int x_dim) {
		this.x_dim = x_dim;
	}

	public void setY_dim(int y_dim) {
		this.y_dim = y_dim;
	}

	public int getN_Blind() {
		return n_Blind;
	}

	public void setN_Blind(int n_Blind) {
		this.n_Blind = n_Blind;
	}

	public int getN_Juror() {
		return n_Juror;
	}

	public void setN_Juror(int n_Juror) {
		this.n_Juror = n_Juror;
	}

	public int getN_Slip() {
		return n_Slip;
	}

	public void setN_Slip(int n_Slip) {
		this.n_Slip = n_Slip;
	}

	public int getN_Dancer() {
		return n_Dancer;
	}

	public void setN_Dancer(int n_Dancer) {
		this.n_Dancer = n_Dancer;

	}

	public static int getHs() {
		return hs;
	}

	public void setHs(int hs) {
		Grid.hs = hs;
	}

	class GameAdapter extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			int pressedCol = e.getX() / CELL_SIZE;
			int pressedRow = e.getY() / CELL_SIZE;
			int x = myPlayer.getX_pos();
			int y = myPlayer.getY_pos();

			boolean doRepaint = false;
			Cell pressedCell;
			Cell currentCell;

			if (!inGame) {
				newGame();
				repaint();
			}

			if ((pressedCol < 0 || pressedCol >= x_dim) || (pressedRow < 0 || pressedRow >= y_dim)) {
				return;
			}

			pressedCell = cells[pressedRow][pressedCol];
			currentCell = cells[y][x];

			if (e.getButton() == MouseEvent.BUTTON3) {

				return;

			} else {
				int energy, danceSkills, looksSkills, score;

				if (myPlayer.getEnergy() <= 0) {
					inGame = false;

					statusBar.setText("You ran out of energy !");

					doRepaint = true;

				} else {

					if (pressedCell.isBlindSpot() || pressedCell.isSlipTile() || !pressedCell.isReachable(myPlayer)) {

						if (pressedCell.isBlindSpot()) {
							statusBar.setText("Invalid move");
						} else if (pressedCell.isSlipTile()) {
							statusBar.setText("Invalid move");
						} else {
							statusBar.setText("Invalid move : unreachable");
						}
						// statusBar.setText("Invalid move");
						return;

					} else if (pressedCell.equals(currentCell)) {

						energy = myPlayer.getEnergy();
						looksSkills = myPlayer.getLooks();
						danceSkills = myPlayer.getDance_skills();
						score = myPlayer.getScore();

						statusBar.setText("energy = " + energy + "; looks = " + looksSkills + "; dance = " + danceSkills
								+ "=> score : " + score);
						return;

					} else if (pressedCell.isJuror()) {

						int id = pressedCell.getJurorId(myJuror, n_Juror);
						int n_b = currentCell.countBlindSpot(myBlindSpot, n_Blind);

						if (myPlayer.judgeWon(myJuror[id], n_b)) {

							myPlayer.gainLooks(10);
							pressedCell.setJuror(false);

							pressedCell.setPlayer(true);
							currentCell.setPlayer(false);
							myPlayer.move(pressedCol, pressedRow);

							statusBar.setText("You convinced a juror");

							doRepaint = true;

						} else {

							statusBar.setText("You did not convinced a juror");

							myPlayer.loseEnergy(1);// or not ?
							return;

						}

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

							myPlayer.loseEnergy(1);// or not ?
							return;

						}

					} else if (pressedCell.isTrophy()) { // add condition to access trophy

						if (myPlayer.canCatchTrophy()) {

							inGame = false;
							pressedCell.setTrophy(false);

							pressedCell.setPlayer(true);
							currentCell.setPlayer(false);
							myPlayer.move(pressedCol, pressedRow);

							statusBar.setText("You won !");

							myPlayer.setHs();// myPlayer.getEnergy()+myPlayer.getDance_skills()+myPlayer.getLooks());

							try {
								Highscore.UpdateHighScore(myPlayer);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}

							doRepaint = true;

						} else {

							statusBar.setText("Invalid move");

							myPlayer.loseEnergy(1);// or not ?
							return;
							
						}



		

					} else {

						pressedCell.setPlayer(true);
						currentCell.setPlayer(false);
						myPlayer.move(pressedCol, pressedRow);

						energy = myPlayer.getEnergy();
						looksSkills = myPlayer.getLooks();
						danceSkills = myPlayer.getDance_skills();
						score = myPlayer.getScore();

						statusBar.setText("energy = " + energy + "; looks = " + looksSkills + "; dance = " + danceSkills
								+ "=> score : " + score);

						// statusBar.setText("energy = " + energy + "; looks = " + looksSkills + ";
						// dance = " + danceSkills);

						doRepaint = true;
					}
				}

			}

			if (doRepaint) {
				repaint();
			}
		}
	}

}
