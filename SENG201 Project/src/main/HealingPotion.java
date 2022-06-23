package main;
/**
 * subclass of items which descirbes the healing potion
 * @author angusburrowes
 *
 */
public class HealingPotion extends Item {
	/**
	 * name of item
	 */
	private static String itemname = "Healing potion";
	/**
	 * price of item
	 */
	private static int price = 15;
	/**
	 * sell price of item
	 */
	private static int sellPrice = 10;

	
	/**
	 * type of item
	 */

	private static String itemDescription = "A healing potion which restores 50 points of health. For the cost of 15 gold.";

	private static String itemType = "Heal";
	/**
	 * magnitude item increases stat by
	 */
	private static int magnitude = 50; 
	/**
	 * how much item increases heal
	 */
	private static int healMag = 50;
	/** 
	 * how much item increases attack
	 */
	private static int attackMag = 0;
	/**
	 * how much item increases aggression
	 */
	private static int agressMag=0;
	/**
	 * constructor
	 */
	public HealingPotion(){
		super(itemname,price, sellPrice,itemDescription, itemType,magnitude, healMag, attackMag, agressMag);
}
	/**
	 * gets heal amount
	 * @return int how much to increase current health by
	 */
	public int getheal() {
		return healMag;
}
	
}

	



