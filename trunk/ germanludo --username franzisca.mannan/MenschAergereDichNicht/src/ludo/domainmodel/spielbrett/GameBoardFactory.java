package ludo.domainmodel.spielbrett;

import java.awt.Color;

import ludo.domainmodel.spieler.SpielerFarbe;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Creates a {@link GameBoard} and initializes it.
 *
 */
public class GameBoardFactory {

	public static GameBoard createGameBoard(Color color)
	{
		GameBoard gameBoard = new GameBoard();
		
		//Start configuration
		configure(gameBoard, color);
		
		return gameBoard;
	}

	/**
	 * Configures a given {@link GameBoard} and its field directions depending on its {@link Color}.
	 * 
	 * @param gameBoard
	 * @param color
	 */
	private static void configure(GameBoard gameBoard, Color color) 
	{
		/*
		 * The list index starts from zero. We start counting from Zero - so
		 * overall there are 44 game fields and the maximum number of game fields
		 * per GameBoard is 44. So the list index represents the GameField ID.
		 */
		for(int i = 0; i < 44; i++)
		{
			//Settings for the red GameBoard
			if(color.equals(SpielerFarbe.ROT))
			{
				if(i == 0)
					gameBoard.addGameField(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.UNTEN));					
				else if(i  > 39 && i < 43)
					gameBoard.addGameField(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.UNTEN));					
				else if( ( (i > 0) && (i < 3) ) || ( (i > 13) && (i< 18) ) || (i == 39)  || ( (i > 7) && ( i < 10) ))
					gameBoard.addGameField(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.UNTEN));					
				else if( ( (i > 3) && (i < 8) ) || ( (i > 29) && (i < 34) ) ||  (i == 39) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));					
				else if( ( (i > 19) && (i < 24) ) || ( (i > 27) && (i < 30) ) ||  ( (i > 33) && (i < 38) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));					
				else if( ( (i > 9) && (i < 14) ) || ( (i > 23) && (i < 28) ) ||  ( (i > 17) && (i < 20) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));					
			}				
		} else if (		
	} )
	
	
}
