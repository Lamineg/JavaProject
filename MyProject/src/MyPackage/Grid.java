package MyPackage;

import java.util.*;

public class Grid {
	
	//dimensions of the grid
	private int x_dim;

	private int y_dim;
	
	//position of the trophy
	private int trophy_x;
	private int trophy_y;
	
	//number of objects for the penalties and bonuses
	private int n_Blind;

	private int n_Juror;
	private int n_Slip;
	private int n_Dancer;
	
	//lists of objects
	BlindSpot [] myBlindSpot;
	Juror [] myJuror;
	SlipperyTile [] mySlipTile;
	Dancer [] myDancer;
	
	//method to construct the grid using the difficulty chosen by the player. 
	Grid(int difficulty){
		
		//set the dimensions at 15, whatever the difficulty
		this.x_dim = 15;
		this.y_dim = 15;
		
		//set the number of blinding spotlights at difficulty + 1
		this.n_Blind = difficulty+1;
		
		//set the number of jurors at difficulty + 1
		this.n_Juror = difficulty+1;
		
		//set the number of slippery tiles at difficulty +1
		this.n_Slip = difficulty+1;
		
		//set the number of dancers at difficulty + 1
		this.n_Dancer = difficulty+1;
		
		//create list of objects of size equal to the number of objects of that class
		myBlindSpot = new BlindSpot[n_Blind];
		myJuror = new Juror[n_Juror];
		mySlipTile = new SlipperyTile[n_Slip];
		myDancer = new Dancer[n_Dancer];
		
		//create an array of unique position that the obstacles can have and delete the position when the position
		//is selected
		ArrayList<UniquePosition> unique = new ArrayList<UniquePosition>();
		for (int i = 0; i < x_dim; i++) {
			for (int j = 0; j < y_dim; j++) {

				unique.add(new UniquePosition(i, j));

			}
		}
		
		//cannot have anything on position (0,0), which is the player starting position
		unique.remove(0);
		
		//will store the positions of each bonus/penalty
		int posX;
		int posY;
		
		//chose a random position in the array of unique position
		int random = (int) (Math.random()*(unique.size()));
		
		//assign a random x and y to the trophy
		trophy_x = unique.get(random).getPosX();
		trophy_y = unique.get(random).getPosY();
		
		//delete that unique position to avoid duplicate positions
		unique.remove(random);
		
		//each of the blinding spotlights will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Blind ; i++) {
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).getPosX();
			posY = unique.get(random).getPosY();
			
			myBlindSpot[i] = new BlindSpot(posX,posY,1);
			
			unique.remove(random);

		}
		
		//each of the jurors will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Juror ; i++) {
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).getPosX();
			posY = unique.get(random).getPosY();
			
			myJuror[i] = new Juror(posX,posY,i,10);
			
			unique.remove(random);

		}
		
		//each of the slippery tiles will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Slip ; i++) {

			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).getPosX();
			posY = unique.get(random).getPosY();
			
			mySlipTile[i] = new SlipperyTile(posX,posY,1);
			
			unique.remove(random);
		}
		
		//each of the dancers will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Dancer ; i++) {
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).getPosX();
			posY = unique.get(random).getPosY();
			
			myDancer[i] = new Dancer(posX,posY,i,10);
			
			unique.remove(random);
			
		}
		
		
	}
	
	//getter for x_dim
	public int getX_dim() {
		return x_dim;
	}
	
	//getter for y_dim
	public int getY_dim() {
		return y_dim;
	}
	
	//method to remove the juror, will be used after a confrontation succeeds with a juror
	void removeJuror(int id){
		myJuror[id].setActive(false);
	}
	
	//method to remove a dancer, will be used when we beat another dancer
	void removeDancer(int id){
		myDancer[id].setActive(false);
	}

public int getTrophy_x() {
	return trophy_x;
}

public void setTrophy_x(int trophy_x) {
	this.trophy_x = trophy_x;
}

public int getTrophy_y() {
	return trophy_y;
}

public void setTrophy_y(int trophy_y) {
	this.trophy_y = trophy_y;
}

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
}