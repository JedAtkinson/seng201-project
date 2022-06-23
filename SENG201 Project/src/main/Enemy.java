package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is for enemies to battle
 * @author Angus Burrowes Jed Atkinson
 *
 */
public class Enemy {
	//Variables
	/**
	 * Enemies name
	 */
	private String enemyName;
	/**
	 * Team containing all enemies Monsters
	 */
	private Team team = new Team();
	/**
	 * Difficulty of enemy
	 */
	private int difficulty;
	/** 
	 * Amount of gold gained when enemy defeated
	 */
	private int reward;
	/**
	 * Creating a list of random names to be chosen for monster name if none is specified. 
	 */
	private List<String> unspecifiedNames = Arrays.asList("Kanto", "Johto", "Hoenn", "Sinnoh", "Unova", 
			"Rowan", "Aurea", "Juniper", "Fennel", "Augustine", "Kukui", "Magnolia", "Sonia", "Krane"
			, "Kaminko", "Bellis", "Brock", "Misty", "Surge", "Erika", "Koga" , "Sabrina", "Blaine", 
			"Janine", "Falkner", "Bugsy", "Whitney", "Morty", "Chuck", "Jasmine","Pryce", "Roxanne", 
			"Brawly", "Wattson", "Flannery","Norman","Winona","Liz", "Jeffery", "Son", "Kane", "Mo", "Sadio", "Diaz",
			"Erling");
	//Constructors
	/**
	 * Constructs enemy with random name, team and difficulty
	 */
	public Enemy() {
		RandomNumber rng = new RandomNumber();
		
		int indexNumber = rng.randomNumberInRange(0,unspecifiedNames.size());
		enemyName = unspecifiedNames.get(indexNumber);
		
		for (int i = 0; i < 3; i++) {
			team.addMonster(Monster.getRandomMonster());
		}
		
		//randomly implemented for testing, use next constructor in game
		difficulty = rng.randomNumberInRange(1,10); 
		reward = difficulty*10 + rng.randomNumberInRange(-10,10);
	}
	
	/**
	 * Constructs enemy with given difficulty and team size
	 * @param diff   how difficult the player wants the game
	 * @param teamSize     How many monsters should be on the enemy team
	 */
	public Enemy(int diff, int teamSize) {
		RandomNumber rng = new RandomNumber();
		
		int indexNumber = rng.randomNumberInRange(0,unspecifiedNames.size());
		enemyName = unspecifiedNames.get(indexNumber);
		
		for (int i = 0; i < teamSize; i++) {
			team.addMonster(Monster.getRandomMonster());
		}
		
		difficulty = diff;
		reward = difficulty*10 + rng.randomNumberInRange(-10,10);
	}
	/**
	 * checks if the enemy team has enough monsters to fight 
	 * @return  true or false depending on if the enemy can fight
	 */
	public boolean isAvalible() {
		if (team.getAvalibleMonsters().size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//Getters and Setter
	/**
	 * Returns enemies name
	 * @return Enemy name
	 */
	public String getName() {
		return enemyName;
	}
	
	/**
	 * Returns enemies Team
	 * @return Enemy Team
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * returns reward amount
	 * @return int amount of gold won for beating enemy
	 */
	public int getReward() {
		return reward;
	}
	
	/**
	 * returns a string representation of Enemy
	 * @return String representation of Enemy
	 */
	public String toString() {
		String returnString = "Name: "+enemyName;
		returnString += "\nDifficulty: "+difficulty;
		returnString += "\nReward: $"+reward;
		return returnString;
	}
}
