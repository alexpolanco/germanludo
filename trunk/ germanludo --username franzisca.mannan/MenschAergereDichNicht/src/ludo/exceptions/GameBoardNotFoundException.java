package ludo.exceptions;

public class GameBoardNotFoundException extends Exception {

	public GameBoardNotFoundException(String msg, Throwable exc)
	{
		super(msg, exc);
	}

	public GameBoardNotFoundException(String msg)
	{
		super(msg);
	}
}
