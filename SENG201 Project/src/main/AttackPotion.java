package main;
/**
 * class for the attackpotion which extends item
 * @author angusburrowes and Jed Atkinson
 *
 */

public class AttackPotion extends Item {
	/**
	 * name of the item
	 */
	private static String itemname = "Attack potion";
	/**
	 * price the item can be bought for
	 */
	private static int price = 15;
	/**
	 * price the item can be sold for
	 */
	private static int sellPrice = 10;
	/**
	 * description of item
	 */
	private static String itemDescription = "A basic attack potion which adds 5 points for attack for  the cost of 10 gold.";
	/**
	 * type of item
	 */
	private static String itemType = "Attack";
	
	/**
	 * magnitude a stat is increased by
	 */
	private static int magnitude = 5 ;
	/**
	 * how much the item effects heal
	 */
	
	private static int healMag = 0;
	/**
	 * how much the item increases attack by
	 */

	private static int attackMag = 5;
	/**
	 * how much the item increases aggression by
	 */

	private static int agressMag=0;
	
	/**
	 * constructor
	 */
	public AttackPotion(){
		super(itemname,price, sellPrice,itemDescription, itemType,magnitude, healMag, attackMag, agressMag);
}
	/**
	 * gets the damage given by item
	 * @return int damage increased by
	 */
	public int getdamage() {
		return attackMag;
}
	
}

	


	