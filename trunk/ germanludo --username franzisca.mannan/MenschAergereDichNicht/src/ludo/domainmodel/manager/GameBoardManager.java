package ludo.domainmodel.manager;

import java.awt.Color;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import ludo.domainmodel.Collision;
import ludo.domainmodel.Counter;
import ludo.domainmodel.GameBoard;
import ludo.domainmodel.GameField;
import ludo.domainmodel.Player;
import ludo.exceptions.CounterPositionNotFoundException;
import ludo.exceptions.GameBoardNotFoundException;
import ludo.exceptions.GameFieldIsOccupiedException;
import ludo.ui.GameBoardUI;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Organizes available {@link GameBoard}s.
 *
 */
public class GameBoardManager {

	private static Logger log = Logger.getLogger(GameBoardManager.class);
	private static GameBoardManager self = null;
	// GameBoards of the available players
	private LinkedList<GameBoard> gameBoards = new LinkedList<GameBoard>();
	
	private GameBoardManager()
	{
		
	}
	
	public static GameBoardManager getInstance()
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

	/**
	 * Calculates, whether a {@link Collision} took place between the counters
	 * of the {@link Player} whose {@link Color} was passed as an argument and
	 * the {@link GameField}
	 * 
	 * @param boardToCompare
	 *            The {@link GameBoard} with which we are comparing our player
	 *            position
	 * @param position
	 *            The location which is occupied by a {@link Counter} on another
	 *            {@link GameBoard}
	 * @param offset
	 *            The calculative position-difference to the current
	 *            {@link GameBoard}
	 */
	private Collision collisionDetectionCalculation(GameBoard boardToCompare, int position, int offset)
	{
		GameField field = null;			
		// Check whether the GameBoard is null - if so, skip
		if(boardToCompare != null)
		{
			// Modify calculation
			if(position > 39)
			{
				log.debug("Starting collision detection for field-id bigger than 39");
				field = boardToCompare.getGameFieldList().get(((position)));												
			} else {
				// check the GameField which complies to the position of the other
				// GameField and whether its occupied
				log.debug("Starting collision detection for field-id smaller than 39");
				field = boardToCompare.getGameFieldList().get(((position + offset) % 40));								
			}
			
			if(field.isOccupied())
			{
				log.debug("Collision detected");
				return new Collision(boardToCompare, field.getFieldNumber());
			}
		}
		return null;
	}

