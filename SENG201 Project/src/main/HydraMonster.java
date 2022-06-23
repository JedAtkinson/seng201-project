package main;

/**
 * subClass of Monster which describes the Hydra Monster
 * @author angusburrowes
 *
 */
public class HydraMonster extends Monster {
	/**
	 * random number for generating stats
	 */
private static RandomNumber rando = new RandomNumber();
/**
 * name of monster
 */
	private static String name;
	/**
	 * description of monster
	 */
	private static String  description = "The Hydra monster has needle like fangs causing it to cause more\n"
			+ "damage and the cost of having slightly less health.";
	/**
	 * max health of monster
	 */
	private static int maxHealth =  rando.randomNumberInRange(90,80);
	/**
	 * heal of monster
	 */
	private static int heal = rando.randomNumberInRange(30, 20);
	/**
	 * damage given by monster
	 */
	private static int damage = rando.randomNumberInRange(45, 30);
	/**
	 * aggression of monster
	 */
	private static int aggression = rando.randomNumberInRange(30, 20);
	
	
	
	
	
	/**
	 * constructor
	 */
	
	
	public HydraMonster() {
		super(name, maxHealth,heal,damage,aggression,200,150,0,0,description);
		super.setType("Hydra");
		super.setRandomName();
	
	}
	
	
	

}
