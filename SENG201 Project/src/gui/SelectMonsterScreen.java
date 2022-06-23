package gui;

import main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
/**
 * screen to select a monster 
 * @author Angus Burrowes
 *
 */
public class SelectMonsterScreen {

	private JFrame frame;
	
	private Team team;
	private Item item;
	private Monster selectedMonster;
	
	/**
	 * Create the application.
	 * @param i  item to be used
	 * @param t team to be used on
	 */
	public SelectMonsterScreen(Item i, Team t) {
		team = t;
		item = i;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(10, 152, 266, 15);
		frame.getContentPane().add(lblError);
		
		List<String> monsterNames =  team.getPlayerTeam().stream().map(Monster::getName).collect(Collectors.toList());
		JList listMonsters = new JList(monsterNames.toArray());
		listMonsters.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listMonsters.getSelectedIndex() >= 0) {
					selectedMonster = team.getMonster(listMonsters.getSelectedIndex());
				}
			}
		});
		frame.getContentPane().setLayout(null);
		listMonsters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMonsters.setBounds(10, 11, 266, 129);
		frame.getContentPane().add(listMonsters);
		
		JButton btnUse = new JButton("Use item on selected monster");
		btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedMonster != null) {
					item.useItem(selectedMonster);
					frame.dispose();
				} else {
					lblError.setText("No monster selected");
				}
			}
		});
		btnUse.setBounds(12, 179, 264, 23);
		frame.getContentPane().add(btnUse);
	}
}
