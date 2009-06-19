package ludo.domainmodel;

import java.awt.Color;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import ludo.exceptions.CounterPositionNotFoundException;
import ludo.exceptions.InvalidIndexException;

/**
 * A {@link GameBoard} exists for each player and contains 44 GameFields. A
 * {@link GameBoard} has a certain {@link Color} which is either Red, Yellow,
 * Blue or Green.
 * 
 */
public class GameBoard {

	private static GameBoard self = null;
	
	//The list of GameFields represents the GameBoard on which the player moves
	private LinkedList<GameField> fieldList = new LinkedList<GameField>();
	
	public GameBoard()
	{		
	}


	/**
	 * Returns a {@link LinkedList} containing all {@link GameField} on this
	 * {@link GameBoard} in the correct order.
	 */
	public LinkedList<GameField> getGameFieldList()
	{
		return fieldList;
	}

	/**
	 * Calculates the field number on a {@link GameBoard} by using an offset.
	 */
	public int calculateFieldNumber(int position, int offset)
	{
		if((position + offset) % 40 == 0)
		{
			return 40;
		}
		else
		{
			return (position + offset) % 40;			
		}
	}
	

	//TODO belongs to a different class - Counter class maybe?
	/**
	 * Checks whether the passed {@link Counter} is somewhere on this
	 * {@link GameBoard}. If yes, it returns the index ID of that
	 * {@link GameField}, if not it returns an exception.
	 * 
	 * @throws CounterPositionNotFoundException 
	 */
	public GameField getCounterPosition(Counter figur) throws CounterPositionNotFoundException
	{		
		// TODO seems to do the same stuff as getCounter - maybe merge both
		// methods or reuse code
		for(GameField field : this.getGameFieldList())
		{
			if(field.isOccupied() && field.getIsOccupiedBy().equals(figur))
			{
				return field;
			}
		}
		return null;
		//TODO use exception instead of null?
//		throw new CounterPositionNotFoundException("The " + figur.getCounterColor().toString() + " Counter could not be found on this GameBoard");
	}

	/**
	 * Returns the Counter at the given field number or throws an exception, if
	 * the counter could not be found.
	 * 
	 * @throws CounterPositionNotFoundException
	 */
	public Counter getCounter(int fieldNUmber) throws CounterPositionNotFoundException
	{
		if(getGameFieldList().get(fieldNUmber).isOccupied()) {			
			return getGameFieldList().get(fieldNUmber).getIsOccupiedBy();
		} else {
			throw new CounterPositionNotFoundException(
					"No counter was found on the GameField with the field number: "
							+ fieldNUmber);			
		}
	}
	
	/**
	 * Appends a {@link GameField} at the index, specified by the
	 * {@link GameField} to this {@link GameBoard}.
	 */
	public void addGameField(GameField field)
	{
		getGameFieldList().add(field.getFieldNumber(), field);
	}
}
