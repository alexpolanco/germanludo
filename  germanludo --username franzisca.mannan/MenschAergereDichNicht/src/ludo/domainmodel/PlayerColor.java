package ludo.domainmodel;

/**
 * The possible player colors are: red, blue, green and yellow.
 */
public enum PlayerColor {

	RED, BLUE, YELLOW, GREEN;
	
	@Override
	public String toString()
	{
		if(this == PlayerColor.RED)
		{
			return "Rot";
		} 
		else if(this == PlayerColor.BLUE)
		{
			return "Blau";
		} 
		else if(this == PlayerColor.YELLOW)
		{
			return "Gelb";
		}
		else if(this == PlayerColor.GREEN)
		{
			return "Gruen";
		}
		else
		{
			return "Undefined";
		}
	}
}
