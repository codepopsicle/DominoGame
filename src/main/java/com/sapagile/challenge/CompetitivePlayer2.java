package com.sapagile.challenge;

import java.util.Map;

/**
 * Implementation of CompetitivePlayer
 */
public class CompetitivePlayer2 extends AbstractPlayer{

    @Override
    protected DominoTile addTileToTop(DominoTile currentTile){
        /* If domino line is empty, then add tile from player's tile set with the max pips */
        if(currentTile==null){
            int max = -1;
            int maxValueKey = -1;
            for(Map.Entry<Integer,DominoTile> entry : tileMap.entrySet()) {
                DominoTile tile = entry.getValue();
                int tileValue = tile.getUpperHalf() + tile.getLowerHalf();
                if (max == -1) {
                    max = tileValue;
                }
                else if(max < tileValue){
                    max = tileValue;
                    maxValueKey = entry.getKey();
                }
            }
            return tileMap.get(maxValueKey);
        }

        /* Try and tile from tile set to top with max pips */

        return addTile(currentTile, true);
    }

    @Override
    protected DominoTile addTileToBottom(DominoTile currentTile){
        /* Try and tile from tile set to bottom with max pips */

        return addTile(currentTile, false);
    }

    private DominoTile addTile(DominoTile currentTile, boolean isTop){
        int max = -1;
        int maxValueKey = -1;
        for(Map.Entry<Integer,DominoTile> entry : tileMap.entrySet()) {
            DominoTile tile = entry.getValue();
            int totalTileValue = tile.getLowerHalf() + tile.getUpperHalf();
            int tileHalfValue;
            int compareValue;
            if (isTop) {
                tileHalfValue = tile.getLowerHalf();
                compareValue = currentTile.getUpperHalf();
            } else {
                tileHalfValue = tile.getUpperHalf();
                compareValue = currentTile.getLowerHalf();
            }
            if (compareValue == tileHalfValue) {
                if ((max == -1) || (max < totalTileValue)) {
                    max = totalTileValue;
                    maxValueKey = entry.getKey();
                }
            } else {
                tile.reverseTile();
                if (isTop) {
                    tileHalfValue = tile.getLowerHalf();
                    compareValue = currentTile.getUpperHalf();
                } else {
                    tileHalfValue = tile.getUpperHalf();
                    compareValue = currentTile.getLowerHalf();
                }
                if (compareValue == tileHalfValue) {
                    if ((max == -1) || (max < totalTileValue)) {
                        max = totalTileValue;
                        maxValueKey = entry.getKey();
                    }
                }
            }
        }
        if(maxValueKey!=-1){
            return tileMap.get(maxValueKey);
        }
        return null;
    }

}
