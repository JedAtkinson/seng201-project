package main;
/**
 * subClass of monster specifically for monster of the type 
 * giant
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public class GiantMonster extends Monster{
	/**
	 * random numbers for generating stats
	 */
private static RandomNumber rando = new RandomNumber();
/**
 * name of monster
 */
	
	private static String name;
	/**
	 * description of monster
	 */
	private static String  description = "The giants sheer size makes it a difficult enemy to defeat.\n"
			+ "It has greater health but less attack due to its clumsyness.";
	/**
	 * max health of monster
	 */
	private static int maxHealth =  rando.randomNumberInRange(150,110);
	/**
	 * amount monster can heal
	 */
	private static int heal = rando.randomNumberInRange(30, 22);
	/**
	 * damage a monster does
	 */
	private static int damage = rando.randomNumberInRange(25, 20);
	/**
	 * aggression monster has
	 */
	private static int aggression = rando.randomNumberInRange(30, 20);
	
	
	
	
	/**
	 * constructor
	 */
	public GiantMonster() { 
		super(name, maxHealth,heal,damage,aggression,200,150,0,0,description);
		super.setType("Fire");
		super.setRandomName();
	
	}
	
	

}
