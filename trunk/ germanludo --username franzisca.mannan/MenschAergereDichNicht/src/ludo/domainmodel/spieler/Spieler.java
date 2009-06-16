package ludo.domainmodel.spieler;

import java.awt.Color;
import java.util.LinkedList;

import ludo.domainmodel.spielbrett.Spielbrett;
import ludo.domainmodel.spielbrett.Spielfeld;

/**
 * 
 * Abstrakte Spieler-Klasse vereint Eigenschafter von menschlichen und NPC Spielern.
 *
 */
public class Spieler {

	//Jeder Spieler hat eine eindeutige Nummer
	private int spielerID;
	private String spielerName;
	private SpielerFarbe spielerFarbe;
	
	//Koordinaten der Medaille
	private int xMedaille;
	private int yMedaille;
	
	/**
	 * Eine Liste mit allen dem Spieler gehörenden Spielfiguren. Den aktuellen
	 * Status einer Spielfigur kann man von selbiger erfragen.
	 */
	private LinkedList<Spielfigur> spielFiguren = new LinkedList<Spielfigur>();
	
		
	/**
	 * 
	 * @param id rot=1, blau=2, gelb=3 und gruen=4
	 * @param name
	 * @param farbe rot ,blau, gelb oder gruen
	 * @param figuren
	 */
	public Spieler(int id, String name, SpielerFarbe farbe, int x, int y)
	{
		spielerID = id;
		spielerName = name;
		spielerFarbe = farbe;
		
		xMedaille = x;
		yMedaille = y;
	}

	/**
	 * Gibt true zurück, wenn der Spieler mindestens eine aktive Spielfigur auf
	 * dem Feld hat, false andernfalls.
	 * 
	 */
	public boolean hasAktiveSpielfiguren()
	{
		System.out.println("Prüfe, ob der Spieler mindestens eine aktive Spielfigur hat");
		for(Spielfigur f : spielFiguren)
		{
			//TODO potentially faulty
			if(Spielbrett.getInstance().getSpielfeldForSpielfigur(f) != null)
			{
				System.out.println("Spieler hat mindestens eine aktive Spielfigur");
				return true;				
			}
		}
		System.out.println("Spieler hat keien aktiven Spielfiguren mehr");
		return false;
	}

	/**
	 * True, wenn sich alle Figuren dieses Spielers im Ziel befinden, false
	 * andernfalls.
	 */
	public boolean hasAlleFigurenImZiel()
	{
		System.out.println("Prüfe, ob der Spieler " + this.getSpielerFarbe() + " alle Figuren im Ziel hat");
		LinkedList<Spielfeld> brett = Spielbrett.getInstance().getSpielbrett(this.getSpielerFarbe());
		
		// Prüfe für die letzten vier Felder des Spielerbretts, ob diese belegt
		// sind
		for(int i = 44; i > 40; i--)
		{
			if(!brett.get(i-1).isBesetzt())
			{
				return false;
			}
			System.out.println("Spieler " + this.getSpielerFarbe() + " besetzt Feld " + i);
		}
		System.out.println("Spieler " + this.getSpielerFarbe() + " hat alle Figuren in Ziel");
		return true;
	}
	
	public String getSpielerName() {
		return spielerName;
	}


	public void setSpielerName(String spielerName) {
		this.spielerName = spielerName;
	}


	public LinkedList<Spielfigur> getSpielFiguren() {
		return spielFiguren;
	}


	public void setSpielFiguren(LinkedList<Spielfigur> spielFiguren) {
		this.spielFiguren = spielFiguren;
	}


	public int getSpielerID() {
		return spielerID;
	}


	public SpielerFarbe getSpielerFarbe() {
		return spielerFarbe;
	}

	public int getXMedaille() {
		return xMedaille;
	}

	public int getYMedaille() {
		return yMedaille;
	}



	
}
