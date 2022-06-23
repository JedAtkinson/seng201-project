package tests;

import main.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Player;

class GameTest {

	@Test
	void testConstructor() {
		Player player = new Player("Name");
		Game game = new Game(player, 10, 2);
		assertEquals(game.getPlayer(), player);
		assertEquals(game.getCurrentDay(), 1);
	}
	
	@Test
	void testNextDay() {
		Player player = new Player("Name");
		Game game = new Game(player, 10, 2);
		ArrayList<Enemy> enemies = (ArrayList<Enemy>) game.getEnemies().clone();
		String value = game.nextDay();
		
		assertFalse(value == null);//Checks event String is returned
		assertEquals(game.getCurrentDay(), 2);//Checks day incremented
		System.out.println(enemies +" "+ game.getEnemies());
		assertFalse(enemies == game.getEnemies());//checks enemies updates
	}
	
	@Test
	void testRemoveEnemy() {
		Player player = new Player("Name");
		Game game = new Game(player, 10, 2);
		ArrayList<Enemy> enemies = (ArrayList<Enemy>) game.getEnemies().clone();
		game.removeEnemy(game.getEnemies().get(0));
		
		assertFalse(game.getEnemies() == enemies);
	}
	
	

}
