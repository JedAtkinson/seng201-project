package gui;

import main.*;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;

import java.awt.FlowLayout;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
/**
 * creates the screen for selecting the first monster
 * @author Angus Burrowes and Jed Atkinson
 *
 */

public class StartingMonsterSelectScreen {

	private JFrame frmStarterMonsterSelect;
	private JTextField textFieldMonsterName;
	private JLabel lblEnterNameErr;
	private String[] monsterTypeName = {"Giant", "Hydra", "Vampire", "WereWolf", "Zombie"};
	private Monster[] monsterTypes = {new GiantMonster(), new HydraMonster(), new Vampire(), new WereWolfMonster(), new ZombieMonster()};
	private boolean validName = true;
	private GUIManager manager;

	/**
	 * Create the application.
	 * @param guiManager gui driver
	 */
	public StartingMonsterSelectScreen(GUIManager guiManager) {
		manager = guiManager;
		initialize();
		frmStarterMonsterSelect.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStarterMonsterSelect = new JFrame();
		frmStarterMonsterSelect.setTitle("Starter Monster Select Screen");
		frmStarterMonsterSelect.setBounds(100, 100, 700, 300);
		frmStarterMonsterSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStarterMonsterSelect.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Create your first Monster");
		lblTitle.setBounds(132, 11, 200, 14);
		frmStarterMonsterSelect.getContentPane().add(lblTitle);
		
		JTextArea txtrMonsterTypeDescription = new JTextArea("Defult "+monsterTypes[0].toString());
		txtrMonsterTypeDescription.setWrapStyleWord(true);
		txtrMonsterTypeDescription.setLineWrap(true);
		txtrMonsterTypeDescription.setEditable(false);
		txtrMonsterTypeDescription.setBounds(396, 11, 280, 241);
		frmStarterMonsterSelect.getContentPane().add(txtrMonsterTypeDescription);
		
		JLabel lblEnterName = new JLabel("Choose Monsters name");
		lblEnterName.setBounds(10, 63, 170, 14);
		frmStarterMonsterSelect.getContentPane().add(lblEnterName);
		
		textFieldMonsterName = new JTextField();
		textFieldMonsterName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkCanContinue();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkCanContinue();
			}
		});
		textFieldMonsterName.setBounds(200, 60, 170, 20);
		frmStarterMonsterSelect.getContentPane().add(textFieldMonsterName);
		textFieldMonsterName.setColumns(10);
		
		lblEnterNameErr = new JLabel("Leave empty for defult name");
		lblEnterNameErr.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEnterNameErr.setBounds(200, 91, 170, 14);
		frmStarterMonsterSelect.getContentPane().add(lblEnterNameErr);
		
		JLabel lblSelectMonsterType = new JLabel("Select a Monster type");
		lblSelectMonsterType.setBounds(10, 137, 170, 14);
		frmStarterMonsterSelect.getContentPane().add(lblSelectMonsterType);
		
		JComboBox<String> comboBoxMonsterType = new JComboBox<String>(monsterTypeName);
		comboBoxMonsterType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Monster monster = monsterTypes[comboBoxMonsterType.getSelectedIndex()];
				txtrMonsterTypeDescription.setText("Defult "+monster.toString());
			}
		});
		comboBoxMonsterType.setBounds(200, 133, 170, 22);
		frmStarterMonsterSelect.getContentPane().add(comboBoxMonsterType);
		
		JButton btnAddMonster = new JButton("Add Monster to team");
		btnAddMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validName) {
					Monster monster = monsterTypes[comboBoxMonsterType.getSelectedIndex()];
					if (textFieldMonsterName.getText().length() != 0) {
						monster.setName(textFieldMonsterName.getText());
					}
					manager.getGame().getPlayer().addToTeam(monster);
					manager.launchMainScreen();
					frmStarterMonsterSelect.dispose();
				} else {lblEnterNameErr.setText("Invalid Name");}
			}
		});
		btnAddMonster.setBounds(85, 229, 220, 23);
		frmStarterMonsterSelect.getContentPane().add(btnAddMonster);
	}
	
	/**
	 * Checks if text field input is valid
	 */
	private void checkCanContinue() {
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		if (p.matcher(textFieldMonsterName.getText()).find()) {
			lblEnterNameErr.setText("Name can not contain special charcters");
			validName = false;
		} else {
			int len = textFieldMonsterName.getText().length();
			if (len > 0 && len < 3 || len > 15) {
				lblEnterNameErr.setText("Name must be 3-15 characters long");
				validName = false;
			} else {
				lblEnterNameErr.setText("");
				validName = true;
			}
		}
	}
}
