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
		assertEquals(game.scores.length, 10);
		assertEquals(game.allframes.length, 2);
	}

	@Test
	public void test3() throws Exception {
		game.init();
		
		game.allframes[0][0] = 4;
		game.allframes[1][0] = 5;
		
		game.sumResult();	

		assertEquals(game.scores[0], 9);
		
	}

	//스트라이크 테스트 
	@Test
	public void test4() throws Exception {

		game.init();
		
		game.allframes[0][0] = 10;
		game.allframes[0][1] = 10;
		game.allframes[0][2] = 10;	
		
		game.sumResult();	
		assertEquals(game.scores[0], 30);
		
	}

	//종합 테스트 
	@Test
	public void test5() throws Exception {

		game.init();
		
		game.allframes[0][0] = 9;
		game.allframes[1][0] = 1;
		
		game.allframes[0][1] = 8;	
		game.allframes[1][1] = 0;

		game.allframes[0][2] = 10;

		game.allframes[0][3] = 10;		

		game.allframes[0][4] = 8;

		game.allframes[0][5] = 10;

		game.allframes[0][6] = 8;
		game.allframes[1][6] = 1;
		
		game.allframes[0][7] = 9;
		game.allframes[1][7] = 1;
		
		game.allframes[0][8] = 8;
		game.allframes[1][8] = 1;
		
		game.allframes[0][9] = 10;
		game.allframes[1][9] = 0;

		game.allframes[0][10] = 9;		
		game.allframes[1][10] = 1;
		
		game.sumResult();
		
		assertEquals(game.scores[0], 18);
		assertEquals(game.scores[1], 26);
		
		assertEquals(game.scores[2], 54);
		assertEquals(game.scores[3], 72);
		
		assertEquals(game.scores[4], 80);
		assertEquals(game.scores[5], 99);
				
		assertEquals(game.scores[6], 108);
		assertEquals(game.scores[7], 126);		

		assertEquals(game.scores[8], 135);
		assertEquals(game.scores[9], 155);		
	
	}

	
	//종합 테스트 
		@Test
		public void test6() throws Exception {

			game.init();
			
			game.allframes[0][0] = 9;
			game.allframes[1][0] = 1;
			
			game.allframes[0][1] = 0;	
			game.allframes[1][1] = 10;

			game.allframes[0][2] = 10;

			game.allframes[0][3] = 10;		

			game.allframes[0][4] = 0;
			game.allframes[1][4] = 5;	

			game.allframes[0][5] = 6;

			game.allframes[0][6] = 0;
			game.allframes[1][6] = 8;
			
			game.allframes[0][7] = 8;
			game.allframes[1][7] = 0;
			
			game.allframes[0][8] = 10;
			game.allframes[1][8] = 0;
			
			game.allframes[0][9] = 3;
			game.allframes[1][9] = 6;
			
			game.sumResult();
			
			assertEquals(game.scores[0], 10);
			assertEquals(game.scores[1], 30);
			
			assertEquals(game.scores[2], 50);
			assertEquals(game.scores[3], 65);
			
			assertEquals(game.scores[4], 70);
			assertEquals(game.scores[5], 76);
			
			assertEquals(game.scores[6], 84);
			assertEquals(game.scores[7], 92);
					
			assertEquals(game.scores[8], 111);
			assertEquals(game.scores[9], 120);		
		
		}
	

}
