package ludo.domainmodel;

import java.util.LinkedList;

import ludo.domainmodel.spielbrett.Spielfeld;
import ludo.domainmodel.spieler.Spielfigur;

/**
 * Ein Kollisionsobjekt enthält informationen zu einer Kollision mit einem anderen Spieler.
 *
 */
public class Kollision {

	private LinkedList<Spielfeld> kollisionsBrett;
	private int feldID;
	
	public Kollision(LinkedList<Spielfeld> kollisionsFeld, int feldID) {
		super();
		this.kollisionsBrett = kollisionsFeld;
		this.feldID = feldID;
	}

	/**
	 * Gibt das Spielfeld zurück, auf dem die Kollision stattgefunden hat oder
	 * null, wenn das Feld nicht gefunden wurde.
	 */
	public Spielfeld getKollisionsFeld() {
		for(Spielfeld f : kollisionsBrett)
		{
			if(f.getPositionsID() == feldID)
			{
				return f;
			}
		}
		return null;
	}

	public int getFeldID() {
		return feldID;
	}

	/**
	 * Gibt die Spielfigur zurück, mit der man kollidiert ist oder null wenn
	 * diese nicht gefunden wird.
	 */
	public Spielfigur getKollidierendeSpielfigur()
	{
		Spielfigur figur = null;
		
		//Finde das Spielfend auf dem die Kollision stattgefunden hat
		for(int i = 0; i < 40; i++)
		{
			if(kollisionsBrett.get(i).getPositionsID() == feldID)
			{
				//Überprüfung, ob wir auch das richtige Feld gefunden haben
				figur = kollisionsBrett.get(i).getBesetztVon();				
			}
		}
		return figur;
	}
	
}
