package MyPackage;

public class Program {
	
	public static void main(String[] args) {
		
		//create new player
		Player player1 = new Player();
		
		System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
		
		// TEST FOR GRID PART ;

		Grid myGrid = new Grid(12,3,2,2,2,2);

		int [] positions_x = {2,2,-1};//new int [4];
		int [] positions_y = {3,-1,1};//new int [4];
		
		//System.out.println("blind x = "+myGrid.myBlindSpot.x+", blind y = "+ myGrid.myBlindSpot.y);
		
		//
		
		System.out.println("energy = "+player1.energy);
		
		int i = 0;
		int movX, movY;
		
		while (player1.energy > 0 && (myGrid.trophy_x != player1.x_pos || myGrid.trophy_y != player1.y_pos)) {
			
			//player1.move(positionsx[i], positions_y[i]);
			visibility(myGrid, player1);
			
			/* Replace this part by a scanner  or GUI*/
			//movX = (int) (Math.random()*(4 + 4 + 1)) - 4;
			//movY = (int) (Math.random()*(4 + 4 + 1)) - 4;
			
			movX = (int) (Math.random()*(1 + 1 + 1)) - 1;
			movY = (int) (Math.random()*(1 + 1 + 1)) - 1;
			
			player1.move(movX, movY);
			
			if(player1.x_pos >= myGrid.x_dim) player1.x_pos = myGrid.x_dim - 1;
			if(player1.x_pos < 0) player1.x_pos = 0;
			if(player1.y_pos >= myGrid.y_dim) player1.y_pos = myGrid.y_dim - 1;
			if(player1.y_pos < 0) player1.y_pos = 0;
			
			/*                        */
			
			System.out.println("x = "+player1.x_pos+", y = "+ player1.y_pos);
			
			for(int j = 0; j < myGrid.n_Blind ; j++) {
				
				if (myGrid.myBlindSpot[j].x == player1.x_pos && myGrid.myBlindSpot[j].y == player1.y_pos) {
					System.out.println("You are on a blind spot !");
				}
				
			}
			
			for(int j = 0; j < myGrid.n_Juror ; j++) {
				
				if (myGrid.myJuror[j].x == player1.x_pos && myGrid.myJuror[j].y == player1.y_pos) {
					System.out.println("You play against a juror !");
				}
				
			}
			
			for(int j = 0; j < myGrid.n_Slip ; j++) {
				
				if (myGrid.mySlipTile[j].x == player1.x_pos && myGrid.mySlipTile[j].y == player1.y_pos) {
					System.out.println("You are on a slip tile !");
				}
				
			}
			
			for(int j = 0; j < myGrid.n_Dancer ; j++) {
				
				if (myGrid.myDancer[j].x == player1.x_pos && myGrid.myDancer[j].y == player1.y_pos) {
					System.out.println("You play against a dancer !");
				}
				
			}
			
			if (myGrid.trophy_x == player1.x_pos && myGrid.trophy_y == player1.y_pos) {
				System.out.println("You found the trophy !");
			}
			if (player1.energy <= 0) {
				System.out.println("You run out of energy :'( ");
			}
			
			i++;
		}
		
	}
	
	public static void visibility(Grid myGrid, Player myPlayer) {
		
		for(int j = 0; j < myGrid.n_Dancer ; j++) {
			
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
		
		for(int j = 0; j < myGrid.n_Juror ; j++) {
			
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
		
		for(int j = 0; j < myGrid.n_Slip ; j++) {
			
			if (myGrid.mySlipTile[j].x == myPlayer.x_pos && myGrid.mySlipTile[j].y == myPlayer.y_pos + 1) {
				System.out.print("Dale glissante en haut; ");
			}
			else if(myGrid.mySlipTile[j].x == myPlayer.x_pos && myGrid.mySlipTile[j].y == myPlayer.y_pos - 1){
				System.out.print("Dale glissante en bas; ");
			}
			else if(myGrid.mySlipTile[j].x == myPlayer.x_pos + 1 && myGrid.mySlipTile[j].y == myPlayer.y_pos){
				System.out.print("Dale glissante a droite; ");
			}
			else if(myGrid.mySlipTile[j].x == myPlayer.x_pos - 1 && myGrid.mySlipTile[j].y == myPlayer.y_pos){
				System.out.print("Dale glissante a gauche; ");
			}
			
		}
		
		for(int j = 0; j < myGrid.n_Blind ; j++) {
			
			if (myGrid.myBlindSpot[j].x == myPlayer.x_pos && myGrid.myBlindSpot[j].y == myPlayer.y_pos + 1) {
				System.out.print("Spot en haut; ");
			}
			else if(myGrid.myBlindSpot[j].x == myPlayer.x_pos && myGrid.myBlindSpot[j].y == myPlayer.y_pos - 1){
				System.out.print("Spot en bas; ");
			}
			else if(myGrid.myBlindSpot[j].x == myPlayer.x_pos + 1 && myGrid.myBlindSpot[j].y == myPlayer.y_pos){
				System.out.print("Spot a droite; ");
			}
			else if(myGrid.myBlindSpot[j].x == myPlayer.x_pos - 1 && myGrid.myBlindSpot[j].y == myPlayer.y_pos){
				System.out.print("Spot a gauche; ");
			}
			
		}
		
		if (myGrid.trophy_x == myPlayer.x_pos && myGrid.trophy_y == myPlayer.y_pos + 1) {
			System.out.print("Trophee en haut; ");
		}
		else if(myGrid.trophy_x == myPlayer.x_pos && myGrid.trophy_y == myPlayer.y_pos - 1){
			System.out.print("Trophee en bas; ");
		}
		else if(myGrid.trophy_x == myPlayer.x_pos + 1 && myGrid.trophy_y == myPlayer.y_pos){
			System.out.print("Trophee a droite; ");
		}
		else if(myGrid.trophy_x == myPlayer.x_pos - 1 && myGrid.trophy_y == myPlayer.y_pos){
			System.out.print("Trophee a gauche; ");
		}
		
		
		System.out.println(" ");
		
	}
	
	

}
