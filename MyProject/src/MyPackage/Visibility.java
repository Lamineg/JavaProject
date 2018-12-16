package MyPackage;

public class Visibility {

	public static void vis(Grid myGrid, Player myPlayer) {

		for (int j = 0; j < myGrid.n_Dancer; j++) {

			if (myGrid.myDancer[j].active) {
				if (myGrid.myDancer[j].x == myPlayer.x_pos && myGrid.myDancer[j].y == myPlayer.y_pos + 1) {
					System.out.print("Danceur en haut; ");
				} else if (myGrid.myDancer[j].x == myPlayer.x_pos && myGrid.myDancer[j].y == myPlayer.y_pos - 1) {
					System.out.print("Danceur en bas; ");
				} else if (myGrid.myDancer[j].x == myPlayer.x_pos + 1 && myGrid.myDancer[j].y == myPlayer.y_pos) {
					System.out.print("Danceur a droite; ");
				} else if (myGrid.myDancer[j].x == myPlayer.x_pos - 1 && myGrid.myDancer[j].y == myPlayer.y_pos) {
					System.out.print("Danceur a gauche; ");
				}

			}
		}

		for (int j = 0; j < myGrid.n_Juror; j++) {

			if (myGrid.myJuror[j].active) {
				if (myGrid.myJuror[j].x == myPlayer.x_pos && myGrid.myJuror[j].y == myPlayer.y_pos + 1) {
					System.out.print("Juge en haut; ");
				} else if (myGrid.myJuror[j].x == myPlayer.x_pos && myGrid.myJuror[j].y == myPlayer.y_pos - 1) {
					System.out.print("Juge en bas; ");
				} else if (myGrid.myJuror[j].x == myPlayer.x_pos + 1 && myGrid.myJuror[j].y == myPlayer.y_pos) {
					System.out.print("Juge a droite; ");
				} else if (myGrid.myJuror[j].x == myPlayer.x_pos - 1 && myGrid.myJuror[j].y == myPlayer.y_pos) {
					System.out.print("Juge a gauche;");
				}
			}
		}

		for (int j = 0; j < myGrid.n_Slip; j++) {

			if (myGrid.mySlipTile[j].x == myPlayer.x_pos && myGrid.mySlipTile[j].y == myPlayer.y_pos + 1) {
				System.out.print("Dale glissante en haut; ");
			} else if (myGrid.mySlipTile[j].x == myPlayer.x_pos && myGrid.mySlipTile[j].y == myPlayer.y_pos - 1) {
				System.out.print("Dale glissante en bas; ");
			} else if (myGrid.mySlipTile[j].x == myPlayer.x_pos + 1 && myGrid.mySlipTile[j].y == myPlayer.y_pos) {
				System.out.print("Dale glissante a droite; ");
			} else if (myGrid.mySlipTile[j].x == myPlayer.x_pos - 1 && myGrid.mySlipTile[j].y == myPlayer.y_pos) {
				System.out.print("Dale glissante a gauche; ");
			}

		}

		for (int j = 0; j < myGrid.n_Blind; j++) {

			if (myGrid.myBlindSpot[j].x == myPlayer.x_pos && myGrid.myBlindSpot[j].y == myPlayer.y_pos + 1) {
				System.out.print("Spot en haut; ");
			} else if (myGrid.myBlindSpot[j].x == myPlayer.x_pos && myGrid.myBlindSpot[j].y == myPlayer.y_pos - 1) {
				System.out.print("Spot en bas; ");
			} else if (myGrid.myBlindSpot[j].x == myPlayer.x_pos + 1 && myGrid.myBlindSpot[j].y == myPlayer.y_pos) {
				System.out.print("Spot a droite; ");
			} else if (myGrid.myBlindSpot[j].x == myPlayer.x_pos - 1 && myGrid.myBlindSpot[j].y == myPlayer.y_pos) {
				System.out.print("Spot a gauche; ");
			}

		}

		if (myGrid.trophy_x == myPlayer.x_pos && myGrid.trophy_y == myPlayer.y_pos + 1) {
			System.out.print("Trophee en haut; ");
		} else if (myGrid.trophy_x == myPlayer.x_pos && myGrid.trophy_y == myPlayer.y_pos - 1) {
			System.out.print("Trophee en bas; ");
		} else if (myGrid.trophy_x == myPlayer.x_pos + 1 && myGrid.trophy_y == myPlayer.y_pos) {
			System.out.print("Trophee a droite; ");
		} else if (myGrid.trophy_x == myPlayer.x_pos - 1 && myGrid.trophy_y == myPlayer.y_pos) {
			System.out.print("Trophee a gauche; ");
		}

		System.out.println(" ");

	}


}
