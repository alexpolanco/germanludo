package ludo.domainmodel.spielbrett;

import ludo.domainmodel.spieler.Spielfigur;


/**
  * Jedes Spielfeld wird duch ein eigenes Spielfeld-Objekt repräsentiert.

  */ 
public class Spielfeld {

	/*
	 * Jedes Spielfeld hat eine Positions-id zwischen 1 und 44. Relevant für die
	 * anderen sind nur die Felder 1 bis 40, da die Felder 41 bis 44 die
	 * Hausfelder der jeweiligen Spielfigur sind.
	 */
	private int positionsID;
	
	// Der Feldtyp des aktuellen Feldes - enthält weitere Informationen zu dem
	// Feldtypen und evtl. Restriktionen o.ä.
	private FeldTyp feldTyp;
		
	//Gibt an in welcher Richtung das Folgefeld liegt
	private Bewegungsrichtung richtungZuFolgeFeld;
	
	/*
	 * Das Spielfeld merkt sich auch, welche Figur hier gerade bei ihm
	 * draufsteht - das braucht ihr,um - wenn eure Figur auf ein neues Feld
	 * kommt zu checken, ob da schon jemand drauf stehtund wenn ja wer - weil
	 * der ja dann zur�ck in seinen Bunker mussACHTUNG: Der Wert ist null
	 * (potenzielle Nullpointer-Exceptions!) wenn niemand draufsteht, daher
	 * immer erst mit der entsprechenden Methode (s.u.) abfragen OB jemand drauf
	 * steht.
	 */
	private Spielfigur besetztVon = null;
						
	public Spielfeld(int positionsID, FeldTyp feldTyp,
			Bewegungsrichtung folgeFeld) {
		super();
		this.positionsID = positionsID;
		this.feldTyp = feldTyp;
		this.richtungZuFolgeFeld = folgeFeld;
	}
	
	//Getter für den Feldtyp

	public FeldTyp getFeldTyp() {
		return feldTyp;
	}
	
	//Getter und Setter für die Positions-id
	
	public int getPositionsID() {
		return positionsID;
	}


	public void setPositionsID(int positionsID) {
		this.positionsID = positionsID;
	}	

	//Getter und Setter für das Folgefeld
	
	public Bewegungsrichtung getRichtungZuFolgeFeld() {
		return richtungZuFolgeFeld;
	}

	public void setRichtungZuFolgeFeld(Bewegungsrichtung feld) {
		this.richtungZuFolgeFeld = feld;
	}
		
	
	//Getter und Setter für die Spielfiguren und das Besetztzeichen

	public boolean isBesetzt() {
		if(besetztVon == null)
		{
			return false;			
		}
		else
		{
			return true;			
		}
	}

	public Spielfigur getBesetztVon() {
		return besetztVon;
	}

	public void setBesetztVon(Spielfigur besetztVon) {
		System.out.println("Spielfeld mit der PositionsID " + getPositionsID()
				+ " wird besetzt von Spielfigur "
				+ besetztVon.getFigurenFarbe());
		this.besetztVon = besetztVon;
	}

	public void setNichtBesetzt() {
		System.out.println("Spieler " + getBesetztVon().getFigurenFarbe()
				+ " wird entfernt von Feld " + getPositionsID());
		besetztVon = null;
	}
}