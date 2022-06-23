package main;
/**
 * class for ressusitation potion which extends items.
 * @author Angus Burrowes and Jed Atkinson.
 *
 */
public class ResuscitationPotion extends Item {
	/**
	 * item name
	 */
	private static String itemname = "Resuscitation potion";
	/**
	 * item price
	 */
	private static int price = 50;
	/**
	 * sell price of item
	 */
	private static int sellPrice = 30;
	/**
	 * description of item
	 */
	private static String itemDescription = " Used to bring a monster back from the brink of death for the cost of 50 gold";
	/**
	 * type of item
	 */
	private static String itemType = "resuscitate";
	/**
	 * magnitude of item
	 */
	private static int magnitude = 50;
	/**
	 * mag of heal
	 */
	private static int magnitudeHeal = 0;
	/**
	 * mag of attack
	 */
	private static int magnitudeAttack=0;
	/**
	 * mag of aggression
	 */

	private static int magnitudeAgression=0; 
	
	/**
	 * constructor
	 */
	
	ResuscitationPotion() {
		super(itemname,price, sellPrice,itemDescription, itemType,magnitude, magnitudeHeal, magnitudeAttack, magnitudeAgression);
	}
}


