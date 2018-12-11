package MyPackage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class Program {

	
	//main method is the first method that the program will run
	public static void main(String[] args) throws IOException {
	
	//create file for highscores and update it
	
	File test = new File("High Scores.txt");
	
	/*
	PrintWriter output = new PrintWriter(test);
	output.println("this is a test");
	output.println("and this is the second line");
	output.close();
	*/
	
	//Read the file
	Scanner input = new Scanner(test);
	String first = input.nextLine();
	String second = input.nextLine();
	
	System.out.println(first+second);
	input.close();
	
	}
	
	}
	
	