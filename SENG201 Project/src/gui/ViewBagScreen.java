package gui;

import main.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;


/**
 * Gui screen to view bag
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public class ViewBagScreen {

	private JFrame frmViewBagScreen;
	private JTextArea textArea;
	private JList listItems;
	private JLabel lblGold;
	private JLabel lblMessage;
	
	private GUIManager manager;
	private Inventry inventory;
	private Item selectedItem;

	/**
	 * Create and launch the application.
	 * @param guiManager manager which manages gui
	 */
	public ViewBagScreen(GUIManager guiManager) {
		manager = guiManager;
		inventory = manager.getGame().getPlayer().getBackpack();
		
		initialize();
		setVariables();
		frmViewBagScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewBagScreen = new JFrame();
		frmViewBagScreen.setTitle("View Bag Screen");
		frmViewBagScreen.setBounds(100, 100, 450, 300);
		frmViewBagScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewBagScreen.getContentPane().setLayout(null);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(30, 204, 396, 14);
		frmViewBagScreen.getContentPane().add(lblMessage);
		
		JLabel lblTitle = new JLabel(manager.getGame().getPlayer().getPlayerName()+"'s Bag");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 228, 14);
		frmViewBagScreen.getContentPane().add(lblTitle);
		
		lblGold = new JLabel("Gold: ");
		lblGold.setHorizontalAlignment(SwingConstants.CENTER);
		lblGold.setBounds(285, 11, 141, 14);
		frmViewBagScreen.getContentPane().add(lblGold);
		
		JLabel lblItemsTitle = new JLabel("Items");
		lblItemsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsTitle.setBounds(10, 36, 198, 14);
		frmViewBagScreen.getContentPane().add(lblItemsTitle);
		
		//List of inventory items
		listItems = new JList( inventory.getInventry().toArray());
		listItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//Sets description to selected item
				if (listItems.getSelectedIndex() >= 0) {
					selectedItem = inventory.getItem(listItems.getSelectedIndex());
					textArea.setText(selectedItem.getDescription());
				}
			}
		});
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItems.setBounds(20, 61, 188, 132);
		frmViewBagScreen.getContentPane().add(listItems);
		
		JLabel lblItemDesc = new JLabel("Selected Item");
		lblItemDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemDesc.setBounds(285, 36, 141, 14);
		frmViewBagScreen.getContentPane().add(lblItemDesc);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(285, 57, 141, 136);
		frmViewBagScreen.getContentPane().add(textArea);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If an item is selected sell item
				if (selectedItem != null) { 
					inventory.sellItem(listItems.getSelectedIndex(), manager.getGame().getPlayer());
					lblMessage.setText(selectedItem.getName()+" sold");
					setVariables();
					selectedItem = null;
				} else {
					lblMessage.setText("No item Selected");
				}
			}
		});
		btnSell.setBounds(165, 229, 110, 23);
		frmViewBagScreen.getContentPane().add(btnSell);
		
		JButton btnUse = new JButton("Use Item");
		btnUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// If an item is selected opens SelectMonsterScreen and uses item on selected monster
				if (selectedItem != null) {
					new SelectMonsterScreen(inventory.getItem(listItems.getSelectedIndex()), manager.getGame().getPlayer().getTeam());
					lblMessage.setText(inventory.getItem(listItems.getSelectedIndex()).getName()+" Used");
					inventory.getInventry().remove(listItems.getSelectedIndex());
					setVariables();
					selectedItem = null;
				} else {
					lblMessage.setText("No item Selected");
				}
			}
		});
		btnUse.setBounds(10, 229, 110, 23);
		frmViewBagScreen.getContentPane().add(btnUse);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			//Updates MainScreen and close this
			public void actionPerformed(ActionEvent e) {
				manager.updateMainScreen();
				manager.closeScreen(frmViewBagScreen);
			}
		});
		btnExit.setBounds(316, 229, 110, 23);
		frmViewBagScreen.getContentPane().add(btnExit);
	}
	
	/**
	 * Updates the on screen elements to show the relevant variables
	 */
	public void setVariables() {
		lblGold.setText("Gold: "+manager.getGame().getPlayer().getGoldAmount()); //Set gold label
		listItems.setListData(inventory.getInventry().toArray());
	}
}
