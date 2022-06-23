package main;
import java.util.ArrayList;
import java.util.List;
/**
 * class which holds players items in inventry and creates an instance of inventry
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public class Inventry  {
	/**
	 * Creating an ArrayList object to to store items called inventry.
	 */
	private ArrayList<Item> inventry = new ArrayList<Item>();
	/**
	 * gets the inventry held by player
	 * @return  arrayList which shows all players items
	 */
	public ArrayList<Item> getInventry(){
		return inventry;
	}
		
	/**
	 * gets inv item at index
	 * @param index where item is stored
	 * @return item at the index
	 */
	
	public Item getItem(int index) {
		return inventry.get(index);
	}
/**
 * gets the amount of items in players bag
 * @return int number of items 
 */
	
	public int getInvSize(){
		return inventry.size();
	}
	/**
	 * Adds a specific item to the inventory.
	 * @param item    item to be added to inventory
	 */
	
	public void addItem(Item item) {
		inventry.add(item);
	}
	/**
	 * removes item from bag.
	 * @param item    item to be removed.
	 */
	
	public void removeItem(Item item) {
		inventry.remove(item);
	}
	/**
	 * to check if an instance of an item already exists.
	 * if there is it can be used else make a new instance.
	 * @param <E>  something 
	 * @param clazz something else 
	 * @return boolean depending on outcome
	 */
	public <E> boolean containsInstance(Class<? extends E> clazz) {
		List<E> list = (List<E>) inventry;
	    return list.stream().anyMatch(e -> clazz.isInstance(e));
	}
	
	
	/**
	 * buys item from the store. Adds the item to the players inventory and removes the gold from their wallet
	 * @param item    item being bought
	 * @param player   player who buys item
	 */
	public void buyItem(Item item, Player player) {
		
		if(item.getPurchasePrice()<= player.getGoldAmount()) {
			player.addToBackback(item);
			player.removeGold(item.getPurchasePrice());
			System.out.println("You have sucsessfully purchased a " + item.getName());
		}
		else {
				System.out.println("You do not have enought gold to make this purchase. Come back once youve won more battles!");
			
			
		}
		
		
		
	

	}
	/**
	 * sells item from players inventory. Removes item
	 * and compensates player with gold.
	 * @param index     index of the item to be removed.
	 * @param player    player who owns the item.
	 */
	
	public void sellItem(int index, Player player) {
		player.giveGold(inventry.get(index).getSellPrice());
		inventry.remove(index);
	}
	
	/**
	 * Returns a string of items in Inventory if there are any
	 * else it returns a string saying inventory empty
	 * @return String representation of Inventory 
	 */
	public String toString() {
		String returnString;
		if (inventry.size() > 0) {
			returnString = "Your Inventory:\n";
			for (int i=0; i < inventry.size(); i++) {
				returnString += "("+i+")\n";
				returnString += inventry.get(i)+"\n";
			}
		} else {
			returnString = "Your Inventory is empty, visit the store to buy items";
		}
		
		return returnString;
	}
}

