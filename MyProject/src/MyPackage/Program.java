package MyPackage;

import java.io.IOException;

public class Program {

	// main method is the first method that the program will run

	public static void main(String[] args) throws IOException, InterruptedException {

		FirstScreen first = new FirstScreen();

		first.run();

		FirstScreen.Wait(first);

		new Game(first.getDifficulty(), first.getUsername(), first.getInput_skills());

	}
}