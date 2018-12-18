package MyPackage;

public class Cell {
    private boolean dancer;
    private boolean juror;
    private boolean slipTile;
    private boolean blindSpot;
    private boolean trophy;
    private boolean player;
    
    private int x;
    private int y;
    
    private boolean visible;

    private int value;

    public Cell(int x, int y) {
    	
        this.visible = false;
        
        this.dancer  = false;
        this.juror  = false;
        this.slipTile  = false;
        this.blindSpot  = false;
        this.trophy = false;
        this.player = false;
        
        this.x = x;
        this.y = y;
        
        this.value = 0;
    }

    public int getValue() {
        return this.value;
    }

    /*public void uncover() {
        this.cover = false;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isEmpty() {
        return this.value == 0;
    }

    public boolean isMine() {
        return this.mine;
    }

    public void setMine(boolean b) {
        this.mine = b;
    }

    public boolean isMarked() {
        return this.mark;
    }

    public boolean isCovered() {
        return this.cover;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public boolean isCoveredMine() {
        return this.cover && this.mine;
    }

    public void checked() {
        this.checked = true;
    }

    public void clearChecked() {
        this.checked = false;
    }*/

    public void setAroundMines(int count) {
        this.value = count;
    }

    public int getAroundMines() {
        return this.value;
    }
    
    /**/
    
    public boolean isBlindSpot() {
        return this.blindSpot;
    }

    public boolean isDancer() {
        return this.dancer;
    }

    public boolean isJuror() {
        return this.juror;
    }

    public boolean isSlipTile() {
        return this.slipTile;
    }
    
    public boolean isTrophy() {
        return this.trophy;
    }
    
    public boolean isPlayer() {
        return this.player;
    }
    
    public boolean isVisible(Player myPlayer) {
        
    	if (x == myPlayer.x_pos && y == myPlayer.y_pos + 1) {
			
    		return true;
    		
		} else if(x == myPlayer.x_pos && y == myPlayer.y_pos - 1){
			
			return true;
			
		} else if(x == myPlayer.x_pos + 1 && y == myPlayer.y_pos){
			
			return true;
			
		} else if(x == myPlayer.x_pos - 1 && y == myPlayer.y_pos){
			
			return true;
			
		} else {
			
			return false;
		}

    }
    
    public boolean isReachable(Player myPlayer) {
		
		if(Math.abs(x - myPlayer.x_pos) <= 1 && Math.abs(y - myPlayer.y_pos) <= 1) {
			return true;
		}
		else
		{
			return false;
		}
		
    }
    
    public void setBlindSpot(boolean blindSpot) {
        this.blindSpot = blindSpot;
    }
    
    public void setSlipTile(boolean slipTile) {
        this.slipTile = slipTile;
    }
    
    public void setDancer(boolean dancer) {
        this.dancer = dancer;
    }
    
    public void setJuror(boolean juror) {
        this.juror = juror;
    }
    
    public void setTrophy(boolean trophy) {
        this.trophy = trophy;
    }
    
    public void setPlayer(boolean player) {
        this.player = player;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public int countSlipTile(SlipperyTile mySlipTile[], int n_Slip) {
		
		int n_s = 0;
		
		for(int j = 0; j < n_Slip ; j++) {
			
			if (Math.abs(mySlipTile[j].x - x) <= mySlipTile[j].influenceRadius && Math.abs(mySlipTile[j].y - y) <= mySlipTile[j].influenceRadius) {
				//System.out.println("You are in a slip tile area!");
				n_s++;
			}
			
		}
		
		return n_s;
	}
    
    public int countBlindSpot(BlindSpot myBlindSpot[], int n_Blind) {
		
		int n_b = 0; 
		
		for(int j = 0; j < n_Blind ; j++) {
			
			if (Math.abs(myBlindSpot[j].x - x) <= myBlindSpot[j].influenceRadius && Math.abs(myBlindSpot[j].y - y) <= myBlindSpot[j].influenceRadius) {
				//System.out.println("You are in a blind spot zone!");
				n_b++;
			}	
		}
		
		return n_b;
	}
    
    public int getJurorId(Juror myJuror[], int n_Juror) {
    	
    	int id = 0;
    	
    	for(int j = 0; j < n_Juror ; j++) {
				
    			id = j;
				if (myJuror[j].x == x && myJuror[j].y == y) {

						return id;		
					
				}
				
		}
    	
    	return id;
    }
    
    public int getDancerId(Dancer myDancer[], int n_Dancer) {
    	
    	int id = 0;
    	
    	for(int j = 0; j < n_Dancer ; j++) {
				
    		id = j;
				if (myDancer[j].x == x && myDancer[j].y == y) {

						return id;		
					
				}
		}
    	
    	return id;
    }
    
}
