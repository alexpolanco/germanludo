package ludo.domainmodel;

import java.awt.Point;
import java.util.LinkedList;

import ludo.domainmodel.spielbrett.GameBoard;

/**
 * Play class unites common behavior and attributes of human and ai
 * {@link Player}.
 */
public abstract class Player {

	//Every player has a unique ID
	private int playerID;
	//The name of the player
	private String playerName;
	//The color which has been assigned to the player
	private SpielerFarbe playerColor;
	//The GameBoard on which the counters of the player move
	private GameBoard gameBoard;
	
	//Coordinates of the medal in case the player finishes the game
	private Point medalLocation;

	//Position of the start field for the player's counters
	private Point startFieldLocation;

	//Position where the player name is displayed
	private Point playerNameLocation;

	/**
	 * Returns a list containing all counters of the current Player. Players
	 * which have reached their home zone are removed from the list of active
	 * {@link Counter}s and are hence not considered anymore when checking for
	 * active {@link Counter}s.
	 */
	private LinkedList<Counter> counters = new LinkedList<Counter>();	
	

	/**
	 * Creates a new {@link Player}.
	 * 
	 * @param name name of the {@link Player}
	 * @param color color of the {@link Player}
	 * @param medal coordinates for the player medal, which is displayed once he finishes the game
	 * @param start coordinates for the start field for the current {@link Player}
	 */
	public Player(String name, SpielerFarbe color, Point start, Point medal, Point nameLocation)
	{
		playerName = name;
		playerColor = color;
		playerNameLocation = nameLocation;
		
		medalLocation = medal;
		startFieldLocation = start;
	}

	public Player(String name, SpielerFarbe color)
	{
		playerName = name;
		playerColor = color;
	}
	
	/**
	 * Returns true if the {@link Player} has active {@link Counter}s on his
	 * {@link GameBoard} or false otherwise. A {@link Counter} is considered
	 * inactive, if he is either in his home zone or in his starting zone.
	 */
	public boolean hasActiveCounters()
	{
		for(Counter c : counters)
		{
			if(c.isActive())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true, if the {@link Player} has no more active {@link Counter}s,
	 * because they're all in their home zone, or false otherwise. In case this
	 * method returns true the {@link Player} has obviously completed the game,
	 * can be given a medal and be removed from the list of active
	 * {@link Player}s.
	 */
	public boolean hasAllCountersInHomeZone()
	{
		if(getCounters().size() == 0)
		{
			return true;	
		}
		return false;
	}

	public LinkedList<Counter> getCounters() {
		return counters;
	}

	public void setCounters(LinkedList<Counter> counters) {
		this.counters = counters;
	}

	public int getPlayerID() {
		return playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public SpielerFarbe getPlayerColor() {
		return playerColor;
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public Point getMedalLocation() {
		return medalLocation;
	}

	public Point getStartFieldLocation() {
		return startFieldLocation;
	}

	public Point getPlayerNameLocation() {
		return playerNameLocation;
	}

	public void setMedalLocation(Point medalLocation) {
		this.medalLocation = medalLocation;
	}

	public void setStartFieldLocation(Point startFieldLocation) {
		this.startFieldLocation = startFieldLocation;
	}

	public void setPlayerNameLocation(Point playerNameLocation) {
		this.playerNameLocation = playerNameLocation;
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}	

	
}
