package ludo.domainmodel.spieler;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ludo.domainmodel.Kollision;
import ludo.domainmodel.Koordinate;
import ludo.domainmodel.PlayerOwned;
import ludo.domainmodel.spielbrett.Bewegungsrichtung;
import ludo.domainmodel.spielbrett.FeldTyp;
import ludo.domainmodel.spielbrett.InvalidIndexException;
import ludo.domainmodel.spielbrett.Spielbrett;
import ludo.domainmodel.spielbrett.Spielfeld;
import ludo.main.SpielManager;
import ludo.ui.SpielbrettGrafik;
import ludo.ui.controls.FigurenListener;


/**
 * 
 * Auf was für einem Feld isch die Spielfigur gerade befindet lässt sich über die aktuelle Position und
 * den jeweiligen Feldtyp an dieser Position ermitteln. 
 *
 */
public class Spielfigur implements PlayerOwned {
	
	public void setXPosition(int position) {
		xPosition = position;
	}

	public void setYPosition(int position) {
		yPosition = position;
	}

	//Position der Spielfigur auf dem Startfeld
	private int xPositionStartfeld;
	private int yPositionStartfeld;	
		
	//Ursprüngliche Position der Spielfigur in dem Startbereich
	private int xPositionStartBereich;
	private int yPositionStartBereich;	
	
	//aktuelle Position der Spielfigur auf einem Koordinatenfeld
	private int xPosition;
	private int yPosition;
	
	//Die Spielfigur wei� wer ihr Besitzer ist - also welcher Spieler (falls eure Spielerklasse anders hei�t
	//passt ihr es halt an ;-)
	private Spieler besitzer;
	
	private SpielerFarbe figurenFarbe;

	//Das Abbild unserer Spielfigur
	private JLabel spielFigurGrafik;
	
	//Der Konstruktor der Spielfigur-Klasse - hier initialisiert man alles was man evtl. braucht
	//Vielleicht noch die Farbe der Spielfigur speichern oder was ihr halt sonst noch so an Infos braucht
	public Spielfigur(int x, int y, int xStartfeld, int yStartfeld, Spieler besitzer, SpielerFarbe farbe, ImageIcon icon)
	{
		xPositionStartfeld = xStartfeld;
		yPositionStartfeld = yStartfeld;
		
		xPositionStartBereich = xPosition = x;
		yPositionStartBereich = yPosition = y;

		this.besitzer = besitzer;
		
		spielFigurGrafik = new JLabel(icon);
		spielFigurGrafik.addMouseListener(new FigurenListener());
		figurenFarbe = farbe;
	}	
	
	public Spieler getBesitzer ()
	{
		return besitzer;
	}

	public void setBesitzer(Spieler neuerBesitzer) {
		besitzer = neuerBesitzer;		
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public int getXPositionStartBereich() {
		return xPositionStartBereich;
	}

	public int getYPositionStartBereich() {
		return yPositionStartBereich;
	}
	
	public int getXPositionStartfeld() {
		return xPositionStartfeld;
	}

	public int getYPositionStartfeld() {
		return yPositionStartfeld;
	}

	public SpielerFarbe getFigurenFarbe() {
		return figurenFarbe;
	}

	/**
	 * Bewegt eine Spielfigur von einem Punkt zum nächsten und aktualisiert auch
	 * die Grafik entsprechend.
	 * 
	 * @param position x und y Koordinate der neuen Position
	 */
	private void setNewPosition(int x, int y) {
		//aktualisiere Koordinaten
		xPosition = x;
		yPosition = y;
		
		//TODO zeichne Position der Figur neu
				
	}
	
	public JLabel getSpielfigurGrafik()
	{
		return spielFigurGrafik;
	}
	
	/**
	 * Setzte eine Spielfigur zurück in den Startbereich (bspw. wenn sie geschlagen wurde).
	 */
	public void setzeSpielfigurInDenStartbereich()
	{		
		//Resette die Spielfeldpositionen
		xPosition = xPositionStartBereich; 
		yPosition = yPositionStartBereich;
		
		//Zeichne Standort der Spielfigur neu
		SpielbrettGrafik.getInstance().zeichneSpielfigur(this);
	}
	
	/**
	 * Setzte eine Spielfigur auf das Startfeld. Etwaige Gegner die dort stehen werden
	 * geschlagen.
	 */
	public void setzeSpielfigurAufStart()
	{
		//Nach Kollisionen schauen
		Spielfigur kollisionsFigur = berechneKollisionViaCoordinates(
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
						+ Spielbrett.getInstance().getSpielfeldForSpielfigur(
								kollisionsFigur));
				
				Spielbrett.getInstance().getSpielfeldForSpielfigur(kollisionsFigur).setNichtBesetzt();
			}									
		}
		//Aktualisiere Koordinaten die zum Zeichnen verwendet werden
		setNewPosition(getXPositionStartfeld(), getYPositionStartfeld());
		
		//Aktualisiere die Feldinformationen des Startfeldes
		Spielbrett.getInstance().getSpielbrett(this.getFigurenFarbe()).get(0).setBesetztVon(this);

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
		Spielfeld feld = Spielbrett.getInstance().getSpielfeldForSpielfigur(this);
				
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
			Spielfigur kollisionsFigur = berechneKollisionViaCoordinates(zielPunkt.getX(), zielPunkt.getY());
			
		
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
					Spielbrett.getInstance().getSpielfeldForSpielfigur(kollisionsFigur).setNichtBesetzt();

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
		Spielfeld aktuellesFeld = Spielbrett.getInstance().getSpielfeldForSpielfigur(this);
		
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
		Spielfeld neuesFeld = Spielbrett.getInstance().getSpielbrett(this.getFigurenFarbe()).get(aktuellesFeld.getPositionsID());
		
