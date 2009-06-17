package ludo.exceptions;

import ludo.domainmodel.spielbrett.GameBoard;
import ludo.domainmodel.spieler.Counter;

/**
 * Is thrown, if a {@link Counter} was not found on a given {@link GameBoard}.
 * 
 */
public class CounterPositionNotFoundException extends Exception {

	public CounterPositionNotFoundException(String msg, Throwable exc)
	{
		super(msg, exc);
	}

	public CounterPositionNotFoundException(String msg)
	{
		super(msg);
	}

}
