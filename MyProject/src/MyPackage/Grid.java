package MyPackage;

import java.util.*;

public class Grid {
	

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
	
	Grid(int difficulty){
	//Grid(int x_dim, int y_dim, int n_Blind, int n_Juror, int n_Slip, int n_Dancer){
		this.x_dim = difficulty+5;
		this.y_dim = difficulty+5;
		this.n_Blind = difficulty+1;
		this.n_Juror = difficulty+1;
		this.n_Slip = difficulty+1;
		this.n_Dancer = difficulty+1;
		
		/*
		trophy_x = (int) (Math.random()*(x_dim + 1));
		trophy_y = (int) (Math.random()*(y_dim + 1));
		*/
		
		myBlindSpot = new BlindSpot[n_Blind];
		myJuror = new Juror[n_Juror];
		mySlipTile = new SlipperyTile[n_Slip];
		myDancer = new Dancer[n_Dancer];
		
		//create an array of unique position that the obstacles can have and delete when position
		//is taken
		ArrayList<UniquePosition> unique = new ArrayList<UniquePosition>();
		for (int i = 1; i < x_dim + 1; i++) {
			for (int j = 1; j < y_dim + 1; j++) {

				unique.add(new UniquePosition(i, j));

			}
		}
		
		//cannot have anything on positon (1,1), which is the player starting postion
		unique.remove(1);
		
		int posX;
		int posY;
		
		//chose a random position in the array of unique position
		int random = (int) (Math.random()*(unique.size()));
		
		//assign the x and y of the unique position to the object
		trophy_x = unique.get(random).posX;
		trophy_y = unique.get(random).posY;
		
		//delete that unique position to avoid duplicate positions
		unique.remove(random);
		
		for(int i = 0; i < n_Blind ; i++) {
			
			/*do {
				
				posX = (int) (Math.random()*(x_dim + 1));
				posY = (int) (Math.random()*(y_dim + 1));
			//}
			//while(listOfRandom.contains(posX));
			*/
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
			myBlindSpot[i] = new BlindSpot(posX,posY,1);
			
			unique.remove(random);

		}
		
		for(int i = 0; i < n_Juror ; i++) {
			/*
			posX = (int) (Math.random()*(x_dim + 1));
			posY = (int) (Math.random()*(y_dim + 1));
			*/
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
			myJuror[i] = new Juror(posX,posY,i,10);
			
			unique.remove(random);

		}
		for(int i = 0; i < n_Slip ; i++) {
			/*
			posX = (int) (Math.random()*(x_dim + 1));
			posY = (int) (Math.random()*(y_dim + 1));
			*/
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
			mySlipTile[i] = new SlipperyTile(posX,posY,1);
			
			unique.remove(random);
		}
		for(int i = 0; i < n_Dancer ; i++) {
			
			/*
			posX = (int) (Math.random()*(x_dim + 1));
			posY = (int) (Math.random()*(y_dim + 1));
			*/
			
			random = (int) (Math.random()*(unique.size()));
			posX = unique.get(random).posX;
			posY = unique.get(random).posY;
			
			myDancer[i] = new Dancer(posX,posY,i,10);
			
			unique.remove(random);
			
		}
		
		
	}
	
	public int getX_dim() {
		return x_dim;
	}
	
	public int getY_dim() {
		return y_dim;
	}
	void removeJuror(int id){
		myJuror[id].active = false;
	}
	
	void removeDancer(int id){
		myDancer[id].active = false;
	}

}
