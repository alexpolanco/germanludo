package ludo.domainmodel.spielbrett;

import static org.junit.Assert.*;

import java.util.LinkedList;

import ludo.domainmodel.manager.SpielManager;
import ludo.domainmodel.spieler.SpielerFarbe;
import ludo.domainmodel.spieler.Counter;
import ludo.exceptions.InvalidIndexException;

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
		GameBoard.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(GameField feld : GameBoard.getInstance().getSpielbrett(SpielerFarbe.ROT))
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
		GameBoard.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(GameField feld : GameBoard.getInstance().getSpielbrett(SpielerFarbe.BLAU))
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
		GameBoard.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(GameField feld : GameBoard.getInstance().getSpielbrett(SpielerFarbe.GELB))
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
		GameBoard.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(GameField feld : GameBoard.getInstance().getSpielbrett(SpielerFarbe.GRUEN))
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
		GameBoard.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(GameField feld : GameBoard.getInstance().getSpielbrett(SpielerFarbe.ROT))
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
		GameBoard.getInstance().neuesSpiel();
		int i = 1;
		
		//Prüfe die Reihenfolge der Spielfelder
		for(GameField feld : GameBoard.getInstance().getSpielbrett(SpielerFarbe.GELB))
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
		assertTrue(GameBoard.getInstance().berechnePosition(1, 30) == 31);
		assertTrue(GameBoard.getInstance().berechnePosition(9, 30) == 39);
		assertTrue(GameBoard.getInstance().berechnePosition(15, 30) == 5);
		assertTrue(GameBoard.getInstance().berechnePosition(20, 30) == 10);
		assertTrue(GameBoard.getInstance().berechnePosition(27, 30) == 17);
		assertTrue(GameBoard.getInstance().berechnePosition(36, 30) == 26);
		assertTrue(GameBoard.getInstance().berechnePosition(30, 30) == 20);
		assertTrue(GameBoard.getInstance().berechnePosition(10, 30) == 40);

		assertTrue(GameBoard.getInstance().berechnePosition(1, 10) == 11);
		assertTrue(GameBoard.getInstance().berechnePosition(9, 10) == 19);
		assertTrue(GameBoard.getInstance().berechnePosition(15, 10) == 25);
		assertTrue(GameBoard.getInstance().berechnePosition(20, 10) == 30);
		assertTrue(GameBoard.getInstance().berechnePosition(27, 10) == 37);
		assertTrue(GameBoard.getInstance().berechnePosition(36, 10) == 6);
		assertTrue(GameBoard.getInstance().berechnePosition(30, 10) == 40);
	}
	
	@Test
	public void TestCollisionDetectionByCoordinates()
	{
		System.out.println("Testing Collision detection by Coordinates");
		//Initialisiere Spielbrett
		GameBoard.getInstance().neuesSpiel();
		//Initialisiere Spieler und Figuren
		SpielManager.getInstance().initialisiereSpieler("Rot", "Blau", "Gelb", "Gruen");
		
		//Setze Figur Rot auf Feld 3 von Rot
		SpielManager.getInstance().spielerWechsel();
		Counter spielFigurA = SpielManager.getInstance().getAktiverSpieler().getSpielFiguren().getFirst();
		Counter spielFigurB = SpielManager.getInstance().getAktiverSpieler().getSpielFiguren().getLast();
		
		spielFigurA.setXPosition(50);
		spielFigurA.setYPosition(10);
		
		spielFigurB.setXPosition(55);
		spielFigurB.setYPosition(7);
				
		Counter figurC = spielFigurA.berechneKollisionViaCoordinates(50, 10);
		
		assertTrue(figurC.equals(spielFigurB));
	}
}
