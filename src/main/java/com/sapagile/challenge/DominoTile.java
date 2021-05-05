package com.sapagile.challenge;

/**
 * DominoTile class
 */
public class DominoTile {

    private int upperHalf;
    private int lowerHalf;
    private int tileId;

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

    public DominoTile(int upper, int lower, int Id){
        this.upperHalf = upper;
        this.lowerHalf = lower;
        this.tileId = Id;
    }

    public int getUpperHalf() {
        return upperHalf;
    }

    public void setUpperHalf(int upperHalf) {
        this.upperHalf = upperHalf;
    }

    public int getLowerHalf() {
        return lowerHalf;
    }

    public void setLowerHalf(int lowerHalf) {
        this.lowerHalf = lowerHalf;
    }

    /**
     * Reverses the tile
     */
    public void reverseTile(){
        int temp = this.upperHalf;
        this.upperHalf = this.lowerHalf;
        this.lowerHalf = temp;
    }
}
