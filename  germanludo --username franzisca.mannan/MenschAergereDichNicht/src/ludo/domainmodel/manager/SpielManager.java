package ludo.domainmodel.manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ludo.domainmodel.spielbrett.GameBoard;
import ludo.domainmodel.spieler.Player;
import ludo.domainmodel.spieler.SpielerFarbe;
import ludo.domainmodel.spieler.Counter;
import ludo.ui.SpielbrettGrafik;
import ludo.ui.SpielerSetupDialog;

/**
 * Organisiert und initialisiert das Spiel von Anfang bis Ende.
 *
 */
public class SpielManager {
	
	private static SpielManager self = null;	
	
	private int spielerProRunde;
	
	//Spieler rot id = 1 Spieler blau id = 2 Spieler gelb id = 3 Spieler gruen id = 4
	private LinkedList<Player> spielerListe = new LinkedList<Player>();
	
	public static SpielManager getInstance()
	{
		if(self == null)
		{
			self = new SpielManager();
		}
		return self;
	}
	
	private SpielManager () 
	{
		GameBoard.getInstance().neuesSpiel();
	}
	
	public static void main (String [] args)
	{
		SpielbrettGrafik.getInstance().spielbrettAnzeigen();

		//Initiales setup - eingeben der Spielernamen und indirektes bestimmten der Spieleranzahl
		JDialog spielerAuswahlDialog = new SpielerSetupDialog(SpielbrettGrafik.getInstance().getFrame(), "Spielerauswahl");
		
	}
	
	
	public int wuerfeln ()  
	{
	  	int gewuerfelteZahl;
	  	
	  	//Clarification by obfuscation ;-)
	  	while((4%(22*5/20)) == (21/7*12)/9)
	  	{
	  		gewuerfelteZahl = (int)(10*Math.random());
	  		if(gewuerfelteZahl > 0 && gewuerfelteZahl <7){
	  			return gewuerfelteZahl;	
	  		}
	  	}
	}	  	
	  	
	/**
	 * Initialisiert die Spieler - leere Namensfelder werden ignoriert - und der Spieler
	 * spielt nicht mit.
	 */
	public void initialisiereSpieler(String spielerRot, String spielerBlau, String spielerGelb, String spielerGruen)
	{
		if(!spielerRot.equals(""))
		{
			Player rot = new Player(1, spielerRot, SpielerFarbe.ROT, 470, 70);
			LinkedList<Counter> figuren = new LinkedList<Counter>();
			//oben rechts
			figuren.add(new Counter(535, 70, 365, 15, rot, SpielerFarbe.ROT, new ImageIcon( this.getClass().getResource("spielerRot.png"))));
			//unten rechts 
			figuren.add(new Counter(535, 130, 365, 15, rot, SpielerFarbe.ROT,new ImageIcon(  this.getClass().getResource("spielerRot.png"))));
			//oben links
			figuren.add(new Counter(470, 70, 365, 15, rot, SpielerFarbe.ROT,new ImageIcon(  this.getClass().getResource("spielerRot.png"))));
			//unten links
			figuren.add(new Counter(470, 130, 365, 15, rot, SpielerFarbe.ROT,new ImageIcon(  this.getClass().getResource("spielerRot.png"))));

			rot.setSpielFiguren(figuren);
			spielerListe.add(rot);
			spielerProRunde++;
		}
		if(!spielerBlau.equals(""))
		{
			Player blau = new Player(2, spielerBlau, SpielerFarbe.BLAU, 475, 550);
			LinkedList<Counter> figuren = new LinkedList<Counter>();
			figuren.add(new Counter(530, 550, 605, 375, blau, SpielerFarbe.BLAU, new ImageIcon(  this.getClass().getResource("spielerBlau.png"))));
			figuren.add(new Counter(530, 620, 605, 375, blau, SpielerFarbe.BLAU,new ImageIcon(   this.getClass().getResource("spielerBlau.png"))));
			figuren.add(new Counter(475, 550, 605, 375, blau, SpielerFarbe.BLAU,new ImageIcon(   this.getClass().getResource("spielerBlau.png"))));
			figuren.add(new Counter(475, 620, 605, 375, blau, SpielerFarbe.BLAU,new ImageIcon(   this.getClass().getResource("spielerBlau.png"))));

			blau.setSpielFiguren(figuren);
			spielerListe.add(blau);			
			spielerProRunde++;
		}
		if(!spielerGelb.equals(""))
		{
			Player gelb = new Player(3, spielerGelb, SpielerFarbe.GELB, 50, 550);
			LinkedList<Counter> figuren = new LinkedList<Counter>();
			figuren.add(new Counter(50, 550, 245, 620, gelb, SpielerFarbe.GELB, new ImageIcon(  this.getClass().getResource("spielerGelb.png"))));
			figuren.add(new Counter(120, 615, 245, 620, gelb, SpielerFarbe.GELB,new ImageIcon(  this.getClass().getResource("spielerGelb.png"))));
			figuren.add(new Counter(120, 550, 245, 620, gelb, SpielerFarbe.GELB,new ImageIcon(  this.getClass().getResource("spielerGelb.png"))));
			figuren.add(new Counter(50, 615, 245, 620, gelb, SpielerFarbe.GELB,new ImageIcon(  this.getClass().getResource("spielerGelb.png"))));

			gelb.setSpielFiguren(figuren);
			spielerListe.add(gelb);			
			spielerProRunde++;
		}
		if(!spielerGruen.equals(""))
		{
			Player gruen = new Player(4, spielerGruen, SpielerFarbe.GRUEN, 50, 70);
			LinkedList<Counter> figuren = new LinkedList<Counter>();
			figuren.add(new Counter(50, 70, 5, 260, gruen, SpielerFarbe.GRUEN, new ImageIcon( this.getClass().getResource("spielerGruen.png"))));
			figuren.add(new Counter(120, 130, 5, 260, gruen, SpielerFarbe.GRUEN,new ImageIcon(  this.getClass().getResource("spielerGruen.png"))));
			figuren.add(new Counter(120, 70, 5, 260, gruen, SpielerFarbe.GRUEN,new ImageIcon(  this.getClass().getResource("spielerGruen.png"))));
			figuren.add(new Counter(50, 130, 5, 260, gruen, SpielerFarbe.GRUEN,new ImageIcon( this.getClass().getResource("spielerGruen.png"))));

			gruen.setSpielFiguren(figuren);
			spielerListe.add(gruen);			
			spielerProRunde++;
		}
	}

