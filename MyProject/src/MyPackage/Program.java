package MyPackage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Program {

	// main method is the first method that the program will run
	public static void main(String[] args) throws IOException {

		Player player = new Player("Aymeric", 0, 0);

		player.currentScore();

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

		score[countLines] = new Highscore(player.username, player.hs);

		for (int x = 0; x < countLines + 1; x++) {
			System.out.println(score[x].username + score[x].hs);
		}

		// Arrays.sort(score, new SortbyScore());
		Arrays.sort(score, new SortByScore());

		for (int x = 0; x < countLines + 1; x++) {
			System.out.println(score[x].username + score[x].hs);
		}

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
			writer.close();

		}
	}
}
