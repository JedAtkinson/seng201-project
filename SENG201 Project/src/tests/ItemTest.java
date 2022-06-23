package tests;
import main.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;


class ItemTest {
	Team team = new Team();
	
	
	public void useItemTest(){
		
		Monster monster3 = new Monster("hen44ry",100,20,20,20,200,150,0,0,"fw44fegv");
		
		team.addMonster(monster3);
		AttackPotion attack = new AttackPotion();
		AggressionPotion aggress = new AggressionPotion();
		HealingPotion heal = new HealingPotion();
		
		attack.useItem(monster3);
		heal.useItem(monster3);
		aggress.useItem(monster3);
		
		assertEquals(monster3.getDamage(),25);
		assertEquals(monster3.getHealth(),25);
	}
	
}
