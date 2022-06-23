package main;
import java.util.Arrays;
import java.util.List;

 /**
  *This class describes the  Parent monster class
  * @author angusburrowes
  *
  */

public class Monster implements Purchaseable{
	
private String monsterName;
private int maxHealth;
private int currentHealth;
private int damageGiven;
private int monsterAgression;    
private int monsterCost;
private int monsterSellingPrice;
private int healAmount;
private int level;
private int xp;
private String monsterType;
private String description;


/**
 * Creating a list of random names to be chosen for monster name if none is specified.
 */
private List<String> unspecifiedNames = Arrays.asList(" Keef", "Thug", "Jack","Feng", "Bill","Kaprice",
		"Kelly K","Marcus", "Bruno", "David", "Mason", "Anthony", "Alex", "Victor", "Chris", "Jaydon",
"Lil Boat", "Jenry Jilbert", "Rocco", "Goober", "Christiano ", "Carl ","Jeffery","Jeff","Joe", "KC", "Sus"
,"luke","Drainno", "Max", "Peter","Kodak", "Marley", "Chief", "Bruno", "Jasper", "tyler", "Health", "Jerry","John",
"Fred","Daniel","Richie", "Will", "Jason", "Theo", "Harry", "Raph","Kevin", "Geogre");





/**
 Monster constructor class
 * @param name name given to monster
 * @param monsterMaxHealth Maximum health monster has
 * @param heal  the amount a monster can heal by
 * @param damage the damage given by the monster
 * @param reach   the aggression a monster starts with
 * @param purchasePrice  how much it costs to purchase monster
 * @param sellingPrice   how much can be earned from selling monster
 * @param experience    current experience of monster capped at 100
 * @param levels        current level of monster, increases when 
 * xp reaches 100 and buffs monster stats briefly
 * @param about   description of monster
 */
public Monster(String name, int monsterMaxHealth, int heal, int damage, int reach, int purchasePrice, int sellingPrice, int experience, int levels,String about) {
monsterName = name;
maxHealth = monsterMaxHealth;
healAmount = heal;
damageGiven = damage;
monsterAgression = reach;
monsterCost = purchasePrice;
monsterSellingPrice = sellingPrice;
currentHealth = maxHealth;
level = levels;
xp = experience;
description = about;
}



/**
 * Constructor when nothing is given. For generating random monsters.
 */
public Monster() {
RandomNumber rng = new RandomNumber();
int indexNumber = rng.randomNumberInRange(0,unspecifiedNames.size());
monsterName = unspecifiedNames.get(indexNumber);
maxHealth = rng.randomNumberInRange(0,100);
currentHealth = maxHealth;
healAmount = rng.randomNumberInRange(0, 20);
damageGiven = rng.randomNumberInRange(0, 30);
monsterAgression = rng.randomNumberInRange(0, 10);
level = 0;
xp = 0;
}

/**
 * constructor to create random monster when difficulty set
 * @param difficulty    how difficult the player wants the 
 * game to be.
 */
public Monster(int difficulty) {
	
	
}


/**
 * Creates and returns new monster of random type
 * @return Monster of random type

 */
public static Monster getRandomMonster() {
	Monster monster;
	RandomNumber rng = new RandomNumber();
	int rand = rng.randomNumberInRange(0,4);
	switch (rand) {
	case 0:
		monster = new GiantMonster();
		break;
	case 1:
		monster = new HydraMonster();
		break;
	case 2:
		monster = new Vampire();
		break;
	case 3:
		monster = new WereWolfMonster();
		break;
	default:
		monster = new ZombieMonster();
		break;
	}
	return monster;
}


/**
 * Gets the name of the Monster
 * @return String name of monster
 */
public String getName() {
return monsterName;
}
/**
 * Gets the maxHealth of the Monster
 * @return Int max health of the monster
 */
public int getMaxHealth() {
return maxHealth;
}

/**
 * heals the monster. Will be run each night
 * @param healAmount   the amount the monster can heal
 */
public void heal(int healAmount) {
if(((getHealth() + healAmount) <= getMaxHealth()) && ((getHealth())> 0) && (healAmount > 0)){
currentHealth += healAmount;
}
else if ((getHealth()+ healAmount) >= getMaxHealth()) {
currentHealth = maxHealth;
}
else if((getHealth()+ healAmount)<= 0) {
currentHealth = 0;
}
}

/**
 * brings the monsters health back to full
 * after fainting
 */
public void resucitate() {
currentHealth = maxHealth;
}
/**
 * returns monster to full health
 */
public void fullHealth() {
currentHealth = maxHealth;
}

/**
 * gets the damage given by monster
 * @return  int  damage given
 */
public int getDamage() {
return damageGiven;
}
/**
 * removes health from monster after
 * being attacked
 * @param damage   int amount to decrease health by
 */
public void takeDamage(int damage) {
if (currentHealth < damage) {
	currentHealth = 0;
} else {
	currentHealth = currentHealth - damage;
}
}
/**
 * sets the type the monster is.
 * @param type   String type of monster
 */
public void setType(String type) {
monsterType = type;
}
/**
 * returns the amount a monster can heal by.
 * @return   int heal amount.
 */
public int getHeal() {
return healAmount;
}
/**
 * gets type of monster
 * @return String representation of monster type
 */
public String getMonsterType() {
return monsterType;
}

/**
 * gets health of monster
 * @return  int current health
 */
public int getHealth() {
return currentHealth;
}
/**
 * gets monster aggression
 * @return  int aggression of monster
 */
public int getAgression() {
return monsterAgression;
}
/**
 * increases monster aggression
 * @param agressionGained  int amount to increase aggression by.
 */
public void gainAgression(int agressionGained) {
monsterAgression += agressionGained;
}
/**
 * increases monster attack
 * @param AttackGained  int amount to increase attack by.
 */
public void increaseAttack(int AttackGained) {
damageGiven += AttackGained;
}
/**
 * gets the current level of monster
 * @return  int monster level
 */
public int getLevel() {
return level;
}
/**
 * gets monster current xp
 * @return   int current xp
 */
public int getXp() {
return xp;
}
/**
 * increases xp by a given amount
 * @param amount  int amount to increase xp by.
 */

public void increaseXp(int amount) {
	xp = xp+ amount;
	
}
/**
 * checks if monster is able to level up
 * and levels monster up if it has 100 xp
 */

/**
 * Levels up monsters by increasing some stats
 */
public void increaseLevel() {
maxHealth += 10;
damageGiven += 5;
monsterAgression += 5;
}
/**
 * sets the name of the monster
 * @param newName string name to be given to monster.
 */
public void setName(String newName) {
	monsterName = newName;
}
/**
 * sets monsters name to a random name.
 */
public void setRandomName() {
RandomNumber rng = new RandomNumber();
int namesLength = unspecifiedNames.size();
int indexNumber = rng.randomNumberInRange(0,namesLength);
monsterName = unspecifiedNames.get(indexNumber);
}

/**
 * returns the stats needed for the starting monster
 * @return  String starting monster important stats.
 */
public String startingMonsterStats() {
	String stringReturn = "Name: "+monsterName;
	 stringReturn += "\nDescription" + description;
	 stringReturn += "\nHealth: "+maxHealth;
	 stringReturn += "\nDamage: "+damageGiven;
	 stringReturn += "\nAgression: "+monsterAgression;
	 
	return stringReturn;
}
/**
 * gets the stats that need to be seen in battle
 * @return String battle stats
 */

public String getBattleStats() {
	String stringReturn = "Name: "+monsterName;
	 stringReturn += "\n\nType: "+monsterType;
	 stringReturn += "\n\nHealth: "+currentHealth+"/"+maxHealth;
	 stringReturn += "\n\nDamage: "+damageGiven;
	 stringReturn += "\n\nAgression: "+monsterAgression;
	 
	return stringReturn;
}
/**
 * to string method of monster
 */
@Override
public String toString() {
    String returnString = "Name: "+monsterName;
    returnString += "\nType: "+monsterType;
    returnString += "\nDescription: " + description;
    returnString += "\nHealth: "+maxHealth;
    returnString += "\nDamage: "+damageGiven;
    returnString += "\nAgression: "+monsterAgression;
    returnString += "\nCost: "+monsterCost;
    returnString += "\nsell: "+monsterSellingPrice;
   
    return returnString;
}

/**
 * returns description of monster
 */
@Override
public String getDescription() {
return description;
}

/**
 * gets purchase price of monster
 */
@Override
public int getPurchasePrice() {

return monsterCost;
}
/**
 * gets sell price of monster
 */

@Override
public int getSellPrice() {

return monsterSellingPrice;
}



}