		//Spielerdaten vom vorherhigen Feld löschen
		Spielbrett.getInstance().getSpielfeldForSpielfigur(this).setNichtBesetzt();

		//Spieldaten auf dem neuen Feld aktualisieren
		if( Spielbrett.getInstance().getSpielbrett(getFigurenFarbe()).contains(neuesFeld) )
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
		LinkedList<Spielfeld> brett = Spielbrett.getInstance().getSpielbrett(this.getFigurenFarbe());

		//Bestimme das aktuelle Spielfeld der Figur
		Spielfeld aktuellesFeld = Spielbrett.getInstance().getSpielfeldForSpielfigur(this);
		
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
	
	/**
	 * Prüft - ob eine Kolligion vorliegt. Wenn ja, gibt es ein Kollisionsobjekt zurück, andernfalls null.
	 */
	private Kollision berechneKollision()
	{
		Kollision kollision = null;
		
		try 
		{
			// Prüfe die aktuelle Figurenfarbe und anhand dessen entscheide
			// welche anderen Felder geprüft werden müssen
			switch(figurenFarbe)
			{
				case ROT:
				{
					System.out.println("Prüfe Kollisionen mit dem roten Spieler");
					//Prüfe das blaue Brett
					kollision = Spielbrett.getInstance().collisionDetection(
						Spielbrett.getInstance().getSpielbrett(SpielerFarbe.BLAU), 1, 30);
					if(kollision == null)
					{
						//Prüfe das gelbe Brett
						kollision = Spielbrett.getInstance().collisionDetection(
							Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GELB), 1, 20);
						if(kollision == null)
						{
							//Prüfe das gruene Brett
							kollision = Spielbrett.getInstance().collisionDetection(
								Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GRUEN), 1, 10);							
							if(kollision == null)
							{
								//Prüfe das rote Brett
								kollision = Spielbrett.getInstance().collisionDetection(
									Spielbrett.getInstance().getSpielbrett(SpielerFarbe.ROT), 1, 0);													
							}							
						}
					}						
				} break;
				case BLAU:
				{
					System.out.println("Prüfe Kollisionen mit dem blauen Spieler");
					
					kollision = Spielbrett.getInstance().collisionDetection(
						Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GELB), 1, 30);
					if(kollision == null)
					{
						kollision = Spielbrett.getInstance().collisionDetection(
							Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GRUEN), 1, 20);
						if(kollision == null)
						{
							kollision = Spielbrett.getInstance().collisionDetection(
								Spielbrett.getInstance().getSpielbrett(SpielerFarbe.ROT), 1, 10);							
							if(kollision == null)
							{
								kollision = Spielbrett.getInstance().collisionDetection(
									Spielbrett.getInstance().getSpielbrett(SpielerFarbe.BLAU), 1, 0);													
							}							
						}
					}					
				}break;
				case GELB:
				{
					System.out.println("Prüfe Kollisionen mit dem gelben Spieler");
					
					kollision = Spielbrett.getInstance().collisionDetection(
							Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GRUEN), 1, 30);
						if(kollision == null)
						{
							kollision = Spielbrett.getInstance().collisionDetection(
								Spielbrett.getInstance().getSpielbrett(SpielerFarbe.ROT), 1, 20);
							if(kollision == null)
							{
								kollision = Spielbrett.getInstance().collisionDetection(
									Spielbrett.getInstance().getSpielbrett(SpielerFarbe.BLAU), 1, 10);							
								if(kollision == null)
								{
									kollision = Spielbrett.getInstance().collisionDetection(
										Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GELB), 1, 0);													
								}							
							}
						}					
				}break;
				case GRUEN:
				{
					System.out.println("Prüfe Kollisionen mit dem gruenen Spieler");
					
					kollision = Spielbrett.getInstance().collisionDetection(
							Spielbrett.getInstance().getSpielbrett(SpielerFarbe.ROT), 1, 30);
						if(kollision == null)
						{
							kollision = Spielbrett.getInstance().collisionDetection(
								Spielbrett.getInstance().getSpielbrett(SpielerFarbe.BLAU), 1, 20);
							if(kollision == null)
							{
								kollision = Spielbrett.getInstance().collisionDetection(
									Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GELB), 1, 10);							
								if(kollision == null)
								{
									kollision = Spielbrett.getInstance().collisionDetection(
										Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GRUEN), 1, 0);													
								}							
							}
						}					
				}break;
			}
		} 
		catch (InvalidIndexException e) 
		{
			e.printStackTrace();
		}		
		return kollision;
	}

	/**
	 * Eine weitere Methode Kollisionen zu berechnen - nämlich via Koordinaten auf dem Spielfeld.
	 * Returns a {@link Spielfigur} if there is a collision or false otherwise.
	 */
	public Spielfigur berechneKollisionViaCoordinates(int xFigur, int yFigur)
	{
		for(Spieler s : SpielManager.getInstance().getSpielerListe())
		{
			for(Spielfigur figur : s.getSpielFiguren())
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
	
}