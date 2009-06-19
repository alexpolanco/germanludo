package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ludo.domainmodel.manager.GameManager;
import ludo.ui.GameBoardUI;
import ludo.ui.PlayerSetupDialog;

public class PlayerSetupDialogListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		
		if(source.equals("OK"))
		{
			// Initialize the game
			JButton okButton = (JButton)e.getSource();
			PlayerSetupDialog parent = (PlayerSetupDialog)okButton.getParent().getParent().getParent().getParent();
		
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
			GameBoardUI.getInstance().endGame();
		}
		
	}

}
