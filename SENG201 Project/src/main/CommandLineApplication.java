package main;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Command line interface for the the game, prints options and 
 * takes input from user
 * @author OEM
 *
 */

public class CommandLineApplication {
	
	private Game game;
	private Player player;
	private Scanner scanner = new Scanner(System.in);
	
	/**
	 * does the initial setup of the game
	 */
	private void setUp() {
		//Get player name
		System.out.println("What is you'r name?");
		player = new Player(scanner.nextLine());
		
		//Get how many days the game to last
		System.out.println("how many days they would like the game to last (5-15)");
		int numDays = scanner.nextInt();
		
		//Get difficulty
		System.out.println("Choose difficulty");
		System.out.println("(1) Normal\n(2) Expert");
		int difficulty = scanner.nextInt();
		
		game = new Game(player, numDays, difficulty);
		
		//Get players starting monster
		System.out.println("Choose your starting Monster");
		ArrayList<Monster> startingMonsters = game.getStartingMonsters();
		for (int i = 0; i < startingMonsters.size(); i++) {
			System.out.println("\nOption ("+i+")");
			System.out.println(startingMonsters.get(i).startingMonsterStats());
		}
		
		player.addToTeam(startingMonsters.get(scanner.nextInt()));
		
		game.setDaysEnemies();
	}
/**
 * creates the main loop of the program
 * allowing the player to visit store
 * etc
 */
	private void mainLoop() {
		//Prints current gold, day and days remaining at the start of each day
		System.out.println("-".repeat(30));
		System.out.println("Gold: "+player.getGoldAmount());
		System.out.println("Current Day: "+game.getCurrentDay());
		System.out.println("Days Remaining: "+(game.getNumofDays()-game.getCurrentDay()));
		
		//Repeats options until player sleeps
		boolean sleep = false;
		while (!sleep) {
			System.out.println("\n(0) View team");
			System.out.println("(1) View inventory");
			System.out.println("(2) View battles");
			System.out.println("(3) View shop");
			System.out.println("(4) Sleep");
			
			scanner.nextLine();
			
			switch(scanner.nextInt()) {
			  case 0:
				  //Prints players team
				  System.out.println(player.getTeam());
				  break;
			  case 1:
				  //Prints all items in players backpack
				  System.out.println(player.getBackpack());
				  break;
			  case 2:
				  //Shows available battles
				  viewBattles();
				  break;
			  case 3:
				  //Prints store
				  shop();
				  break;
			  case 4:
				  //If there are days remaining go to next day
				  if (game.getCurrentDay() != game.getNumofDays()) {
					  System.out.println("You sleep into the next day");
					  System.out.println(game.nextDay());
					  sleep = true;
					  mainLoop();
				  //Else exit mainLoop and finish game
				  } else {System.out.println("All days done"); sleep = true;}
				  break;
			}
		}
	}
	/**
	 * creates the shop where items and monsters can be purchased
	 */
	private void shop() {
		System.out.println("Gold: "+player.getGoldAmount());
		System.out.println("(0) view Monsters for sale");
		System.out.println("(1) view Potions for sale");
		
		switch (scanner.nextInt()) {
		case 0:
			System.out.println(game.getStore().monstersToString());
			if (scanner.hasNextInt()) {
				player.buyMonster(game.getStore().getMonster(scanner.nextInt()));
			} else {
				scanner.nextLine();
			}
			break;
		case 1:
			System.out.println(game.getStore().itemsToString());
			if (scanner.hasNextInt()) {
				player.buyItem(game.getStore().getItem(scanner.nextInt()));
			} else {scanner.nextLine();}
			break;
		default:
			System.out.println("Invalid input");
		}
	}
/**
 * generates battles to be fought each day
 */
	private void viewBattles() {
		//Prints randomly generated enemies to chose from
		ArrayList<Enemy> enemies = game.getEnemies();
		if (enemies.size() > 0) {
			System.out.println("Avalable battles:");
			for (int i = 0; i < enemies.size(); i++) {
				System.out.println("("+i+")\n"+enemies.get(i));
			}
			System.out.println("\nEnter non int to exit");
			
			//If player enters integer enter battle else back to main loop
			if (scanner.hasNextInt()) {
				if (game.getPlayer().getTeam().getAvalibleMonsters().size() > 0) {
					battle(enemies.get(scanner.nextInt()));
				} else {
					scanner.nextInt();
					System.out.println("You have no avalible monsters to battle with");
				}
			} else {scanner.nextLine();}
		} else {
			System.out.println("No more battles avalible today, sleep to resfresh");
		}
	}
	/**
	 * allows the player to fight enemy
	 * @param enemy  enemy to be fought
	 */
	private void battle(Enemy enemy) {
		System.out.println("Entering battle");
		
		Battle battle = new Battle(game.getPlayer(), selectMonster(), enemy);
		
		//Starts loop for while still Monsters to battle
		while (battle.inBattle) {
			
			while (battle.playerMonsterAlive & battle.enemyMonsterAlive) {
				//Prints current monsters stats
				System.out.println("*".repeat(30));
				System.out.println(battle.getMonstersStats());
				System.out.println("*".repeat(30));
				
				//Prints players options
				System.out.println("(0) Attack");
				//Add potions available for use here
				
				//Gets input
				switch (scanner.nextInt()) {
				case 0:
					//Roll dice until won or loss
					double damage = -1;
					while (damage < 0) {
						int d1 = battle.diceRoll();
						int d2 = battle.diceRoll();
						System.out.println("You rolled: "+d1);
						System.out.println(enemy.getName()+" rolled: "+d2);
						damage = battle.playerAttack(d1, d2);
						if (damage > 0) {System.out.println("You did "+damage+" damage");}
						if (damage == 0) {System.out.println("Your attack missed, 0 damage done");}
						if (damage == -1) {System.out.println("Draw, rerolling die");}
					}
					break;
				}
				if (battle.enemyMonsterAlive) {
					//Enemy attacks
					System.out.println(enemy.getName()+"'s turn");
					double damage = -1;
					while (damage < 0) {
						int d1 = battle.diceRoll();
						int d2 = battle.diceRoll();
						System.out.println(enemy.getName()+" rolled: "+d1);
						System.out.println("You rolled: "+d2);
						damage = battle.enemyAttack(d1, d2);
						if (damage > 0) {System.out.println(enemy.getName()+" did "+damage+" damage");}
						if (damage == 0) {System.out.println(enemy.getName()+"'s attack missed, 0 damage done");}
						if (damage == -1) {System.out.println("Draw, rerolling die");}
					}
				}
			}
			if (!battle.playerMonsterAlive) {
				System.out.println("Your monster fainted");
				if (game.getPlayer().getTeam().getAvalibleMonsters().size() > 0) {
					battle.setPlayerMonster(selectMonster());
				} else {
					System.out.println("You have no avalible monsters");
					System.out.println("Battle lost");
					battle.inBattle = false;
				}
			}
			if (!battle.enemyMonsterAlive) {
				System.out.println(battle.getEnemyMonster().getName()+" fainted");
				if (!battle.pickEnemyMonster()) {
					System.out.println("You win!");
					System.out.println("You get "+enemy.getReward()+" gold for defeting "+enemy.getName());
					player.giveGold(enemy.getReward());
					game.removeEnemy(enemy);
				} else {
					battle.enemyMonsterAlive = true;
				}
			}
		}
	}
	/**
	 * player selects monster that want to fight for them
	 * @return   monster that was selected by the player
	 */
	public Monster selectMonster() {
		//Select Monster for player
		ArrayList<Monster> avalibleMonsters = game.getPlayer().getTeam().getAvalibleMonsters();
		System.out.println("Chose your monster");
		for (int i = 0; i < avalibleMonsters.size(); i++) {
			System.out.println("\n("+i+") "+avalibleMonsters.get(i));
		}
		Monster selectedMonster = avalibleMonsters.get(scanner.nextInt());
		return selectedMonster;
	}
	
	
}
