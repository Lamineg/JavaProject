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
	
	private final int CELL_SIZE  = 50;//to fix
    private final int NUM_IMAGES = 8;// to fix
    
    private final int IMAGE_BLINDSPOT       = 1;
    private final int IMAGE_SLIPTILE      = 2;
    private final int IMAGE_PLAYER       = 3;
    private final int IMAGE_DANCER	 = 4;
    
    private final int IMAGE_JUROR       = 5;
    private final int IMAGE_TROPHY      = 6;
    private final int IMAGE_WHITE       = 7;
    private final int IMAGE_BLACK = 0;
    
	private JLabel statusBar;
	
	private Cell[][] cells;
	private Image[] img;
	
	private boolean inGame;

	int x_dim;// = 5;
	int y_dim;// = 5;
	
	int trophy_x;// = 3;
	int trophy_y;// = 3;
	
	int n_Blind;
	int n_Juror;
	int n_Slip;
	int n_Dancer;
	
	BlindSpot [] myBlindSpot;
	Juror [] myJuror;
	SlipperyTile [] mySlipTile;
	Dancer [] myDancer;
	
	Player myPlayer;
	
	Grid(int x_dim, int y_dim, int n_Blind, int n_Juror, int n_Slip, int n_Dancer, JLabel statusBar){
		
		
		
        /*   */
        
		this.x_dim = x_dim;
		this.y_dim = y_dim;
		this.n_Blind = n_Blind;
		this.n_Juror = n_Juror;
		this.n_Slip = n_Slip;
		this.n_Dancer = n_Dancer;
		
		myBlindSpot = new BlindSpot[n_Blind];
		myJuror = new Juror[n_Juror];
		mySlipTile = new SlipperyTile[n_Slip];
		myDancer = new Dancer[n_Dancer];
		
		myPlayer = new Player(0,0);
		
		/*  */
		
		this.statusBar = statusBar;
		this.img = new Image[NUM_IMAGES];
		
		for (int i = 0; i < NUM_IMAGES; i++) {
            String path = "img/m" + i + ".gif";
            img[i] = new ImageIcon(path).getImage();
        }
		
		this.setDoubleBuffered(true);
        this.addMouseListener(new GameAdapter());
        
        this.newGame();
		
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
                this.cells[i][j] = new Cell(j,i);
            }
        }
        
        this.cells[0][0].setPlayer(true);
    }
	
	public void newGame () {
        Random random;

        random = new Random();

        this.inGame = true;
        //this.remainderMines = totalMines;

        this.initCells();
        //this.statusBar.setText(Integer.toString(this.remainderMines));

        int randX, randY;
        int remainder = 1; //1 trophy
        while(remainder > 0) {
        	randY = random.nextInt(this.y_dim);
        	randX = random.nextInt(this.x_dim);
        	
        	Cell cell = this.cells[randY][randX];
        	if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy() && !cell.isPlayer()) {
                cell.setTrophy(true);
                remainder--;
            }
        }

        
        int remainderBlind = n_Blind;
        while (remainderBlind > 0) {
            randY = random.nextInt(this.y_dim);
            randX = random.nextInt(this.x_dim);

            Cell cell = this.cells[randY][randX];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy() && !cell.isPlayer()) {
                cell.setBlindSpot(true);
                myBlindSpot[n_Blind - remainderBlind] = new BlindSpot(randX,randY,1);
                remainderBlind--;
            }
        }
        
        int remainderSlip = n_Slip;
        while (remainderSlip > 0) {
            randY = random.nextInt(this.y_dim);
            randX = random.nextInt(this.x_dim);

            Cell cell = this.cells[randY][randX];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy() && !cell.isPlayer()) {
                cell.setSlipTile(true);
                mySlipTile[n_Slip - remainderSlip] = new SlipperyTile(randX,randY,1);
                remainderSlip--;
            }
        }
        
        int remainderDancer = n_Dancer;
        while (remainderDancer > 0) {
            randY = random.nextInt(this.y_dim);
            randX = random.nextInt(this.x_dim);

            Cell cell = this.cells[randY][randX];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy() && !cell.isPlayer()) {
                cell.setDancer(true);
                myDancer[n_Dancer - remainderDancer] = new Dancer(randX,randY,n_Dancer - remainderDancer,10);
                remainderDancer--;
            }
        }
        
        int remainderJuror = n_Juror;
        while (remainderJuror > 0) {
            randY = random.nextInt(this.y_dim);
            randX = random.nextInt(this.x_dim);

            Cell cell = this.cells[randY][randX];
            if (!cell.isBlindSpot() && !cell.isJuror() && !cell.isDancer() && !cell.isBlindSpot() && !cell.isTrophy() && !cell.isPlayer()) {
                cell.setJuror(true);
                myJuror[n_Juror - remainderJuror] = new Juror(randX,randY,n_Juror - remainderJuror,10);
                remainderJuror--;
            }
        }

        //this.setMineCounts();
    }
	
	public void paint(Graphics g) {
        int coveredCells = 0;

        for (int i = 0; i < this.y_dim; i++) {
            for (int j = 0; j < this.x_dim; j++) {
                Cell cell = this.cells[i][j];
                int imageType;
                int xPosition, yPosition;

                /*if (cell.isCovered()) {
                    coveredCells++;
                }*/

                /*if (inGame) {
                    if (cell.isMine() && !cell.isCovered()) {
                        inGame = false;
                    }
                }*/

                imageType = this.decideImageType(cell);

                xPosition = (j * CELL_SIZE);
                yPosition = (i * CELL_SIZE);

                g.drawImage(img[imageType], xPosition, yPosition, this);
            }
        }

        /*if (coveredCells == 0 && inGame) {
            inGame = false;
            statusBar.setText("Game Won");
        } else if (!inGame) {
            statusBar.setText("Game Lost");
        }*/
    }
	
	private int decideImageType(Cell cell) {
        int imageType = cell.getValue();

        if (!inGame) {
            
        	if(cell.isBlindSpot()) { //replace by a case ?
        		imageType = IMAGE_BLINDSPOT;
        	} else if(cell.isSlipTile()) {
        		imageType = IMAGE_SLIPTILE;
        	} else if(cell.isPlayer()) {
        		imageType = IMAGE_PLAYER;
        	} else if(cell.isDancer()) {
        		imageType = IMAGE_DANCER;
        	} else if(cell.isJuror()) {
        		imageType = IMAGE_JUROR;
        	} else if(cell.isTrophy()) {
        		imageType = IMAGE_TROPHY;
        	} else {
        		imageType = IMAGE_WHITE;
        	}
        	
        } else {
        	
        	if(cell.isPlayer()) {
        		imageType = IMAGE_PLAYER;
        	} else if(cell.isVisible(myPlayer)) {
        		
        		if(cell.isBlindSpot()) { //replace by a case ?
            		imageType = IMAGE_BLINDSPOT;
            	} else if(cell.isSlipTile()) {
            		imageType = IMAGE_SLIPTILE;
            	} else if(cell.isDancer()) {
            		imageType = IMAGE_DANCER;
            	} else if(cell.isJuror()) {
            		imageType = IMAGE_JUROR;
            	} else if(cell.isTrophy()) {
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
	
	class GameAdapter extends MouseAdapter {
        
		public void mousePressed(MouseEvent e) {
            int pressedCol = e.getX() / CELL_SIZE;
            int pressedRow = e.getY() / CELL_SIZE;

            boolean doRepaint = false;
            Cell pressedCell;
            Cell currentCell;

            if (!inGame) {
                newGame();
                repaint();
            }

            if ((pressedCol < 0 || pressedCol >= x_dim)
                || (pressedRow < 0 || pressedRow >= y_dim)) {
                return;
            }

            pressedCell = cells[pressedRow][pressedCol];
            currentCell = cells[myPlayer.y_pos][myPlayer.x_pos];

            if (e.getButton() == MouseEvent.BUTTON3) {
                
            	return;
            	/*doRepaint = true;

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
                */
            } else {
                if (pressedCell.isBlindSpot() || pressedCell.isSlipTile() || !pressedCell.isReachable(myPlayer)) {
                	
                	if(pressedCell.isBlindSpot()) {
                		statusBar.setText("Invalid move : blindspot");      
                	} else if(pressedCell.isSlipTile()) {
                		statusBar.setText("Invalid move : SlipTile");
                	} else {
                		statusBar.setText("Invalid move : unreachable");
                	}
                	//statusBar.setText("Invalid move");
                	return;
                
                } else if(pressedCell.equals(currentCell)) {
                	
                	statusBar.setText("energy = " + myPlayer.energy + "; looks = " + myPlayer.looksSkills + "; dance = " + myPlayer.danceSkills);
                	return;
                	
                } else if(pressedCell.isJuror()) {
                	
                	int id = pressedCell.getJurorId(myJuror, n_Juror);
                	int n_b = currentCell.countBlindSpot(myBlindSpot, n_Blind);
                	
                	if(myPlayer.judgeWon(myJuror[id],n_b)) {
                		
                		myPlayer.gainLooks();
                		pressedCell.setJuror(false);
                		
                		pressedCell.setPlayer(true);
                    	currentCell.setPlayer(false);
                    	myPlayer.move(pressedCol, pressedRow);
                    	
                    	statusBar.setText("You convinced a juror");
                    	
                    	doRepaint = true;
                    	
                    	
                	} else {
                		
                		statusBar.setText("You did not convinced a juror");
                		
                		myPlayer.loseEnergy();//or not ?
                		return;
                		
                	}
                		
                } else if(pressedCell.isDancer()) {
                	
                	int id = pressedCell.getDancerId(myDancer, n_Dancer);
                	int n_s = currentCell.countSlipTile(mySlipTile, n_Slip);
                	
                	if(myPlayer.danceWon(myDancer[id],n_s)) {
                		
                		myPlayer.gainDancingSkills();
                		pressedCell.setDancer(false);
                		
                		pressedCell.setPlayer(true);
                    	currentCell.setPlayer(false);
                    	myPlayer.move(pressedCol, pressedRow);
                    	
                    	statusBar.setText("You won a dance battle");
                    	
                    	doRepaint = true;
                    	
                	} else {
                		
                		statusBar.setText("You lost a dance battle");
                		
                		myPlayer.loseEnergy();//or not ?
                		return;
                		
                		
                	}
                	
                } else if(pressedCell.isTrophy()) { //add condition to access trophy
                	
                	inGame = false;
                	pressedCell.setTrophy(false);
                	
                	pressedCell.setPlayer(true);
                	currentCell.setPlayer(false);
                	myPlayer.move(pressedCol, pressedRow);
                	
                	statusBar.setText("You won !");
                	
                	doRepaint = true;
                	
                } else {
                	
                	pressedCell.setPlayer(true);
                	currentCell.setPlayer(false);
                	myPlayer.move(pressedCol, pressedRow);
                	
                	statusBar.setText("energy = " + myPlayer.energy + "; looks = " + myPlayer.looksSkills + "; dance = " + myPlayer.danceSkills);

                	doRepaint = true;
                }
                
                
                

                
                
                /*pressedCell.uncover();
                if (pressedCell.isMine()) {
                    inGame = false;
                } else if (pressedCell.isEmpty()) {
                    findEmptyCells(pressedRow, pressedCol, 0);
                }*/
                
                
            }

            if (doRepaint) {
                repaint();
            }
        }
    }
}
