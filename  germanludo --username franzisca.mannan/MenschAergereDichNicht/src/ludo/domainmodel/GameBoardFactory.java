package ludo.domainmodel;

import java.awt.Color;

/**
 * Creates a {@link GameBoard} and initializes it.
 * 
 */
public class GameBoardFactory {

	public static GameBoard createGameBoard(SpielerFarbe color)
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
	private static void configure(GameBoard gameBoard, SpielerFarbe color) 
	{
		/*
		 * The list index starts from zero. We start counting from Zero - so
		 * overall there are 44 game fields and the maximum number of game fields
		 * per GameBoard is 44. So the list index represents the GameField ID.
		 */
		for(int i = 0; i < 44; i++)
		{
			//Configuration for the red GameBoard	
			if(color.equals(SpielerFarbe.ROT))
			{
				if(i == 0)
					gameBoard.addGameField(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.UNTEN));					
				else if(i  > 39 && i < 44)
					gameBoard.addGameField(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.UNTEN));					
				else if( ( (i > 0) && (i < 4) ) || ( (i > 13) && (i< 18) ) || (i == 39)  || ( (i > 7) && ( i < 10) ))
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));					
				else if( ( (i > 3) && (i < 8) ) || ( (i > 29) && (i < 34) ) ||  (i == 38) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));					
				else if( ( (i > 19) && (i < 24) ) || ( (i > 27) && (i < 30) ) ||  ( (i > 33) && (i < 38) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));					
				else if( ( (i > 9) && (i < 14) ) || ( (i > 23) && (i < 28) ) ||  ( (i > 17) && (i < 20) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));					
				//Configuration for the blue GameBoard	
			} else if (color.equals(SpielerFarbe.BLAU)) {
				if(i == 0)
					gameBoard.addGameField(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.LINKS));					
				else if(i  > 39 && i < 44)
					gameBoard.addGameField(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.LINKS));					
				else if( ( (i > 0) && (i < 4) ) || ( (i > 13) && (i< 18) ) || (i == 39)  || ( (i > 7) && ( i < 10) ))
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));					
				else if( ( (i > 3) && (i < 8) ) || ( (i > 29) && (i < 34) ) ||  (i == 38) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));					
				else if( ( (i > 19) && (i < 24) ) || ( (i > 27) && (i < 30) ) ||  ( (i > 33) && (i < 38) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));					
				else if( ( (i > 9) && (i < 14) ) || ( (i > 23) && (i < 28) ) ||  ( (i > 17) && (i < 20) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));			
			//Configuration for the yellow GameBoard	
			} else if (color.equals(SpielerFarbe.GELB)) {
				if(i == 0)
					gameBoard.addGameField(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.OBEN));					
				else if(i  > 39 && i < 44)
					gameBoard.addGameField(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.OBEN));					
				else if( ( (i > 0) && (i < 4) ) || ( (i > 13) && (i< 18) ) || (i == 39)  || ( (i > 7) && ( i < 10) ))
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));					
				else if( ( (i > 3) && (i < 8) ) || ( (i > 29) && (i < 34) ) ||  (i == 38) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));					
				else if( ( (i > 19) && (i < 24) ) || ( (i > 27) && (i < 30) ) ||  ( (i > 33) && (i < 38) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));					
				else if( ( (i > 9) && (i < 14) ) || ( (i > 23) && (i < 28) ) ||  ( (i > 17) && (i < 20) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));						
				//Configuration for the green GameBoard	
			} else if (color.equals(SpielerFarbe.GRUEN)) {
				if(i == 0)
					gameBoard.addGameField(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.RECHTS));					
				else if(i  > 39 && i < 44)
					gameBoard.addGameField(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.RECHTS));					
				else if( ( (i > 0) && (i < 4) ) || ( (i > 13) && (i< 18) ) || (i == 39)  || ( (i > 7) && ( i < 10) ))
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));					
				else if( ( (i > 3) && (i < 8) ) || ( (i > 29) && (i < 34) ) ||  (i == 38) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));					
				else if( ( (i > 19) && (i < 24) ) || ( (i > 27) && (i < 30) ) ||  ( (i > 33) && (i < 38) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));					
				else if( ( (i > 9) && (i < 14) ) || ( (i > 23) && (i < 28) ) ||  ( (i > 17) && (i < 20) ) )
					gameBoard.addGameField(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));									
			}
		}
	}
}
