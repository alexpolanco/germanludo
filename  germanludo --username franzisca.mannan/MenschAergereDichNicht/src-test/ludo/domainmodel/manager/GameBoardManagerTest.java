package ludo.domainmodel.manager;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import ludo.domainmodel.Player;
import ludo.domainmodel.SpielerFarbe;
import ludo.ui.SpielbrettGrafik;

import org.apache.commons.lang.math.IntRange;
import org.junit.Before;
import org.junit.Test;

public class GameBoardManagerTest {

	@Before
	public void setUp() throws Exception {
		// Draw player names and counters
		SpielbrettGrafik.getInstance().spielbrettAnzeigen();
		//Create initial setup
		GameManager.getInstance().initializePlayers("Red Player", "Blue Player", "Yellow Player", "");
		
		
	}

	@Test
	public void PlayerSetup () throws Exception
	{
		// Check whether the number of players (three) is correct
		assertTrue(PlayerManager.getInstance().getPlayerList().size() == 3);
		
		// Check whether the colors of those players are red, blue and yellow
		for (Player player : PlayerManager.getInstance().getPlayerList())
		{
			// Only players with the following color have been initialized
			assertTrue(player.getPlayerColor() == SpielerFarbe.ROT
					|| player.getPlayerColor() == SpielerFarbe.BLAU
					|| player.getPlayerColor() == SpielerFarbe.GELB);
			assertFalse(player.hasActiveCounters());
			//Make sure that every player has 4 counters
			assertTrue(player.getCounters().size() == 4);			
		}
	}
	
	@Test
	public void DiceTesting () throws Exception
	{
		IntRange range = new IntRange(1, 6);
		for (int i = 0; i < 50; i++)
		{
			assertTrue(range.containsInteger(GameManager.getInstance().throwDice()));
		}
	}
	
	
}
