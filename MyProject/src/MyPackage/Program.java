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
		System.out.println("Balance between Looks and Dance (0 favors Looks, " 
							+ "5 is same for both and 10 favors Dance: "		);
		int input_skills = question.nextInt();
		
		
		
		//use the player's inputs for username, input_skills and difficulty to determine to instantiate a new player object.
		Player player1 = new Player(username, input_skills, difficulty);

		//use the player's inputs for the difficulty to instantiate a new object grid. 
		Grid myGrid = new Grid(difficulty);

		//instantiate the variables that will take in the player's desired move
		int movX;
		int movY;

		//the while loop that makes the game running, the game continues running until the player has either lost its energy or
		//found the trophy
		while (player1.getEnergy() > 0 && (myGrid.getTrophy_x() != player1.getX_pos() || myGrid.getTrophy_y() != player1.getY_pos())) {

			// call method to check visibility (see class visibility)
			Visibility.vis(myGrid, player1);
			
			// Ask the player for the next move
			System.out.println("What is your next move?");
			System.out.print("x: ");
			movX = question.nextInt();
			System.out.print("y: ");
			movY = question.nextInt();
			
			//move the player
			player1.move1(movX, movY);

			// uses the isValidmove method to check whether the move is valid or not, if not
			// valid goes back to old position
			// and regains its lost energy
			if (!IsValidMove.isValidMove(player1, myGrid)) {
				// if the move is invalid the position of the player goes back to its previous
				// position
				player1.setX_pos(player1.getX_old());
				player1.setY_pos(player1.getY_old());
				// if the move is invalid the player regains its energy
				player1.gainEnergy(1);;
			}

			//new position of the player
			System.out.println("x = " + player1.getX_pos() + ", y = " + player1.getY_pos());
			
			//instantiate n_b and n_s which indicates the number of blinding spotlights and slippery tiles that are 
			//around the player
			int n_b = 0;
			int n_s = 0;
			
			//instantiate temp_look and temp_dance which contains the temporary looks and dancing skills of the player while he is 
			//influenced by the surrounding blinding spotlights or slippery tiles
			int temp_looks = player1.getLooks();
			int temp_dance = player1.getDance_skills();
			
			//n_b adds 1 for each spots that are next to the player
			for (int j = 0; j < myGrid.getN_Blind(); j++) {

				if (Math.abs(myGrid.myBlindSpot[j].getX() - player1.getX_pos()) <= myGrid.myBlindSpot[j].getInfluenceRadius()
						&& Math.abs(myGrid.myBlindSpot[j].getY() - player1.getY_pos()) <= myGrid.myBlindSpot[j].getInfluenceRadius()) {
					n_b++;
				}	

			}
			
			//if there are at least one blinding spot in the area surrounding the player then a temporary looks variable will be
			//calculated depending on the number of blinding spots and the difficulty chosen by the player
			if(n_b>0) {
			n_b = n_b*(difficulty+1);
			temp_looks = temp_looks - n_b;
			System.out.println("Number of blinding spotlights around you: "+n_b);
			System.out.println("Your looks are temporarly at: "+temp_looks);
			}
			
			//n_s adds 1 for each slippery tile surrounding the player
			for (int j = 0; j < myGrid.getN_Slip(); j++) {

				if (Math.abs(myGrid.mySlipTile[j].getX() - player1.getX_pos()) <= myGrid.mySlipTile[j].getInfluenceRadius()
						&& Math.abs(myGrid.mySlipTile[j].getY() - player1.getY_pos()) <= myGrid.mySlipTile[j].getInfluenceRadius()) {
					n_s++;
				}
				
			}
			
			//if there are at least one blinding spot in the area surrounding the player then a temporary looks variable will be
			//calculated depending on the number of blinding spots and the difficulty chosen by the player
			if(n_s>0) {
			n_s = n_s*(difficulty+1);
			temp_dance = temp_dance - n_s;
			System.out.println("Number of slippery tiles around you: "+n_s);
			System.out.println("Your dancing skills are temporarly at: "+temp_dance);
			}
			
			//for loop that checks for every Juror if they share the same position as the player, if it's the case
			//the "battle" between juror and dancer starts using the judgeWon method of the class Win. If the player
			//wins, he takes the position of the juror and the juror disappears. If the juror wins, the player goes back to
			//its old spot but regains its energy.
			for (int j = 0; j < myGrid.getN_Juror(); j++) {

				if (myGrid.myJuror[j].isActive()) {
					if (myGrid.myJuror[j].getX() == player1.getX_pos() && myGrid.myJuror[j].getY() == player1.getY_pos()) {
						System.out.println("You play against a juror !");
						if (Win.judgeWon(player1, myGrid.myJuror[j], temp_looks)) {
							System.out.println("You convinced a juror !");
							//looks gains depending on the difficulty
							player1.gainLooks(11-difficulty);
							System.out.println("looks skills = " + player1.getLooks());
							// remove the juror from grid;
							myGrid.removeJuror(j);
						} else {
							System.out.println("The juror was not convinced !");
							//if we don't convince the juror we got back to our previous position
							player1.setX_pos(player1.getX_old());
							player1.setY_pos(player1.getY_old());
							//the player should not gain back its energy after losing against a juror
							//player1.energy++;

						}
					}
				}
			}

			for (int j = 0; j < myGrid.getN_Dancer(); j++) {

				if (myGrid.myDancer[j].isActive()) {
					if (myGrid.myDancer[j].getX() == player1.getX_pos() && myGrid.myDancer[j].getY() == player1.getY_pos()) {
						System.out.println("You play against a dancer !");
						if (Win.danceWon(player1, myGrid.myDancer[j], n_s)) {
							System.out.println("You won the dance battle !");
							// update looks, skills; CHOICE SCANNER
							player1.gainDancingSkills(11-difficulty);
							System.out.println("dance skills = " + player1.getDance_skills());
							// remove the juror from grid;
							myGrid.removeDancer(j);
						} else {
							System.out.println("You lost the dance battle !");
							//if we don't convince the juror we got back to our previous position
							player1.setX_pos(player1.getX_old());
							player1.setY_pos(player1.getY_old());
							//the player should not gain back its energy after losing against a juror
							//player1.energy++;
						}
					}
				}

			}
			
			//if the player stands on the same position as the trophy, a message appears
			if (myGrid.getTrophy_x() == player1.getX_pos() && myGrid.getTrophy_y() == player1.getY_pos()) {
				// Add condition to take the trophy
				System.out.println("You found the trophy !");
				// calculate the current score of the player
				player1.currentScore();
				System.out.println("Score = " + player1.getHs());

			}
			
			//if the player runs out of energy, a message appears
			if (player1.getEnergy() <= 0) {
				System.out.println("You run out of energy :'( ");
				// calculate the current score of the player
				player1.setHs(0);
				System.out.println("Score = " + player1.getHs());
			}

			System.out.println("energy = " + player1.getEnergy());
			System.out.println("looks = "+ player1.getLooks());
			System.out.println("dancing skills = "+ player1.getDance_skills());
			
		}

		question.close();

		Highscore.UpdateHighScore(player1);

	}
}
