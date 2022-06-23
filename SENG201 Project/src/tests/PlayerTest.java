package tests;
import main.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import main.Monster;
import main.Player;
import main.Team;

class PlayerTest {
	private Player playerTest;
	private Monster monsterTest;
	private Monster monsterTest1;
	private Team team;
	
        
    


	@Test
	
	public void getmembernames() {
		HealingPotion health = new HealingPotion();
		Inventry inventry = new Inventry();
		
		
		
		inventry.addItem(health);
		assertEquals(inventry.getInvSize(),1);
		
		
		

}
	
	@Test
	public void sellMonster() {
		monsterTest = new Monster();
		monsterTest1 = new Monster();
		team = new Team();
		playerTest = new Player("hhhh");
		
		
		playerTest.addToTeam(monsterTest1);
		playerTest.sellMonster(monsterTest);
	
		
		assertEquals(team.getTeamSize(),1);
		
		
		
	}
}

