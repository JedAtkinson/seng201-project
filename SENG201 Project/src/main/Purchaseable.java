package main;
/**
 * class for the interface purchaseable
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public interface Purchaseable {
	/**
	 * gets description of item
	 * @return string of item
	 */
	String getDescription();
	/**
	 * gets the item name
	 * @return  string item name
	 */
	String getName();
	/**
	 * gets purchase price
	 * @return int purchase price
	 */
	int getPurchasePrice();
	
	/**
	 * gets sell price
	 * @return  int sell price 
	 */
	int getSellPrice();
	

}
