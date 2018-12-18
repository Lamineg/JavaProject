package MyPackage;

public class Visibility {

	public static void vis(Grid myGrid, Player myPlayer) {

		for (int j = 0; j < myGrid.getN_Dancer(); j++) {

			if (myGrid.myDancer[j].isActive()) {
				if (myGrid.myDancer[j].getX() == myPlayer.getX_pos() && myGrid.myDancer[j].getY() == myPlayer.getY_pos() + 1) {
					System.out.print("Danceur en haut; ");
				} else if (myGrid.myDancer[j].getX() == myPlayer.getX_pos() && myGrid.myDancer[j].getY() == myPlayer.getY_pos() - 1) {
					System.out.print("Danceur en bas; ");
				} else if (myGrid.myDancer[j].getX() == myPlayer.getX_pos() + 1 && myGrid.myDancer[j].getY() == myPlayer.getY_pos()) {
					System.out.print("Danceur a droite; ");
				} else if (myGrid.myDancer[j].getX() == myPlayer.getX_pos() - 1 && myGrid.myDancer[j].getY() == myPlayer.getY_pos()) {
					System.out.print("Danceur a gauche; ");
				}

			}
		}

		for (int j = 0; j < myGrid.getN_Juror(); j++) {

			if (myGrid.myJuror[j].isActive()) {
				if (myGrid.myJuror[j].getX() == myPlayer.getX_pos() && myGrid.myJuror[j].getY() == myPlayer.getY_pos() + 1) {
					System.out.print("Juge en haut; ");
				} else if (myGrid.myJuror[j].getX() == myPlayer.getX_pos()&& myGrid.myJuror[j].getY() == myPlayer.getY_pos()- 1) {
					System.out.print("Juge en bas; ");
				} else if (myGrid.myJuror[j].getX() == myPlayer.getX_pos()+ 1 && myGrid.myJuror[j].getY() == myPlayer.getY_pos()) {
					System.out.print("Juge a droite; ");
				} else if (myGrid.myJuror[j].getX() == myPlayer.getX_pos()- 1 && myGrid.myJuror[j].getY() == myPlayer.getY_pos()) {
					System.out.print("Juge a gauche;");
				}
			}
		}

		for (int j = 0; j < myGrid.getN_Slip(); j++) {

			if (myGrid.mySlipTile[j].getX() == myPlayer.getX_pos() && myGrid.mySlipTile[j].getY() == myPlayer.getY_pos() + 1) {
				System.out.print("Dale glissante en haut; ");
			} else if (myGrid.mySlipTile[j].getX() == myPlayer.getX_pos() && myGrid.mySlipTile[j].getY() == myPlayer.getY_pos() - 1) {
				System.out.print("Dale glissante en bas; ");
			} else if (myGrid.mySlipTile[j].getX() == myPlayer.getX_pos() + 1 && myGrid.mySlipTile[j].getY() == myPlayer.getY_pos()) {
				System.out.print("Dale glissante a droite; ");
			} else if (myGrid.mySlipTile[j].getX() == myPlayer.getX_pos() - 1 && myGrid.mySlipTile[j].getY() == myPlayer.getY_pos()) {
				System.out.print("Dale glissante a gauche; ");
			}

		}

		for (int j = 0; j < myGrid.getN_Blind(); j++) {

			if (myGrid.myBlindSpot[j].getX() == myPlayer.getX_pos() && myGrid.myBlindSpot[j].getY() == myPlayer.getY_pos() + 1) {
				System.out.print("Spot en haut; ");
			} else if (myGrid.myBlindSpot[j].getX() == myPlayer.getX_pos() && myGrid.myBlindSpot[j].getY() == myPlayer.getY_pos() - 1) {
				System.out.print("Spot en bas; ");
			} else if (myGrid.myBlindSpot[j].getX() == myPlayer.getX_pos() + 1 && myGrid.myBlindSpot[j].getY() == myPlayer.getY_pos()) {
				System.out.print("Spot a droite; ");
			} else if (myGrid.myBlindSpot[j].getX() == myPlayer.getX_pos() - 1 && myGrid.myBlindSpot[j].getY() == myPlayer.getY_pos()) {
				System.out.print("Spot a gauche; ");
			}

		}

		if (myGrid.getTrophy_x() == myPlayer.getX_pos() && myGrid.getTrophy_y() == myPlayer.getY_pos() + 1) {
			System.out.print("Trophee en haut; ");
		} else if (myGrid.getTrophy_x() == myPlayer.getX_pos() && myGrid.getTrophy_y() == myPlayer.getY_pos() - 1) {
			System.out.print("Trophee en bas; ");
		} else if (myGrid.getTrophy_x() == myPlayer.getX_pos() + 1 && myGrid.getTrophy_y() == myPlayer.getY_pos()) {
			System.out.print("Trophee a droite; ");
		} else if (myGrid.getTrophy_x() == myPlayer.getX_pos() - 1 && myGrid.getTrophy_y() == myPlayer.getY_pos()) {
			System.out.print("Trophee a gauche; ");
		}

		System.out.println(" ");

	}


}