	/**
	 * Calculates whether there is a collision between the {@link Counter} of
	 * the {@link Player} and either other {@link Counter}s of the same
	 * {@link Player} or {@link Counter}s of other {@link Player}s. If so, it
	 * 
	 * returns a {@link Collision} object, otherwise it return null.
	 * 
	 * @throws CounterPositionNotFoundException
	 * @throws GameBoardNotFoundException
	 */
	public Collision collisionDetection(Counter counter, int futureCounterPosition) throws CounterPositionNotFoundException, GameBoardNotFoundException
	{
		// First, determine the GameField where the counter currently resides
		GameField countersCurrentField = getCounterPosition(counter);
		
		// Find  a better solution, such as an empty collision
		Collision collision = null;
		
		if(countersCurrentField != null || futureCounterPosition == 0) {			
			/*
			 * Next check all other GameBoards in order to check, whether our
			 * current counter collides with any of the other counters
			 */
			if(counter.getCounterColor() == Color.RED) {
				
				if(futureCounterPosition <= 39)
				{
					collision = collisionDetectionCalculation( getGameBoardByColor(Color.BLUE), futureCounterPosition, 30);					
					if(collision == null) {
						collision = collisionDetectionCalculation( getGameBoardByColor(Color.YELLOW), futureCounterPosition, 20);					
						if(collision == null) {
							collision = collisionDetectionCalculation( getGameBoardByColor(Color.GREEN), futureCounterPosition, 10);					
						}						
					}																			
				}
				if(collision == null) {
					collision = collisionDetectionCalculation( getGameBoardByColor(Color.RED), futureCounterPosition, 0);					
				}						
				
			} else if(counter.getCounterColor() == Color.BLUE) {
				collision = collisionDetectionCalculation( getGameBoardByColor(Color.BLUE), futureCounterPosition, 0);					
				if(collision == null) {
					collision = collisionDetectionCalculation( getGameBoardByColor(Color.YELLOW), futureCounterPosition, 30);					
					if(collision == null) {
						collision = collisionDetectionCalculation( getGameBoardByColor(Color.GREEN), futureCounterPosition, 20);					
						if(collision == null) {
							collision = collisionDetectionCalculation( getGameBoardByColor(Color.RED), futureCounterPosition, 10);					
						}						
					}						
				}										
			} else if(counter.getCounterColor() == Color.YELLOW) {
				collision = collisionDetectionCalculation( getGameBoardByColor(Color.BLUE), futureCounterPosition, 10);					
				if(collision == null) {
					collision = collisionDetectionCalculation( getGameBoardByColor(Color.YELLOW), futureCounterPosition, 0);					
					if(collision == null) {
						collision = collisionDetectionCalculation( getGameBoardByColor(Color.GREEN), futureCounterPosition, 30);					
						if(collision == null) {
							collision = collisionDetectionCalculation( getGameBoardByColor(Color.RED), futureCounterPosition, 20);					
						}						
					}						
				}										
			} else if(counter.getCounterColor() == Color.GREEN) {
				collision = collisionDetectionCalculation( getGameBoardByColor(Color.BLUE), futureCounterPosition, 20);					
				if(collision == null) {
					collision = collisionDetectionCalculation( getGameBoardByColor(Color.YELLOW), futureCounterPosition, 10);					
					if(collision == null) {
						collision = collisionDetectionCalculation( getGameBoardByColor(Color.GREEN), futureCounterPosition, 0);					
						if(collision == null) {
							collision = collisionDetectionCalculation( getGameBoardByColor(Color.RED), futureCounterPosition, 30);					
						}						
					}						
				}										
			}
		}
		return collision;			
	}

	
	/**
	 * Determines the current location ( {@link GameField} ID) of the passed
	 * {@link Counter}.
	 * 
	 * @param counter
	 *            the {@link Counter} whose position we want to determine.
	 * @return the {@link GameField} on which the counter is standing.
	 * @throws CounterPositionNotFoundException
	 *             is thrown, if the given {@link Counter} can not be found
	 *             anywhere on the {@link Player}s {@link GameBoard} who owns
	 *             the {@link Counter}.
	 */
	public GameField getCounterPosition(Counter counter) throws CounterPositionNotFoundException
	{
		// TODO maybe we only return the GameField ID, rather than the whole
		// field

		//TODO potential nullpointer exception
		// Determine the counters owner and retrieve the GameBoard from the
		// owner (i.e. Player)
		return counter.getOwningPlayer().getGameBoard().getCounterPosition(counter);
	}

	/**
	 * Returns true if a given {@link Counter} is on a {@link GameBoard} and
	 * false otherwise.
	 */
	public boolean getIsCounterOnGameBoard(Counter counter)
	{
		try {
			if(getCounterPosition(counter) != null)
				return true;
		} catch (CounterPositionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}

	/**
	 * Returns a {@link GameBoard} of a given {@link Color} or throws an
	 * exception if the {@link GameBoard} could not be found.
	 * 
	 * @throws GameBoardNotFoundException
	 */
	public GameBoard getGameBoardByColor(Color farbe) throws GameBoardNotFoundException
	{
		// Check whether the player is still in the game
		for(Player player : PlayerManager.getInstance().getPlayerList())
		{
			if(player.getPlayerColor().equals(farbe))
			{
				return player.getGameBoard();	
			}			
		}
		//TODO Log statement
		return null;
	}

	public LinkedList<GameBoard> getGameBoards() {
		return gameBoards;
	}

	/**
	 * Places a given {@link Counter} on the {@link GameField} of its owning
	 * {@link Player} at the given field number. This method requires the
	 * {@link GameField} to be NOT occupied anymore, otherwise an exception will
	 * be thrown. Furthermore this method updates the location of the graphical
	 * representation of the {@link Counter} on the graphical {@link GameBoard}.
	 * 
	 * @param counter
	 *            {@link Counter} which should be placed on a specific
	 *            {@link GameField}
	 * @param fieldNumber
	 *            index of the {@link GameField} where the {@link Counter}
	 *            should be placed
	 * @throws GameFieldIsOccupiedException
	 */
	public void placeCounterOnGameField(Counter counter, int fieldNumber) throws GameFieldIsOccupiedException
	{
		if(!counter.getOwningPlayer().getGameBoard().getGameFieldList().get(fieldNumber).isOccupied())
		{
			// If the GameField is not occupied, place the counter there
			counter.getOwningPlayer().getGameBoard().getGameFieldList().get(fieldNumber).setIsOccupiedBy(counter);
			counter.setActive(true);
			GameBoardUI.getInstance().drawCounters();				
			log.debug("Player " + counter.getOwningPlayer().getPlayerName() + " has moved his counter to field " + fieldNumber);

		} else {
			throw new GameFieldIsOccupiedException("The GameField on which the Counter should be placed is already occupied by another counter.");			
		}
	}
}
