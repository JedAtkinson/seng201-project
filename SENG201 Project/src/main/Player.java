package main;
import java.util.ArrayList;

/**
 * This class creates and stores basic information
 *  about the player who is playing the game.
 * 
 * @since 12-04-2022
 */

public class Player {
	/**
	 * name set by the user. If no name is given a random one will be used.
	 */
	private String playerName;
	/**
	 * Gold is the in game currency used to purchase monsters and items. Start with 250 gold.
	 */
	private int gold;
	/**
	 * Team object containing all monsters in the players team.
	 */
	public Team playerTeam = new Team();
	/**
	 * Inventry containing all items stored in the players bag.
	 */
	private Inventry backpack = new Inventry();
	/**
	 * How long the player wants to play for.
	 */
	private int currentDay = 1;
	/**
	 * How hard the player would like the game to be.
	 */
	private String difficulty;
	/**
	 * Player gains points by defeating enemies.
	 * With total score being shown at the end of the game.
	 */
	private int points;
	/**
	 * total gold earned 
	 */
	private int totalGold;
	/**
	 * total enemies defeated 
	 */
	public int enemiesDefeted;
	
	/**
	 * constructor
	 * @param name  of player
	 */
	public Player(String name) {
		playerName = name;
		gold = 0;
	}
	
	/**
	 * sets the players name
	 * @param name to set
	 */
	public void setPlayerName(String name) {
		
		playerName = name;
	}
	/**
	 * Returns the players name
	 * @return player name
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * Return amount of gold
	 * @return amount of gold in bag
	 */
	
	public int getGoldAmount() {
		return gold;
	}
	/**
	 * gets total gold over play
	 * @return int gold total gold amount
	 */
	
	public int getTotalGold() {
		return totalGold;
	}
	
	/**
	 * Returns the members in your team
	 * @return monsters in team
	 */
	public Team getTeam() {
		return playerTeam;
	}
	
	 /**
	  * returns team member names
	  * @return  arraylist of names
	  */
	public ArrayList<String> getTeamNames(){
		ArrayList<String> teamMembers = new ArrayList<String>();
		for(int i = 0; i < playerTeam.getTeamSize(); i++) {
			String name = playerTeam.getMonster(i).getName();
			teamMembers.add(name);
		}
		return teamMembers;
		
	}
	
	/**
	 * Adds item to backpack without deducting gold.
	 * 
	 * @param item to be added to backpack.
	 */
	public void addToBackback(Item item) {
		backpack.addItem(item);
	}
	
	/**
	 * return items in backpack
	 * @return  items in backpack
	 */
	
	public Inventry getBackpack() {
		return backpack;
		
	}
	
	/**
	 * Returns length of game already played
	 * @return length of game already played
	 */
	public int getDaysPlayed() {
		return currentDay;
		
	}
	
	
	/**
	 * Adds monster to team if there is space.
	 * 
	 * @param monster   Monster to be added to team.
	 */
	
	public void addToTeam(Monster monster) {
		playerTeam.addMonster(monster);
	}

	/**
	 * Sets the difficulty wanted by player
	 * @param diff     either normal or expert. Expert is substantially harder.
	 */
	public void setDifficulty(int diff) {
		switch(diff) {
			case 1:
				difficulty = "Normal";
			case 2:
				difficulty = "Expert";
			
		}
	}
	/**
	 * returns difficulty selected by player.
	 * @return difficulty selected by player.
	 */
	public String getDifficulty() {
		return difficulty;
	}
	/**
	 * add a day to current day
	 */
	public void addDay() {
		currentDay +=1;
	}
	
	/**
	 * buys item from store if player has enough gold
	 * and adds it to bag.
	 * @param item     the item being purchased.
	 * @return boolean depending on outcome
	 */
	public boolean buyItem(Item item) {
		//Add check if enough gold
		if(gold >= item.getPurchasePrice()) {
			gold -= item.getPurchasePrice();
			backpack.addItem(item);
			System.out.println(item.getName()+" purchased");
			return true;
		}
		else {
			System.out.println("You do not have enough gold to purchase "+ item.getName());
			return false;
		}
	}
	/**
	 * purchases a monster and addes to team if player has sufficent gold
	 * @param monster  monster to add to team
	 * @return  boolean depending on outcome
	 */
	public boolean buyMonster(Monster monster) {
		if(gold >= monster.getPurchasePrice()) {
			if (playerTeam.getTeamSize() < 3) {
				gold -= monster.getPurchasePrice();
				playerTeam.addMonster(monster);
				System.out.println(monster.getName()+" purchased");
				return true;
			} else {
				System.out.println("Your team is already full");
				return false;
			}
		}
		else {
			System.out.println("You do not have enough gold to purchase "+ monster.getName());
			return false;
		}
	}
	
	/**
	 * Removes given monster from team and adds monsters selling price to players gold
	 * @param monster to sell
	 */
	public void sellMonster(Monster monster) {
		giveGold(monster.getSellPrice());
		playerTeam.removeMonster(monster);
	}
	
	/**
	 * Increase players gold
	 * @param amount  amount to increase gold by
	 */
	public void giveGold(int amount) {
		gold += amount;
		totalGold += amount;
	}
	/**
	 * remove gold from player inventry.
	 * @param goldspent     the amount to remove
	 */
	
	public void removeGold(int goldspent) {
		gold -= goldspent;
	}
	
	/**
	 * increase the players number of points
	 * @param pointsEarned      amount of points to increase by.
	 */
	public void addPoints(int pointsEarned) {
		points += pointsEarned;
	}
	
	
	
}
