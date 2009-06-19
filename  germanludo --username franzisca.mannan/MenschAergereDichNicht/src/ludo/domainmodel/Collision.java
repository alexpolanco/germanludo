package ludo.domainmodel;

import ludo.exceptions.CounterPositionNotFoundException;

/**
 * A Collision contains information about a collision between two
 * {@link Counter}s. Basically only the information about the {@link GameBoard}
 * of the {@link Counter} with which a given {@link Counter} collided is stored.
 * 
 */
public class Collision {

	private GameBoard gameBoard;
	private int fieldNumber;

	public Collision(GameBoard board, int number) {
		gameBoard = board;
		fieldNumber = number;
	}

	/**
	 * Returns the {@link GameField} on which the collision took place.
	 */
	public GameField getGameFieldOfCollision() {
		return gameBoard.getGameFieldList().get(fieldNumber);
	}

	/**
	 * Returns the {@link Counter} with whom another {@link Counter} collided.
	 * If there is no {@link Counter} on the {@link GameField} where the
	 * collision supposedly took place, then an exception is thrown.
	 * @throws CounterPositionNotFoundException 
	 * 
	 */
	public Counter getCollidingCounter() throws CounterPositionNotFoundException
	{
		return (gameBoard.getCounter(fieldNumber));		
	}

	/**
	 * Returns true if the counter in this {@link Collision} has the same owner,
	 * as the {@link Counter} that is passed as an argument and false otherwise.
	 * 
	 * @param counter the {@link Counter} which is compared to the {@link Counter} of this {@link Collision}
	 */
	public boolean hasSameOwner(Counter counter)
	{
		try {
			if(getCollidingCounter().getOwningPlayer().equals(counter.getOwningPlayer()))
				return true;
			else
				return false;
		} catch (CounterPositionNotFoundException e) {
			// TODO check if that really works/make sense
			return false;
		}
	}
}
