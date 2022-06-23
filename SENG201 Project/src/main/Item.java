package main;
/**
 * class which relates to items. It implements the interface purchaseable
 * @author angusburrowes
 *
 */
public class Item implements Purchaseable{
	//Variables
	/**
	 * name of the item.
	 */
	String itemName;
	/**
	 * Price of the item in gold.
	 */
	int itemPrice;
	/**
	 * The price the player can sell the item back to the store for.
	 */
	int itemSellPrice;
	/**
	 * Description of the item.
	 */
	String itemDescription;
	/**
	 * The type of stat that the item effects.
	 */
	String itemType;
	/**
	 * The amount of that item the palyer already has. (cap 3 of each.)
	 */
	int itemCounter;
	
	/**
	 * the amount an item increases stat by.
	 */
	int magnitude;
	/**
	 * the amount of each item in the bag
	 */
	int amount;
	/**
	 * amount the item can heal
	 */
	int itemHeal;
	/**
	 * the amount an item increases attack by
	 */
	int itemAttack;
	/**
	 * the amount an item increases aggression by
	 */
	int itemAgression;
	/**
	 * a boolean to tell if a monster has fainted
	 */
	boolean revive;
	
	/**
	 * constructor 
	 * @param name  to be given to item
	 * @param price of item
	 * @param sell sell price of item
	 * @param description of item
	 * @param type of item
	 * @param itemMag  or item
	 * @param magnitudeHeal  of health increase
	 * @param magnitudeAttack of attack increase
	 * @param magnitudeAgression of aggression increase
	 */
	Item(String name, int price, int sell, String description, String type, int itemMag, int magnitudeHeal, int magnitudeAttack, int magnitudeAgression){
		itemName = name;
		itemPrice = price;
		itemSellPrice = sell;
		itemDescription = description;
		itemType = type;
		magnitude = itemMag;
		itemHeal = magnitudeHeal;
		itemAttack = magnitudeAttack;
		
		itemAgression = magnitudeAgression;
		
		
	}
	
	//getters and setters
	/**
	 * returns the items description
	 * @return String representation of the items 
	 * description
	 */
	
	public String getDescription() {
		return itemDescription;
		
	}
	/**
	 * gets the items name
	 */
public String getName() {
	return itemName;
}
/**
 * gets item purchase price
 */
public int getPurchasePrice() {
	return itemPrice;
}
/**
 * gets selling price of item
 */
public int getSellPrice() {
	return itemSellPrice;
}
/**
 * 
 * gets item type 
 * @return String representation of the type of 
 * item
 */
public String getItemType() {
	return itemType;
	
}


/**
 * 
 * returns item aggression
 * @return int representation of aggression 
 */
public int getItemAgression() {
	return itemAgression;
	
}
/**
 * gets an items heal
 * @return  int representation of 
 * the items heal
 * 
 */

public int getItemHeal() {
	return itemHeal;
}
/**
 * gets an items attack
 * @return int representation of 
 * an items attack
 */
public int getItemAttack() {
	return itemAttack;
	
}

/**
 * adds an item to inventory
 * @param item item to be added.
 * 
 */
public void addAmount(Item item) {
	item.amount += amount;
}
/**
 * uses an item on the monster which increases one of its stats
 * @param monster the monster for the potion to be used on
 */
public void useItem(Monster monster) {
	if(itemType == "Heal") {
		double currentHealth = monster.getHealth();
		monster.heal(itemHeal);
		double newHealth = currentHealth + getItemHeal();
		System.out.println(monster.getName() + " has healed and now has a current health of " + newHealth);
		
	}
	else if (itemType == "Agression") {
		double currentAgression = monster.getAgression();
		monster.gainAgression(itemAgression);
		double newAgression = currentAgression + getItemAgression();
		System.out.println(monster.getName() + " has taken an agression potion and now has an agression of " + newAgression);
	}
	else if (itemType == "Attack") {
		double currentAttack = monster.getDamage();
		monster.increaseAttack(itemAttack);
		double newAttack = currentAttack + getItemAttack();
		System.out.println(monster.getName() + " has taken an attack potion and now has an attack of "+ newAttack);
		
	}
	else if (itemType == "resuscitate"){
		if(monster.getHealth()<= 0) {
			monster.resucitate();
			System.out.println(monster.getName()+ " has been resucitated.");
		}
		else {
			System.out.println(monster.getName() + " cannot be resucitated as they have not fainted.");
		}
		
	}
}

public String toString() {
	return itemName;
}

}
