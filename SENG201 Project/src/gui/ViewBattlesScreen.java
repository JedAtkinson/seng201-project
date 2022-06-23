package gui;

import main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
/**
 * GUI Screen to view battle 
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public class ViewBattlesScreen {

	private JFrame frmViewBattlesScreen;
	private ArrayList<JTextArea> enemyDesc = new ArrayList<JTextArea>();
	
	private GUIManager manager;
	ArrayList<Enemy> enemies;
	private Enemy selectedEnemy;

	/**
	 * Create and launch the application.
	 * @param guiManager gui that manages
	 */
	public ViewBattlesScreen(GUIManager guiManager) {
		manager = guiManager;
		enemies = manager.getGame().getEnemies();
		selectedEnemy = null;
		initialize();
		setEnemies();
		frmViewBattlesScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewBattlesScreen = new JFrame();
		frmViewBattlesScreen.setTitle("View Battles Screen");
		frmViewBattlesScreen.setBounds(100, 100, 500, 350);
		frmViewBattlesScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewBattlesScreen.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Todays battles");
		lblTitle.setBounds(207, 11, 85, 14);
		frmViewBattlesScreen.getContentPane().add(lblTitle);
		
		JLabel lblSelectedEnemy = new JLabel("Select Enemy");
		lblSelectedEnemy.setBounds(10, 36, 145, 14);
		frmViewBattlesScreen.getContentPane().add(lblSelectedEnemy);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(246, 36, 240, 14);
		frmViewBattlesScreen.getContentPane().add(lblError);
		
		JButton btnEnemy1 = new JButton("Enemy 1");
		btnEnemy1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedEnemy = enemies.get(0);
				lblSelectedEnemy.setText("Enemy 1 Selected");
			}
		});
		btnEnemy1.setBounds(10, 61, 145, 23);
		if (!enemies.get(0).isAvalible()) {
			btnEnemy1.setEnabled(false);
		}
		frmViewBattlesScreen.getContentPane().add(btnEnemy1);
		
		JTextArea textAreaEnemy1 = new JTextArea();
		textAreaEnemy1.setEditable(false);
		textAreaEnemy1.setBounds(10, 95, 145, 121);
		frmViewBattlesScreen.getContentPane().add(textAreaEnemy1);
		enemyDesc.add(textAreaEnemy1);
		
		JButton btnEnemy2 = new JButton("Enemy 2");
		btnEnemy2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				selectedEnemy = enemies.get(1);
				lblSelectedEnemy.setText("Enemy 2 Selected");
			}
		});
		btnEnemy2.setBounds(170, 61, 145, 23);
		if (!enemies.get(1).isAvalible()) {
			btnEnemy2.setEnabled(false);
		}
		frmViewBattlesScreen.getContentPane().add(btnEnemy2);
		
		JTextArea textAreaEnemy2 = new JTextArea();
		textAreaEnemy2.setEditable(false);
		textAreaEnemy2.setBounds(170, 95, 145, 121);
		frmViewBattlesScreen.getContentPane().add(textAreaEnemy2);
		enemyDesc.add(textAreaEnemy2);
		
		JTextArea textAreaEnemy3 = new JTextArea();
		textAreaEnemy3.setEditable(false);
		textAreaEnemy3.setBounds(330, 95, 145, 121);
		frmViewBattlesScreen.getContentPane().add(textAreaEnemy3);
		
		JButton btnEnemy3 = new JButton("Enemy 3");
		btnEnemy3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedEnemy = enemies.get(2);
				lblSelectedEnemy.setText("Enemy 3 Selected");
			}
		});
		btnEnemy3.setBounds(330, 61, 145, 23);
		if (!enemies.get(2).isAvalible()) {
			btnEnemy3.setEnabled(false);
		}
		frmViewBattlesScreen.getContentPane().add(btnEnemy3);
		enemyDesc.add(textAreaEnemy3);
		
		JButton btnBattle = new JButton("Battle Enemy");
		btnBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.getGame().getPlayer().getTeam().getAvalibleMonsters().size() > 0) {
					if (selectedEnemy == null) {
						lblError.setText("No Enemy selected");
					} else {
						Battle battle = new Battle(manager.getGame().getPlayer(), manager.getGame().getPlayer().getTeam().getMonster(0), selectedEnemy);
						manager.launchBattleScreen(battle);
						frmViewBattlesScreen.dispose();
					}
				} else {lblError.setText("You have no avalible monsters to battle with!");}
			}
		});
		btnBattle.setBounds(10, 241, 145, 23);
		frmViewBattlesScreen.getContentPane().add(btnBattle);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.closeScreen(frmViewBattlesScreen);
			}
		});
		btnExit.setBounds(330, 241, 145, 23);
		frmViewBattlesScreen.getContentPane().add(btnExit);
	}
	
	private void setEnemies() {
		enemies = manager.getGame().getEnemies();
		for (int i=0; i < 3; i++) {
			enemyDesc.get(i).setText(enemies.get(i).toString());
		}
	}
}
