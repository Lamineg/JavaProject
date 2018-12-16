package MyPackage;

public class Win {

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
