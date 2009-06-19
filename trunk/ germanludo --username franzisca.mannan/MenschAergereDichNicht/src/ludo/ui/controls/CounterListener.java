package ludo.ui.controls;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import ludo.domainmodel.Counter;
import ludo.domainmodel.manager.CounterManager;
import ludo.domainmodel.manager.GameBoardManager;
import ludo.domainmodel.manager.PlayerManager;
import ludo.ui.GameBoardUI;

public class CounterListener implements MouseListener{

	public void mouseClicked(MouseEvent arg0) {
		JLabel label = (JLabel) arg0.getSource();

		// Position of the label on which the player clicked
		Point pointOfClick = new Point(label.getX(), label.getY());
		
		try
		{
			for (Counter counter : PlayerManager.getInstance().getCurrentPlayer().getCounters())
			{
				/*
				 * Does any of the counter's location match the point on which the
				 * player clicked?
				 */
				if(pointOfClick.distance(counter.getCurrentLocation()) < 5)
				{
					// Check whether a 6 was diced and whether the counter is in the
					// starting zone
					if (Integer.valueOf(GameBoardUI.getInstance()
							.getDiceValue()) == 6
							&& !GameBoardManager.getInstance().getIsCounterOnGameBoard(counter))
					{
						// Place the counter on the start field
						CounterManager.getInstance().moveCounterToStartField(counter);
					}
					// Check whether the counter is elsewhere on the GameBoard
					else if (GameBoardManager.getInstance().getIsCounterOnGameBoard(counter))
					{
						CounterManager.getInstance().processCounterMovement(
								counter,
								Integer.valueOf(GameBoardUI.getInstance()
										.getDiceValue()));						
					}
					
					if(Integer.valueOf(GameBoardUI.getInstance().getDiceValue()) != 6)
					{						
						//Nächster Spieler ist an der Reihe
						PlayerManager.getInstance().switchActivePlayer();
						GameBoardUI.getInstance().getDice().setEnabled(true);
					}
					else
					{
						//Reset dice value
						GameBoardUI.getInstance().setDiceValue("Würfel");
						GameBoardUI.getInstance().getDice().setEnabled(true);
						GameBoardUI.getInstance().displayStatusMessage("@Spieler " + PlayerManager.getInstance().getCurrentPlayer().getPlayerName() +  " : Sie dürfen noch einmal würfeln");
					}
					
				}
			}			
		} catch(Exception exc)
		{
			// TODO give error messages on status bar
			exc.printStackTrace();
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
