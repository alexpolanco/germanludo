package ludo.domainmodel.manager;

import java.awt.Point;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import ludo.domainmodel.Counter;
import ludo.domainmodel.GameBoardFactory;
import ludo.domainmodel.HumanPlayer;
import ludo.domainmodel.Player;
import ludo.domainmodel.PlayerColor;
import ludo.exceptions.CounterPositionNotFoundException;
import ludo.exceptions.InvalidColorException;
import ludo.exceptions.NoMoreMedalsException;
import ludo.ui.GameBoardUI;

/**
 * Handles {@link Player}-related tasks and administers a {@link LinkedList} of
 * all actively playing {@link Player}s. Once a {@link Player} has managed to
 * navigate all his {@link Counter}s into their home zone he is removed from the
 * list of active {@link Player}s.
 * 
 */
public class PlayerManager {

	private static Logger log = Logger.getLogger(PlayerManager.class);
	private static PlayerManager self = null;
	
	// A list of all players actively playing the game
	private LinkedList<Player> playerList = new LinkedList<Player>();
	
	// The initial number of players participating in the game
	private int initialNumberOfPlayers = 0;

	private PlayerManager() {

	}

	public static PlayerManager getInstance() {
		if (self == null)
			self = new PlayerManager();
		return self;
	}
	
	public LinkedList<Player> getPlayerList() {
		return playerList;
	}

	public int getInitialNumberOfPlayers() {
		return initialNumberOfPlayers;
	}

	/**
	 * Removes a {@link Player} from the list of actively participating
	 * {@link Player}s. This happens, when a {@link Player} has navigated all
	 * his {@link Counter}s into their home zone.
	 */
	public void removePlayer(Player player) {
		playerList.remove(player);
	}

	/**
	 * Puts the currently active {@link Player} to the end of the player cue and
	 * makes the next {@link Player} in the cue the active {@link Player}.
	 * 
	 * Furthermore it updates the status message showing whose player's turn it
	 * is now.
	 */
	public void switchActivePlayer() {
		playerList.add(playerList.pollFirst());
		GameBoardUI.getInstance().displayStatusMessage("@Spieler " + playerList.peekFirst().getPlayerName() +  " : Sie sind an der Reihe");
	}

	/**
	 * Peeks at the currently active Player (whose turn it is) and returns it to
	 * the calling function.
	 */
	public Player getCurrentPlayer() {
		
		return playerList.peekFirst();
	}

	/**
	 * Returns true if there is a current player whose turn it is and false
	 * otherwise.
	 */
	public boolean hasCurrentPlayer() {
		
		if(getCurrentPlayer() != null)
			return true;
		else
			return false;
	}

