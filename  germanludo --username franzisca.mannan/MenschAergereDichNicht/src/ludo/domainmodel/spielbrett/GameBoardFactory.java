package ludo.domainmodel.spielbrett;

import java.awt.Color;

/**
 * Creates a {@link GameBoard} and initializes it.
 *
 */
public class GameBoardFactory {

	public static GameBoard createGameBoard(Color color)
	{
		GameBoard gameBoard = new GameBoard();
		
		//Start configuration
		configure();
		
		return gameBoard;
	}

	private static void configure() {
		// TODO Auto-generated method stub
		
	}
	
	
}
