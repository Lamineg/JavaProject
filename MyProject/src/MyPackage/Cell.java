package MyPackage;

public class Cell {
    private boolean dancer;
    private boolean juror;
    private boolean slipTile;
    private boolean blindSpot;
    private boolean trophy;
    
    private boolean cover;

    private int value;

    public Cell() {
    	
        this.cover = true;
        
        this.dancer  = false;
        this.juror  = false;
        this.slipTile  = false;
        this.blindSpot  = false;
        this.trophy = false;
        
        this.value = 0;
    }

    public int getValue() {
        return this.value;
    }

    public void uncover() {
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
    }

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
    
}
