package ludo.exceptions;

import ludo.domainmodel.spielbrett.GameField;

/**
 * Is thrown, if a {@link GameField} is unexpectedly occupied.
 *
 */
public class GameFieldIsOccupiedException extends Exception {

	public GameFieldIsOccupiedException(String msg, Throwable exc)
	{
		super(msg, exc);
	}

	public GameFieldIsOccupiedException(String msg)
	{
		super(msg);
	}
}
