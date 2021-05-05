package com.test.sapagile.challenge;

import com.sapagile.challenge.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DominoGameTest {

    private static final Player naivePlayer1 = new Player1();
    private static final Player naivePlayer2 = new Player2();
    private static final Player competitivePlayer1 = new CompetitivePlayer1();
    private static final Player competitivePlayer2 = new CompetitivePlayer2();

    public static final DominoGame naiveDominoGame = new DominoGame(naivePlayer1, naivePlayer2);
    public static final DominoGame competitiveDominoGame = new DominoGame(competitivePlayer1, competitivePlayer2);

    @BeforeAll
    public static void setup() throws InterruptedException{
        // Run both games
        naiveDominoGame.playDominoGame();
        competitiveDominoGame.playDominoGame();
    }

    /**
     * Tests whether the total tile count across tile sets, Stock and Domino line totals 28
     */
    @Test
    public void tileCountTest(){

        Assert.assertEquals(28, naiveDominoGame.getCumulativeTileCount());
        Assert.assertEquals(28, competitiveDominoGame.getCumulativeTileCount());
    }

    /**
     * Tests if the Domino line is formed correctly
     */
    @Test
    public void dominoLineIntegrityTest(){
        List<DominoGame> dominoGameList = new ArrayList<>(Arrays.asList(naiveDominoGame,competitiveDominoGame));
        for(DominoGame dominoGame : dominoGameList){
            int previous = -1;
            boolean integrityPreserved = true;
            Deque<DominoTile> currentTileQueue = new LinkedList<>(dominoGame.getCurrentDominoTileQueue());
            for(DominoTile tile : currentTileQueue){
                if(previous == -1){
                    previous = currentTileQueue.getFirst().getLowerHalf();
                }
                else if(previous!=tile.getUpperHalf()){
                    integrityPreserved = false;
                }
                else{
                    previous = tile.getLowerHalf();
                }
            }
            Assert.assertTrue(integrityPreserved);
        }
    }
}
