package MyPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Highscore {
	
	private String username;
	private int hs;
	

	public Highscore (String username, int hs) {
		this.username = username;
		this.hs = hs;
		
	}
	
	public static void UpdateHighScore(Player player1) throws FileNotFoundException {
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

		score[countLines] = new Highscore(player1.username, player1.getHs());
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
	
	public int getHs() {
		return hs;
	}
	
	public void setHs(int hs) {
		this.hs = hs;
	}
	
	
}
