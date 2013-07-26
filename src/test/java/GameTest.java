import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class GameTest {

	private Game game;
	
	@Before
	public void start() throws Exception {
		game = new Game();
	}
	
	@Test
	public void test1() throws Exception {
		if (game == null) throw new Exception("error");		
	}
	
	@Test
	public void test2() throws Exception {
		assertEquals(Game.getScores().length, 10);
		assertEquals(Game.getAllframes().length, 2);
	}

	@Test
	public void test_spare() throws Exception {
		Game.init();
		
		//insertDrow(frame, times, score)
		
		Game.insertDrow(0, 0, 4);
		Game.insertDrow(0, 1, 5);
				
		Game.sumResult();	

		assertEquals(Game.getScores(0), 9);		
	}

	@Test
	public void test_strike1() throws Exception {
		Game.init();
		
		Game.insertFirstDrow(0, 10);
		
		Game.insertFirstDrow(1, 7);		
		Game.insertSecondDrow(1, 3);
				
		Game.sumResult();	

		assertEquals(Game.getScores(0), 20);		
	}	

	
	@Test
	public void test_strike2() throws Exception {
		Game.init();
		
		Game.insertFirstDrow(0, 10);
		
		Game.insertFirstDrow(1, 2);
		Game.insertSecondDrow(1, 3);
				
		Game.sumResult();	

		assertEquals(Game.getScores(0), 15);		
	}	

	@Test
	public void test_strike3() throws Exception {
		Game.init();
		
		Game.insertFirstDrow(0, 10);		
		Game.insertFirstDrow(1, 10);		
		Game.insertFirstDrow(2, 3);
				
		Game.sumResult();	

		assertEquals(Game.getScores(0), 23);		
	}	

	
	//스트라이크 테스트 
	@Test
	public void test_strike4() throws Exception {

		Game.init();

		Game.insertFirstDrow(0, 10);		
		Game.insertFirstDrow(1, 10);
		Game.insertFirstDrow(2, 10);

		Game.sumResult();	
		assertEquals(Game.getScores(0), 30);		
	}

	//종합 테스트 
	@Test
	public void test_total1() throws Exception {

		Game.init();

		Game.insertFirstDrow(0, 9);
		Game.insertSecondDrow(0, 1);

		Game.insertFirstDrow(1, 8);
		Game.insertSecondDrow(1, 0);

		Game.insertFirstDrow(2, 10);
		Game.insertSecondDrow(2, 0);

		Game.insertFirstDrow(3, 10);
		Game.insertSecondDrow(3, 0);
		
		Game.insertFirstDrow(4, 8);
		Game.insertSecondDrow(4, 0);

		Game.insertFirstDrow(5, 10);
		Game.insertSecondDrow(5, 0);

		Game.insertFirstDrow(6, 8);
		Game.insertSecondDrow(6, 1);
	
		Game.insertFirstDrow(7, 9);
		Game.insertSecondDrow(7, 1);

		Game.insertFirstDrow(8, 8);
		Game.insertSecondDrow(8, 1);

		Game.insertFirstDrow(9, 10);
		Game.insertSecondDrow(9, 0);
	
		Game.insertFirstDrow(10, 9);
		Game.insertSecondDrow(10, 1);
				
		Game.sumResult();
		
		assertEquals(Game.getScores(0), 18);
		assertEquals(Game.getScores(1), 26);
		
		assertEquals(Game.getScores(2), 54);
		assertEquals(Game.getScores(3), 72);
		
		assertEquals(Game.getScores(4), 80);
		assertEquals(Game.getScores(5), 99);
				
		assertEquals(Game.getScores(6), 108);
		assertEquals(Game.getScores(7), 126);		

		assertEquals(Game.getScores(8), 135);
		assertEquals(Game.getScores(9), 155);		
	
	}

	
	//종합 테스트 
		@Test
		public void test_total2() throws Exception {

			Game.init();
			
			Game.insertFirstDrow(0, 9);
			Game.insertSecondDrow(0, 1);

			Game.insertFirstDrow(1, 0);
			Game.insertSecondDrow(1, 10);

			Game.insertFirstDrow(2, 10);
			Game.insertSecondDrow(2, 0);

			Game.insertFirstDrow(3, 10);
			Game.insertSecondDrow(3, 0);
			
			Game.insertFirstDrow(4, 0);
			Game.insertSecondDrow(4, 5);

			Game.insertFirstDrow(5, 6);
			Game.insertSecondDrow(5, 0);

			Game.insertFirstDrow(6, 0);
			Game.insertSecondDrow(6, 8);
		
			Game.insertFirstDrow(7, 8);
			Game.insertSecondDrow(7, 0);

			Game.insertFirstDrow(8, 10);
			Game.insertSecondDrow(8, 0);

			Game.insertFirstDrow(9, 3);
			Game.insertSecondDrow(9, 6);
		
			
			Game.sumResult();
			
			assertEquals(Game.getScores(0), 10);
			assertEquals(Game.getScores(1), 30);
			
			assertEquals(Game.getScores(2), 50);
			assertEquals(Game.getScores(3), 65);
			
			assertEquals(Game.getScores(4), 70);
			assertEquals(Game.getScores(5), 76);
			
			assertEquals(Game.getScores(6), 84);
			assertEquals(Game.getScores(7), 92);
					
			assertEquals(Game.getScores(8), 111);
			assertEquals(Game.getScores(9), 120);		
		
		}
	

}
