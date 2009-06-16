package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ludo.main.SpielManager;
import ludo.ui.SpielbrettGrafik;
import ludo.ui.SpielerSetupDialog;

public class SpielerSetupDialogListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		
		if(source.equals("OK"))
		{
			//Spieler und ihre Namen speichern
			System.out.println("OK");
			
			//Bekomm den Parent von dem aktuellen Button - also den Dialog
			JButton okButton = (JButton)e.getSource();
			//Den parent dialog des Buttons suchen
			SpielerSetupDialog parent = (SpielerSetupDialog)okButton.getParent().getParent().getParent().getParent();
		
			//Gib die Arbeit an den Spielmanager ab
			SpielManager.getInstance().initialisiereSpieler(
					parent.getRoterSpielerName(),
					parent.getBlauerSpielerName(),
					parent.getGelberSpielerName(),
					parent.getGruenerSpielerName());
			
			//Zeichne Spielernamen
			SpielManager.getInstance().zeichneSpielername();
			
			//Zeichne Spieler
			SpielManager.getInstance().zeichneSpielfiguren();
			
			//Blende Dialog aus
			parent.setVisible(false);
			parent.dispose();
			
			//Zeige auf dem Würfen die Farbe des aktiven Spielers an
			SpielbrettGrafik.getInstance().setWuerfelWert(
					SpielManager.getInstance().getAktiverSpieler()
							.getSpielerFarbe().toString());

			
		}
		else if(source.equals("Beenden"))
		{
			//Operation wurde unterbrochen - beende das Spiel
			System.out.println("Beenden");
			SpielbrettGrafik.getInstance().beenden();
		}
		
	}

}
