package ludo.domainmodel.spieler;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import ludo.domainmodel.*;
import ludo.ui.SpielbrettGrafik;
import ludo.ui.controls.FigurenListener;


/**
 * 
 * A Counter is moved around on the {@link GameBoard}. Each {@link Counter} is owned by a {@link Player}.
 *
 */
public class Counter {
		
	//Original position of the counter in the starting zone
	private int xStartingZone;
	private int yStartingZone;	
	
	//Current position of the counter on the GameBoard
	private int xPosition;
	private int yPosition;
	
	//Player who owns this counter
	private Player owningPlayer;
	
	//Color of this counter
	private SpielerFarbe counterColor;

	//The actual image of our player, wrapped in a JLabel
	private JLabel playerImage;
	
	//True if the counter is on the GameBoard, actively participating in the game, false otherwise
	private boolean isActive;

	/**
	 * Creates a new Counter which is owned by a {@link Player}.
	 * 
	 * @param x the x coordinate in the starting zone
	 * @param y the y coordinate in the starting zone
	 * @param owner {@link Player} who owns this {@link Counter}
	 * @param color
	 * @param icon
	 */
	public Counter(int x, int y, Player owner, SpielerFarbe color, ImageIcon icon)
	{
		owningPlayer = owner;		
		counterColor = color;
		xStartingZone = xPosition = x;
		yStartingZone = yPosition = y;		
		playerImage = new JLabel(icon);
		//Add a listener so we can react when a user clicks on a player
		playerImage.addMouseListener(new FigurenListener());
	}	
	
	
	/**
	 * Setzte eine Spielfigur zurück in den Startbereich (bspw. wenn sie geschlagen wurde).
	 */
	public void placeCounterInStartingZone()
	{		
		//TODO superfluent, - can be substituted by other methods.
		throw new NotImplementedException();
	}
	
	/**
	 * Setzte eine Spielfigur auf das Startfeld. Etwaige Gegner die dort stehen werden
	 * geschlagen.
	 */
	public void setzeSpielfigurAufStart()
	{
		//Nach Kollisionen schauen
		Counter kollisionsFigur = berechneKollisionViaCoordinates(
				getXPositionStartfeld(), getYPositionStartfeld());
		
		if(kollisionsFigur != null)
		{
			//Es hat eine Kollision stattgefunden
			if(kollisionsFigur.getFigurenFarbe().equals(this.getFigurenFarbe()))
			{
				// Kollision mit einer Spielfigur der eigenen Farbe - Zug abbrechen 
				SpielbrettGrafik
						.getInstance()
						.displayNachricht(
								"Ungueltiger Zug - eigene Figuren können nicht geschlagen werden (Startfeld).");
				//Abbrechen - keine weitere Verarbeitung
				// TODO eignetlich muss der SPieler, wenn er andere aktive
				// Figuren hat, die Möglichkeit bekommen diese zu ziehen
				return;
			}
			else
			{						
				//Die Kollision fand mit einer anderen Spielfigur statt - entferne diese vom Spielbrett
				kollisionsFigur.setzeSpielfigurInDenStartbereich();
				
				//Das Feld auf dem sie stand leeren
				System.out.println("Entferne Spielfigur "
						+ kollisionsFigur.getFigurenFarbe()
						+ " vom Feld mit der PositionsID "
						+ GameBoard.getInstance().getCounterPosition(
								kollisionsFigur));
				
				GameBoard.getInstance().getCounterPosition(kollisionsFigur).setNichtBesetzt();
			}									
		}
		//Aktualisiere Koordinaten die zum Zeichnen verwendet werden
		setNewPosition(getXPositionStartfeld(), getYPositionStartfeld());
		
		//Aktualisiere die Feldinformationen des Startfeldes
		GameBoard.getInstance().getSpielbrett(this.getFigurenFarbe()).get(0).setBesetztVon(this);

		System.out.println("Spielfigur wird auf Start gesetzt");
		
		//Lasse die Figur zeichnen
		SpielbrettGrafik.getInstance().zeichneSpielfigur(this);						
	}

