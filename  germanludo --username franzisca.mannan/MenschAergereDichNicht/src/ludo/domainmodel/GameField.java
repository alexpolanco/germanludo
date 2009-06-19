package ludo.domainmodel;



/**
  * Jedes Spielfeld wird duch ein eigenes Spielfeld-Objekt repräsentiert.

  */ 
public class GameField {

	// Each GameField ha a number which identifies its location on a GameBoard
	private int fieldNumber;

	//The field type is either home, starting zone or in game
	private FieldType fieldType;
		
	// The direction in which the following GameField lies
	private MovementDirection directionToNextField;
	
	//The counter that is currently occupying the GameField
	private Counter isOccupiedBy = null;
						
	
	public GameField(int number, FieldType type, MovementDirection direction) {
		super();
		fieldNumber = number;
		fieldType = type;
		directionToNextField = direction;
	}
	
	public Counter getIsOccupiedBy() {
		return isOccupiedBy;
	}

	public void setIsOccupiedBy(Counter isOccupiedBy) {
		this.isOccupiedBy = isOccupiedBy;
	}

	public int getFieldNumber() {
		return fieldNumber;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public MovementDirection getDirectionToNextField() {
		return directionToNextField;
	}

	/**
	 * Returns true if the current {@link GameField} is occupied by a counter
	 * and false otherwise.
	 */
	public boolean isOccupied() {
		if(isOccupiedBy == null)
		{
			return false;			
		}
		else
		{
			return true;			
		}
	}

}