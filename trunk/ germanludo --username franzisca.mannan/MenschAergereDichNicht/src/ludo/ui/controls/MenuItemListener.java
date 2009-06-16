package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import ludo.domainmodel.spielbrett.Spielbrett;
import ludo.ui.SpielbrettGrafik;
import ludo.ui.SpielerSetupDialog;

public class MenuItemListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		
		if(cmd.equalsIgnoreCase("Beenden"))
		{
			System.out.println("Beende Spiel via Menü");
			SpielbrettGrafik.getInstance().beenden();
		}
	}

}
