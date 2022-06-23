package main;
import java.util.ArrayList;
import java.util.Collections;
/**
 * This class creates a team 
 * object for the player to store monsters in
 * @author Angus Burrowes
 *
 */

public class Team extends Monster{
	//Variables
	/**
	 * creates the arraylist to store monsters
	 */
	private ArrayList<Monster> playerTeam= new ArrayList <>();

	 /**
	  * amount of monsters on team
	  */
	private int totalTeamAmount;
	
	
	/**
	 * returns the players team
	 * @return an arraylist containing monsters
	 */
	public ArrayList<Monster> getPlayerTeam() {
		return playerTeam;
	}
	
	/**
	 * returns Monsters in players team with more than 0 health
	 * @return an ArrayList containing Monsters
	 */
	public ArrayList<Monster> getAvalibleMonsters() {
		ArrayList<Monster> avalibleMonsters = new ArrayList<Monster>();
		for (Monster monster : playerTeam) {
			if (monster.getHealth() > 0) {
				avalibleMonsters.add(monster);
			}
		}
		return avalibleMonsters;
	}
	
	/**
	 * returns the number of monsters in the team
	 * @return an int representing the number of monsters
	 */
	public int getTeamSize(){
		return playerTeam.size();
	}
	/**
	 * gets total monsters player has had 
	 * @return  int number of monsters
	 */
	public int getTotalTeamAmount() {
		return totalTeamAmount;
	}


/**
 * Tries to add monster to team. A message will appear depending on if failure or
 * Success
 * @param monster  monster to be added to the players team
 */
public void addMonster(Monster monster) {
	if (playerTeam.size()< 3) {
		playerTeam.add(monster);
		//System.out.println(monster.getName()+ " Has sucsessfully been added to your team!");

		totalTeamAmount += 1;

	}
	else {
		System.out.println("unable to add "+ monster.getName() + " because your team is already full!");
	}
	
}

/**
 * removes the specified monster from the team
 * @param monster  monster to be removed 
 */
public void removeMonster(Monster monster) {
	if( playerTeam.size()< 1) {
		System.out.println("Cannot remove monster from an empty team");
		
	}
	else {
		playerTeam.remove(monster);
		System.out.println(monster.getName()+" Has successfully been removed from the team ");
	}
	
}
/**
 * returns the monster at the given index
 * @param index to get monster from
 * @return   monster 
 */
public Monster getMonster(int index) {
	return (Monster) playerTeam.get(index);
	
	
}

/**
 * swaps the position of two monsters
 * @param ind1 first monster to be swapped
 * @param ind2 second monster to be swapped 
 */
public void swapPositions(int ind1, int ind2) {
	if(ind1 < playerTeam.size() && ind2 < playerTeam.size() && ind1 >= 0 && ind2 >= 0) {
		Collections.swap(playerTeam, ind1, ind2);
		System.out.println("Monsters sucsessfully swapped");
		
	}
	
	else {
		System.out.println("Cannot swap monsters");
		
	}	
}
/**
 * heals all monsters in the team
 */
public void healTeam() {
	for (Monster monster : playerTeam) {
		monster.heal(monster.getHeal());;
	}
}
/**
 * returns a string representation of the team
 */
public String toString() {
	String returnString = "Your Team:\n";
	for (int i = 0; i < playerTeam.size(); i++) {
		returnString += "("+i+")\n";
		returnString += playerTeam.get(i)+"\n";
	}
	return returnString;
}
}




