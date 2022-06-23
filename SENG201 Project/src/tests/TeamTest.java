package tests;
import main.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Monster;
import main.Team;

class TeamTest {
	
	Team team = new Team();
	
	

	@Test
	public void testMonsterArray() {
		Monster monster1 = new Monster();
		Monster monster2 = new Monster();
		Monster monster3 = new Monster();
		
		team.addMonster(monster1);
		team.addMonster(monster2);
		team.addMonster(monster3);
		
		assertEquals(team.getPlayerTeam().size(),3);
		
		team.removeMonster(monster1);
		assertEquals(team.getPlayerTeam().size(),2);
		
		
	}
	@Test
	public void testSwappingMonsters() {
		Monster monster1 = new Monster("henry",100,20,20,20,200,150,0,0,"fwfegv");
		Monster monster2 = new Monster("jaymin",100,20,20,20,200,150,0,0,"fwfegv");
		team.addMonster(monster1);
		team.addMonster(monster2);
		team.swapPositions(0, 1);
		assertEquals(team.getMonster(0),monster2);
		
		
	}
	
	@Test
	public void removeMonsterTest() {
		Monster monster1 = new Monster("henry",100,20,20,20,200,150,0,0,"fwfegv");
		Monster monster2 = new Monster("jaymin",100,20,20,20,200,150,0,0,"fwfegv");
		team.addMonster(monster1);
		team.addMonster(monster2);
		assertEquals(team.getTeamSize(),2);
		team.removeMonster(monster2);
		assertEquals(team.getTeamSize(),1);
		
	}
	
	@Test
	public void getAvaliableMonsters(){
		Monster monster1 = new Monster("henry",0,0,20,20,200,150,0,0,"fwfegv");
		Monster monster2 = new Monster("jaymin",100,0,20,20,200,150,0,0,"fwfegv");
		Monster monster3 = new Monster("hen44ry",100,20,20,20,200,150,0,0,"fw44fegv");
		
		team.addMonster(monster1); 
		team.addMonster(monster2);
		team.addMonster(monster3);
		
		assertEquals(team.getAvalibleMonsters().size(),2);
		
	}
	
	@Test 
	public void removeMonster() {
		Monster monster1 = new Monster("henry",0,0,20,20,200,150,0,0,"fwfegv");
		Monster monster2 = new Monster("jaymin",100,0,20,20,200,150,0,0,"fwfegv");
		Monster monster3 = new Monster("hen44ry",100,20,20,20,200,150,0,0,"fw44fegv");
		
		team.addMonster(monster1); 
		team.addMonster(monster2);
		team.addMonster(monster3);
		
		team.removeMonster(monster3);
		team.removeMonster(monster2);
		assertEquals(team.getTeamSize(),1);
	}
	@Test
	public void healTeamTest(){
		Monster monster1 = new Monster("henry",100,100,20,20,200,150,0,0,"fwfegv");
		team.addMonster(monster1); 
		monster1.takeDamage(100);
		team.healTeam();
		assertEquals(monster1.getHealth(),100);
	}

}
