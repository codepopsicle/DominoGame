package com.sapagile.challenge;

import java.util.*;

/**
 * Implementation of Domino Stock
 */
public class Stock {

    private final List<Integer> tileIdList;
    private final HashMap<Integer, DominoTile> tileMap;

    public Stock(){
        tileIdList = new ArrayList<>();
        tileMap = new HashMap<>();
    }

    /**
     * Creates initial Stock with 28 tiles
     */
    public void createStock(){
        int tileCounter = 1;
        for(int i=0; i<7; i++){
            for(int k=0; k<7; k++){
                if (k >= i) {
                    tileMap.put(tileCounter, new DominoTile(i,k, tileCounter));
                    tileIdList.add(tileCounter);
                    tileCounter++;
                }
            }
        }
    }

    /**
     * Deals first set of 7 tiles to both participants
     * @return Map of tiles (tile set)
     */
    public HashMap<Integer, DominoTile> getFirstDraw(){
        HashMap<Integer, DominoTile> drawnTiles = new HashMap<>();
        for(int i=1; i<8; i++){
            DominoTile tile = selectRandomTileAndProcess();
            if(tile!=null){
                drawnTiles.put(tile.getTileId(), tile);
            }
        }
        return drawnTiles;
    }

    /**
     * Draw next tile from stock
     * @return DominoTile
     */
    public DominoTile drawNextTile()
    {
        return selectRandomTileAndProcess();
    }

    private DominoTile selectRandomTileAndProcess(){
        Random rand = new Random();
        if(!tileIdList.isEmpty()){
            int tileId = tileIdList.get(rand.nextInt(tileIdList.size()));
            DominoTile dominoTile = tileMap.get(tileId);

            // deleting from stock
            tileIdList.remove(Integer.valueOf(tileId));
            tileMap.remove(tileId);

            return dominoTile;
        }
        return null;
    }

    public void printCurrentStock(){
        System.out.println("Stock ");
        for(Map.Entry<Integer,DominoTile> entry : tileMap.entrySet()){
            System.out.println(entry.getKey() + " : " + "[" + entry.getValue().getUpperHalf() +
                    "," + entry.getValue().getLowerHalf() + "]");
        }
    }

    public boolean isStockEmpty(){
        return tileMap.isEmpty();
    }

    public int getCurrentStockTileCount(){
        return tileMap.size();
    }
}
