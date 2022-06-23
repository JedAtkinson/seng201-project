package gui;


import main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * gui screen for the shop
 * @author Angus Burrowes
 *
 */
public class ShopScreen {

	private JFrame frmShopScreen;
	private JLabel lblGold;
	private JPanel panelItems;
	private JPanel panelMonsters;
	
	private GUIManager manager;
	private Store store;

	/**
	 * Create and launch the application.
	 * @param  guiManager gui driver
	 */
	public ShopScreen(GUIManager guiManager) {
		manager = guiManager;
		store = manager.getGame().getStore();
		
		initialize();
		setVariables();
		frmShopScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShopScreen = new JFrame();
		frmShopScreen.setTitle("Shop Screen");
		frmShopScreen.setBounds(100, 100, 500, 350);
		frmShopScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopScreen.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Shop");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 206, 14);
		frmShopScreen.getContentPane().add(lblTitle);
		
		lblGold = new JLabel("You have 0 Gold");
		lblGold.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGold.setBounds(226, 11, 200, 14);
		frmShopScreen.getContentPane().add(lblGold);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 466, 236);
		frmShopScreen.getContentPane().add(tabbedPane);
		
		//Items tab
		panelItems = new JPanel();
		tabbedPane.addTab("Items", null, panelItems, null);
		panelItems.setLayout(null);
		
		JLabel lblErrorItem = new JLabel("");
		lblErrorItem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblErrorItem.setBounds(10, 149, 180, 14);
		panelItems.add(lblErrorItem);
		
		JTextArea textAreaItem = new JTextArea();
		textAreaItem.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaItem.setWrapStyleWord(true);
		textAreaItem.setLineWrap(true);
		textAreaItem.setEditable(false);
		textAreaItem.setBounds(221, 10, 230, 187);
		panelItems.add(textAreaItem);
		
		JList listItems = new JList(store.getItemNames().toArray());
		listItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listItems.getSelectedIndex() >= 0) {
					Purchaseable selectedItem = store.getItem(listItems.getSelectedIndex());
					textAreaItem.setText(selectedItem.getDescription());
				}
			}
		});
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItems.setBounds(10, 10, 180, 128);
		panelItems.add(listItems);
		
		JButton btnBuyItem = new JButton("Buy Item");
		btnBuyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listItems.getSelectedIndex() >= 0) {
					Item selectedItem = (Item) store.getItem(listItems.getSelectedIndex());
					if (manager.getGame().getPlayer().buyItem(selectedItem)) {
						lblErrorItem.setText(selectedItem.getName()+" Purchased");
					} else {
						lblErrorItem.setText("You do not have enough gold");
					}
					setVariables();
				} else {
					lblErrorItem.setText("No item selected");
				}
			}
		});
		btnBuyItem.setBounds(10, 174, 180, 23);
		panelItems.add(btnBuyItem);
		
		
		//Monsters tab
		panelMonsters = new JPanel();
		tabbedPane.addTab("Monsters", null, panelMonsters, null);
		panelMonsters.setLayout(null);
		
		JLabel lblErrorMonster = new JLabel("");
		lblErrorMonster.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblErrorMonster.setBounds(10, 149, 180, 14);
		panelMonsters.add(lblErrorMonster);
		
		JTextArea textAreaMonster = new JTextArea();
		textAreaMonster.setFont(new Font("Monospaced", Font.PLAIN, 10));
		textAreaMonster.setWrapStyleWord(true);
		textAreaMonster.setLineWrap(true);
		textAreaMonster.setEditable(false);
		textAreaMonster.setBounds(221, 10, 230, 187);
		panelMonsters.add(textAreaMonster);
		
		JList<?> listMonsters = new JList<Object>(store.getMonsterNames().toArray());
		listMonsters.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listMonsters.getSelectedIndex() >= 0) {
					Monster selectedItem = (Monster) store.getMonster(listMonsters.getSelectedIndex());
					textAreaMonster.setText(selectedItem.toString());
				}
			}
		});
		listMonsters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMonsters.setBounds(10, 10, 180, 128);
		panelMonsters.add(listMonsters);
		
		JButton btnBuyMonster = new JButton("Buy Monster");
		btnBuyMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listMonsters.getSelectedIndex() >= 0) {
					Monster selectedItem = (Monster) store.getMonster(listMonsters.getSelectedIndex());
					if (manager.getGame().getPlayer().buyMonster(selectedItem)) {
						if (manager.getGame().getPlayer().getTeam().getTeamSize() < 3) {
							lblErrorMonster.setText(selectedItem.getName()+" Purchasesed");
						} else {
							lblErrorMonster.setText("Your team is already full");
						}
					} else {
						lblErrorMonster.setText("You do not have enough gold");
					}
					setVariables();
				} else {
					lblErrorMonster.setText("No Monster selected");
				}
			}
		});
		btnBuyMonster.setBounds(10, 174, 180, 23);
		panelMonsters.add(btnBuyMonster);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.closeScreen(frmShopScreen);
			}
		});
		btnExit.setBounds(356, 283, 120, 23);
		frmShopScreen.getContentPane().add(btnExit);
	}
	
	/**
	 * Updates the on screen elements to show the relevant variables
	 */
	public void setVariables() {
		lblGold.setText("You have "+manager.getGame().getPlayer().getGoldAmount()+" Gold");
	}
}
