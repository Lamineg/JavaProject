package MyPackage;

import java.io.IOException;
import java.util.Scanner;

public class Program {

	// main method is the first method that the program will run
	public static void main(String[] args) throws IOException {

		// Initialization of a scanner to get the username, the difficulty and the
		// skills of the player

		Scanner question = new Scanner(System.in);
		System.out.println("Username: ");
		String username = question.nextLine();
		System.out.println("Level of difficulty (from 0 to 10): ");
		int difficulty = question.nextInt();
		System.out.println(
				"Balance between Looks and Dance (0 favors Looks, " + "5 is same for both and 10 favors Dance: ");
		int input_skills = question.nextInt();

		Player player1 = new Player(username, input_skills, difficulty);

		// player1.move(2, 1);

		// create new player

		// TEST FOR GRID PART ;

		Grid myGrid = new Grid(difficulty);

		// Grid myGrid = new Grid();

		// int[] positions_x = { 2, 2, -1 };// new int [4];
		// int[] positions_y = { 3, -1, 1 };// new int [4];

		// System.out.println("blind x = "+myGrid.myBlindSpot.x+", blind y = "+
		// myGrid.myBlindSpot.y);

		// int i = 0;
		int movX;
		int movY;
		while (player1.energy > 0 && (myGrid.trophy_x != player1.x_pos || myGrid.trophy_y != player1.y_pos)) {
			
			//call method to check visibility
			Visibility.vis(myGrid, player1);

			// Replace this part by a scanner or GUI */
			// movX = (int) (Math.random()*(4 + 4 + 1)) - 4;
			// movY = (int) (Math.random()*(4 + 4 + 1)) - 4;

			// movX = (int) (Math.random() * (1 + 1 + 1)) - 1;
			// movY = (int) (Math.random() * (1 + 1 + 1)) - 1;

			// Ask the player for the next move
			System.out.println("What is your next move?");
			System.out.print("x: ");
			movX = question.nextInt();
			System.out.print("y: ");
			movY = question.nextInt();

			player1.move1(movX, movY);// LOST ENERGY ALREADY

			if (player1.x_pos >= myGrid.x_dim)
				player1.x_pos = myGrid.x_dim - 1;
			if (player1.x_pos < 0)
				player1.x_pos = 0;
			if (player1.y_pos >= myGrid.y_dim)
				player1.y_pos = myGrid.y_dim - 1;
			if (player1.y_pos < 0)
				player1.y_pos = 0;

			if (!IsValidMove.isValidMove(player1, myGrid)) {
				player1.x_pos = player1.x_old;
				player1.y_pos = player1.y_old;
				player1.energy++;// SHOULD REGAIN ENERGY
			}

			System.out.println("x = " + player1.x_pos + ", y = " + player1.y_pos);

			// MODIFY THAT PART TO CHECK NUMBER OF BLIND AND SLIP in area

			/*
			 * for(int j = 0; j < myGrid.n_Blind ; j++) {
			 * 
			 * 
			 * 
			 * System.out.println("x = " + player1.x_pos + ", y = " + player1.y_pos);
			 * 
			 * for (int j = 0; j < myGrid.n_Blind; j++) {
			 * 
			 * 
			 * if (myGrid.myBlindSpot[j].x == player1.x_pos && myGrid.myBlindSpot[j].y ==
			 * player1.y_pos) { System.out.println("You are on a blind spot !"); }
			 * 
			 * }
			 * 
			 * for (int j = 0; j < myGrid.n_Slip; j++) {
			 * 
			 * if (myGrid.mySlipTile[j].x == player1.x_pos && myGrid.mySlipTile[j].y ==
			 * player1.y_pos) { System.out.println("You are on a slip tile !"); }
			 * 
			 * 
			 * }
			 */

			int n_b = 0;
			int n_s = 0;
			for (int j = 0; j < myGrid.n_Blind; j++) {

				if (Math.abs(myGrid.myBlindSpot[j].x - player1.x_pos) <= myGrid.myBlindSpot[j].influenceRadius
						&& Math.abs(myGrid.myBlindSpot[j].y - player1.y_pos) <= myGrid.myBlindSpot[j].influenceRadius) {
					// System.out.println("You are in a blind spot zone!");
					n_b++;
				}

			}

			for (int j = 0; j < myGrid.n_Slip; j++) {

				if (Math.abs(myGrid.mySlipTile[j].x - player1.x_pos) <= myGrid.mySlipTile[j].influenceRadius
						&& Math.abs(myGrid.mySlipTile[j].x - player1.x_pos) <= myGrid.mySlipTile[j].influenceRadius) {
					// System.out.println("You are in a slip tile area!");
					n_s++;
				}

			}

			// CHECK IF DANS ZONE INFLUENCE + nombre
			
			for (int j = 0; j < myGrid.n_Juror; j++) {

				if (myGrid.myJuror[j].active) {
					if (myGrid.myJuror[j].x == player1.x_pos && myGrid.myJuror[j].y == player1.y_pos) {
						System.out.println("You play against a juror !");
						if (Win.judgeWon(player1, myGrid.myJuror[j], n_b)) {
							System.out.println("You convinced a juror !");
							// update looks, skills;
							player1.gainLooks(1);
							System.out.println("looks skills = " + player1.looksSkills);
							// remove the juror from grid;
							myGrid.removeJuror(j);
						} else {
							System.out.println("The juror was not convinced !");
							// revenir position avant;
							// NRJ;
							player1.x_pos = player1.x_old;
							player1.y_pos = player1.y_old;
							player1.energy++;

						}
					}
				}
			}

			for (int j = 0; j < myGrid.n_Dancer; j++) {

				if (myGrid.myDancer[j].active) {
					if (myGrid.myDancer[j].x == player1.x_pos && myGrid.myDancer[j].y == player1.y_pos) {
						System.out.println("You play against a dancer !");
						if (Win.danceWon(player1, myGrid.myDancer[j], n_s)) {
							System.out.println("You won the dance battle !");
							// update looks, skills; CHOICE SCANNER
							player1.gainDancingSkills(1);
							System.out.println("dance skills = " + player1.danceSkills);
							// remove the juror from grid;
							myGrid.removeDancer(j);
						} else {
							System.out.println("You lost the dance battle !");
							// revenir position avant;
							// NRJ;
							player1.x_pos = player1.x_old;
							player1.y_pos = player1.y_old;
							player1.energy++;
						}
					}
				}

			}

			if (myGrid.trophy_x == player1.x_pos && myGrid.trophy_y == player1.y_pos) {
				// Add condition to take the trophy
				System.out.println("You found the trophy !");
				// calculate the current score of the player
				player1.currentScore();
				System.out.println("Score = " + player1.hs);
				// if fail, backtrack and energy++

			}
			if (player1.energy <= 0) {
				System.out.println("You run out of energy :'( ");
				// calculate the current score of the player
				player1.hs = 0;
				System.out.println("Score = " + player1.hs);
			}

			// i++;
			System.out.println("energy = " + player1.energy);
		}
		
		question.close();
		
		Highscore.UpdateHighScore(player1);

}
}
