package MyPackage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Program {

	// main method is the first method that the program will run
	public static void main(String[] args) throws IOException, InterruptedException {
		
		FirstScreen first = new FirstScreen();
		
		first.run();
		
		FirstScreen.Wait(first);
		
		System.out.println(first.getUsername());
		
		// Initialization of a scanner to get the username, the difficulty and the
		// skills of the player
		
		Player player1 = new Player(first.getUsername(), first.getInput_skills(), first.getDifficulty());
		

		Grid myGrid = new Grid(first.getDifficulty());

		Scanner question = new Scanner(System.in);

		int movX;
		int movY;
		
		while (player1.energy > 0 && (myGrid.trophy_x != player1.x_pos || myGrid.trophy_y != player1.y_pos)) {

			// player1.move(positionsx[i], positions_y[i]);
			visibility(myGrid, player1);

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

			if (!isValidMove(player1, myGrid)) {
				player1.x_pos = player1.x_old;
				player1.y_pos = player1.y_old;
				player1.energy++;// SHOULD REGAIN ENERGY
			}

			/*                        */

			System.out.println("x = " + player1.x_pos + ", y = " + player1.y_pos);

			for (int j = 0; j < myGrid.n_Blind; j++) {

				if (myGrid.myBlindSpot[j].x == player1.x_pos && myGrid.myBlindSpot[j].y == player1.y_pos) {
					System.out.println("You are on a blind spot !");
				}

			}

			for (int j = 0; j < myGrid.n_Slip; j++) {

				if (myGrid.mySlipTile[j].x == player1.x_pos && myGrid.mySlipTile[j].y == player1.y_pos) {
					System.out.println("You are on a slip tile !");
				}

			}

			for (int j = 0; j < myGrid.n_Juror; j++) {

				if (myGrid.myJuror[j].x == player1.x_pos && myGrid.myJuror[j].y == player1.y_pos) {
					System.out.println("You play against a juror !");
					if (judgeWon(player1, myGrid.myJuror[j])) {
						System.out.println("You convinced a juror !");
						// update looks, skills;
						// remove the juror from grid;
					} else {
						System.out.println("The juror was not convinced !");
						// revenir position avant;
						// NRJ;

					}
				}

			}

			for (int j = 0; j < myGrid.n_Dancer; j++) {

				if (myGrid.myDancer[j].x == player1.x_pos && myGrid.myDancer[j].y == player1.y_pos) {
					System.out.println("You play against a dancer !");
					if (danceWon(player1, myGrid.myDancer[j])) {
						System.out.println("You won the dance battle !");
						// update looks, skills;

						// remove the juror from grid;
					} else {
						System.out.println("You lost the dance battle !");
						// revenir position avant;
						// NRJ;
					}
				}

			}

			if (myGrid.trophy_x == player1.x_pos && myGrid.trophy_y == player1.y_pos) {
				System.out.println("You found the trophy !");
				// calculate the current score of the player
				player1.currentScore();
				System.out.println("Score = " + player1.hs);
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

		// File test = new File("High Scores.txt");

		// count the lines
		Scanner count = new Scanner(new File("High Scores.txt"));
		int countLines = 0;

		while (count.hasNextLine()) {
			countLines++;
			count.nextLine();
		}

		count.close();

		// create array of scores to store the objects Highscore
		Highscore[] score = new Highscore[countLines + 1];

		Scanner scanner = new Scanner(new File("High Scores.txt"));

		for (int i = 0; i < countLines; i++) {
			score[i] = new Highscore(scanner.next(), scanner.nextInt());
		}

		scanner.close();

		score[countLines] = new Highscore(player1.username, player1.hs);
		/*
		 * for (int x = 0; x < countLines + 1; x++) {
		 * System.out.println(score[x].username + score[x].hs); }
		 */
		// Arrays.sort(score, new SortbyScore());
		
		Arrays.sort(score, new SortByScore());

		/*
		 * for (int x = 0; x < countLines + 1; x++) {
		 * System.out.println(score[x].username + score[x].hs); }
		 */

		PrintWriter writer = new PrintWriter("High Scores.txt");

		if (score.length > 10) {
			for (int z = 0; z < 10; z++) {
				writer.println(score[z].username + " " + score[z].hs);
			}
		}

		else {
			for (int z = 0; z < score.length; z++) {
				writer.println(score[z].username + " " + score[z].hs);
			}

		}
		writer.close();
	}

	public static void visibility(Grid myGrid, Player myPlayer) {

		for (int j = 0; j < myGrid.n_Dancer; j++) {

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

		for (int j = 0; j < myGrid.n_Juror; j++) {

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

	public static boolean isValidMove(Player myPlayer, Grid myGrid) {

		if (myPlayer.x_pos > myPlayer.x_old + 1 || myPlayer.x_pos < myPlayer.x_old - 1) {
			System.out.println("Invalid move");
			return false;
		}

		if (myPlayer.y_pos > myPlayer.y_old + 1 || myPlayer.y_pos < myPlayer.y_old - 1) {
			System.out.println("Invalid move");
			return false;
		}

		for (int j = 0; j < myGrid.n_Slip; j++) {

			if (myGrid.mySlipTile[j].x == myPlayer.x_pos && myGrid.mySlipTile[j].y == myPlayer.y_pos) {
				System.out.println("Invalid move");
				return false;
			}

		}

		for (int j = 0; j < myGrid.n_Blind; j++) {

			if (myGrid.myBlindSpot[j].x == myPlayer.x_pos && myGrid.myBlindSpot[j].y == myPlayer.y_pos) {
				System.out.println("Invalid move");
				return false;
			}

		}

		return true;
	}

	public static boolean judgeWon(Player myPlayer, Juror myJuror) {

		int random;
		random = (int) (Math.random() * (3)) - 1; // -1, 0 ou 1

		if (myPlayer.looksSkills >= myJuror.looksThreshold + random) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean danceWon(Player myPlayer, Dancer myDancer) {

		int random;
		random = (int) (Math.random() * (3)) - 1; // -1, 0 ou 1

		if (myPlayer.danceSkills >= myDancer.danceThreshold + random) {
			return true;
		} else {
			return false;
		}
	}
}
