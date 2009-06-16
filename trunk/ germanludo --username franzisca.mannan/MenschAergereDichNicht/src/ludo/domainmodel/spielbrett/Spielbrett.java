package ludo.domainmodel.spielbrett;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import ludo.domainmodel.Kollision;
import ludo.domainmodel.spieler.SpielerFarbe;
import ludo.domainmodel.spieler.Spielfigur;

/**
 * Das Spielbrett steuert das aktuelle Spiel. Die Spielbrettaufteilung ist wie
 * folgt: Rot = oben, Blau = rechts, Gelb = unten, Gruen = links
 * 
 */
public class Spielbrett {

	private static Spielbrett self = null;
	
	//Das Spielbrett wird als vier - ineinander umrechenbare - LinkedLists simuliert
	private LinkedList<Spielfeld> rotesSpielbrett;
	private LinkedList<Spielfeld> blauesSpielbrett;
	private LinkedList<Spielfeld> gelbesSpielbrett;
	private LinkedList<Spielfeld> gruenesSpielbrett;
	
	
	private Spielbrett()
	{		
	}
	
	public static Spielbrett getInstance()
	{
		if(self == null)
		{
			self = new Spielbrett();
		}
		return self;
	}

	/**
	 * Initialisiert neues Spielbrett - verteilt Figuren und resettet vorherige
	 * Ergebnisse bzw. Spielstaende.
	 */
	public void neuesSpiel()
	{
		//TODO initialize game
		rotesSpielbrett = new LinkedList<Spielfeld>();
		blauesSpielbrett = new LinkedList<Spielfeld>();
		gelbesSpielbrett = new LinkedList<Spielfeld>();
		gruenesSpielbrett = new LinkedList<Spielfeld>();
		
		configureSpielbrett();
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
	public Kollision collisionDetection(LinkedList<Spielfeld> vergleichsBrett, int positionsID, int offset) throws InvalidIndexException
	{		
		//TODO Funktionalität des Algorithmus durch unit test sicher stellen
		//substract -1 since the index starts from 0, i.e. 0 = 1, 1 = 2 usw...
		Spielfeld feld = vergleichsBrett.get( ( (positionsID + offset) % 40) - 1);
		
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
	
	private void configureSpielbrett()
	{
		// Wir fangen das zählen bei 1 an - theoretisch läuft die Liste
		// natürlich bei 0 los - wir lassen die Position erstmal unbesetzt und
		// überspringen sie quasi
		for(int i = 1; i < 45; i++)
		{
			//Startfeld setzen
			if(i == 1)
			{
				rotesSpielbrett.add(new Spielfeld(i, FeldTyp.STARTFELD, Bewegungsrichtung.UNTEN));
				blauesSpielbrett.add(new Spielfeld(i, FeldTyp.STARTFELD, Bewegungsrichtung.LINKS));
				gelbesSpielbrett.add(new Spielfeld(i, FeldTyp.STARTFELD, Bewegungsrichtung.OBEN));
				gruenesSpielbrett.add(new Spielfeld(i, FeldTyp.STARTFELD, Bewegungsrichtung.RECHTS));
			}
			else if(i < 41)
			{
				//rote Felder initialisieren - Richtung unten
				if( ( (i > 1) && (i< 5) ) || ( (i > 14) && (i< 19) ) || ( (i > 14) && (i< 19) ) || (i == 40)  || ( (i > 8) && ( i < 11) ))
				{
					rotesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));					
					blauesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));
					gelbesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));
					gruenesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));
				}
				else if( ( (i > 4) && (i < 9) ) || ( (i > 30) && (i < 35) ) ||  (i == 39) )
				{
					rotesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));					
					blauesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));
					gelbesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));
					gruenesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));
				}
				else if( ( (i > 20) && (i < 25) ) || ( (i > 28) && (i < 31) ) ||  ( (i > 34) && (i < 39) ) )
				{
					rotesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));					
					blauesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));
					gelbesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));
					gruenesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));
					
				}
				else if( ( (i > 10) && (i < 15) ) || ( (i > 24) && (i < 29) ) ||  ( (i > 18) && (i < 21) ) )
				{
					rotesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));					
					blauesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));
					gelbesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));
					gruenesSpielbrett.add(new Spielfeld(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));
					
				}
			}
			else
			{
				//für alle i > 40
				rotesSpielbrett.add(new Spielfeld(i, FeldTyp.ZIELFELD, Bewegungsrichtung.UNTEN));					
				blauesSpielbrett.add(new Spielfeld(i, FeldTyp.ZIELFELD, Bewegungsrichtung.LINKS));
				gelbesSpielbrett.add(new Spielfeld(i, FeldTyp.ZIELFELD, Bewegungsrichtung.OBEN));
				gruenesSpielbrett.add(new Spielfeld(i, FeldTyp.ZIELFELD, Bewegungsrichtung.RECHTS));
				
			}
		}
	}

	/**
	 * Gibt das zur übergebenen {@link SpielerFarbe} gehörende Spielbrett zurück, oder
	 * <code>null</code>, wenn es zu der übergebene Farbe kein Spielbrett gibt
	 * 
	 * @param farbe Die Farbe für welche das Spielbrett verlangt wird
	 * @return Da Spielbrett der als Argument übergebenen {@link SpielerFarbe}
	 */
	public LinkedList<Spielfeld> getSpielbrett(SpielerFarbe farbe)
	{
		switch(farbe)
		{
		case ROT: return rotesSpielbrett;
		case BLAU: return blauesSpielbrett;
		case GELB: return gelbesSpielbrett;
		case GRUEN: return gruenesSpielbrett;
		default: return null;
		}
		
	}

	/**
	 * Gibt einem das {@link Spielfeld} zurück, auf dem die als Argument
	 * übergebene {@link Spielfigur} gerade steht, oder null, wenn die
	 * {@link Spielfigur} auf keinem Feld steht.
	 * 
	 * Anhand der {@link SpielerFarbe} der {@link Spielfigur} wird das benötigte
	 * Spielbrett ausgewählt.
	 */
	public Spielfeld getSpielfeldForSpielfigur(Spielfigur figur)
	{
		for(Spielfeld feld : getSpielbrett(figur.getFigurenFarbe()))
		{
			if(feld.getBesetztVon() != null && feld.getBesetztVon().equals(figur))
			{
				System.out.println("Die Figur " + figur.getFigurenFarbe()
						+ " steht derzeit auf dem Feld "
						+ feld.getPositionsID());
				return feld;
			}
		}
		System.out.println("Das Feld auf dem die Figur " + figur.getFigurenFarbe() + " stehen soll wurde nicht gefunden.");
		return null;
	}
}
