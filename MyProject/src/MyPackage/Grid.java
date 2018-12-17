package MyPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.*;

public class Grid extends JPanel {
	
	private final int CELL_SIZE  = 15;//to fix
    private final int NUM_IMAGES = 13;// to fix
	
	private JLabel statusBar;
	
	private Cell[][] cells;
	private Image[] img;

	int x_dim;// = 5;
	int y_dim;// = 5;
	
	int trophy_x;// = 3;
	int trophy_y;// = 3;
	
	int n_Blind;
	int n_Juror;
	int n_Slip;
	int n_Dancer;
	
	BlindSpot [] myBlindSpot;
	//List<Juror> myJurorList = new ArrayList<Juror>();
	//myJurorlist.add(new Juror(1, 2, 3));
	
	Juror [] myJuror;
	SlipperyTile [] mySlipTile;
	Dancer [] myDancer;
	
	Grid(int x_dim, int y_dim, int n_Blind, int n_Juror, int n_Slip, int n_Dancer){
		
		
		
        /*   */
        
		this.x_dim = x_dim;
		this.y_dim = y_dim;
		this.n_Blind = n_Blind;
		this.n_Juror = n_Juror;
		this.n_Slip = n_Slip;
		this.n_Dancer = n_Dancer;
		
		//trophy_x = (int) (Math.random()*(x_dim + 1));
		//trophy_y = (int) (Math.random()*(y_dim + 1));
		
		myBlindSpot = new BlindSpot[n_Blind];
		myJuror = new Juror[n_Juror];
		mySlipTile = new SlipperyTile[n_Slip];
		myDancer = new Dancer[n_Dancer];
		
		/*  */
		
		this.img = new Image[NUM_IMAGES];
		
		for (int i = 0; i < NUM_IMAGES; i++) {
            String path = "img/j" + i + ".gif";
            img[i] = new ImageIcon(path).getImage();
        }
		
		this.setDoubleBuffered(true);
        this.addMouseListener(new GameAdapter());
        
        this.initCells();
        this.newGame();
		
		//List<Integer> listOfRandom = new ArrayList<Integer>();
		
		/*int posX, posY;
		for(int i = 0; i < n_Blind ; i++) {
			
			//do {
				posX = (int) (Math.random()*(x_dim + 1));
				posY = (int) (Math.random()*(y_dim + 1));
			//}
			//while(listOfRandom.contains(posX));
			
			myBlindSpot[i] = new BlindSpot(posX,posY,1);
		}
		for(int i = 0; i < n_Juror ; i++) {
			
			posX = (int) (Math.random()*(x_dim + 1));
			posY = (int) (Math.random()*(y_dim + 1));
			myJuror[i] = new Juror(posX,posY,i,10);
		}
		for(int i = 0; i < n_Slip ; i++) {
	
			posX = (int) (Math.random()*(x_dim + 1));
			posY = (int) (Math.random()*(y_dim + 1));
			mySlipTile[i] = new SlipperyTile(posX,posY,1);
		}
		for(int i = 0; i < n_Dancer ; i++) {
			
			posX = (int) (Math.random()*(x_dim + 1));
			posY = (int) (Math.random()*(y_dim + 1));
			myDancer[i] = new Dancer(posX,posY,i,10);
		}*/
		
		
	}
	
	void removeJuror(int id){
		myJuror[id].active = false;
	}
	
	void removeDancer(int id){
		myDancer[id].active = false;
	}
	
	
	
	/*  */
	
	private void initCells () {
        this.cells = new Cell[y_dim][x_dim];

        for (int i = 0; i < this.y_dim; ++i) {
            for (int j = 0; j < this.x_dim; ++j) {
                this.cells[i][j] = new Cell();
            }
        }
    }
	
	public void newGame () {
        Random random;

        random = new Random();

        //this.inGame = true;
        //this.remainderMines = totalMines;

        //this.initCells();
        //this.statusBar.setText(Integer.toString(this.remainderMines));

        int remainderBlind = n_Blind;
        while (remainderBlind >= 0) {
            int randX = random.nextInt(this.y_dim);
            int randY = random.nextInt(this.x_dim);

            Cell cell = this.cells[randX][randY];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()) {
                cell.setBlindSpot(true);
                remainderBlind--;
            }
        }
        
        int remainderSlip = n_Slip;
        while (remainderSlip >= 0) {
            int randX = random.nextInt(this.y_dim);
            int randY = random.nextInt(this.x_dim);

            Cell cell = this.cells[randX][randY];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()) {
                cell.setSlipTile(true);
                remainderSlip--;
            }
        }
        
        int remainderDancer = n_Dancer;
        while (remainderDancer >= 0) {
            int randX = random.nextInt(this.y_dim);
            int randY = random.nextInt(this.x_dim);

            Cell cell = this.cells[randX][randY];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()) {
                cell.setDancer(true);
                remainderDancer--;
            }
        }
        
        int remainderJuror = n_Juror;
        while (remainderJuror >= 0) {
            int randX = random.nextInt(this.y_dim);
            int randY = random.nextInt(this.x_dim);

            Cell cell = this.cells[randX][randY];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy()) {
                cell.setJuror(true);
                remainderJuror--;
            }
        }

        //this.setMineCounts();
    }

	class GameAdapter extends MouseAdapter {
        
		public void mousePressed(MouseEvent e) {
            int pressedCol = e.getX() / CELL_SIZE;
            int pressedRow = e.getY() / CELL_SIZE;

            boolean doRepaint = false;
            Cell pressedCell;

            if (!inGame) {
                newGame();
                repaint();
            }

            if ((pressedCol < 0 || pressedCol >= columns)
                || (pressedRow < 0 || pressedRow >= rows)) {
                return;
            }

            pressedCell = cells[pressedRow][pressedCol];

            if (e.getButton() == MouseEvent.BUTTON3) {
                doRepaint = true;

                if (!pressedCell.isCovered()) {
                    return;
                }

                String str;
                if (!pressedCell.isMarked()) {
                    pressedCell.setMark(true);
                    remainderMines--;
                } else {
                    pressedCell.setMark(false);
                    remainderMines++;
                }

                statusBar.setText(Integer.toString(remainderMines));
            } else {
                if (pressedCell.isMarked() || !pressedCell.isCovered()) {
                    return;
                }

                doRepaint = true;

                pressedCell.uncover();
                if (pressedCell.isMine()) {
                    inGame = false;
                } else if (pressedCell.isEmpty()) {
                    findEmptyCells(pressedRow, pressedCol, 0);
                }
            }

            if (doRepaint) {
                repaint();
            }
        }
    }
}
