package MyPackage;

public class IsValidMove {

	public static boolean isValidMove(Player myPlayer, Grid myGrid) {
		
		//the player cannot travel an x distance bigger than 1
		if (myPlayer.x_pos > myPlayer.x_old + 1 || myPlayer.x_pos < myPlayer.x_old - 1) {
			System.out.println("Invalid move");
			return false;
		}

		//the player cannot travel a y distance bigger than 1
		if (myPlayer.y_pos > myPlayer.y_old + 1 || myPlayer.y_pos < myPlayer.y_old - 1) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid
		if (myPlayer.x_pos >= myGrid.x_dim) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid		
		if (myPlayer.x_pos < 0) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid (y axis)
		if (myPlayer.y_pos >= myGrid.y_dim) {			
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid (y axis)
		if (myPlayer.y_pos < 0) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot be on the same position as a slippery tile
		for (int j = 0; j < myGrid.n_Slip; j++) {

			if (myGrid.mySlipTile[j].x == myPlayer.x_pos && myGrid.mySlipTile[j].y == myPlayer.y_pos) {
				System.out.println("Invalid move");
				return false;
			}

		}
		//the player cannot be on the same position as a blinding spotlight
		for (int j = 0; j < myGrid.n_Blind; j++) {

			if (myGrid.myBlindSpot[j].x == myPlayer.x_pos && myGrid.myBlindSpot[j].y == myPlayer.y_pos) {
				System.out.println("Invalid move");
				return false;
			}

		}

		return true;
			
	}
}

	
