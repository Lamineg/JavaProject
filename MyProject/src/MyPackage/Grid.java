package MyPackage;

public class Grid {
	

	int x_dim;// = 5;
	int y_dim;// = 5;
	
	int trophy_x;// = 3;
	int trophy_y;// = 3;
	
	BlindSpot myBlindSpot;// = new BlindSpot();
	Juror myJuror;
	SlipperyTile mySlipTile;
	
	Grid(int x, int y){
		x_dim = x;
		y_dim = y;
		
		trophy_x = (int) (Math.random()*(x + 1));
		trophy_y = (int) (Math.random()*(y + 1));
	}

}
