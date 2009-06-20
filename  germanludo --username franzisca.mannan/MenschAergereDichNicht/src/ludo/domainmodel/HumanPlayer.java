package ludo.domainmodel;

import java.awt.Color;
import java.awt.Point;

/**
 * Represents a human player.
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(String name, Color color, Point start,
			Point medal, Point nameLocation) {
		super(name, color, start, medal, nameLocation);
	}

	public HumanPlayer(String name, Color color) {
		super(name, color);
	}

}
