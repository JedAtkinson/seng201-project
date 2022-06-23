package gui;

import javax.swing.JFrame;

import main.*;
/**
 * manages and store info relating to the GUI
 * @author angusburrowes
 *
 */
public class GUIManager {
	/**
	 * Game object containing the game
	 */
	private Game game;
	/**
	 * MainScreen object
	 */
	private MainScreen mainScreen;
	/**
	 * sets up the game initially
	 * @param g  game to be set up
	 */
	public void setGame(Game g) {
		game = g;
	}
	/**
	 * gets the game being played
	 * @return  instance of the game
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * closes the frame
	 * @param frame   frame to be closed
	 */
	public void closeScreen(JFrame frame) {
		frame.dispose();
		mainScreen.setEnabled(true);
	}
	
	/**
	 * Launches the SetUpScreen
	 */
	public void launchSetupScreen() {
		new SetUpScreen(this);
	}
	
	/**
	 * Launches the StartingMonsterSelectScreen
	 */
	public void launchStartingMonsterSelectScreen() {
		new StartingMonsterSelectScreen(this);
	}
	
	/**
	 * Launches the launchMainScreen
	 */
	public void launchMainScreen() {
		mainScreen = new MainScreen(this);
	}
	
	/**
	 * Updates the main screen
	 */
	public void updateMainScreen() {
		mainScreen.setVariables();
	}
	
	/**
	 * Launches the ViewTeamScreen
	 */
	public void launchViewTeamScreen() {
		mainScreen.setEnabled(false);
		new ViewTeamScreen(this);
	}
	
	/**
	 * Launches the ViewBattlesScreen
	 */
	public void launchViewBattlesScreen() {
		mainScreen.setEnabled(false);
		new ViewBattlesScreen(this);
	}
	
	/**
	 * Launches the BattleScreen with a given battle
	 * @param battle  which battle should be launched
	 */
	public void launchBattleScreen(Battle battle) {
		new BattleScreen(this, battle);
	}
	
	/**
	 * Launches the ViewBagScreen
	 */
	public void launchViewBagScreen() {
		mainScreen.setEnabled(false);
		new ViewBagScreen(this);
	}
	
	/**
	 * Launches the ShopScreen
	 */
	public void launchShopScreen() {
		mainScreen.setEnabled(false);
		new ShopScreen(this);
	}
	
	/**
	 * Launches the EndGameScreen
	 */
	public void launchEndGameScreen() {
		new EndGameScreen(this);
	}

	public static void main(String[] args) {
		GUIManager manager = new GUIManager();
		manager.launchSetupScreen();
	}
}
