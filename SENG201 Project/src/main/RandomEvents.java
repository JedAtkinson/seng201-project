package main;
/** 
 * this class is run ever night and it determines if one of the following random events occur over night
 */
public class RandomEvents {
	/**
	 * description of the event
	 */
	private String eventDescription;
	/**
	 * which instance of the game the events belong to
	 */
	private Game game;
	/**
	 * a random number 
	 */
	private RandomNumber rng = new RandomNumber();
	/**
	 * enum with the different events that can occur.
	 * @param g game to apply random events to
	 */
	
	public RandomEvents(Game g) {
		game = g;
		int num = rng.randomNumberInRange(0, 10);
		switch(num) {
		case 0:
			monsterLeaves();
			break;
		case 1:
			monsterJoins();
			break;
		case 2:
			robbed();
			break;
		case 3:
			monsterLevelUp();
			break;
		default:
			eventDescription = "No events occured";
		}
	}
	/**
	 * returns the event description
	 * @return String of the events description
	 */
	public String getDescription() {
		return eventDescription;
	}
	/**
	 * determines if a monster should leave 
	 * and returns true or false accordingly
	 * @return boolean depending on success
	 */
	public boolean monsterLeaves() {
		if (game.getPlayer().getTeam().getTeamSize() == 3) {
			int num = rng.randomNumberInRange(0, game.getPlayer().getTeam().getTeamSize());
			Monster monster = game.getPlayer().getTeam().getMonster(num);
			game.getPlayer().getTeam().removeMonster(monster);
			eventDescription = "Your monster - "+monster.getName()+" left your team in the night";
			return true;
		} else {
			eventDescription = "No events occured";
			return false;
		}
	}
	
	/**
	 * determines if a monster should join
	 * and returns true or false accordingly
	 * @return boolean depending on success
	 */
	public boolean monsterJoins() {
		if (game.getPlayer().getTeam().getTeamSize() < 3) {
			Monster monster = Monster.getRandomMonster();
			game.getPlayer().addToTeam(monster);
			eventDescription = "A monster - "+monster.getName()+" joined your team in the night";
			return true;
		} else {
			eventDescription = "No events occured";
			return false;
		}
	}
	/**
	 * determines if a player should be robbed
	 * and returns true or false accordingly 
	 * @return boolean depending on success
	 */
	public boolean robbed() {
		if (game.getPlayer().getGoldAmount() > 30) {
			game.getPlayer().removeGold(10);
			eventDescription = "You were robbed in the night, you lose 10 gold";
			return true;
		} else {
			eventDescription = "No events occured";
			return false;
		}
	}
	/**
	 * determines if a monster should level up
	 * and returns true or false accordingly 
	 * @return boolean depending on success
	 */
	public boolean monsterLevelUp() {
		int num = rng.randomNumberInRange(0, game.getPlayer().getTeam().getTeamSize());
		Monster monster = game.getPlayer().getTeam().getMonster(num);
		monster.increaseLevel();
		eventDescription = "Your monster - "+monster.getName()+" Leveled up over night";
		return true;
	}
}
