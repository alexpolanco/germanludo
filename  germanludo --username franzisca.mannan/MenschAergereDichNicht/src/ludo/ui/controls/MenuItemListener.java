package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ludo.ui.GameBoardUI;

public class MenuItemListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		
		if(cmd.equalsIgnoreCase("Beenden"))
		{
			System.out.println("Beende Spiel via Menü");
			GameBoardUI.getInstance().endGame();
		}
	}

}
