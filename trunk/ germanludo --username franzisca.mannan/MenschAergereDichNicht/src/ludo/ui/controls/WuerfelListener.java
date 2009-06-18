package ludo.ui.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ludo.domainmodel.Counter;
import ludo.domainmodel.manager.CounterManager;
import ludo.domainmodel.manager.GameManager;
import ludo.domainmodel.manager.PlayerManager;
import ludo.domainmodel.spielbrett.GameBoard;
import ludo.exceptions.CounterPositionNotFoundException;
import ludo.exceptions.GameBoardNotFoundException;
import ludo.exceptions.GameFieldIsOccupiedException;
import ludo.ui.SpielbrettGrafik;

/**
 * Handles clicks on the dice.
 */
public class WuerfelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Es wurde gewuerfelt");
		
		// Check whether there is an active Player that is allowed to dice
		if(PlayerManager.getInstance().hasCurrentPlayer())
		{
			//Display the current dice value
			SpielbrettGrafik.getInstance().setDiceValue(
					String.valueOf(GameManager.getInstance().throwDice()));		

			// If the player has no active counter and didnt dice a 6 change the
			// active player
			if(Integer.valueOf(SpielbrettGrafik.getInstance().getDiceValue()) < 6)
			{
				if(PlayerManager.getInstance().getCurrentPlayer().hasActiveCounters()) {
					//TODO let the player set his counter
					SpielbrettGrafik.getInstance().displayStatusMessage("Bitte setzen die eine Spielfigur");
				} else {					
					SpielbrettGrafik.getInstance().displayStatusMessage("Spielerwechsel - da keine aktive Figur auf dem Spielfeld.");
					PlayerManager.getInstance().switchActivePlayer();
				}
			} 
			else if(Integer.valueOf(SpielbrettGrafik.getInstance().getDiceValue()) == 6) 
			{
				// If dice shows 6 and no counter is out - put one out
				// automatically
				if(!PlayerManager.getInstance().getCurrentPlayer().hasActiveCounters()) {
					try {
						CounterManager.getInstance().placeCounterOnStartField(
								PlayerManager.getInstance().getCurrentPlayer()
										.getCounters().getFirst());
						
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
					SpielbrettGrafik.getInstance().displayStatusMessage("Bitte setzen die eine Spielfigur");
				}				
			}
		} else {
			SpielbrettGrafik .getInstance().displayStatusMessage(
							"Es gibt derzeit keinen aktiven Spieler - daher kann nicht gewürfelt werden.");
		}
	}
}
