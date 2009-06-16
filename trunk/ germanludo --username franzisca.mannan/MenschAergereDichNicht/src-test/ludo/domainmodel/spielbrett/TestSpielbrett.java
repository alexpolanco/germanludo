package ludo.domainmodel.spielbrett;

import static org.junit.Assert.*;

import java.util.LinkedList;

import ludo.domainmodel.spieler.SpielerFarbe;
import ludo.domainmodel.spieler.Spielfigur;
import ludo.main.SpielManager;

import org.junit.Test;


/**
 * Das Spielbrett steuert das aktuelle Spiel. Die Spielbrettaufteilung ist wie
 * folgt: Rot = oben, Blau = rechts, Gelb = unten, Gruen = links
 * 
 */
public class TestSpielbrett {

	@Test
	public void TestListOrderFuerRotesSpielbrett()
	{
		Spielbrett.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(Spielfeld feld : Spielbrett.getInstance().getSpielbrett(SpielerFarbe.ROT))
		{
			//Compare expected ID and actual ID
			assertTrue(feld.getPositionsID() == i);
			System.out.println("Field ID: " + feld.getPositionsID() + "\n");
			i++;
		}
	}

	@Test
	public void TestListOrderFuerBlauesSpielbrett()
	{
		Spielbrett.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(Spielfeld feld : Spielbrett.getInstance().getSpielbrett(SpielerFarbe.BLAU))
		{
			//Compare expected ID and actual ID
			assertTrue(feld.getPositionsID() == i);
			System.out.println("Field ID: " + feld.getPositionsID() + "\n");
			i++;
		}
	}

	@Test
	public void TestListOrderFuerGelbesSpielbrett()
	{
		Spielbrett.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(Spielfeld feld : Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GELB))
		{
			//Compare expected ID and actual ID
			assertTrue(feld.getPositionsID() == i);
			System.out.println("Field ID: " + feld.getPositionsID() + "\n");
			i++;
		}
	}
	
	@Test
	public void TestListOrderFuerGruenesSpielbrett()
	{
		Spielbrett.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(Spielfeld feld : Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GRUEN))
		{
			//Compare expected ID and actual ID
			assertTrue(feld.getPositionsID() == i);
			System.out.println("Field ID: " + feld.getPositionsID() + "\n");
			i++;
		}
	}
	
	@Test
	public void TestDirectionFuerRotesSpielfeld()
	{
		Spielbrett.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(Spielfeld feld : Spielbrett.getInstance().getSpielbrett(SpielerFarbe.ROT))
		{
			if(feld.getPositionsID() == 1)
			{
				assertTrue(feld.getFeldTyp().equals(FeldTyp.STARTFELD));
				assertTrue(feld.getRichtungZuFolgeFeld().equals(Bewegungsrichtung.UNTEN));
			} 
			else if(feld.getPositionsID() == 19)
			{
				assertTrue(feld.getRichtungZuFolgeFeld().equals(Bewegungsrichtung.LINKS));				
			}
			else if(feld.getPositionsID() == 40)
			{
				assertTrue(feld.getRichtungZuFolgeFeld().equals(Bewegungsrichtung.UNTEN));				
			}
			
			i++;
		}
		
	}

	@Test
	public void TestDirectionFuerGelbesSpielfeld()
	{
		Spielbrett.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(Spielfeld feld : Spielbrett.getInstance().getSpielbrett(SpielerFarbe.GELB))
		{
			if(feld.getPositionsID() == 1)
			{
				assertTrue(feld.getFeldTyp().equals(FeldTyp.STARTFELD));
				assertTrue(feld.getRichtungZuFolgeFeld().equals(Bewegungsrichtung.OBEN));
			} 
			else if(feld.getPositionsID() == 19)
			{
				assertTrue(feld.getRichtungZuFolgeFeld().equals(Bewegungsrichtung.RECHTS));				
			}
			else if(feld.getPositionsID() == 40)
			{
				assertTrue(feld.getRichtungZuFolgeFeld().equals(Bewegungsrichtung.OBEN));				
			}
			
			i++;
		}
		
	}
	
	@Test
	public void TestFeldUmrechnungsAlgorithmus () throws InvalidIndexException
	{
		assertTrue(Spielbrett.getInstance().berechnePosition(1, 30) == 31);
		assertTrue(Spielbrett.getInstance().berechnePosition(9, 30) == 39);
		assertTrue(Spielbrett.getInstance().berechnePosition(15, 30) == 5);
		assertTrue(Spielbrett.getInstance().berechnePosition(20, 30) == 10);
		assertTrue(Spielbrett.getInstance().berechnePosition(27, 30) == 17);
		assertTrue(Spielbrett.getInstance().berechnePosition(36, 30) == 26);
		assertTrue(Spielbrett.getInstance().berechnePosition(30, 30) == 20);
		assertTrue(Spielbrett.getInstance().berechnePosition(10, 30) == 40);

		assertTrue(Spielbrett.getInstance().berechnePosition(1, 10) == 11);
		assertTrue(Spielbrett.getInstance().berechnePosition(9, 10) == 19);
		assertTrue(Spielbrett.getInstance().berechnePosition(15, 10) == 25);
		assertTrue(Spielbrett.getInstance().berechnePosition(20, 10) == 30);
		assertTrue(Spielbrett.getInstance().berechnePosition(27, 10) == 37);
		assertTrue(Spielbrett.getInstance().berechnePosition(36, 10) == 6);
		assertTrue(Spielbrett.getInstance().berechnePosition(30, 10) == 40);
	}
	
	@Test
	public void TestCollisionDetectionByCoordinates()
	{
		System.out.println("Testing Collision detection by Coordinates");
		//Initialisiere Spielbrett
		Spielbrett.getInstance().neuesSpiel();
		//Initialisiere Spieler und Figuren
		SpielManager.getInstance().initialisiereSpieler("Rot", "Blau", "Gelb", "Gruen");
		
		//Setze Figur Rot auf Feld 3 von Rot
		SpielManager.getInstance().spielerWechsel();
		Spielfigur spielFigurA = SpielManager.getInstance().getAktiverSpieler().getSpielFiguren().getFirst();
		Spielfigur spielFigurB = SpielManager.getInstance().getAktiverSpieler().getSpielFiguren().getLast();
		
		spielFigurA.setXPosition(50);
		spielFigurA.setYPosition(10);
		
		spielFigurB.setXPosition(55);
		spielFigurB.setYPosition(7);
				
		Spielfigur figurC = spielFigurA.berechneKollisionViaCoordinates(50, 10);
		
		assertTrue(figurC.equals(spielFigurB));
	}
}
