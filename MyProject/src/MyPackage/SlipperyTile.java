package MyPackage;

public class SlipperyTile {
	
	//x and y represent the position of the slippery tile, influence Radius represents how far the penalty can influence the player
	int x;
	int y;
	int influenceRadius;
	
	//constructor, instantiates the object SlipperyTile
	SlipperyTile(int x, int y, int radius){
		this.x = x;
		this.y = y;
		influenceRadius = radius;
	}

}
