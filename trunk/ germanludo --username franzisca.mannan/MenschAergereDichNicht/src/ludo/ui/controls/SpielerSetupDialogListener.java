package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ludo.domainmodel.manager.GameManager;
import ludo.ui.SpielbrettGrafik;
import ludo.ui.SpielerSetupDialog;

public class SpielerSetupDialogListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		
		if(source.equals("OK"))
		{
			// Initialize the game
			JButton okButton = (JButton)e.getSource();
			SpielerSetupDialog parent = (SpielerSetupDialog)okButton.getParent().getParent().getParent().getParent();
		
			GameManager.getInstance().initializePlayers(
					parent.getRoterSpielerName(),
					parent.getBlauerSpielerName(),
					parent.getGelberSpielerName(),
					parent.getGruenerSpielerName());

			//Hide the dialog
			parent.setVisible(false);
			parent.dispose();			
		}
		else if(source.equals("Beenden"))
		{
			SpielbrettGrafik.getInstance().beenden();
		}
		
	}

}
