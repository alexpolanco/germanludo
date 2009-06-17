package ludo.domainmodel.spielbrett;

import java.awt.Color;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import ludo.domainmodel.Kollision;
import ludo.domainmodel.spieler.SpielerFarbe;
import ludo.domainmodel.spieler.Counter;

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
	
	//Color of the game board - identifies to which player the gameboard belongs
	
	
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
		blauesSpielbrett = new LinkedList<GameField>();
		gelbesSpielbrett = new LinkedList<GameField>();
		gruenesSpielbrett = new LinkedList<GameField>();
		
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
	
	private void configureSpielbrett()
	{
		/*
		 * The list index starts from zero. We start counting from Zero - so
		 * overall there are 44 game fields and the maximum numer of game fields
		 * per GameBoard is 44. So the list index represents the GameField ID.
		 */
		for(int i = 0; i < 44; i++)
		{
			//Set the start field
			if(i == 0)
			{
				fieldList.add(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.UNTEN));
				blauesSpielbrett.add(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.LINKS));
				gelbesSpielbrett.add(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.OBEN));
				gruenesSpielbrett.add(new GameField(i, FeldTyp.STARTFELD, Bewegungsrichtung.RECHTS));
			}
			else if(i < 41)
			{
				//rote Felder initialisieren - Richtung unten
				if( ( (i > 1) && (i< 5) ) || ( (i > 14) && (i< 19) ) || ( (i > 14) && (i< 19) ) || (i == 40)  || ( (i > 8) && ( i < 11) ))
				{
					fieldList.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));					
					blauesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));
					gelbesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));
					gruenesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));
				}
				else if( ( (i > 4) && (i < 9) ) || ( (i > 30) && (i < 35) ) ||  (i == 39) )
				{
					fieldList.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));					
					blauesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));
					gelbesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));
					gruenesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));
				}
				else if( ( (i > 20) && (i < 25) ) || ( (i > 28) && (i < 31) ) ||  ( (i > 34) && (i < 39) ) )
				{
					fieldList.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));					
					blauesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));
					gelbesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));
					gruenesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));
					
				}
				else if( ( (i > 10) && (i < 15) ) || ( (i > 24) && (i < 29) ) ||  ( (i > 18) && (i < 21) ) )
				{
					fieldList.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.LINKS));					
					blauesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.OBEN));
					gelbesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.RECHTS));
					gruenesSpielbrett.add(new GameField(i, FeldTyp.LAUFBAHNFELD, Bewegungsrichtung.UNTEN));
					
				}
			}
			else
			{
				//für alle i > 40
				fieldList.add(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.UNTEN));					
				blauesSpielbrett.add(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.LINKS));
				gelbesSpielbrett.add(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.OBEN));
				gruenesSpielbrett.add(new GameField(i, FeldTyp.ZIELFELD, Bewegungsrichtung.RECHTS));
				
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
	public LinkedList<GameField> getSpielbrett(SpielerFarbe farbe)
	{
		switch(farbe)
		{
		case ROT: return fieldList;
		case BLAU: return blauesSpielbrett;
		case GELB: return gelbesSpielbrett;
		case GRUEN: return gruenesSpielbrett;
		default: return null;
		}
		
	}

	/**
	 * Gibt einem das {@link GameField} zurück, auf dem die als Argument
	 * übergebene {@link Counter} gerade steht, oder null, wenn die
	 * {@link Counter} auf keinem Feld steht.
	 * 
	 * Anhand der {@link SpielerFarbe} der {@link Counter} wird das benötigte
	 * Spielbrett ausgewählt.
	 */
	public GameField getSpielfeldForSpielfigur(Counter figur)
	{
		for(GameField feld : getSpielbrett(figur.getFigurenFarbe()))
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
