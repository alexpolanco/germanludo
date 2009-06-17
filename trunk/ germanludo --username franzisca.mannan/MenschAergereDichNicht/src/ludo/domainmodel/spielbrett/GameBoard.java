package ludo.domainmodel.spielbrett;

import java.awt.Color;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import ludo.domainmodel.Kollision;
import ludo.domainmodel.spieler.SpielerFarbe;
import ludo.domainmodel.spieler.Counter;
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
	private LinkedList<GameField> fieldList;
	
	public GameBoard()
	{		
	}

	/**
	 * Initialisiert neues Spielbrett - verteilt Figuren und resettet vorherige
	 * Ergebnisse bzw. Spielstaende.
	 */
	//TODO shift to SpielManager or GameBoardManager
	public void neuesSpiel()
	{
		//TODO initialize game
		fieldList = new LinkedList<GameField>();
		
		configureSpielbrett();
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
	 * Rechnet die Spielfeldpositionen um. Der Algorithmus ist wie folgt (im Uhrzeigersinn):
	 * 
	 * Startfeld(Spieler n+1) = (Feld(Spieler n) + 30) modulo 40
	 * Startfeld(Spieler n+2) = (Feld(Spieler n) + 20) modulo 40
	 * Startfeld(Spieler n+3) = (Feld(Spieler n) + 10) modulo 40
	 * 
	 * Zur Erklärung: 40 = maximale Anzahl an relevanten Feldern - wird dies überschritten gehts bei 1
	 * weiter (als wenn ein Byte überläuft).
	 * 
	 * @return ein Kollisionsobjekt wenn eine Kollision stattgefunden hat, null andernfalls
	 * @throws InvalidIndexException 
	 * 
	 */
	public Kollision collisionDetection(LinkedList<GameField> vergleichsBrett, int positionsID, int offset) throws InvalidIndexException
	{		
		//TODO Funktionalität des Algorithmus durch unit test sicher stellen
		//substract -1 since the index starts from 0, i.e. 0 = 1, 1 = 2 usw...
		GameField feld = vergleichsBrett.get( ( (positionsID + offset) % 40) - 1);
		
		if(feld.getPositionsID() == (positionsID + offset) % 40)
		{
			if(feld.isBesetzt())
			{
				return new Kollision(vergleichsBrett, feld.getPositionsID());
			}
			else
			{
				return null;
			}			
		}
		else
		{
			throw new InvalidIndexException("Die erwartete positionsID ist inkorrekt - inkonsistente Feldliste.");
		}

	}

	/**
	 * Berechnet anhand eines Offset die gegebene Position auf das übergebene
	 * Brett um.
	 */
	public int berechnePosition(int position, int offset)
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
		for(GameField feld : this.getGameFieldList())
		{
			if(feld.isBesetzt() && feld.getBesetztVon().equals(figur))
			{
				return feld;
			}
		}
		throw new CounterPositionNotFoundException("The " + figur.getCounterColor().toString() + " Counter could not be found on this GameBoard");
	}

	/**
	 * Appends a {@link GameField} at the index, specified by the
	 * {@link GameField} to this {@link GameBoard}.
	 */
	public void addGameField(GameField field)
	{
		getGameFieldList().add(field.getPositionsID(), field);
	}
}