	/**
	 * Returns true, if there is at least two {@link Player}s left and false
	 * otherwise.
	 */
	public boolean hasActivePlayers()
	{
		if(playerList.size() > 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Adds a new {@link Player} to the list of participating {@link Player}s
	 * and also adds and initializes all four {@link Counter}s to it.
	 * @throws InvalidColorException 
	 */
	public void addNewPlayer(String name, PlayerColor color) throws InvalidColorException {
		Player player = new HumanPlayer(name, color);
		player.setGameBoard(GameBoardFactory.createGameBoard(player.getPlayerColor()));
		
		//Initialize red Player
		if(color.equals(PlayerColor.RED)) {
			log.debug("Initializing red player named " + name + " with the color " + color.toString());
			player.setStartFieldLocation(new Point(365, 15));
			player.setPlayerNameLocation(new Point(475, 30));
			LinkedList<Counter> counters = new LinkedList<Counter>();
			counters.add(new Counter(new Point(535, 70) , player, player.getPlayerColor(), new ImageIcon( this.getClass().getResource("spielerRot.png"))));
			counters.add(new Counter(new Point(535, 130), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerRot.png"))));
			counters.add(new Counter(new Point(470, 70), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerRot.png"))));
			counters.add(new Counter(new Point(470, 130), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerRot.png"))));
			player.setCounters(counters);
		} else if(color.equals(PlayerColor.BLUE)) {
			log.debug("Initializing red player named " + name + " with the color " + color.toString());
			player.setStartFieldLocation(new Point(605, 375));
			player.setPlayerNameLocation(new Point(475, 505));
			LinkedList<Counter> counters = new LinkedList<Counter>();
			counters.add(new Counter(new Point(530, 550) , player, player.getPlayerColor(), new ImageIcon( this.getClass().getResource("spielerBlau.png"))));
			counters.add(new Counter(new Point(530, 620), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerBlau.png"))));
			counters.add(new Counter(new Point(475, 550), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerBlau.png"))));
			counters.add(new Counter(new Point(475, 620), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerBlau.png"))));
			player.setCounters(counters);
		} else if(color.equals(PlayerColor.YELLOW)) {
			log.debug("Initializing red player named " + name + " with the color " + color.toString());
			player.setStartFieldLocation(new Point(245, 620));
			player.setPlayerNameLocation(new Point(30, 505));
			LinkedList<Counter> counters = new LinkedList<Counter>();
			counters.add(new Counter(new Point(50, 550) , player, player.getPlayerColor(), new ImageIcon( this.getClass().getResource("spielerGelb.png"))));
			counters.add(new Counter(new Point(120, 615), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerGelb.png"))));
			counters.add(new Counter(new Point(120, 550), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerGelb.png"))));
			counters.add(new Counter(new Point(50, 615), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerGelb.png"))));
			player.setCounters(counters);
		} else if(color.equals(PlayerColor.GREEN)) {
			log.debug("Initializing red player named " + name + " with the color " + color.toString());
			player.setStartFieldLocation(new Point(5, 260));
			player.setPlayerNameLocation(new Point(30, 30));
			LinkedList<Counter> counters = new LinkedList<Counter>();
			counters.add(new Counter(new Point(50, 70) , player, player.getPlayerColor(), new ImageIcon( this.getClass().getResource("spielerGruen.png"))));
			counters.add(new Counter(new Point(120, 130), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerGruen.png"))));
			counters.add(new Counter(new Point(120, 70), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerGruen.png"))));
			counters.add(new Counter(new Point(50, 130), player, player.getPlayerColor(), new ImageIcon(  this.getClass().getResource("spielerGruen.png"))));
			player.setCounters(counters);
		} else {
			throw new InvalidColorException("The Color for the to be created Player is not valid.");
		}		
		playerList.add(player);
		initialNumberOfPlayers++;
	}

	/**
	 * Returns true, if the {@link Player} has all his {@link Counter}s in his
	 * home zone and false otherwise.
	 */
	public boolean hasCompletedGame(Player player)
	{
		try {
			return player.hasAllCountersInHomeZone();
		} catch (CounterPositionNotFoundException e) {
			log.debug("Error while figuring out, whether the Player has all counters in his home zone.");
		}
		return false;
	}

	/**
	 * Removes a given player from the list of active players and gives him a
	 * medal, according to the position he reached.
	 */
	public void grantMedal(Player player)
	{
		//Draw his medal
		try {
			GameBoardUI.getInstance().drawMedals(player, determineMedal());

		} catch (NoMoreMedalsException e) {
			log.debug("All medals have been given away already - the game is at an end");
			// TODO display rankling
		}

		// Remove player from list of active players but keep his counters on
		// the field		
		getPlayerList().remove(player);		
	}
	
	private ImageIcon determineMedal() throws NoMoreMedalsException
	{
		int medal = getInitialNumberOfPlayers() - PlayerManager.getInstance().getPlayerList().size();
		
		switch(medal)
		{
			case 0: 
			{
				return new ImageIcon(this.getClass().getResource("gold.jpg"));			
			}
			case 1: 
			{
				return new ImageIcon(this.getClass().getResource("silver.jpg"));			
			}
			case 2: 
			{
				return new ImageIcon(this.getClass().getResource("bronze.jpg"));			
			}
		}
		throw new NoMoreMedalsException("All medals have been given to players already");
	}
}
