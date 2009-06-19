package ludo.domainmodel;

import java.awt.Point;

/**
 * Represents a human player.
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(String name, PlayerColor color, Point start,
			Point medal, Point nameLocation) {
		super(name, color, start, medal, nameLocation);
	}

	public HumanPlayer(String name, PlayerColor color) {
		super(name, color);
	}

}
