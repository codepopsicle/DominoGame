package com.sapagile.challenge;

/**
 * Driver code for the game
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException{
        /* Two types of players: Naive and Competitive
           Inject type of player into game based on value of command-line args
         */
        final Player player1;
        final Player player2;
        if(args[0].equals("Naive")){
            player1 = new Player1();
            player2 = new Player2();
        }
        else{
            player1 = new CompetitivePlayer1();
            player2 = new CompetitivePlayer2();
        }
        final DominoGame dominoGame = new DominoGame(player1,player2);
        dominoGame.playDominoGame();
    }

}
