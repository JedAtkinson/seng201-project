package tests;
import main.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import main.Monster;


/**
 * junit tests for the monster class
 * @author Angus Burrowes and Jed Atkinson
 *
 */


public class MonsterTest {
	/**
	 * monster object for testing
	 */
public Monster testMonster;
	/**
	 * test for healing monster
	 */
	@Test
	public void heal() {
		Monster monster1 = new Monster("henry",50,0,20,20,200,150,0,0,"fwfegv");
		monster1.takeDamage(20);
		System.out.println(monster1.getHealth());

		monster1.heal(10);
		System.out.println(monster1.getHealth());
		
		assertEquals(40, monster1.getHealth());
	}
	
	
	
/**
 * test for monster taking damage
 */
	@Test
	public void takeDamage() {
		Monster monster1 = new Monster("henry",50,0,20,20,200,150,0,0,"fwfegv");
		monster1.takeDamage(50);
		assertEquals(0,monster1.getHealth());
		
	
	
		
	}
	/**
	 * test for increasing level and xp of monster
	 */
	@Test
	public void increaseLevel() {
		Monster monster1 = new Monster("henry",50,0,20,20,200,150,100,0,"fwfegv");
		monster1.increaseLevel();
		assertEquals(1,monster1.getLevel());
		assertEquals(0,monster1.getXp());
		
		
		
	}
	/**
	 * test for increase monster attack
	 */
	@Test 
	public void increaseAttack() {
		Monster monster1 = new Monster("henry",0,0,20,20,200,150,0,0,"fwfegv");
		System.out.println(monster1.getDamage());
		monster1.increaseAttack(50);
		System.out.println(monster1.getDamage());
		
		
	}

}
