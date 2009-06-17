package ludo.domainmodel.manager;

import java.util.LinkedList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import ludo.domainmodel.spielbrett.GameBoard;

/**
 * Organizes available {@link GameBoard}s.
 *
 */
public class GameBoardManager {

	private static GameBoardManager self = null;
	// GameBoards of the available players
	private LinkedList<GameBoard> gameBoards;
	
	private GameBoardManager()
	{
		
	}
	
	public GameBoardManager getInstance()
	{
		if(self == null)
			self = new GameBoardManager();
		return self;
	}
	
	/**
	 * Creates a new set of {@link GameBoard}s.
	 */
	public void newGameBoards()
	{
		throw new NotImplementedException();
	}
}
