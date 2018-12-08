package MyPackage;

public class Program {
	
	public static void main(String[] args) {
		
		//create new player
		Player player1 = new Player();
		
		System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
		
		player1.move(1,4);
		
		System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
		
		// TEST FOR GRID PART :
		
		Grid myGrid = new Grid();
		
		int [] positions_x = {2,2,-1};//new int [4];
		int [] positions_y = {3,-1,1};//new int [4];
		
		System.out.println("blind x = "+myGrid.myBlindSpot.x+", blind y = "+ myGrid.myBlindSpot.y);
		
		while (player1.energy > 0 || (myGrid.trophy_x == player1.x_pos && myGrid.trophy_y == player1.y_pos)) {
			
		}
		
	}

}