	/**
	 * Übernimmt den gesamten Bewegungsprozess einer Spielfigur - inklusive
	 * Kollisionsberechnen, kicken von anderen Spielfiguren und die Updates der
	 * jeweiligen Spielfelder und Grafiken.
	 * 
	 * @param wuerfelZahl
	 *            zahl an Schritten die die Figur sich bewegen soll
	 */
	public void bewegeSpielfigur (int wuerfelZahl)
	{		
		GameField feld = GameBoard.getInstance().getCounterPosition(this);
				
		//Prüfen, ob man über das 44 Feld hinaus gehen würde
		if(feld != null && feld.getPositionsID()+wuerfelZahl > 44)
		{
			//TODO convert to status message
			//Gib Fehlermeldung aus, man kann ja nicht über das 44 Feld hinaus laufen
			System.out.println("Zug nicht ausführbar - würde über das letzte (44.) Feld hinaus gehen.");
			return;
		}
		else
		{
			//Simuliere Bewegungsverlauf WENN die Spielfigur ziehen würde
			Koordinate zielPunkt = simulateSpielfigurenMove(wuerfelZahl);

			//Nach Kollisionen schauen
			Counter kollisionsFigur = berechneKollisionViaCoordinates(zielPunkt.getX(), zielPunkt.getY());
			
		
			if(kollisionsFigur != null)
			{
				//Es hat eine Kollision gegeben
				System.out.println("Es hat eine Kollision stattgefunden");
				//Prüfe ob eine Kollision stattgefunden hat mit einer Spielfigur der eigenen Farbe 
				if(kollisionsFigur.getFigurenFarbe().equals(this.getFigurenFarbe())
						&& kollisionsFigur != this)
				{
					//TODO convert to status message
					System.out.println("Ungueltiger Zug - eigene Figuren können nicht geschlagen werden.");
					//Abbrechen - keine weitere Verarbeitung
					return;
				}
				else if (kollisionsFigur != this)
				{			
					//TODO convert to status message
					System.out.println("Eine Spielfigur des Spielers "
							+ kollisionsFigur.getFigurenFarbe() + " wurde geschlagen.");
					
					//Das Feld auf dem sie stand leeren
					GameBoard.getInstance().getCounterPosition(kollisionsFigur).setNichtBesetzt();

					//Die Kollision fand mit einer anderen Spielfigur statt - entferne diese vom Spielbrett
					kollisionsFigur.setzeSpielfigurInDenStartbereich();
					
				}									
			}
			
			
			System.out.println("Das eigentliche Ziehen der Spielfigur beginnt");
			//Bewege die Figur Zug um Zug
			while(wuerfelZahl > 0)
			{
				//Spielfigur bewegen
				bewegeSpielfigur();
				
				wuerfelZahl--;
			}

			//Prüfe, ob der Spieler nun gewonnen hat
			if(SpielManager.getInstance().getAktiverSpieler().hasAlleFigurenImZiel())
			{				
				//Gib ihm ne Medaille
				SpielManager.getInstance().gibMedaille();
			}
		}
	}

	
	/**
	 * Bewegt die Spielfigur ein Feld vorwärts und speichert alle neuen Koordinaten ab.
	 * 
	 */
	public void bewegeSpielfigur ()
	{
		
		//aktuelles Feld feststellen
		GameField aktuellesFeld = GameBoard.getInstance().getCounterPosition(this);
		
		if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.LINKS)
		{
			setNewPosition(getXPosition()-60, getYPosition());			
		}
		else if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.RECHTS)
		{
			setNewPosition(getXPosition()+60, getYPosition());						
		}
		else if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.OBEN)
		{
			setNewPosition(getXPosition(), getYPosition()-60);									
		}
		else if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.UNTEN)
		{
			setNewPosition(getXPosition(), getYPosition()+60);									
		}

		//Hole das Spielfeld, was kommt nach dem Feld auf dem unsere Figur stand
		GameField neuesFeld = GameBoard.getInstance().getSpielbrett(this.getFigurenFarbe()).get(aktuellesFeld.getPositionsID());
		
		//Spielerdaten vom vorherhigen Feld löschen
		GameBoard.getInstance().getCounterPosition(this).setNichtBesetzt();

		//Spieldaten auf dem neuen Feld aktualisieren
		if( GameBoard.getInstance().getSpielbrett(getFigurenFarbe()).contains(neuesFeld) )
		{
			neuesFeld.setBesetztVon(this);			
		}
		else
		{
			throw new NoSuchElementException("Das Zielfeld konnte auf dem Spielbrett der Figur " + getFigurenFarbe() + " nicht gefunden werden.");
		}
		
		//Zeichne Standort der Spielfigur neu
		SpielbrettGrafik.getInstance().zeichneSpielfigur(this);
	}	
	
	public Koordinate simulateSpielfigurenMove(int wuerfelZahl)
	{
		System.out.println("Simuliere theoretischen Bewegungsverlauf der Spielfigur");

		//Setze Koordinate auf aktuelle Position der Spielfigur
		Koordinate zielPunkt = new Koordinate(getXPosition() ,getYPosition());
		
		//Bestimmte das Spielbrett der Figur
		LinkedList<GameField> brett = GameBoard.getInstance().getSpielbrett(this.getFigurenFarbe());

		//Bestimme das aktuelle Spielfeld der Figur
		GameField aktuellesFeld = GameBoard.getInstance().getCounterPosition(this);
		
		while(wuerfelZahl > 0)
		{
			//Spielfigurbewegung simulieren
			
			if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.LINKS)
			{
				zielPunkt.setX(zielPunkt.getX()-60); 
				zielPunkt.setY(zielPunkt.getY()); 
			}
			else if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.RECHTS)
			{
				zielPunkt.setX(zielPunkt.getX()+60); 
				zielPunkt.setY(zielPunkt.getY()); 
			}
			else if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.OBEN)
			{
				zielPunkt.setX(zielPunkt.getX()); 
				zielPunkt.setY(zielPunkt.getY()-60); 
			}
			else if(aktuellesFeld.getRichtungZuFolgeFeld() == Bewegungsrichtung.UNTEN)
			{
				zielPunkt.setX(zielPunkt.getX()); 
				zielPunkt.setY(zielPunkt.getY()+60); 
			}			
			
			//Hole dir das nächste Feld
			aktuellesFeld = brett.get(aktuellesFeld.getPositionsID());
			
			wuerfelZahl--;
		}
		return zielPunkt;
	}
	


	// TODO free for removal
	/**
	 * Eine weitere Methode Kollisionen zu berechnen - nämlich via Koordinaten auf dem Spielfeld.
	 * Returns a {@link Counter} if there is a collision or false otherwise.
	
	public Counter berechneKollisionViaCoordinates(int xFigur, int yFigur)
	{
		for(Player s : SpielManager.getInstance().getSpielerListe())
		{
			for(Counter figur : s.getSpielFiguren())
			{
				//Prüfe ob die X Koordinaten in einem ähnlichen Raum liegen
				if((figur.getXPosition() > xFigur-10 && figur.getXPosition() < xFigur+10))
				{
					if((figur.getYPosition() > yFigur-10 && figur.getYPosition() < yFigur+10))
					{
						if(this != figur)
						{
							return figur;							
						}
					}					
				}
			}
		}
		return null;
	}
 */
	
	/**
	 * Moves a {@link Counter} from its current position to another position.
	 * 
	 * @param x new x coordinate for the players location
	 * @param y new y coordinate for the players location
	 */
	private void setNewPosition(int x, int y) {
		xPosition = x;
		yPosition = y;
	}	
	
	public int getXStartingZone() {
		return xStartingZone;
	}


	public int getYStartingZone() {
		return yStartingZone;
	}


	public Player getOwningPlayer() {
		return owningPlayer;
	}


	public SpielerFarbe getCounterColor() {
		return counterColor;
	}


	public JLabel getPlayerImage() {
		return playerImage;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}	
	
	
}