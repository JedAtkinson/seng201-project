package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
/**
 * 
 * @author Angus Burrowes and Jed Atkinson
 * This class manages and launches the end screen
 *
 */
public class EndGameScreen {

	private JFrame frmEndGameScreen;
	
	private GUIManager manager;
	private JTextArea textArea;

/**
 * Create and launch the application.
 * @param guiManager  interfaces with game class and launches other screens 
 */
	public EndGameScreen(GUIManager guiManager) {
		manager = guiManager;
		
		initialize();
		frmEndGameScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEndGameScreen = new JFrame();
		frmEndGameScreen.setTitle("End Game Screen");
		frmEndGameScreen.setBounds(100, 100, 450, 300);
		frmEndGameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEndGameScreen.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Congratulations "+manager.getGame().getPlayer().getPlayerName()+" you have finished the game");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 416, 14);
		frmEndGameScreen.getContentPane().add(lblTitle);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEndGameScreen.dispose();
			}
		});
		btnExit.setBounds(175, 229, 89, 23);
		frmEndGameScreen.getContentPane().add(btnExit);
		
		textArea = new JTextArea(manager.getGame().getGameStats());
		textArea.setEditable(false);
		textArea.setBounds(10, 36, 416, 157);
		frmEndGameScreen.getContentPane().add(textArea);
	}

}
