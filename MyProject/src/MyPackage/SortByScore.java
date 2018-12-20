package MyPackage;

import java.util.Comparator;

public class SortByScore implements Comparator<Highscore> {
	
	//calculates the difference between the scores of two object Highscores
	public int compare(Highscore a, Highscore b) {
        return b.getHs() - a.getHs();
    }

}
