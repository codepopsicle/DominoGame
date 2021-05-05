# DominoGame
Two player domino game

The logic is divided into two main parts based on my understanding of the game:

1)	Strategy: Two strategies have been implemented: Naïve, and Competitive

a)	Naïve: In this strategy, a player places the first matching tile in his/her tile set on the domino line. Additionally, if the player is the one starting the domino line, a random tile is placed.

b)	Competitive: In the game where a winner is determined either by who finished first or determined by who blocked, assume a scoring method where each player attempts to minimize THE AMOUNT OF POINTS GIVEN to the opponent AT THE END OF THE GAME. The points are basically the total number of pips on all the tiles remaining with the opponent at the end of the game. The naïve strategy is modified in such a way that the player places the tile with maximum pips from his/her tile set that is eligible to be placed on the domino line. This ensures the they will be left the least possible points at the end.

2)	Players: For each of the strategies listed above, two players each are created. The implementations within each pair of players have been made customizable in case their behavior is required to be customized at some point. A pair of players are created based on the strategy (passed in from the command-line), and passed into the game.


RUNNING THE SOLUTION

It has been coded in Java with a Maven build. 

1)	Itt can be run from the terminal at path /SAP_Challenge_Pritish/ using the below commands:

a)	The ‘com.sapagile.challenge.Driver’ is the class that contains the Main method. This can also be run directly using the Maven plugin in the terminal

mvn compile exec:java -Dexec.mainClass="com.sapagile.challenge.Driver" -Dexec.args="Naive" 

OR

mvn compile exec:java -Dexec.mainClass="com.sapagile.challenge.Driver" -Dexec.args="Competitive"  

The command line argument determines the strategy used by the players.

b)	‘mvn test’ can be used to run the unit tests.

