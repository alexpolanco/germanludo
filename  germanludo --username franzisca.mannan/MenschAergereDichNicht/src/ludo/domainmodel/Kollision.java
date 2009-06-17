package ludo.domainmodel;

import java.util.LinkedList;

import ludo.domainmodel.spielbrett.GameBoard;
import ludo.domainmodel.spielbrett.GameField;
import ludo.domainmodel.spieler.Counter;

/**
 * Ein Kollisionsobjekt enthält informationen zu einer Kollision mit einem anderen Spieler.
 *
 */
public class Kollision {

	private GameBoard gameBoard;
	private int fieldID;
	
	public Kollision(GameBoard board, int feldID) {
		super();
		this.gameBoard = board;
		this.fieldID = feldID;
	}

	/**
	 * Gibt das Spielfeld zurück, auf dem die Kollision stattgefunden hat oder
	 * null, wenn das Feld nicht gefunden wurde.
	 */
	public GameField getKollisionsFeld() {
		return gameBoard.getGameFieldList();
	}

	public int getFeldID() {
		return fieldID;
	}

	/**
	 * Gibt die Spielfigur zurück, mit der man kollidiert ist oder null wenn
	 * diese nicht gefunden wird.
	 */
	public Counter getKollidierendeSpielfigur()
	{
		Counter figur = null;
		
		//Finde das Spielfend auf dem die Kollision stattgefunden hat
		for(int i = 0; i < 40; i++)
		{
			if(kollisionsBrett.get(i).getPositionsID() == fieldID)
			{
				//Überprüfung, ob wir auch das richtige Feld gefunden haben
				figur = kollisionsBrett.get(i).getBesetztVon();				
			}
		}
		return figur;
	}
	
}
