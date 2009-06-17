package ludo.domainmodel.spieler;

import java.util.LinkedList;

import ludo.domainmodel.spielbrett.GameBoard;

/**
 * Play class unites common behaviour and attributes of human and ai
 * {@link Player}.
 */
public class Player {

	//Every player has a unique ID
	private int playerID;
	//The name of the player
	private String playerName;
	//The color which has been assigned to the player
	private SpielerFarbe playerColor;
	//The GameBoard on which the counters of the player move
	private GameBoard gameBoard;
	
	//Coordinates of the medal in case the player finishes the game
	private int xCoordinateMedal;
	private int yCoordinateMedal;

	//Position of the start field for the player's counters
	private int xPositionStartfeld;
	private int yPositionStartfeld;

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
	 * @param id unique ID of the player
	 * @param name name of the {@link Player}
	 * @param color color of the {@link Player}
	 * @param xMedal x coordinate for the player medal, which is displayed once he finishes the game
	 * @param yMedal y coordinate for the player medal, which is displayed once he finishes the game
	 */
	public Player(int id, String name, SpielerFarbe color, int xMedal, int yMedal)
	{
		playerID = id;
		playerName = name;
		playerColor = color;
		
		xCoordinateMedal = xMedal;
		yCoordinateMedal = yMedal;
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
	
	public int getXMedaille() {
		return xCoordinateMedal;
	}

	public int getYMedaille() {
		return yCoordinateMedal;
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

	public int getXCoordinateMedal() {
		return xCoordinateMedal;
	}

	public int getYCoordinateMedal() {
		return yCoordinateMedal;
	}

	public int getXPositionStartfeld() {
		return xPositionStartfeld;
	}

	public int getYPositionStartfeld() {
		return yPositionStartfeld;
	}
}
