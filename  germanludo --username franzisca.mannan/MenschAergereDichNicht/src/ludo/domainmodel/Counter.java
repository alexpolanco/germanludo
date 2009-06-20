package ludo.domainmodel;

import java.awt.Color;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ludo.ui.controls.CounterListener;


/**
 * A Counter is moved around on the {@link GameBoard}. Each {@link Counter} is owned by a {@link Player}.
 */
public class Counter {
		
	//Original position of the counter in the starting zone
	private Point startingZoneLocation;
	
	//Current position of the counter 
	private Point currentLocation;
	
	//Player who owns this counter
	private Player owningPlayer;
	
	//Color of this counter
	private Color counterColor;

	//The actual image of our player, wrapped in a JLabel
	private JLabel playerImage;
	
	//True if the counter is on the GameBoard, actively participating in the game, false otherwise
	private boolean isActive;

	/**
	 * Creates a new Counter which is owned by a {@link Player}.
	 * 
	 * @param location {@link Point} in the starting zone
	 * @param owner {@link Player} who owns this {@link Counter}
	 * @param color
	 * @param icon
	 */
	public Counter(Point location, Player owner, Color color, ImageIcon icon)
	{
		owningPlayer = owner;		
		counterColor = color;
		startingZoneLocation = currentLocation = location;	
		
		playerImage = new JLabel(icon);
		//Add a listener so we can react when a user clicks on a player
		playerImage.addMouseListener(new CounterListener());
	}	

	public Player getOwningPlayer() {
		return owningPlayer;
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}


	public void setCurrentLocation(Point current) {
		this.currentLocation = current;
	}

	public Point getStartingZoneLocation() {
		return startingZoneLocation;
	}

	public Color getCounterColor() {
		return counterColor;
	}

	public JLabel getPlayerImage() {
		return playerImage;
	}

	public boolean isActive() {
		return isActive;
	}

	//TODO never used which is wrongs
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}	
	
	
}