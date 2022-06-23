package main;
/**
 * This class represents the sub class of Monster, dragon
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public class Dragon extends Monster {
	/**
	 * random number object for generating stats
	 */
private static RandomNumber rando = new RandomNumber();
/**
 * name of monster
 */
	private static String name;
	/**
	 * description of monster
	 */
	private static String  description = "The dragon monster is the most fearsome of all the monsters. It comes equipped with huge health and huge attack. ";
	/**
	 * max health
	 */
	private static int maxHealth =  rando.randomNumberInRange(200,170);
	/**
	 * how much a monster can heal
	 */
	private static int heal = rando.randomNumberInRange(60, 40);
	/**
	 * how much damage a monster does 
	 */
	private static int damage = rando.randomNumberInRange(50, 40);
	/**
	 * how much aggression a monster has
	 */
	private static int aggression = rando.randomNumberInRange(30, 20);
	
	
	
	
	
	
	
	/**
	 * constructor
	 */
	public Dragon() {
		super(name, maxHealth,heal,damage,aggression,200,150,0,0,description);
		super.setType("Fire");
		super.setRandomName();
	
	}
	
	
	

}
