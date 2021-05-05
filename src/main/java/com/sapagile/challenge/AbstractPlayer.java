package com.sapagile.challenge;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class implementing main functionalities of a Player. Inherited by all players
 */
public abstract class AbstractPlayer implements Player{

    protected HashMap<Integer, DominoTile> tileMap;

    @Override
    public void addToTileMap(int tileId, DominoTile tile) {
        tileMap.put(tileId, tile);
    }

    @Override
    public void removeFromTileMap(int tileId) {
        tileMap.remove(tileId);
    }

    @Override
    public void printTileMap(){
        System.out.println(this.getClass().getSimpleName());
        for(Map.Entry<Integer,DominoTile> entry : tileMap.entrySet()){
            System.out.println(entry.getKey() + " : " + "[" + entry.getValue().getUpperHalf() +
                    "," + entry.getValue().getLowerHalf() + "]");
        }
    }

    @Override
    public boolean isTileMapEmpty() {
        return tileMap.isEmpty();
    }

    @Override
    public int getCurrentTileCount() {
        return tileMap.size();
    }

    @Override
    public void setTileMap(HashMap<Integer, DominoTile> map){
        tileMap = map;
    }

    /**
     *
     * @param currentTile
     * @param isTop
     * @return DominoTile added to the top or bottom
     * Method adds a tile either to top or bottom of the domino line. If isTop=true, then adds to top
     * ,else bottom
     *
     */
    @Override
    public DominoTile addTileToTopOrBottom(DominoTile currentTile, boolean isTop){
        DominoTile addedTile;
        if(isTop){
            addedTile = addTileToTop(currentTile);
        }
        else{
            addedTile = addTileToBottom(currentTile);
        }
        return addedTile;
    }

    /**
     *
     * @param currentTile
     * @return DominoTile
     * Implementation in Player class
     *
     */
    protected abstract DominoTile addTileToTop(DominoTile currentTile);

    /**
     *
     * @param currentTile
     * @return DominoTile
     * Implementation in player class
     */
    protected abstract DominoTile addTileToBottom(DominoTile currentTile);
}
