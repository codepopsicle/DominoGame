package com.sapagile.challenge;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implementation of the Domino gameplay
 */
public class DominoGame {

    private final Player player1;
    private final Player player2;
    private final Deque<DominoTile> currentDominoTileQueue = new LinkedList<>();
    private Player causeOfBlock;
    private final Stock stock;

    public DominoGame(Player p1, Player p2){
        stock = new Stock();
        this.player1 = p1;
        this.player2 = p2;
        // create stock
        stock.createStock();

        // set tile sets for the players
        player1.setTileMap(stock.getFirstDraw());
        player2.setTileMap(stock.getFirstDraw());
    }

    /**
     * Method to start game play
     * @throws InterruptedException
     */
    public void playDominoGame() throws InterruptedException{
        boolean didSomebodyWin = false;

        DominoTile currentTopTile = null;
        DominoTile currentBottomTile = null;

        /* Play until stock is empty */
        while(!stock.isStockEmpty()){

            /* updating the top and bottom of the domino line */
            if(!currentDominoTileQueue.isEmpty()){
                currentTopTile = currentDominoTileQueue.getFirst();
                currentBottomTile = currentDominoTileQueue.getLast();
            }

            // Player 1 plays
            boolean isStockEmpty = doPlayerTurn(player1, currentTopTile, currentBottomTile);
            Thread.sleep(1000);

            /* Check if player has already won */
            if(player1.isTileMapEmpty()){
                System.out.println("Player 1 wins !!");
                didSomebodyWin = true;
                break;
            }

            /* updating top and bottom tiles before Player 2 plays */

            if(!currentDominoTileQueue.isEmpty()){
                currentTopTile = currentDominoTileQueue.getFirst();
                currentBottomTile = currentDominoTileQueue.getLast();
            }


            /* check if stock is empty */
            if(!isStockEmpty){
                // Player 2 plays
                doPlayerTurn(player2, currentTopTile, currentBottomTile);
                Thread.sleep(1000);

                /* check if player has already won */
                if(player2.isTileMapEmpty()){
                    System.out.println("Player 2 wins !!");
                    didSomebodyWin = true;
                    break;
                }
            }

        }

        if(!didSomebodyWin){
            // If nobody won, then the player who blocked last won
            System.out.println(causeOfBlock.getClass().getSimpleName() + " caused the block");
            System.out.println(causeOfBlock.getClass().getSimpleName() + " wins !!");
        }

        // Print the domino line
        System.out.println("\nDomino Line");
        for(DominoTile tile : currentDominoTileQueue){
            System.out.println("[" + tile.getUpperHalf() +
                    "," + tile.getLowerHalf() + "]");
        }
    }


    /**
     * Implementation of a player playing his/her turn
     * @param player
     * @param topTile
     * @param bottomTile
     * @return
     */
    private boolean doPlayerTurn(Player player, DominoTile topTile, DominoTile bottomTile){
        boolean isStockEmpty = false;
        boolean isSameTopAndBottomElement = false;
        if(topTile==bottomTile){
            /* Either 0 or 1 element is present in the deque.
               If 0 elements are present, then element is added to the top
               If 1 element is present, then it cn be either added on the top or bottom
            */
            isSameTopAndBottomElement = true;
        }
        DominoTile playerTile = player.addTileToTopOrBottom(topTile, true);
        if(playerTile!=null){
            // add element to top
            currentDominoTileQueue.addFirst(playerTile);
            causeOfBlock = player;
            System.out.println(player.getClass().getSimpleName() + " plays a tile (" +
                    playerTile.getUpperHalf() + " , " + playerTile.getLowerHalf() + ")");
            player.removeFromTileMap(playerTile.getTileId());
        }
        else{
            /*
             Try to add element to bottom
             If top and bottom elements are the same, the element = topElement
             Else, element = bottomElement
             */
            if(isSameTopAndBottomElement){
                playerTile = player.addTileToTopOrBottom(topTile, false);
            }
            else{
                playerTile = player.addTileToTopOrBottom(bottomTile,false);
            }
            if(playerTile!=null){
                currentDominoTileQueue.addLast(playerTile);
                causeOfBlock = player;
                System.out.println(player.getClass().getSimpleName() + " plays a tile (" +
                        playerTile.getUpperHalf() + " , " + playerTile.getLowerHalf() + ")");
                player.removeFromTileMap(playerTile.getTileId());
            }
            else{
                // draw from stock
                DominoTile tileFromStock = stock.drawNextTile();
                if(tileFromStock!=null){
                    player.addToTileMap(tileFromStock.getTileId(), tileFromStock);
                    System.out.println(player.getClass().getSimpleName() + " draws from stock ");

                    /* Recursively call doPlayerTurn() until the player is able to place a tile
                       Ideally should not result in StackOverflow error unless the Stock is really large
                       In this case Stock is at max 14
                     */
                    doPlayerTurn(player, topTile, bottomTile);
                }
                else{
                    isStockEmpty = true;
                }
            }
        }
        return isStockEmpty;
    }

    public Deque<DominoTile> getCurrentDominoTileQueue(){
        return this.currentDominoTileQueue;
    }

    public int getCumulativeTileCount(){
        return currentDominoTileQueue.size() + player1.getCurrentTileCount() + player2.getCurrentTileCount()
                + stock.getCurrentStockTileCount();
    }
}
