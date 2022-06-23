package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
/**
 * creates the GUI for the mainScreen and manages it
 * @author Angus Burrowes and Jed Atkinson
 */
public class MainScreen {

	private JFrame frmMainScreen;
	private JLabel lblDayNum;
	private JLabel lblDaysRemaining;
	private JLabel lblGold;
	
	private GUIManager manager;

	/**
	 * Create and launch the application.
	 * @param guiManager which gui driver class to use
	 */
	public MainScreen(GUIManager guiManager) {
		manager = guiManager;
		initialize();
		setVariables();
		frmMainScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainScreen = new JFrame();
		frmMainScreen.setTitle("Main Screen");
		frmMainScreen.setBounds(100, 100, 450, 300);
		frmMainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainScreen.getContentPane().setLayout(null);
		
		lblDayNum = new JLabel("Day number: ");
		lblDayNum.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDayNum.setBounds(10, 11, 120, 25);
		frmMainScreen.getContentPane().add(lblDayNum);
		
		lblDaysRemaining = new JLabel("Days remaining: ");
		lblDaysRemaining.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDaysRemaining.setBounds(156, 11, 140, 25);
		frmMainScreen.getContentPane().add(lblDaysRemaining);
		
		lblGold = new JLabel("You have  gold");
		lblGold.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblGold.setBounds(306, 11, 120, 25);
		frmMainScreen.getContentPane().add(lblGold);
		
		JLabel lblEvent = new JLabel("");
		lblEvent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEvent.setBounds(10, 47, 416, 25);
		frmMainScreen.getContentPane().add(lblEvent);
		
		JButton btnShop = new JButton("Go to shop");
		btnShop.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchShopScreen();
			}
		});
		btnShop.setBounds(10, 90, 120, 23);
		frmMainScreen.getContentPane().add(btnShop);
		
		JButton btnBag = new JButton("View Bag");
		btnBag.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchViewBagScreen();
			}
		});
		btnBag.setBounds(156, 90, 120, 23);
		frmMainScreen.getContentPane().add(btnBag);
		
		JButton btnTeam = new JButton("View Team");
		btnTeam.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchViewTeamScreen();
			}
		});
		btnTeam.setBounds(306, 90, 120, 23);
		frmMainScreen.getContentPane().add(btnTeam);
		
		JButton btnSleep = new JButton("Go to sleep");
		btnSleep.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String event = manager.getGame().nextDay();
				if (event == null) { //Game has ended
					manager.launchEndGameScreen();
					frmMainScreen.dispose();
				} else {
					lblEvent.setText(event);
					setVariables();
				}
			}
		});
		btnSleep.setBounds(10, 161, 120, 23);
		frmMainScreen.getContentPane().add(btnSleep);
		
		JButton btnBattles = new JButton("View Battles");
		btnBattles.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBattles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchViewBattlesScreen();
			}
		});
		btnBattles.setBounds(156, 161, 120, 23);
		frmMainScreen.getContentPane().add(btnBattles);
		
		JButton btnEndGame = new JButton("End Game");
		btnEndGame.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchEndGameScreen();
				frmMainScreen.dispose();
			}
		});
		btnEndGame.setBounds(156, 229, 120, 23);
		frmMainScreen.getContentPane().add(btnEndGame);
	}
	
	/**
	 * Updates the on screen elements to show the relevant variables
	 */
	public void setVariables() {
		lblDayNum.setText("Day number: "+manager.getGame().getCurrentDay());
		lblDaysRemaining.setText("Days remaining: "+manager.getGame().getDaysRemaining());
		lblGold.setText("You have "+manager.getGame().getPlayer().getGoldAmount()+" gold");
	}
	/**
	 * enables or disables the screen depending on input
	 * @param bool  whether or not you want 
	 * the screen to be enabled
	 */
	
	public void setEnabled(boolean bool) {
		frmMainScreen.setEnabled(bool);
	}
}
