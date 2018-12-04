package MyPackage;

public class Program {
	
	public static void main(String[] args) {
		
		Player player1 = new Player();
		
		System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
		
		player1.move(1,4);
		
		System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
		
	}

}
