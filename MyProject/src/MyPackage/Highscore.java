package MyPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Highscore {

	private String username;

	private int hs;
	
	//constructs an object highscore with username and score
	public Highscore(String username, int hs) {
		this.username = username;
		this.hs = hs;

	}
	
	//updates the high score text file and takes as argument the player to get his score
	public static void UpdateHighScore(Player player1) throws FileNotFoundException {
		
		//new scanner to recover the top scores stored in a text file
		Scanner count = new Scanner(new File("High Scores.txt"));
		
		//count the number of lines to determine the lenght of the array that will store the 
		// high score objects
		int countLines = 0;

		while (count.hasNextLine()) {
			countLines++;
			count.nextLine();
		}

		count.close();

		// create array of scores to store the objects Highscore
		Highscore[] score = new Highscore[countLines + 1];

		//new scanner to recover the username and the score at each line of the text
		Scanner scanner = new Scanner(new File("High Scores.txt"));

		for (int i = 0; i < countLines; i++) {
			score[i] = new Highscore(scanner.next(), scanner.nextInt());
		}

		scanner.close();
		
		//the last element of the list will be the current score of the player
		score[countLines] = new Highscore(player1.getUsername(), player1.getHs());
		
		//we then sort the list using the class SortByScore
		Arrays.sort(score, new SortByScore());

		//new object PrintWriter to write back the updated highscores in the text file
		PrintWriter writer = new PrintWriter("High Scores.txt");
		
		
		//we only want to store maximum 1O scores, if there are more than 10 scores in the 
		//score list we only print the 10 best
		if (score.length > 10) {
			for (int z = 0; z < 10; z++) {
				writer.println(score[z].getUsername() + " " + score[z].getHs());
			}
		}

		//if there are less than 10 we print everything
		else {

			{
				for (int z = 0; z < score.length; z++) {
					writer.println(score[z].getUsername() + " " + score[z].getHs());
				}

			}
		}
		writer.close();
	}

	public int getHs() {
		return hs;
	}

	public void setHs(int hs) {
		this.hs = hs;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
