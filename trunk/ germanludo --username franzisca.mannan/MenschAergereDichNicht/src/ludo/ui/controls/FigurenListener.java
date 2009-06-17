package ludo.ui.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import ludo.domainmodel.manager.SpielManager;
import ludo.domainmodel.spielbrett.GameBoard;
import ludo.domainmodel.spieler.Counter;
import ludo.ui.SpielbrettGrafik;

public class FigurenListener implements MouseListener{

	public void mouseClicked(MouseEvent arg0) {
		JLabel label = (JLabel) arg0.getSource();
		System.out.println("Es wurde eine Spielfigur angeklickt");
		
		//Die Position des Bildes auf das der Spieler geklickt hat
		int xPosition = label.getX();
		int yPosition = label.getY();

		System.out.println("Der Spieler klickte auf X = " + xPosition + "| Y = " + yPosition);
		
		// Bestimme anhand der aktuellen Koordinaten der Spielfigur, ob die
		// Figur dem aktuell aktiven Spieler gehört - falls nicht gib eine
		// Fehlermeldung aus
		for(Counter figur : SpielManager.getInstance().getAktiverSpieler().getSpielFiguren())
		{
			System.out.println("Überprüfe Spielfiguren des "
					+ SpielManager.getInstance().getAktiverSpieler()
							.getSpielerFarbe());

			System.out.println("Aktuell zu prüfende Figur steht auf X = "
					+ figur.getXPosition() + "| Y = " + figur.getYPosition());

			if(figur.getXPosition() == xPosition && figur.getYPosition() == yPosition)
			{
				System.out.println("Die beiden Positionskoordinate stimmen überein");
				//Prüfe ob die Würfelzahl 6 lautet und der angeklickte Spieler im Startbereich ist
				if (Integer.valueOf(SpielbrettGrafik.getInstance()
						.getWuerfelWert()) == 6
						&& GameBoard.getInstance().getCounterPosition(
								figur) == null)
				{
					//Wenn das klar geht, setze die Spielfigur aufs Startfeld
					figur.setzeSpielfigurAufStart();
				}
				//Prüfe ob die Figur irgendwo auf dem Feld steht - die gewürfelte Zahl ist dabei egal
				else if(GameBoard.getInstance().getCounterPosition(
						figur) != null)
				{
					//Gib die weitere Verarbeitung an die folgende Methode ab - die setzt auch die Figur auf dem Brett
					//und kickt andere Spieler von selbigem herunter :-)
					figur.bewegeSpielfigur(Integer.valueOf(SpielbrettGrafik.getInstance().getWuerfelWert()));
						
					if(Integer.valueOf(SpielbrettGrafik.getInstance().getWuerfelWert()) != 6)
					{						
						//Nächster Spieler ist an der Reihe
						SpielManager.getInstance().spielerWechsel();
					}
					else
					{
						SpielbrettGrafik.getInstance().setWuerfelWert(figur.getFigurenFarbe().toString());
					}
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
