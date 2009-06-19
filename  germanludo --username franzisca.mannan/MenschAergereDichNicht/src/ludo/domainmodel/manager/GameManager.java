package ludo.domainmodel.manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ludo.domainmodel.Counter;
import ludo.domainmodel.Player;
import ludo.domainmodel.SpielerFarbe;
import ludo.domainmodel.spielbrett.GameBoard;
import ludo.exceptions.InvalidColorException;
import ludo.ui.SpielbrettGrafik;
import ludo.ui.SpielerSetupDialog;

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
		SpielbrettGrafik.getInstance().spielbrettAnzeigen();

		JDialog playerSelectionDialog = new SpielerSetupDialog(SpielbrettGrafik
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
			PlayerManager.getInstance().addNewPlayer(redName, SpielerFarbe.ROT);
			PlayerManager.getInstance().addNewPlayer(blueName, SpielerFarbe.BLAU);
			PlayerManager.getInstance().addNewPlayer(yellowName, SpielerFarbe.GELB);
			PlayerManager.getInstance().addNewPlayer(greenName, SpielerFarbe.GRUEN);
			
			//Draw player names
			SpielbrettGrafik.getInstance().drawPlayerNames(PlayerManager.getInstance().getPlayerList());
			//Draw counters of players
			for (Player player : PlayerManager.getInstance().getPlayerList())
			{
				for (Counter counter : player.getCounters())
				{
					SpielbrettGrafik.getInstance().zeichneSpielfigur(counter);					
				}
			}
			
		} catch (InvalidColorException e) {
			//TODO log statement required
			SpielbrettGrafik.getInstance().displayStatusMessage("Error during Game initialization - invalid color");
		}
	}
}
