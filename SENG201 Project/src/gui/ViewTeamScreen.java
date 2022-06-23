package gui;

import main.*;

import java.awt.EventQueue;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Gui Screen to view team
 * @author Angus Burrowes
 *
 */
public class ViewTeamScreen {

	private JFrame frmViewTeamScreen;
	private JLabel lblSelectedMonster;
	private JLabel lblError;
	private JButton btnSwap;
	private ArrayList<JTextArea> monsterDescriptions = new ArrayList<JTextArea>();
	private ArrayList<JButton> monsterButtons = new ArrayList<JButton>();
	
	private GUIManager manager;
	private Team team;
	private Monster selectedMonster;
	private Monster swapMonster;

	/**
	 * Create and launch the application.
	 * @param guiManager to manage Gui
	 */
	public ViewTeamScreen(GUIManager guiManager) {
		manager = guiManager;
		team = manager.getGame().getPlayer().getTeam();
		initialize();
		setMonsterDesc();
		frmViewTeamScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewTeamScreen = new JFrame();
		frmViewTeamScreen.setTitle("View Team Screen");
		frmViewTeamScreen.setBounds(100, 100, 500, 350);
		frmViewTeamScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewTeamScreen.getContentPane().setLayout(null);
		
		lblError = new JLabel("");
		lblError.setBounds(250, 11, 226, 14);
		frmViewTeamScreen.getContentPane().add(lblError);
		
		lblSelectedMonster = new JLabel("Monster 1 selected");
		setSelectedMonster(0);
		lblSelectedMonster.setBounds(10, 11, 170, 14);
		frmViewTeamScreen.getContentPane().add(lblSelectedMonster);
		
		JButton btnMonster1 = new JButton("Monster 1");
		btnMonster1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedMonster(0);
			}
		});
		btnMonster1.setBounds(10, 51, 145, 23);
		frmViewTeamScreen.getContentPane().add(btnMonster1);
		monsterButtons.add(btnMonster1);
		
		JTextArea txtrMonster1Desc = new JTextArea();
		txtrMonster1Desc.setEditable(false);
		txtrMonster1Desc.setText("");
		txtrMonster1Desc.setBounds(10, 85, 145, 116);
		frmViewTeamScreen.getContentPane().add(txtrMonster1Desc);
		monsterDescriptions.add(txtrMonster1Desc);
		
		JButton btnMonster2 = new JButton("Monster 2");
		btnMonster2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedMonster(1);
			}
		});
		btnMonster2.setBounds(170, 51, 145, 23);
		frmViewTeamScreen.getContentPane().add(btnMonster2);
		monsterButtons.add(btnMonster2);
		
		JTextArea txtrMonster2Desc = new JTextArea();
		txtrMonster2Desc.setEditable(false);
		txtrMonster2Desc.setText("");
		txtrMonster2Desc.setBounds(170, 85, 145, 116);
		frmViewTeamScreen.getContentPane().add(txtrMonster2Desc);
		monsterDescriptions.add(txtrMonster2Desc);
		
		JButton btnMonster3 = new JButton("Monster 3");
		btnMonster3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectedMonster(2);
			}
		});
		btnMonster3.setBounds(331, 51, 145, 23);
		frmViewTeamScreen.getContentPane().add(btnMonster3);
		monsterButtons.add(btnMonster3);
		
		JTextArea txtrMonster3Desc = new JTextArea();
		txtrMonster3Desc.setEditable(false);
		txtrMonster3Desc.setText("");
		txtrMonster3Desc.setBounds(331, 85, 145, 116);
		frmViewTeamScreen.getContentPane().add(txtrMonster3Desc);
		monsterDescriptions.add(txtrMonster3Desc);
		
		JButton btnSellMonster = new JButton("Sell Monster");
		btnSellMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team.getTeamSize() > 1) {
					manager.getGame().getPlayer().sellMonster(selectedMonster);
					setMonsterDesc();
					lblError.setText(selectedMonster.getName()+" has been sold for "+selectedMonster.getSellPrice()+" gold");
				} else {lblError.setText("You need at least 1 Monster");}
			}
		});
		btnSellMonster.setBounds(170, 279, 145, 23);
		frmViewTeamScreen.getContentPane().add(btnSellMonster);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.updateMainScreen();
				manager.closeScreen(frmViewTeamScreen);
			}
		});
		btnExit.setBounds(331, 279, 145, 23);
		frmViewTeamScreen.getContentPane().add(btnExit);
		
		btnSwap = new JButton("Swap Monster");
		btnSwap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapMonster = selectedMonster;
				lblError.setText("Select Monster to swap with");
			}
		});
		btnSwap.setBounds(10, 279, 145, 23);
		frmViewTeamScreen.getContentPane().add(btnSwap);
	}
	
	/**
	 * Sets the monster description to the selected monster
	 */
	public void setMonsterDesc() {
		for (int i=0; i < 3; i++) {
			if (i < team.getTeamSize()) {
				Monster monster = team.getMonster(i);
				String description = "Name: "+monster.getName();
				description += "\n\nHealth: "+monster.getHealth()+"/"+monster.getMaxHealth();
				description += "\nAgression: "+monster.getAgression();
				description += "\nAttack: "+monster.getDamage();
				description += "\nSell price: "+monster.getSellPrice()+" gold";
				monsterDescriptions.get(i).setText(description);
			} else {
				monsterDescriptions.get(i).setText("No monster");
				monsterButtons.get(i).setEnabled(false);
			}
		}
		if (team.getTeamSize() < 2) {
			btnSwap.setEnabled(false);
		}
	}
	
	/**
	 * Sets the selected monster to the given index
	 * @param i index of monster
	 */
	public void setSelectedMonster(int i) {
		lblError.setText("");
		if (i < team.getTeamSize()) {
			selectedMonster = team.getMonster(i);
			lblSelectedMonster.setText("Monster "+(i+1)+" selected");
			if (swapMonster != null) {
				team.swapPositions(team.getPlayerTeam().indexOf(swapMonster), i);
				lblError.setText(swapMonster.getName()+" swaped with "+team.getMonster(i).getName());
				swapMonster = null;
				setMonsterDesc();
			}
		}
	}
}
