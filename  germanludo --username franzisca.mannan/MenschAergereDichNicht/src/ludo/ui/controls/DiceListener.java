package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ludo.domainmodel.manager.CounterManager;
import ludo.domainmodel.manager.GameManager;
import ludo.domainmodel.manager.PlayerManager;
import ludo.exceptions.CounterPositionNotFoundException;
import ludo.exceptions.GameBoardNotFoundException;
import ludo.exceptions.GameFieldIsOccupiedException;
import ludo.ui.GameBoardUI;

/**
 * Handles clicks on the dice.
 */
public class DiceListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {		
		// Check whether there is an active Player that is allowed to dice
		if(PlayerManager.getInstance().hasCurrentPlayer())
		{
			//Display the current dice value
			GameBoardUI.getInstance().setDiceValue(
					String.valueOf(GameManager.getInstance().throwDice()));		

			// If the player has no active counter and didnt dice a 6 change the
			// active player
			if(Integer.valueOf(GameBoardUI.getInstance().getDiceValue()) < 6)
			{
				//Disable dice for now
				GameBoardUI.getInstance().getDice().setEnabled(false);
				
				if(PlayerManager.getInstance().getCurrentPlayer().hasActiveCounters()) {
					//TODO let the player set his counter
					GameBoardUI.getInstance().displayStatusMessage("@Spieler " + PlayerManager.getInstance().getCurrentPlayer().getPlayerName() +  " : Bitte setzen sie eine ihrer Spielfiguren");
				} else {					
					GameBoardUI.getInstance().displayStatusMessage("Spielerwechsel - da keine aktive Figur auf dem Spielfeld.");
					PlayerManager.getInstance().switchActivePlayer();
					//Enable dice again
					GameBoardUI.getInstance().getDice().setEnabled(true);					
				}
			} 
			else if(Integer.valueOf(GameBoardUI.getInstance().getDiceValue()) == 6) 
			{
				// If dice shows 6 and no counter is out - put one out
				// automatically
				if(!PlayerManager.getInstance().getCurrentPlayer().hasActiveCounters()) {
					try {
						CounterManager.getInstance().moveCounterToStartField(
								PlayerManager.getInstance().getCurrentPlayer()
										.getCounters().getFirst());
						// Reset dice - otherwise the player can reuse the
						// current dice value
						GameBoardUI.getInstance().setDiceValue("Würfel");
						GameBoardUI.getInstance().getDice().setEnabled(true);
						
						GameBoardUI.getInstance().displayStatusMessage("@Spieler " + PlayerManager.getInstance().getCurrentPlayer().getPlayerName() +  " : Sie dürfen noch einmal würfeln.");
						
					} catch (CounterPositionNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (GameFieldIsOccupiedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (GameBoardNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				} else {
					GameBoardUI.getInstance().displayStatusMessage("@Spieler " + PlayerManager.getInstance().getCurrentPlayer().getPlayerName() +  " : Bitte setzen sie eine ihrer Spielfiguren");
					GameBoardUI.getInstance().getDice().setEnabled(false);					
				}				
			}
		} else {
			GameBoardUI .getInstance().displayStatusMessage(
							"Es gibt derzeit keinen aktiven Spieler - daher kann nicht gewürfelt werden.");
		}
	}
}
