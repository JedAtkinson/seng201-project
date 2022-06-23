package gui;

import main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
/**
 * This class impliments a battle in the GUI
 * @author angusburrowes and Jed Atkinson
 *
 */
public class BattleScreen {

	private JFrame frmBattleScreen;
	private JTextArea txtrPlayerMonsterDesc;
	private JTextArea txtrEnemyMonsterDesc;
	private JFormattedTextField ftfPlayerDice;
	private JFormattedTextField ftfEnemyDice;
	private JTextArea textArea;
	private JButton btnAttack;
	private JButton btnFlee;
	private JButton btnTeam;
	
	private GUIManager manager;
	private Battle battle;
	private RandomNumber rng = new RandomNumber();

/**
 * Create and launch the application.
 * @param guiManager    access and store game data 
 * @param newBattle     battle that has been launched
 */
	public BattleScreen(GUIManager guiManager, Battle newBattle) {
		manager = guiManager;
		battle = newBattle;
		
		initialize();
		setVariables();
		frmBattleScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattleScreen = new JFrame();
		frmBattleScreen.setTitle("Battle Screen");
		frmBattleScreen.setBounds(100, 100, 500, 300);
		frmBattleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleScreen.getContentPane().setLayout(null);
		
		//Player
		JLabel lblYourMonster = new JLabel("Your Monster");
		lblYourMonster.setHorizontalAlignment(SwingConstants.LEFT);
		lblYourMonster.setBounds(10, 11, 212, 14);
		frmBattleScreen.getContentPane().add(lblYourMonster);
		
		txtrPlayerMonsterDesc = new JTextArea();
		txtrPlayerMonsterDesc.setEditable(false);
		txtrPlayerMonsterDesc.setText("Name: \r\n\r\nHealth: \r\nAttack: \r\nAgression: ");
		txtrPlayerMonsterDesc.setBounds(10, 36, 140, 160);
		frmBattleScreen.getContentPane().add(txtrPlayerMonsterDesc);
		
		JLabel lblYourDice = new JLabel("Your dice");
		lblYourDice.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourDice.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblYourDice.setBounds(162, 36, 60, 14);
		frmBattleScreen.getContentPane().add(lblYourDice);
		
		ftfPlayerDice = new JFormattedTextField();
		ftfPlayerDice.setText("1");
		ftfPlayerDice.setHorizontalAlignment(SwingConstants.CENTER);
		ftfPlayerDice.setBounds(173, 61, 30, 30);
		frmBattleScreen.getContentPane().add(ftfPlayerDice);
		
		//Enemy
		JLabel lblEnemyMonster = new JLabel(battle.getEnemy().getName()+"'s Monster");
		lblEnemyMonster.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnemyMonster.setBounds(255, 11, 221, 14);
		frmBattleScreen.getContentPane().add(lblEnemyMonster);
		
		txtrEnemyMonsterDesc = new JTextArea();
		txtrEnemyMonsterDesc.setEditable(false);
		txtrEnemyMonsterDesc.setText("Name: \r\n\r\nHealth: \r\nAttack: \r\nAgression: ");
		txtrEnemyMonsterDesc.setBounds(336, 36, 140, 160);
		frmBattleScreen.getContentPane().add(txtrEnemyMonsterDesc);
		
		JLabel lblEnemyDice = new JLabel("Enemies dice");
		lblEnemyDice.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyDice.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEnemyDice.setBounds(255, 36, 70, 14);
		frmBattleScreen.getContentPane().add(lblEnemyDice);
		
		ftfEnemyDice = new JFormattedTextField();
		ftfEnemyDice.setText("1");
		ftfEnemyDice.setHorizontalAlignment(SwingConstants.CENTER);
		ftfEnemyDice.setBounds(283, 61, 30, 30);
		frmBattleScreen.getContentPane().add(ftfEnemyDice);
		
		//Buttons
		btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerDiceRoll();
			}
		});
		btnAttack.setBounds(10, 229, 140, 23);
		frmBattleScreen.getContentPane().add(btnAttack);
		
		btnFlee = new JButton("Flee Battle");
		btnFlee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battle.inBattle = false;
				manager.closeScreen(frmBattleScreen);
			}
		});
		btnFlee.setBounds(173, 229, 140, 23);
		frmBattleScreen.getContentPane().add(btnFlee);
		
		btnTeam = new JButton("View Bag");
		btnTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchViewBagScreen();
			}
		});
		btnTeam.setBounds(336, 229, 140, 23);
		frmBattleScreen.getContentPane().add(btnTeam);
		
		textArea = new JTextArea("You have entered a battle\n against "+battle.getEnemy().getName());
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(160, 102, 166, 94);
		frmBattleScreen.getContentPane().add(textArea);
	}
	
	/**
	 * Updates the on screen elements to show the relevant variables
	 */
	private void setVariables() {
		txtrPlayerMonsterDesc.setText(battle.getPlayerMonster().getBattleStats());
		txtrEnemyMonsterDesc.setText(battle.getEnemyMonster().getBattleStats());
	}
	
	/**
	 * Rolls dice for player and calls attack method
	 */
	private void playerDiceRoll() {
		btnAttack.setEnabled(false);
		btnTeam.setEnabled(false);
		btnFlee.setEnabled(false);
		textArea.setText("Rolling dice...");
		Timer timer = new Timer(100, new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				count ++;
				ftfPlayerDice.setText(Integer.toString(rng.randomNumberInRange(1, 6)));
				ftfEnemyDice.setText(Integer.toString(rng.randomNumberInRange(1, 6)));
				if (count > 10) { //Stops roll after 10 repeats
					Timer s = (Timer)e.getSource();
					s.stop();
					playerAttack();
				}
			}
		});
		timer.start();
	}
	
	/**
	 * Rolls dice for the enemy and calls attack method
	 */
	private void enemyDiceRoll() {
		Timer timer = new Timer(100, new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				count ++;
				if (count > 20) { //Waits 30 repeats before rolling
					textArea.setText(battle.getEnemy().getName()+"'s turn");
					ftfPlayerDice.setText(Integer.toString(rng.randomNumberInRange(1, 6)));
					ftfEnemyDice.setText(Integer.toString(rng.randomNumberInRange(1, 6)));
					if (count > 30) { //Stops roll after 40 repeats
						Timer s = (Timer)e.getSource();
						s.stop();
						enemyAttack();
					}
				}
			}
		});
		timer.start();
	}
	
	/**
	 * Attacks the enemy depending on dice roll and outputs feedback.
	 * Checks if the enemy monster is dead and if battle finished 
	 */
	private void playerAttack() {
		boolean done = false;
		int pr = Integer.parseInt(ftfPlayerDice.getText());
		int er = Integer.parseInt(ftfEnemyDice.getText());
		int result = battle.playerAttack(pr, er);
		if (result == 0) {
			textArea.setText("Your Attack misses\n0 damage done");
			done = true;
		} else {
			if (result == -1) {
				textArea.setText("You draw\nPress attack to reroll");
			} else {
				textArea.setText("Your attack hits\n"+result+" damage done");
				done = true;
				if (!battle.enemyMonsterAlive) { // If enemy monster faints
					textArea.setText(textArea.getText()+"\n"+battle.getEnemyMonster().getName()+" faints");
					if (battle.pickEnemyMonster()) { // If Enemy still has monsters available
						textArea.setText(textArea.getText()+"\n"+battle.getEnemy().getName()+" swaps out his monster\nYour turn");
						setVariables();
						done = false;
					} else {
						int reward = battle.getEnemy().getReward();
						textArea.setText(textArea.getText()+"\nEnemy has no Monsters left\nYou win "+reward+" gold");
						manager.getGame().getPlayer().giveGold(reward);
						manager.updateMainScreen(); //Update the main screen
					}
				}
			}
		}
		if (done) {
			if (battle.inBattle) {
				setVariables();
				enemyDiceRoll();
			} else {
				btnFlee.setText("Leave");
				btnFlee.setEnabled(true);
			}
		}else {
			btnAttack.setEnabled(true);
			btnTeam.setEnabled(true);
			btnFlee.setEnabled(true);
		}
	}
	
	/**
	 * Attacks the player depending on dice roll and outputs feedback.
	 * Checks if the player monster is dead and if battle finished 
	 */
	private void enemyAttack() {
		boolean done = false;
		int pr = Integer.parseInt(ftfPlayerDice.getText());
		int er = Integer.parseInt(ftfEnemyDice.getText());
		int result = battle.enemyAttack(er, pr);
		if (result == 0) {
			textArea.setText(battle.getEnemy().getName()+"'s Attack misses\n0 damage done");
			done = true;
		} else {
			if (result == -1) {
				textArea.setText("Draw, "+battle.getEnemy().getName()+"'s rerolling...");
			} else {
				textArea.setText(battle.getEnemy().getName()+"'s attack hits\n"+result+" damage done");
				done = true;
				if (!battle.playerMonsterAlive) {
					textArea.setText(textArea.getText()+"\n"+battle.getPlayerMonster().getName()+" faints");
					if (manager.getGame().getPlayer().getTeam().getAvalibleMonsters().size() > 0) {
						Monster nextMonster = manager.getGame().getPlayer().getTeam().getAvalibleMonsters().get(0);
						battle.setPlayerMonster(nextMonster);
						textArea.setText(textArea.getText()+"\n"+"Your next monster: "+nextMonster.getName()+" tags in");
						setVariables();
					} else {
						textArea.setText(textArea.getText()+"\n"+"You have no avalible monsters left in your team\nYou lose this battle");
						btnAttack.setEnabled(false);
						btnTeam.setEnabled(false);
						battle.inBattle = false;
					}
				}
			}
		}
		if (done) {
			if (battle.inBattle) { //If still in battle continue
				setVariables();
				btnAttack.setEnabled(true);
				btnTeam.setEnabled(true);
				btnFlee.setEnabled(true);
			} else {
				btnFlee.setText("Leave");
				btnFlee.setEnabled(true);
			}
		} else {
			enemyDiceRoll();
		}
	}
}
