package ludo.domainmodel.manager;

import java.awt.Color;
import java.util.LinkedList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import ludo.domainmodel.Kollision;
import ludo.domainmodel.spielbrett.GameBoard;
import ludo.domainmodel.spielbrett.GameField;
import ludo.domainmodel.spieler.Counter;
import ludo.domainmodel.spieler.Player;
import ludo.domainmodel.spieler.SpielerFarbe;
import ludo.exceptions.CounterPositionNotFoundException;
import ludo.exceptions.GameBoardNotFoundException;
import ludo.exceptions.InvalidIndexException;

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

	/**
	 * Calculates, whether a {@link Kollision} took place between the counters of the {@link Player} whose {@link Color} was passed as an argument and the {@link GameField}
	 * 
	 * @param boardToCompare The {@link GameBoard} with which we are comparing our player position
	 * @param position The location which is occupied by a {@link Counter} on another {@link GameBoard}
	 * @param offset The calculative position-difference to the current {@link GameBoard}
	 */
	private Kollision collisionDetectionCalculation(GameBoard boardToCompare, int position, int offset)
	{
		//check the GameField which complies to the position of the other GameField and whether its occupied
		GameField feld = boardToCompare.getGameFieldList().get(((position + offset) % 40));
		
		if(feld.isBesetzt())
		{
			return new Kollision(boardToCompare, feld.getPositionsID());
		}
		else
		{
			return null;
		}					
	}

	/**
	 * Calculates whether there is a collision between the {@link Counter} of
	 * the {@link Player} and either other {@link Counter}s of the same
	 * {@link Player} or {@link Counter}s of other {@link Player}s. If so, it
	 * 
	 * returns a {@link Kollision} object, otherwise it return zero.
	 * 
	 * @throws CounterPositionNotFoundException
	 * @throws GameBoardNotFoundException
	 */
	public Kollision collisionDetection(Counter counter) throws CounterPositionNotFoundException, GameBoardNotFoundException
	{
		// First, determine the GameField where the counter currently resides
		GameField countersCurrentField = getCounterPosition(counter);
		// Find  a better solution, such as an empty collision
		Kollision kollision = null;
		
		if(countersCurrentField != null) {
			/*
			 * Next check all other GameBoards in order to check, whether our
			 * current counter collides with any of the other counters
			 */
			if(counter.getCounterColor() == SpielerFarbe.ROT) {
				
				kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.BLAU), countersCurrentField.getPositionsID(), 30);					
				if(kollision == null) {
					kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GELB), countersCurrentField.getPositionsID(), 20);					
					if(kollision == null) {
						kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GRUEN), countersCurrentField.getPositionsID(), 10);					
						if(kollision == null) {
							kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.ROT), countersCurrentField.getPositionsID(), 0);					
						}						
					}						
				}									
			} else if(counter.getCounterColor() == SpielerFarbe.BLAU) {
				kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.BLAU), countersCurrentField.getPositionsID(), 0);					
				if(kollision == null) {
					kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GELB), countersCurrentField.getPositionsID(), 30);					
					if(kollision == null) {
						kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GRUEN), countersCurrentField.getPositionsID(), 20);					
						if(kollision == null) {
							kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.ROT), countersCurrentField.getPositionsID(), 10);					
						}						
					}						
				}										
			} else if(counter.getCounterColor() == SpielerFarbe.GELB) {
				kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.BLAU), countersCurrentField.getPositionsID(), 10);					
				if(kollision == null) {
					kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GELB), countersCurrentField.getPositionsID(), 0);					
					if(kollision == null) {
						kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GRUEN), countersCurrentField.getPositionsID(), 30);					
						if(kollision == null) {
							kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.ROT), countersCurrentField.getPositionsID(), 20);					
						}						
					}						
				}										
			} else if(counter.getCounterColor() == SpielerFarbe.GRUEN) {
				kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.BLAU), countersCurrentField.getPositionsID(), 20);					
				if(kollision == null) {
					kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GELB), countersCurrentField.getPositionsID(), 10);					
					if(kollision == null) {
						kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.GRUEN), countersCurrentField.getPositionsID(), 0);					
						if(kollision == null) {
							kollision = collisionDetectionCalculation( getGameBoardByColor(SpielerFarbe.ROT), countersCurrentField.getPositionsID(), 30);					
						}						
					}						
				}										
			}
		}
		throw new CounterPositionNotFoundException("The counter could not be attributed to any of the available GameBoards.");
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
		// Determine the counters owner and retrieve the GameBoard from the
		// owner (i.e. Player)
		return counter.getOwningPlayer().getGameBoard().getCounterPosition(counter);
	}

	/**
	 * Returns a {@link GameBoard} of a given {@link Color} or throws an
	 * exception if the {@link GameBoard} could not be found.
	 * 
	 * @throws GameBoardNotFoundException
	 */
	public GameBoard getGameBoardByColor(SpielerFarbe farbe) throws GameBoardNotFoundException
	{
		for(Player player : SpielManager.getInstance().getSpielerListe())
		{
			if(player.getPlayerColor().equals(farbe))
			{
				return player.getGameBoard();	
			}			
		}
		throw new GameBoardNotFoundException("No GameBoard of the color " + farbe.toString() + " was found.");
	}

	public LinkedList<GameBoard> getGameBoards() {
		return gameBoards;
	}
}
