package main;
/**
 * Vampire subclass whos parent is monster.
 * holds and stores info about the vampire
 * @author Angus Burrowes
 *
 */
public class Vampire extends Monster{
	/**
	 * random number for generating stats
	 */
private static RandomNumber rando = new RandomNumber();
/**
 * na,e of monster
 */
	private static String name;
	/**
	 * description of monsters
	 */
	private static String  description = "the vampire monster is notorious for staying out at night.\n This causes them to be more likely to trigger random events.\n"
			+ "The vampire comes equipped with modest health and slightly more attack. ";
	/**
	 * max health of monster
	 */
	private static int maxHealth =  rando.randomNumberInRange(100,80);
	/**
	 * heal of monster
	 */
	private static int heal = rando.randomNumberInRange(20, 16);
	/**
	 * damage of monster
	 */
	private static int damage = rando.randomNumberInRange(40, 30);
	/**
	 * aggression of monster
	 */
	private static int aggression = rando.randomNumberInRange(30, 20);
	
	
	
	
	
	
	/**
	 * constructor
	 */
	
	public Vampire() {
		super(name, maxHealth,heal,damage,aggression,200,150,0,0,description);
		super.setType("Vampire");
		super.setRandomName();
	
	}


}
