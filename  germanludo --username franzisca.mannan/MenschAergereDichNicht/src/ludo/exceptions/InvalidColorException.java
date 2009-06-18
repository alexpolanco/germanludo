package ludo.exceptions;

public class InvalidColorException extends Exception {

	public InvalidColorException(String msg, Throwable exc)
	{
		super(msg, exc);
	}

	public InvalidColorException(String msg)
	{
		super(msg);
	}
}
