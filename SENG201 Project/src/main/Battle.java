package main;
import java.util.Random;
/**
 * This class implements the battle between two teams
 * @author angusburrowes and Jed Atkinson
 *
 */
public class Battle {
	/**
	 * player who is playing the game
	 */
	private Player player;
	
	/**
	 * the enemy the player is battling
	 */
	private Enemy enemy;
	/**
	 * the monster the player is using to battle
	 */
	private Monster playerMonster;
	/**
	 * the monster the enemy is using to battle the player
	 */
	private Monster enemyMonster;
	/**
	 * index of empty monster slot
	 */
	private int enemyMonsterIndex = 0;
	/**
	 * boolean to check if in battle or not
	 */
	public boolean inBattle;
	/**
	 * check if enemy has monsters to battle
	 */
	
	public boolean enemyMonsterAlive;
	/**
	 * check if player have monsters who can battle 
	 */
	public boolean playerMonsterAlive;
	/**
	 * random number for generating dice roll
	 */
	private Random rng = new Random();
	
	/**
	 * contructor
	 * @param p   player to fight
	 * @param pM  monster to fight
	 * @param e   enemey to fight against
	 */
	public Battle(Player p, Monster pM, Enemy e) {
		player = p;
		playerMonster = pM;
		enemy = e;
		enemyMonsterAlive = true;
		playerMonsterAlive = true;
		pickEnemyMonster();
		
		inBattle = true;
	}
	/**
	 * set players monster
	 * @param monster      monster to be set 
	 */
	
	public void setPlayerMonster(Monster monster) {
		playerMonster = monster;
	}
	/**
	 * returns the monster the player is currently using
	 * @return   monster that player is using
	 */
	public Monster getPlayerMonster() {
		return playerMonster;
	}
	/**
	 * returns enemy the player is battling
	 * @return enemy   enemy in battle
	 */
	public Enemy getEnemy() {
		return enemy;
	}
	/**
	 * returns the monster the enemy is using in current battle
	 * @return monster   monster the enemy is using 
	 */
	public Monster getEnemyMonster() {
		return enemyMonster;
	}
	/**
	 * returns a string representation of the monster 
	 * @return    String   of all monster stats
	 */
	
	public String getMonstersStats() {
		String output = "Your Monster:";
		output += "\n"+playerMonster.startingMonsterStats();
		output += "\n\nEnemies Monster:";
		output += "\n"+enemyMonster.startingMonsterStats();
		return output;
	}
	
	
	/**
	 * determines if enemy has any available monsters to fight
	 * @return boolean depending on if there are monsters to fight
	 */
	public boolean pickEnemyMonster() {
		if (enemyMonsterIndex >= enemy.getTeam().getTeamSize()) {
			inBattle = false;
			player.enemiesDefeted += 1;
			return false;
		} else {
			enemyMonster = enemy.getTeam().getMonster(enemyMonsterIndex);
			enemyMonsterAlive = true;
			enemyMonsterIndex ++;
			return true;
		}
	}
	/**
	 * rolls the dice which determines if a monsters attack hits or misses.
	 * if the attacking monster rolls higher their attack hits.
	 * @return  boolean depending on if the attacker will hit or miss
	 */
	public int diceRoll() {
		return rng.nextInt(6) + 1;
	}
	
	
	/**
	 * determines damage done from attack by the player.
	 * @param playerRoll  int that player rolled
	 * @param enemyRoll   int that enemy rolled
	 * @return   int Returns damage done if playerRoll higher, 0 if enemyRoll higher or -1 for reroll
	 */
	
	public int playerAttack(int playerRoll, int enemyRoll) {
		if (playerRoll > enemyRoll) { //Player wins
			int damage = playerMonster.getDamage();
			enemyMonster.takeDamage(damage);
			if (enemyMonster.getHealth() <= 0) {
				enemyMonsterAlive = false;
			}
			return damage;
		} else {
			if (playerRoll < enemyRoll) {
				return 0;
			} else {
				return -1;
			}
		}
	}
/**
 * heals the players monster in battle if they have a healing potion available
 */
	public void playerHeal() {
		playerMonster.heal(playerMonster.getHeal());
	}
	/**
	 * Determines damage done by enemy attack
	 * @param enemyRoll   enemy dice roll
	 * @param playerRoll  player dice roll
	 * @return int Returns damage done if enemyRoll higher, 0 if playerRoll higher or -1 for reroll
	 */
	
	public int enemyAttack(int enemyRoll, int playerRoll) {
		if (enemyRoll > playerRoll) { //enemy wins
			int damage = enemyMonster.getDamage();
			playerMonster.takeDamage(damage);
			if (playerMonster.getHealth() <= 0) {
				playerMonsterAlive = false;
			}
			return damage;
		} else {
			if (enemyRoll < playerRoll) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}
