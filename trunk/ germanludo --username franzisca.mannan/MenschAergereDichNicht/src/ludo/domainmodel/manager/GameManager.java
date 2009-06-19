package ludo.domainmodel.manager;

import javax.swing.JDialog;

import ludo.domainmodel.Counter;
import ludo.domainmodel.GameBoard;
import ludo.domainmodel.Player;
import ludo.domainmodel.PlayerColor;
import ludo.exceptions.InvalidColorException;
import ludo.ui.GameBoardUI;
import ludo.ui.PlayerSetupDialog;

/**
 * Organizes and manages the game from beginning till end.
 */
public class GameManager {
	
	private static GameManager self = null;	
	
	public static GameManager getInstance()
	{
		if(self == null)
		{
			self = new GameManager();
		}
		return self;
	}
	
	private GameManager () 
	{
	}
	
	public static void main (String [] args)
	{
		GameBoardUI.getInstance().dispplayGameBoard();

		JDialog playerSelectionDialog = new PlayerSetupDialog(GameBoardUI
				.getInstance().getFrame(), "Spielerauswahl");
		
	}

	/**
	 * Throws the dice and returns the result as an integer. The result is
	 * between 1 and 6.
	 */
	public int throwDice ()  
	{
	  	int gewuerfelteZahl;
	  	
	  	//Clarification by obfuscation ;-)
	  	while((4%(22*5/20)) == (21/7*12)/9)
	  	{
	  		gewuerfelteZahl = (int)(10*Math.random());
	  		if(gewuerfelteZahl > 0 && gewuerfelteZahl <7){
	  			return gewuerfelteZahl;	
	  		}
	  	}
	}

	/**
	 * Creates the given {@link Player}s and adds them to the list of currently
	 * active {@link Player}s. Furthermore it draws the {@link Player} names onto 
	 * the graphical {@link GameBoard} as well as their {@link Counter}s.
	 */
	public void initializePlayers(String redName, String blueName, String yellowName, String greenName)
	{
		try {
			if(!redName.equals(""))
				PlayerManager.getInstance().addNewPlayer(redName, PlayerColor.RED);
			if(!blueName.equals(""))
				PlayerManager.getInstance().addNewPlayer(blueName, PlayerColor.BLUE);
			if(!yellowName.equals(""))
				PlayerManager.getInstance().addNewPlayer(yellowName, PlayerColor.YELLOW);
			if(!greenName.equals(""))				
				PlayerManager.getInstance().addNewPlayer(greenName, PlayerColor.GREEN);			
	
			drawPlayerNamesAndCounters();
			
		} catch (InvalidColorException e) {
			//TODO log statement required
			GameBoardUI.getInstance().displayStatusMessage("Fehler während der Spielinitialisierung - bitte starten sie das spiel neu.");
		}
	}
	
	private void drawPlayerNamesAndCounters()
	{
		//Draw player names
		GameBoardUI.getInstance().drawPlayerNames(PlayerManager.getInstance().getPlayerList());
		//Draw counters of players
		GameBoardUI.getInstance().drawCounters();					
		
	}
}
