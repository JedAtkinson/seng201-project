package main;
import java.util.ArrayList;
/**
 * class creates an instance of store where items and monsters can be bought
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public class Store {
	//variables
	/**
	 * arrayList of monsters that can be bought
	 */
	private static ArrayList<Purchaseable> shopMonsters= new ArrayList<Purchaseable>();
	/**
	 * array list of items that can be bought
	 */
	private ArrayList<Purchaseable> items = new ArrayList<Purchaseable>();
	
	/**
	 * creates instances of all potions and adds to shop
	 */
	public void purchaseablePotions() {
		items.clear();
		items.add(new AggressionPotion());
		items.add(new HealingPotion());
		items.add(new AttackPotion());
		items.add(new ResuscitationPotion());
		
	}
	/**
	 * gets items that can be bought
	 * @return arraylist of purchaseable items
	 */
	public ArrayList<Purchaseable> getItems() {
		return items;
	}
	/**
	 * gets monsters that can be bought
	 * @return arraylist of purchaseable monsters
	 */
	public ArrayList<Purchaseable> getMonsters() {
		return shopMonsters;
	}
	/**
	 * retruns monster names
	 * @return String arrayList of monster names
	 */
	public ArrayList<String> getMonsterNames() {
		ArrayList<String> monsterNames = new ArrayList<String>();
		for (Purchaseable monster : shopMonsters) {
			monsterNames.add(monster.getName());
		}
		return monsterNames;
	}
	
	/**
	 * returns item names
	 * @return String arrayList of names
	 */
	public ArrayList<String> getItemNames() {
		ArrayList<String> itemNames = new ArrayList<String>();
		for (Purchaseable item : items) {
			itemNames.add(item.getName());
		}
		return itemNames;
	}
	/**
	 * creates instance of each monster and adds to store
	 */
	public void purchaseableMonsters() {
		shopMonsters.clear();
		shopMonsters.add(new Dragon());
		shopMonsters.add(new Vampire());
		shopMonsters.add(new HydraMonster());
		shopMonsters.add(new ZombieMonster());
		shopMonsters.add(new WereWolfMonster());
		shopMonsters.add(new GiantMonster());
		

		
		
	}
	
	
	
/**
 * adds item to store
 * @param item Item to be added tos tore
 */
	public void addItem(Item item) {
		items.add(item);
	}
	/**
	 * to string for monsters
	 * @return String representation of monsters in store
	 */
	
	public String monstersToString() {
		
		String returnString = "Monsters for sale";
		for(int i =0; i < shopMonsters.size(); i++) {
			Monster monsterInfo = (Monster) shopMonsters.get(i);
			returnString += "\n("+i+") "+ monsterInfo.getName() +"\n "+monsterInfo.getDescription()
			+"\n Health:" + monsterInfo.getMaxHealth() + "  Damage: " + monsterInfo.getDamage();
 			
		}
		

		
		return returnString;
	}
	/**
	 * to string for items
	 * @return String representation of items in shop
	 */
	public String itemsToString() {
		String returnString = "items for sale:";
		for(int i = 0; i < items.size(); i++) {
			Purchaseable itemInfo = items.get(i);
			 returnString += "\n("+i+") "+ itemInfo.getName();
			
		}
		return returnString;
	}
	/**
	 * gets monster at index
	 * @param i  index of monster
	 * @return  monster at index
	 */
	public Monster getMonster(int i) {
		return (Monster) shopMonsters.get(i);
	}
	/**
	 * gets item at index
	 * @param i index
	 * @return item at index
	 */
	public Item getItem(int i) {
		return (Item) items.get(i);
	}
	
	
	
}
