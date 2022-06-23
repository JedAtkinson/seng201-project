package main;
/**
 * Class for aggression potion which extends item
 * @author angusburrowes and Jed Atkinson
 *
 */
public class AggressionPotion extends Item {
	/**
	 * name of the item
	 */
	private static String itemname = "aggression potion";
	/**
	 * price of item
	 */
	private static int price = 15;
	/**
	 * sell price of item
	 */
	private static int sellPrice = 10;
	/** 
	 * description of item
	 */
	private static String itemDescription = "An aggression potion adds 5 points to aggression for  the cost of 10 gold.";
	/**
	 * type of item
	 */
	private static String itemType = "Agression";
	/**
	 * magnitude stats is increase by
	 */
	private static int magnitude = 5; 
	/**
	 * how much heal increases by
	 */
	private static int healMag = 0;
	/**
	 * how much attack is increased by
	 */
	private static int attackMag = 0;
	/** 
	 * how much aggression is increased by
	 */
	private static int agressMag=5;
	
	/**
	 * constructor
	 */
		public AggressionPotion(){
		super(itemname,price, sellPrice,itemDescription, itemType,magnitude, healMag, attackMag, agressMag);
}
		/**
		 * returns aggression of potion
		 * @return int aggression increase amount
		 */
	public int getAggression() {
		return agressMag;
}
	
}