package MyPackage;

public class Program {
	
	public static void main(String[] args) {
		
		//create new player
		Player player1 = new Player();
		
		System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
		
		//player1.move(1,4);
		
		//System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
		
		// TEST FOR GRID PART :
		
		Grid myGrid = new Grid(5,5,2,2,2,2);
		
		int [] positions_x = {2,2,-1};//new int [4];
		int [] positions_y = {3,-1,1};//new int [4];
		
		//System.out.println("blind x = "+myGrid.myBlindSpot.x+", blind y = "+ myGrid.myBlindSpot.y);
		
		//
		
		System.out.println("energy = "+player1.energy);
		
		double r = Math.random()*4;
		int u = (int) r;
		System.out.println("u = " + u);
		System.out.println("r = " + r);
		
		System.out.println("trophy x = "+myGrid.trophy_x+", trophy y = "+ myGrid.trophy_y);
		
		int i = 0;
		int movX, movY;
		
		while (player1.energy > 0 && (myGrid.trophy_x != player1.x_pos || myGrid.trophy_y != player1.y_pos)) {
			
			//player1.move(positions_x[i], positions_y[i]);
			
			movX = (int) (Math.random()*(4 + 4 + 1)) - 4;
			movY = (int) (Math.random()*(4 + 4 + 1)) - 4;
			
			player1.move(movX, movY);
			
			if(player1.x_pos > 4) player1.x_pos = 4;
			if(player1.x_pos < 0) player1.x_pos = 0;
			if(player1.y_pos > 4) player1.y_pos = 4;
			if(player1.y_pos < 0) player1.y_pos = 0;
			
			System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
			
			if (myGrid.trophy_x == player1.x_pos && myGrid.trophy_y == player1.y_pos) {
				System.out.println("You found the trophy !");
			}
			if (player1.energy <= 0) {
				System.out.println("You run out of energy :'( ");
			}
			
			
			/*if(player1.x_pos == ) {
				
			}*/
			i++;
		}
		
	}

}
