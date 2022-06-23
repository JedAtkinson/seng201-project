package main;
import java.util.ArrayList;

/**
 * This class stores overall game objects/variables and
 * methods that change the game state
 * 
 * @since 12-04-2022
 */

public class Game {
	//Variables
	/**
	 * The player of the game
	 */
	private Player player;
	/**
	 * The number of days the game will go for
	 */
	private int numOfDays;
	/**
	 * The current day number (instantiated with 1)
	 */
	private int currentDay = 1;
	/**
	 * The difficulty of the game
	 */
	private int difficulty;
	/**
	 * A Store object
	 */
	private Store store;
	/**
	 * ArrayList of enemies available to battle for the day
	 */
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	
	//Getters and setters
	/**
	 * Sets player, number of days and difficulty
	 * @param given_player  player that is playing the game
	 * @param numDays    number of days the player wants to play for
	 * @param diff    how diffiuclt the player wants their game to be
	 */
	public Game(Player given_player, int numDays, int diff) {
		player = given_player;
		numOfDays = numDays;
		difficulty = diff;
		if (difficulty == 1) {
			player.giveGold(20);
		}
		store = new Store();
		store.purchaseablePotions();
		store.purchaseableMonsters();
		
		setDaysEnemies();
	}
	
	/**
	 * Instantiates player of game 
	 * @param name name given to player 
	 */
	public void setPlayer(String name) {
		player = new Player(name);
	}
	
	/**
	 * @return Player object
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Sets the number of days the game will run for
	 * @param days number of days player wants to play for
	 */
	public void setNumOfDays(int days) {
		//Add check if between 5-15
		numOfDays = days;
	}
	
	/**
	 * gets the length of the game
	 * @return Total number of days
	 */
	public int getNumofDays() {
		return numOfDays;
	}
	
	/**
	 * How many more days the game will run for 
	 * @return Int number of days remaining
	 */
	public int getDaysRemaining() {
		return numOfDays - currentDay;
	}
	
	/**
	 * Adds one to currentDay (goes to next day) and random event happens
	 * @return String description of any overnight event or null if all days done
	 */
	public String nextDay() {
		if (currentDay == numOfDays) {
			return null;
		} else {
			currentDay += 1;
			player.getTeam().healTeam();
			setDaysEnemies();
			store.purchaseablePotions();
			store.purchaseableMonsters();
			RandomEvents event = new RandomEvents(this);
			return event.getDescription();
		}
	}
	
	/**
	 * returns the day the game is currently on
	 * @return current day number
	 */
	public int getCurrentDay() {
		return currentDay;
	}
	
	/**
	 * Sets difficulty of the game
	 * @param diff how difficult player wants game
	 */
	public void setDifficulty(int diff) {
		difficulty = diff;
	}
	
	/**
	 * Creates and adds 3 random Monsters to a list and returns it
	 * @return ArrayList of startingMonsters
	 */
	public ArrayList<Monster> getStartingMonsters() {
		ArrayList<Monster> startingMonsters = new ArrayList<Monster>();
		for (int i = 0; i < 3; i++) {
			startingMonsters.add(new Monster());
		}
		return startingMonsters;
	}
	
	/**
	 * @return Store
	 */
	public Store getStore() {
		return store;
	}
	
	/**
	 * @return ArrayList of enemies to battle
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	/**
	 * Removes given enemy from the days enemies
	 * @param enemy enemy to be removed 
	 */
	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}
	
	/**
	 * Creates new set of enemies for day based on difficulty and player team size
	 */
	public void setDaysEnemies() {
		enemies.clear();
		//Set enemy team size based on current day and difficulty
		int teamSize = 1;
		if (currentDay > 5 || (difficulty > 1 & currentDay > 3)) {
			teamSize = 2;
		} else if (currentDay > 10 || (difficulty > 2 & currentDay > 7)) {
			teamSize = 3;
		}
		for (int i = 0; i < 3; i++) {
			Enemy enemy = new Enemy(difficulty*currentDay, teamSize);
			enemies.add(enemy);
		}
	}
	
	/**
	 * Returns a string of stats about the game
	 * @return Multiline String
	 */
	public String getGameStats() {
		RandomNumber rng = new RandomNumber();
		int pointsMultipler =  rng.randomNumberInRange(difficulty, difficulty*2);
		String returnString = "Your Game stats";
		returnString += "\nSurvived for "+currentDay+" Days";
		returnString += "\nGained a total of "+player.enemiesDefeted*pointsMultipler+" points";
		returnString += "\nGained a total of "+player.getTotalGold()+" Gold";
		returnString += "\nDefeted a total of "+player.enemiesDefeted+" enemies";
		returnString += "\nGathered a total of "+player.getTeam().getTotalTeamAmount()+" Monsters in your team";
		return returnString;
	}
}
