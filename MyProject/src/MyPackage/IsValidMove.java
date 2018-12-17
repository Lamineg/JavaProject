package MyPackage;

public class IsValidMove {

	public static boolean isValidMove(Player myPlayer, Grid myGrid) {
		
		//the player cannot travel an x distance bigger than 1
		if (myPlayer.getX_pos() > myPlayer.getX_old() + 1 || myPlayer.getX_pos() < myPlayer.getX_old() - 1) {
			System.out.println("Invalid move");
			return false;
		}

		//the player cannot travel a y distance bigger than 1
		if (myPlayer.getY_pos() > myPlayer.getY_old() + 1 || myPlayer.getY_pos() < myPlayer.getY_old() - 1) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid
		if (myPlayer.getX_pos() >= myGrid.getX_dim()) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid		
		if (myPlayer.getX_pos() < 0) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid (y axis)
		if (myPlayer.getY_pos() >= myGrid.getY_dim()) {			
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot go out of the grid (y axis)
		if (myPlayer.getY_pos() < 0) {
			System.out.println("Invalid move");
			return false;
		}
		
		//the player cannot be on the same position as a slippery tile
		for (int j = 0; j < myGrid.getN_Slip(); j++) {

			if (myGrid.mySlipTile[j].getX() == myPlayer.getX_pos() && myGrid.mySlipTile[j].getY() == myPlayer.getY_pos()) {
				System.out.println("Invalid move");
				return false;
			}

		}
		//the player cannot be on the same position as a blinding spotlight
		for (int j = 0; j < myGrid.getN_Blind(); j++) {

			if (myGrid.myBlindSpot[j].getX() == myPlayer.getX_pos() && myGrid.myBlindSpot[j].getY() == myPlayer.getY_pos()) {
				System.out.println("Invalid move");
				return false;
			}

		}

		return true;
			
	}
}

	
