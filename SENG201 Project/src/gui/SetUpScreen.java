package gui;

import main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
/**
 * screen for player to set up at start of game
 * @author Angus Burrowes and Jed Atkinson
 *
 */
public class SetUpScreen {

	private JFrame frmSetupScreen;
	private JTextField textFieldNameInput;
	private JLabel lblNameInputMessage;
	private boolean validName;
	GUIManager manager;
	
	private String easyDiffDescription = "Get 20 starting gold and easy Enemies";
	private String expertDiffDescription = "Get no starting gold and difficult enemies";

	/**
	 * Create and launch the application.
	 * @param guiManager GUI driver
	 */
	public SetUpScreen(GUIManager guiManager) {
		manager = guiManager;
		initialize();
		frmSetupScreen.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSetupScreen = new JFrame();
		frmSetupScreen.setTitle("Setup Screen");
		frmSetupScreen.setBounds(100, 100, 450, 300);
		frmSetupScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSetupScreen.getContentPane().setLayout(null);
		
		JLabel lblNameInput = new JLabel("What is your name?");
		lblNameInput.setBounds(10, 26, 178, 14);
		frmSetupScreen.getContentPane().add(lblNameInput);
		
		textFieldNameInput = new JTextField();
		textFieldNameInput.getDocument().addDocumentListener(new DocumentListener() {
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
		textFieldNameInput.setBounds(226, 26, 200, 20);
		frmSetupScreen.getContentPane().add(textFieldNameInput);
		textFieldNameInput.setColumns(10);
		
		lblNameInputMessage = new JLabel("");
		lblNameInputMessage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNameInputMessage.setBounds(226, 57, 200, 14);
		frmSetupScreen.getContentPane().add(lblNameInputMessage);
		
		JLabel lblSelectDays = new JLabel("Select number of days");
		lblSelectDays.setBounds(10, 96, 178, 14);
		frmSetupScreen.getContentPane().add(lblSelectDays);
		
		JSlider sliderSelectDays = new JSlider();
		sliderSelectDays.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sliderSelectDays.setMajorTickSpacing(5);
		sliderSelectDays.setMinorTickSpacing(1);
		sliderSelectDays.setPaintTicks(true);
		sliderSelectDays.setPaintLabels(true);
		sliderSelectDays.setValue(5);
		sliderSelectDays.setMaximum(15);
		sliderSelectDays.setMinimum(5);
		sliderSelectDays.setBounds(226, 82, 200, 44);
		frmSetupScreen.getContentPane().add(sliderSelectDays);
		
		JLabel lblSelectDifficulty = new JLabel("Select difficulty");
		lblSelectDifficulty.setBounds(10, 159, 178, 14);
		frmSetupScreen.getContentPane().add(lblSelectDifficulty);
		
		JLabel lblDiffMessage = new JLabel(easyDiffDescription);
		lblDiffMessage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDiffMessage.setBounds(226, 185, 200, 14);
		frmSetupScreen.getContentPane().add(lblDiffMessage);
		
		JRadioButton rdbtnEasyDiff = new JRadioButton("Easy");
		rdbtnEasyDiff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDiffMessage.setText(easyDiffDescription);
			}
		});
		rdbtnEasyDiff.setSelected(true);
		rdbtnEasyDiff.setBounds(226, 155, 90, 23);
		frmSetupScreen.getContentPane().add(rdbtnEasyDiff);
		
		JRadioButton rdbtnDiffExpert = new JRadioButton("Expert");
		rdbtnDiffExpert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDiffMessage.setText(expertDiffDescription);
			}
		});
		rdbtnDiffExpert.setBounds(336, 155, 90, 23);
		frmSetupScreen.getContentPane().add(rdbtnDiffExpert);
		
		//Group the radio buttons.
	    ButtonGroup diffButtonGroup = new ButtonGroup();
	    diffButtonGroup.add(rdbtnEasyDiff);
	    diffButtonGroup.add(rdbtnDiffExpert);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validName) {
					int diff;
					if (rdbtnDiffExpert.isSelected()) {
						diff = 2;
					} else {diff = 1;}
					Player player = new Player(textFieldNameInput.getText());
					Game game = new Game(player, sliderSelectDays.getValue(), diff);
					manager.setGame(game);
					manager.launchStartingMonsterSelectScreen();
					frmSetupScreen.dispose();
				} else {lblNameInputMessage.setText("Invalid Name");}
			}
		});
		btnContinue.setBounds(120, 229, 150, 23);
		frmSetupScreen.getContentPane().add(btnContinue);
	}
	
	/**
	 * Checks if text field input is valid
	 */
	private void checkCanContinue() {
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		if (p.matcher(textFieldNameInput.getText()).find()) {
			lblNameInputMessage.setText("Name can not contain special charcters");
			validName = false;
		} else {
			if (textFieldNameInput.getText().length() < 3 || textFieldNameInput.getText().length() > 15) {
				lblNameInputMessage.setText("Name must be 3-15 characters long");
				validName = false;
			} else {
				lblNameInputMessage.setText("");
				validName = true;
			}
		}
	}
}
