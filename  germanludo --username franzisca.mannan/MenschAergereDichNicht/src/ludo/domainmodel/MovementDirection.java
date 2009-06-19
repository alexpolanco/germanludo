package ludo.domainmodel;

/**
 * Defines the directions in which a {@link Counter} can move.
 * 
 */
public enum MovementDirection {

	LEFT, RIGHT, ABOVE, BELOW;
	
	@Override
	public String toString()
	{
		if(this == MovementDirection.LEFT)
		{
			return "Links";
		} 
		else if(this == MovementDirection.RIGHT)
		{
			return "Rechts";
		} 
		else if(this == MovementDirection.BELOW)
		{
			return "Unten";
		}
		else if(this == MovementDirection.ABOVE)
		{
			return "Oben";
		}
		else
		{
			return "Undefined";
		}
	}
}
