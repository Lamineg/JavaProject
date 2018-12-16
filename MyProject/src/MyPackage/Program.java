package MyPackage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
		System.out.println("Balance between Looks and Dance (0 favors Looks, " 
		+ "5 is same for both and 10 favors Dance: ");
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

			// player1.move(positionsx[i], positions_y[i]);
			visibility(myGrid, player1);

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

			if (!isValidMove(player1, myGrid)) {
				player1.x_pos = player1.x_old;
				player1.y_pos = player1.y_old;
				player1.energy++;// SHOULD REGAIN ENERGY
			}

		


			System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
			
			//MODIFY THAT PART TO CHECK NUMBER OF BLIND AND SLIP in area
			
			/*for(int j = 0; j < myGrid.n_Blind ; j++) {
				


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

				
			}*/
			
			int n_b = 0; 
			int n_s = 0;
			for(int j = 0; j < myGrid.n_Blind ; j++) {
				
				if (Math.abs(myGrid.myBlindSpot[j].x - player1.x_pos) <= myGrid.myBlindSpot[j].influenceRadius && Math.abs(myGrid.myBlindSpot[j].y - player1.y_pos) <= myGrid.myBlindSpot[j].influenceRadius) {
					//System.out.println("You are in a blind spot zone!");
					n_b++;
				}
				
			}
			
			for(int j = 0; j < myGrid.n_Slip ; j++) {
				
				if (Math.abs(myGrid.mySlipTile[j].x - player1.x_pos) <= myGrid.mySlipTile[j].influenceRadius && Math.abs(myGrid.mySlipTile[j].x - player1.x_pos) <= myGrid.mySlipTile[j].influenceRadius) {
					//System.out.println("You are in a slip tile area!");
					n_s++;
				}
				
			}
			
			//CHECK IF DANS ZONE INFLUENCE + nombre
			
			for(int j = 0; j < myGrid.n_Juror ; j++) {
				
				if(myGrid.myJuror[j].active) {
					if (myGrid.myJuror[j].x == player1.x_pos && myGrid.myJuror[j].y == player1.y_pos) {
						System.out.println("You play against a juror !");
						if(judgeWon(player1,myGrid.myJuror[j],n_b)) {
							System.out.println("You convinced a juror !");
							//update looks, skills;
							player1.gainLooks(1);
							System.out.println("looks skills = " + player1.looksSkills);
							//remove the juror from grid;
							myGrid.removeJuror(j);
						}
						else 
						{
							System.out.println("The juror was not convinced !");
							//revenir position avant;
							//NRJ;
							player1.x_pos = player1.x_old;
							player1.y_pos = player1.y_old; 
							player1.energy++;
							
						}
					}
				}
			}
			
			for(int j = 0; j < myGrid.n_Dancer ; j++) {
				
				if(myGrid.myDancer[j].active) {
					if (myGrid.myDancer[j].x == player1.x_pos && myGrid.myDancer[j].y == player1.y_pos) {
						System.out.println("You play against a dancer !");
						if(danceWon(player1,myGrid.myDancer[j],n_s)) {
							System.out.println("You won the dance battle !");
							//update looks, skills; CHOICE SCANNER
							player1.gainDancingSkills(1);
							System.out.println("dance skills = " + player1.danceSkills);
							//remove the juror from grid;
							myGrid.removeDancer(j);
						}
						else 
						{
							System.out.println("You lost the dance battle !");
							//revenir position avant;
							//NRJ;
							player1.x_pos = player1.x_old;
							player1.y_pos = player1.y_old; 
							player1.energy++;
						}
					}
				}


			}


			if (myGrid.trophy_x == player1.x_pos && myGrid.trophy_y == player1.y_pos) {
				//Add condition to take the trophy
				System.out.println("You found the trophy !");
				// calculate the current score of the player
				player1.currentScore();
				System.out.println("Score = " + player1.hs);
				//if fail, backtrack and energy++
			
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

		
		for(int j = 0; j < myGrid.n_Dancer ; j++) {
			
			if(myGrid.myDancer[j].active) {
				if (myGrid.myDancer[j].x == myPlayer.x_pos && myGrid.myDancer[j].y == myPlayer.y_pos + 1) {
					System.out.print("Danceur en haut; ");
				}
				else if(myGrid.myDancer[j].x == myPlayer.x_pos && myGrid.myDancer[j].y == myPlayer.y_pos - 1){
					System.out.print("Danceur en bas; ");
				}
				else if(myGrid.myDancer[j].x == myPlayer.x_pos + 1 && myGrid.myDancer[j].y == myPlayer.y_pos){
					System.out.print("Danceur a droite; ");
				}
				else if(myGrid.myDancer[j].x == myPlayer.x_pos - 1 && myGrid.myDancer[j].y == myPlayer.y_pos){
					System.out.print("Danceur a gauche; ");
				}

			}
		}

		
		for(int j = 0; j < myGrid.n_Juror ; j++) {
			
			if(myGrid.myJuror[j].active) {
				if (myGrid.myJuror[j].x == myPlayer.x_pos && myGrid.myJuror[j].y == myPlayer.y_pos + 1) {
					System.out.print("Juge en haut; ");
				}
				else if(myGrid.myJuror[j].x == myPlayer.x_pos && myGrid.myJuror[j].y == myPlayer.y_pos - 1){
					System.out.print("Juge en bas; ");
				}
				else if(myGrid.myJuror[j].x == myPlayer.x_pos + 1 && myGrid.myJuror[j].y == myPlayer.y_pos){
					System.out.print("Juge a droite; ");
				}
				else if(myGrid.myJuror[j].x == myPlayer.x_pos - 1 && myGrid.myJuror[j].y == myPlayer.y_pos){
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

	
	public static boolean judgeWon(Player myPlayer, Juror myJuror, int n_blind) { 
		// add number of blindspots
		
		int random;
		random = (int) (Math.random()*(3)) - 1; //-1, 0 ou 1
		
		if(myPlayer.looksSkills - n_blind >= myJuror.looksThreshold + random) {

			return true;
		
		} else {
			return false;
		}
	}


	
	public static boolean danceWon(Player myPlayer, Dancer myDancer, int n_spot) {
		//add number of slips
		
		int random;
		random = (int) (Math.random()*(3)) - 1; //-1, 0 ou 1
		
		if(myPlayer.danceSkills - n_spot >= myDancer.danceThreshold + random) {

			return true;
		
		} else {
			return false;
		}
	}
}
	
	
