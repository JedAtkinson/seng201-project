package main;

/**
 * class for Zombie which is a subClass of Monster
 * @author Angus Burrowes
 *
 */
public class ZombieMonster extends Monster {
	/**
	 * random number for gerating stats
	 */
private static RandomNumber rando = new RandomNumber();
/**
 * name of monster
 */
	private static String name;
	/**
	 * description of monster
	 */
	private static String  description = "The undead properties of the zombie monster make it so it can fully heal itself over night.\n"
			+ "This causes the zombie to have a normal attack but slightly less health. ";
	/**
	 * max health of monster
	 */
	private static int maxHealth =  rando.randomNumberInRange(80,60);
	/**
	 * heal of monster
	 */
	private static int heal = rando.randomNumberInRange(16, 12);
	/**
	 * damage of monster
	 */
	private static int damage = rando.randomNumberInRange(30, 20);
	/**
	 * aggression of monster
	 */
	private static int aggression = rando.randomNumberInRange(30, 20);
	
	
	
	
	
	
	
	/**
	 * constructor
	 */
	public ZombieMonster() {
		super(name, maxHealth,heal,damage,aggression,200,150,0,0,description);
		super.setType("Zombie");
		super.setRandomName();
	
	}
	
	
	
}

