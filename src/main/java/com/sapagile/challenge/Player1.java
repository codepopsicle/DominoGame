package com.sapagile.challenge;

import java.util.Map;
import java.util.Random;

/**
 * Implementation of Naive player
 */
public class Player1 extends AbstractPlayer{

    @Override
    protected DominoTile addTileToTop(DominoTile currentTile){
        /* If domino line is empty, then add random tile from player's tile set */
        if(currentTile==null){
            Random generator = new Random();
            Object[] values = tileMap.keySet().toArray();
            int randomValue = (int) values[generator.nextInt(values.length)];
            return tileMap.get(randomValue);
        }

        /* Try and add first tile from tile set to top which matches */

        return addTile(currentTile, true);
    }

    @Override
    protected DominoTile addTileToBottom(DominoTile currentTile){
        /* Try and add first tile from tile set to bottom which matches */

        return addTile(currentTile, false);
    }

    private DominoTile addTile(DominoTile currentTile, boolean isTop){
        for(Map.Entry<Integer,DominoTile> entry : tileMap.entrySet()) {
            DominoTile tile = entry.getValue();
            int tileHalfValue;
            int compareValue;
            if(isTop){
                tileHalfValue = tile.getLowerHalf();
                compareValue = currentTile.getUpperHalf();
            }
            else{
                tileHalfValue = tile.getUpperHalf();
                compareValue = currentTile.getLowerHalf();
            }
            if(compareValue==tileHalfValue){
                return tile;
            }
            tile.reverseTile();
            if(isTop){
                tileHalfValue = tile.getLowerHalf();
                compareValue = currentTile.getUpperHalf();
            }
            else{
                tileHalfValue = tile.getUpperHalf();
                compareValue = currentTile.getLowerHalf();
            }
            if(compareValue==tileHalfValue){
                return tile;
            }
        }
        return null;
    }


}
