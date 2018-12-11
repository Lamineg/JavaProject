package MyPackage;


public class Program {

	
	//main method is the first method that the program will run
	public static void main(String[] args) {
		
	Player player1 = new Player(0,0);
	
	player1.move(2, 1);
	
	System.out.println(player1.x_pos + ", " + player1.y_pos);
			
	}

}
