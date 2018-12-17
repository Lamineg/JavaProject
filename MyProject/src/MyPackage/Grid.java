package MyPackage;

import java.util.*;

public class Grid {
	
	//dimensions of the grid
	int x_dim;
	int y_dim;
	
	//position of the trophy
	int trophy_x;
	int trophy_y;
	
	//number of objects for the penalties and bonuses
	int n_Blind;
	int n_Juror;
	int n_Slip;
	int n_Dancer;
	
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
		trophy_x = unique.get(random).posX;
		trophy_y = unique.get(random).posY;
		
		//delete that unique position to avoid duplicate positions
		unique.remove(random);
		
		//each of the blinding spotlights will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Blind ; i++) {
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
			myBlindSpot[i] = new BlindSpot(posX,posY,1);
			
			unique.remove(random);

		}
		
		//each of the jurors will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Juror ; i++) {
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
			myJuror[i] = new Juror(posX,posY,i,10);
			
			unique.remove(random);

		}
		
		//each of the slippery tiles will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Slip ; i++) {

			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
			mySlipTile[i] = new SlipperyTile(posX,posY,1);
			
			unique.remove(random);
		}
		
		//each of the dancers will get its unique random position and that position will then be removed
		//from the arraylist
		for(int i = 0; i < n_Dancer ; i++) {
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
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
		myJuror[id].active = false;
	}
	
	//method to remove a dancer, will be used when we beat another dancer
	void removeDancer(int id){
		myDancer[id].active = false;
	}

}
