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
	
	Grid(int x_dim, int y_dim, int n_Blind, int n_Juror, int n_Slip, int n_Dancer){
		this.x_dim = x_dim;
		this.y_dim = y_dim;
		this.n_Blind = n_Blind;
		this.n_Juror = n_Juror;
		this.n_Slip = n_Slip;
		this.n_Dancer = n_Dancer;
		
		trophy_x = (int) (Math.random()*(x_dim + 1));
		trophy_y = (int) (Math.random()*(y_dim + 1));
		
		myBlindSpot = new BlindSpot[n_Blind];
		myJuror = new Juror[n_Juror];
		mySlipTile = new SlipperyTile[n_Slip];
		myDancer = new Dancer[n_Dancer];
		
		//List<Integer> listOfRandom = new ArrayList<Integer>();
		
		int posX, posY;
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
		}
		
		
	}
	
	void removeJuror(int id){
		myJuror[id].active = false;
	}
	
	void removeDancer(int id){
		myDancer[id].active = false;
	}

}
