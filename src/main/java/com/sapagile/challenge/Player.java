package com.sapagile.challenge;

import java.util.HashMap;

/**
 * Player interface defining main functions of a Player
 */
public interface Player {

    void addToTileMap(int tileId, DominoTile tile);

    void removeFromTileMap(int tileId);

    void printTileMap();

    boolean isTileMapEmpty();

    int getCurrentTileCount();

    void setTileMap(HashMap<Integer, DominoTile> tileMap);

    DominoTile addTileToTopOrBottom(DominoTile currentTile, boolean isTop);
}
