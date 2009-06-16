package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ludo.domainmodel.spielbrett.Spielbrett;
import ludo.domainmodel.spieler.Spielfigur;
import ludo.main.SpielManager;
import ludo.ui.SpielbrettGrafik;

/**
 * Fängt Klick-Ereignisse des Würfels ab und behandelt diese
 *
 */
public class WuerfelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Es wurde gewuerfelt");
		
		//Überprüfen ob es überhaupt einen aktiven Spieler gibt, der würfeln darf
		if(SpielManager.getInstance().getAktiverSpieler() != null)
		{
			//Zeige die gerade gewürfelte Zahl an
			SpielbrettGrafik.getInstance().setWuerfelWert(String.valueOf(SpielManager.getInstance().wuerfeln()));		

			//Wenn der Spieler keine Figur draußen hat und es sich nicht um eine 6 handelt, wechsel den aktiven Spieler
			if(Integer.valueOf(SpielbrettGrafik.getInstance().getWuerfelWert()) < 6)
			{
				if(SpielManager.getInstance().getAktiverSpieler().hasAktiveSpielfiguren())
				{
					System.out.println("Der aktive Spieler hat mindestens eine Figur auf dem Feld - bitte wählen sie ihren Zug.");
				}
				else
				{
					//Hat keine aktiven Spielfiguren, also ist der nächste dran
					System.out.println("Spielerwechsel - da keine aktive Figur auf dem Feld.");
					SpielManager.getInstance().spielerWechsel();
					System.out.println("\n\nNächster aktiver Spieler ist: "
							+ SpielManager.getInstance().getAktiverSpieler()
									.getSpielerFarbe().toString());					
				}
			} 
			else if(Integer.valueOf(SpielbrettGrafik.getInstance().getWuerfelWert()) == 6)
			{
				//Wenn es eine 6 war und noch keine Figur draußen ist, setze automatisch eine Figur aufs Startfeld
				if(!SpielManager.getInstance().getAktiverSpieler().hasAktiveSpielfiguren())
				{
					System.out.println("Eine 6 wurde gewürfelt - Figur wird automatisch auf Start gesetzt.");
					SpielManager.getInstance().getAktiverSpieler().getSpielFiguren().getFirst().setzeSpielfigurAufStart();
					
				} 
				else
				{
					System.out.println("Der aktive Spieler hat mindestens eine Figur auf dem Feld - bitte wählen sie ihren Zug.");
					
				}				
				//Da der Spieler eine 6 gewürfelt hat, bleibt er aktiv
			}
		} 
		else
		{
			System.out.println("Derzeit gibt es keinen aktiven Spieler - das Würfeln ist somit nicht erlaubt.");
		}
	}

}
