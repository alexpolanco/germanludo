package ludo.exceptions;

/**
 * 
 * Thrown, if the organization or location of an index is unexpected.
 */
public class InvalidIndexException extends Exception {

	
	public InvalidIndexException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public InvalidIndexException (String msg)
	{
		super(msg);
	}
}
