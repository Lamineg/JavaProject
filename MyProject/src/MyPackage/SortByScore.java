package MyPackage;

import java.util.Comparator;

public class SortByScore implements Comparator<Highscore> {
	
	public int compare(Highscore a, Highscore b) {
        return b.hs - a.hs;
    }

}