	/**
	 * Zeichnet die Spielernamen der teilnehmenden Spieler auf das Spielfeld.
	 */
	public void zeichneSpielername()
	{
		for(Player s : spielerListe)
		{
			//Zeichne den Spielernamen aufs Spielfeld - die Koordinaten hängen von der gewählten Farbe ab
			if(s.getSpielerFarbe() == SpielerFarbe.ROT) {
				SpielbrettGrafik.getInstance().zeichneSpielername(s.getSpielerName(), 475, 30);
			} else if(s.getSpielerFarbe() == SpielerFarbe.BLAU) {
				SpielbrettGrafik.getInstance().zeichneSpielername(s.getSpielerName(), 475, 505);				
			} else if(s.getSpielerFarbe() == SpielerFarbe.GELB) {
				SpielbrettGrafik.getInstance().zeichneSpielername(s.getSpielerName(), 30, 505);								
			} else if(s.getSpielerFarbe() == SpielerFarbe.GRUEN) {
				SpielbrettGrafik.getInstance().zeichneSpielername(s.getSpielerName(), 30, 30);				
			}
		}
	}
	
	/**
	 * Zeichnet alle Spielfiguren auf das Spielfeld.
	 */
	public void zeichneSpielfiguren()
	{
		//Führe das Zeichnen der Figuren für alle Spieler aus
		for(Player s : spielerListe)
		{			
			//Gehe über alle Spielfiguren und zeichne sie
			for(Counter figur : s.getSpielFiguren())
			{
				SpielbrettGrafik.getInstance().zeichneSpielfigur(figur);
			}
		}
	}

	public LinkedList<Player> getSpielerListe() {
		return spielerListe;
	}

	/**
	 * Entfernt den aktuell aktiven/agierenden Spieler aus der Liste der teilnehmenden Spieler.
	 * Grund ist in der Regel, dass alle Figuren ins Ziel geführt wurden.
	 */
	public void spielerEntfernen(Player spieler)
	{
		spielerListe.remove(spieler);
	}
	
	/**
	 * Schaut sich denn ersten Spieler in der Queue an und liefert ihn zurück -
	 * oder null, wenn die Queue leer ist.
	 */
	public Player getAktiverSpieler() {
		
		return spielerListe.peekFirst();
	}

	
	
	/**
	 * Setzt den aktiven Spieler ans ende der Queue und befördert somit dessen
	 * zuvorigen Nachfolger zum nun aktiven Spieler.
	 */
	public void spielerWechsel()
	{
		// Setzt den ersten Spieler ans Ende der Liste und befördert somit den
		// ehemaligen Vorgänger zum aktuellen Spieler
		spielerListe.add(spielerListe.pollFirst());
		
		//Aktualisieren der Würfelanzeige für aktuellen Spieler
		SpielbrettGrafik.getInstance().setWuerfelWert(getAktiverSpieler().getSpielerFarbe().toString());
	}
	
	public int getSpielerProRunde() {
		return spielerProRunde;
	}

	/**
	 * Verleiht dem ausscheidenden SPieler eine Medaille.
	 */
	public void gibMedaille()
	{
		//Rechne den Medaillenplatz aus: 1, 2, 3 oder vierter
		switch(getSpielerProRunde()-getSpielerListe().size()+1)
		{
			case 1:
			{
				SpielbrettGrafik.getInstance().zeichneMedaille(
						getAktiverSpieler().getXMedaille(),
						getAktiverSpieler().getYMedaille(),
						new ImageIcon(SpielbrettGrafik.getInstance()
								.getImageOrdnerPfad()
								+ "gold.jpg"));
			}break;
			case 2:
			{
				SpielbrettGrafik.getInstance().zeichneMedaille(
						getAktiverSpieler().getXMedaille(),
						getAktiverSpieler().getYMedaille(),
						new ImageIcon(SpielbrettGrafik.getInstance()
								.getImageOrdnerPfad()
								+ "silver.jpg"));
			}break;
			case 3:
			{
				SpielbrettGrafik.getInstance().zeichneMedaille(
						getAktiverSpieler().getXMedaille(),
						getAktiverSpieler().getYMedaille(),
						new ImageIcon(SpielbrettGrafik.getInstance()
								.getImageOrdnerPfad()
								+ "bronze.jpg"));
			}break;
		}
		
		//Entferne Spieler von aktiven Spielern
		spielerEntfernen(getAktiverSpieler());
	}
}
