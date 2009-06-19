package ludo.domainmodel.manager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import ludo.domainmodel.Collision;
import ludo.domainmodel.Counter;
import ludo.ui.SpielbrettGrafik;

import org.junit.Before;
import org.junit.Test;

public class CounterManagerTest {

	//Counter of first player
	private Counter counterOne;
	//Counter of last player
	private Counter counterTwo;
	
	@Before
	public void setUp() throws Exception {
		SpielbrettGrafik.getInstance().spielbrettAnzeigen();
		//Create initial setup
		GameManager.getInstance().initializePlayers("Red Player", "Blue Player", "Yellow Player", "");
		
		
	}
	
	@Test 
	public void startField () throws Exception
	{
		moveCounterOneToStartField();
		
		// Cleanup by putting the counter back to his starting zone
		CounterManager.getInstance().moveCounterToStartingZone(counterOne);
	}
	
	@Test
	public void moveCounter () throws Exception
	{
		moveCounterOneToStartField();
		
		//Move counter one and check for collisions
		CounterManager.getInstance().processCounterMovement(counterOne, 5);
		
		assertTrue(PlayerManager.getInstance().getPlayerList().getFirst().hasActiveCounters());
		assertFalse(PlayerManager.getInstance().getPlayerList().getFirst().hasAllCountersInHomeZone());
		
		// Cleanup by putting the counter back to his starting zone
		CounterManager.getInstance().moveCounterToStartingZone(counterOne);
	}	

	@Test
	public void collisionDetection () throws Exception
	{
		moveCounterOneToStartField();
		moveCounterTwoToStartField();
		
		// Next we gonna move the counter around the GameBoard
		// Calculate collision - there should be none
		assertNull(GameBoardManager.getInstance().collisionDetection(
				counterOne,
				GameBoardManager.getInstance().getCounterPosition(counterOne)
						.getFieldNumber() + 4));

		//We'll have to have  a collision sooner or later
		Collision collision = null;
		
		// We'll keep moving counterTwo till it hits counter One
		for(int i = 1; i < 44; i ++)
		{
			collision = GameBoardManager.getInstance().collisionDetection(counterTwo, i);
			
			if(collision != null && !collision.hasSameOwner(counterTwo))
			{
				//We found the collision - break
				i = 44;
			}
		}

		assertNotNull(collision);
		
		//Now we'll examine the collision
		assertFalse(collision.hasSameOwner(counterTwo));
		
		// Cleanup by putting the counters back to their starting zone
		CounterManager.getInstance().moveCounterToStartingZone(counterOne);
		CounterManager.getInstance().moveCounterToStartingZone(counterTwo);
	}
	
	private void moveCounterOneToStartField() throws Exception
	{
		counterOne = PlayerManager.getInstance().getPlayerList().getFirst()
		.getCounters().getFirst();
		// Make sure the counter is not on the GameBoard
		assertFalse(counterOne.isActive());
		assertFalse(PlayerManager.getInstance().getPlayerList().getFirst().hasActiveCounters());
		//Place the counter on the GameBoard (start field)
		CounterManager.getInstance().moveCounterToStartField(counterOne);
		// Make sure the counter is on the GameBoard
		assertTrue(counterOne.isActive());				
	}

	private void moveCounterTwoToStartField() throws Exception
	{
		counterTwo = PlayerManager.getInstance().getPlayerList().getLast()
				.getCounters().getFirst();
		// Make sure the counter is not on the GameBoard
		assertFalse(counterTwo.isActive());
		assertFalse(PlayerManager.getInstance().getPlayerList().getLast().hasActiveCounters());
		//Place the counter on the GameBoard (start field)
		CounterManager.getInstance().moveCounterToStartField(counterTwo);
		// Make sure the counter is on the GameBoard
		assertTrue(counterTwo.isActive());				
	}
}
