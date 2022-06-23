package main;
/**
 * class for WereWolf which is a subClass of Monster
 * @author Angus Burrowes
 *
 */
public class WereWolfMonster extends Monster {
	/**
	 * random number for generating monsters
	 */
	private static RandomNumber rando = new RandomNumber();
	/**
	 * monsters name 
	 */
	private static String name;
	/**
	 * description of monster
	 */
	private static String  description = "The werewolf is the most aggressive of all the monster types\n"
			+ " and will always attack first when attacking a different type of monster.\n"
			+ " It comes equipped with modest health and modest attack. ";
	/**
	 * maxHealth of monster
	 */
	private static int maxHealth =  rando.randomNumberInRange(100,80);
	/**
	 * heal of monster
	 */
	private static int heal = rando.randomNumberInRange(20, 16);
	/**
	 * damage of monster
	 */
	private static int damage = rando.randomNumberInRange(30, 20);
	/**
	 * aggression of monster
	 */
	private static int aggression = rando.randomNumberInRange(200, 180);
	
	
	
	
	
	
	
	/** 
	 * constructor
	 */
	public WereWolfMonster() {
		super(name, maxHealth,heal,damage,aggression,200,150,0,0,description);
		super.setType("WereWolf");
		super.setRandomName();
	
	}
	
	
}
 