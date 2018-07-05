package skilltracker;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import skillcalculator.FinalCalculatorGUI;
import skillcalculator.InvalidSaveException;
import skillcalculator.PlayerDataComparator;

import utils.Utility;

/**
 * This class handles building the user interface and handles the processing
 * of its operations.
 * 
 * @author Dylan Weaver
 *
 */
public class SkillTrackerGui {

	private JFrame frmClanSkillTracker = new JFrame("Clan Skill Tracker");
	
	private JLabel labelSelectSkill = new JLabel("Select Skill:");
	private JLabel labelEnterNames = new JLabel("Enter Names:");
	private JLabel labelExperience = new JLabel("Experience:");
	private JLabel labelLevels = new JLabel("Levels:");
	private JLabel labelRelativeRanking = new JLabel("Relative Rankings:");
	private JLabel labelErrors = new JLabel("Errors:");
	
	private JScrollPane scrollPanePlayers = new JScrollPane();
	private JScrollPane scrollPaneExperience = new JScrollPane();
	private JScrollPane scrollPaneLevels = new JScrollPane();
	private JScrollPane scrollPaneRelativeRanking = new JScrollPane();
	private JScrollPane scrollPaneErrors = new JScrollPane();
	
	private JTextArea textAreaPlayers = new JTextArea();
	private JTextArea textAreaExperience = new JTextArea();
	private JTextArea textAreaLevels = new JTextArea();
	private JTextArea textAreaRelativeRanking = new JTextArea();
	private JTextArea textAreaErrors = new JTextArea();
	
	private JButton buttonCalculate = new JButton("Calculate");
	private JButton buttonClearAll = new JButton("Clear All");
	private JButton buttonSave = new JButton("Save Data");
	private JButton buttonLoad = new JButton("Load Data");
	private JButton buttonInstructions = new JButton("Instructions");
	private JButton buttonResults = new JButton("Results");
	
	private JSeparator separatorErrors = new JSeparator();
	private JSeparator separatorSkills = new JSeparator();
	
	private JRadioButton rdButtonAttack = new JRadioButton("Attack");
	private JRadioButton rdButtonStrength = new JRadioButton("Strength");
	private JRadioButton rdButtonDefence = new JRadioButton("Defence");
	private JRadioButton rdButtonRanged = new JRadioButton("Ranged");
	private JRadioButton rdButtonPrayer = new JRadioButton("Prayer");
	private JRadioButton rdButtonMagic = new JRadioButton("Magic");
	private JRadioButton rdButtonConstruction = new JRadioButton("Construction");
	private JRadioButton rdButtonRunecrafting = new JRadioButton("Runecrafting");
	private JRadioButton rdButtonHitpoints = new JRadioButton("Hitpoints");
	private JRadioButton rdButtonAgility = new JRadioButton("Agility");
	private JRadioButton rdButtonHerblore = new JRadioButton("Herblore");
	private JRadioButton rdButtonThieving = new JRadioButton("Thieving");
	private JRadioButton rdButtonCrafting = new JRadioButton("Crafting");
	private JRadioButton rdButtonFletching = new JRadioButton("Fletching");
	private JRadioButton rdButtonSlayer = new JRadioButton("Slayer");
	private JRadioButton rdButtonHunter = new JRadioButton("Hunter");
	private JRadioButton rdButtonMining = new JRadioButton("Mining");
	private JRadioButton rdButtonSmithing = new JRadioButton("Smithing");
	private JRadioButton rdButtonFishing = new JRadioButton("Fishing");
	private JRadioButton rdButtonCooking = new JRadioButton("Cooking");
	private JRadioButton rdButtonFiremaking = new JRadioButton("Firemaking");
	private JRadioButton rdButtonWoodcutting = new JRadioButton("Woodcutting");
	private JRadioButton rdButtonFarming = new JRadioButton("Farming");
	private JRadioButton rdButtonTotal = new JRadioButton("Total");
	
	private ButtonGroup skillButtonGroup = new ButtonGroup();
	
	static int skillNumber = -1; // The current skill id that is selected
	private static int verifySkillNumber = -1; // Used to make sure the correct skill is saved with the correct data

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Make it look pretty
					try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (Exception e) {
						
					}					
					SkillTrackerGui window = new SkillTrackerGui();
					window.frmClanSkillTracker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application
	 */
	public SkillTrackerGui() {
		initialize(); // Initializes each component
	}

	/**
	 * Initialize the contents of the frame and creates necessary action handlers
	 */
	private void initialize() {
		frmClanSkillTracker.setTitle("Clan Toolkit");
		frmClanSkillTracker.setIconImage(Toolkit.getDefaultToolkit().getImage(SkillTrackerGui.class.getResource("/media/overall.gif")));
		frmClanSkillTracker.setResizable(false);
		frmClanSkillTracker.setBounds(100, 100, 907, 739);
		frmClanSkillTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClanSkillTracker.getContentPane().setLayout(null);
		
		// List of all the components, makes adding components to the frame easier.
		JComponent[] listOfComponents = new JComponent[] { labelSelectSkill, labelExperience, labelLevels,
				labelRelativeRanking, labelEnterNames, scrollPanePlayers, scrollPaneExperience, scrollPaneLevels, 
				scrollPaneErrors, scrollPaneRelativeRanking, textAreaPlayers, textAreaExperience, textAreaLevels, 
				textAreaRelativeRanking, textAreaErrors, labelErrors, buttonCalculate, buttonClearAll, buttonSave, 
				buttonLoad, buttonInstructions, buttonResults, separatorErrors, separatorSkills };
		
		// Go through each component and add it to the frame.
		for (JComponent component : listOfComponents) {
			if (component instanceof JLabel) {
				// If the component is a label, give it a bold font
				component.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			
			frmClanSkillTracker.getContentPane().add(component);
		}
		
		// Set the bounds for every component
		labelSelectSkill.setBounds(10, 11, 69, 14);
		labelEnterNames.setBounds(343, 12, 80, 14);
		labelExperience.setBounds(343, 184, 80, 14);
		labelLevels.setBounds(343, 356, 46, 14);
		labelRelativeRanking.setBounds(343, 528, 115, 14);
		labelErrors.setBounds(10, 324, 46, 14);
		
		scrollPanePlayers.setBounds(343, 32, 538, 141);
		scrollPaneExperience.setBounds(343, 204, 538, 141);
		scrollPaneLevels.setBounds(343, 376, 538, 141);
		scrollPaneRelativeRanking.setBounds(343, 548, 538, 141);
		scrollPaneErrors.setBounds(10, 344, 323, 205);
		
		separatorSkills.setBounds(10, 287, 323, 2);
		separatorErrors.setBounds(10, 583, 323, 2);
		
		buttonCalculate.setBounds(10, 633, 89, 23);
		buttonClearAll.setBounds(244, 633, 89, 23);
		buttonSave.setBounds(127, 633, 89, 23);
		buttonLoad.setBounds(127, 667, 89, 23);
		buttonInstructions.setBounds(244, 667, 89, 23);
		buttonResults.setBounds(10, 667, 89, 23);

		// Handles settings for the textAreas and scrollPanes
		textAreaPlayers.setLineWrap(true);
		textAreaPlayers.setWrapStyleWord(true);
		scrollPanePlayers.setViewportView(textAreaPlayers);
		
		textAreaExperience.setLineWrap(true);
		textAreaExperience.setWrapStyleWord(true);
		textAreaExperience.setEditable(false);
		scrollPaneExperience.setViewportView(textAreaExperience);

		textAreaLevels.setLineWrap(true);
		textAreaLevels.setWrapStyleWord(true);
		textAreaLevels.setEditable(false);
		scrollPaneLevels.setViewportView(textAreaLevels);
		
		textAreaRelativeRanking.setLineWrap(true);
		textAreaRelativeRanking.setWrapStyleWord(true);
		textAreaRelativeRanking.setEditable(false);
		scrollPaneRelativeRanking.setViewportView(textAreaRelativeRanking);

		scrollPaneErrors.setViewportView(textAreaErrors);
		textAreaErrors.setEditable(false);
		
		// Calculate actionListener
		buttonCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printResultsToScreen();
			}
		});
		
		// Clear All actionListener
		buttonClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clear every textArea, unselect any skill, reset skillNumbers
				Utility.clearAll(textAreaPlayers, textAreaExperience, textAreaLevels, 
						textAreaRelativeRanking, textAreaErrors);
				skillButtonGroup.clearSelection();
				skillNumber = -1;
				verifySkillNumber = -1;
			}
		});
		
		// Save actionListener
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					save(); // Save if you can!
				} catch (InvalidSaveException e) {
					// Otherwise give the user some feedback on why saving failed
					String errorMessage = "Can't save. Possible causes of this are:"
							+ "\n- No skill selected\n- 'Names' textbox empty\n- Haven't calculated player data";
					JOptionPane.showMessageDialog(frmClanSkillTracker, errorMessage,
			    			"Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		// Load actionListener
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load(); // Load the skill and name data
			}
		});
		
		// Instructions actionListener
		buttonInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String instructionMessage = "To begin, enter a list of names that you would like to track"
						+ " into the 'Enter Names:' box.\nThen, select a skill that you would like to collect"
						+ " data for. Then click calculate.\n\nSave this text file, and label it as either the"
						+ " beginning or ending of the tracking.\nIf it was the beginning, then run this program"
						+ " again after the event is over to get the\nending data, following the above instructions."
						+ "\n\nWhen you are ready to calculate the final results and see who gained the most experience,"
						+ "\nclick the 'Results' box and follow the instructions by clicking on the 'Instructions' button.";
				JOptionPane.showMessageDialog(frmClanSkillTracker, instructionMessage,
		    			"Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// Calculate Results actionListener
		buttonResults.addActionListener(new ActionListener() {
			boolean alreadyOpen = false; // Only allow one results menu to be opened at once
			public void actionPerformed(ActionEvent arg0) {
				if (!alreadyOpen) {
					alreadyOpen = true;
					FinalCalculatorGUI finalCalculator = new FinalCalculatorGUI();
					finalCalculator.getFrame().setVisible(true);
					finalCalculator.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					// Checks for when the results menu is closed so alreadyOpen can be set to false
					finalCalculator.getFrame().addWindowListener(new WindowAdapter() {
						  @Override
						  public void windowClosed(WindowEvent e) {
						     alreadyOpen = false;
						  }
						});
				} else {
					JOptionPane.showMessageDialog(frmClanSkillTracker, "You already have a result menu open.", 
			    			"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// Handles all of the skill buttons.
		initializeSkillButtons();
	}
	
	/**
	 * Handles the skill buttons, their buttongroup and actionListeners
	 */
	private void initializeSkillButtons() {
		// The buttons are added one column at a time
		JRadioButton[] columnOne = new JRadioButton[] { rdButtonTotal, rdButtonAttack, rdButtonDefence, rdButtonStrength,
				rdButtonHitpoints, rdButtonRanged, rdButtonPrayer, rdButtonMagic };
		
		JRadioButton[] columnTwo = new JRadioButton[] { rdButtonCooking, rdButtonWoodcutting, rdButtonFletching, rdButtonFishing,
				rdButtonFiremaking, rdButtonCrafting, rdButtonSmithing, rdButtonMining };
		
		JRadioButton[] columnThree = new JRadioButton[] { rdButtonHerblore, rdButtonAgility, rdButtonThieving, rdButtonSlayer,
				rdButtonFarming, rdButtonRunecrafting, rdButtonHunter, rdButtonConstruction };

		//Adds the first column of skills to the frame, and also adds them to a button group and actionListener
		for (int i = 0; i < columnOne.length; i++) {
			frmClanSkillTracker.getContentPane().add(columnOne[i]);
			columnOne[i].setBounds(10, 32 + (26 * i), 100, 23);
			skillButtonGroup.add(columnOne[i]);
			columnOne[i].addActionListener(new SkillButtonActionListener(i));
		}
		
		//Adds the second column of skills to the frame, and also adds them to a button group and actionListener
		for (int i = 0; i < columnTwo.length; i++) {
			frmClanSkillTracker.getContentPane().add(columnTwo[i]);
			columnTwo[i].setBounds(112, 32 + (26 * i), 100, 23);
			skillButtonGroup.add(columnTwo[i]);
			columnTwo[i].addActionListener(new SkillButtonActionListener(i + columnOne.length));
		}
		
		//Adds the third column of skills to the frame, and also adds them to a button group and actionListener
		for (int i = 0; i < columnThree.length; i++) {
			frmClanSkillTracker.getContentPane().add(columnThree[i]);
			columnThree[i].setBounds(214, 32 + (26 * i), 100, 23);
			skillButtonGroup.add(columnThree[i]);
			columnThree[i].addActionListener(new SkillButtonActionListener(i + columnOne.length + columnTwo.length));
		}
	}
	
	/**
	 * Outputs each player's position, name, level, and experience to {@code textAreaResults}. 
	 * Also outputs any errors that occurred to {@code textAreaErrors}
	 */
	private void printResultsToScreen() {
		List<Player> players = new ArrayList<>(); // Will contain player's skill data
		List<Player> playersSorted = new ArrayList<>(); // Used to print relative ranks
		List<Player> playerErrors = new ArrayList<>(); // Contains any invalid player names
		
		// Only continue if there are names to process
		if(!textAreaPlayers.getText().isEmpty()) {
			// Check that a skill is even selected before doing any processing
			if (skillNumber == -1) {
				JOptionPane.showMessageDialog(frmClanSkillTracker, "You haven't selected a skill!", 
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// Used as a safety measure during saving to ensure the user doesn't save the wrong skill 
			// with the wrong data
			verifySkillNumber = skillNumber;
			// Clear the results and errors so that they don't stack up upon consecutive runs
			Utility.clearAll(textAreaExperience, textAreaLevels, textAreaRelativeRanking, textAreaErrors);
			String[] arrayOfPlayers = textAreaPlayers.getText().trim().replaceAll(",", ", ").split(", ");
			
			// Collect data from each player for the specified skill
			players = Calculations.runScraper(arrayOfPlayers, skillNumber); 
			
			if (players != null) {
				ListIterator<Player> playerIterator = players.listIterator(); // An iterator for the list of players
				while (playerIterator.hasNext()) {
					Player currPlayer = playerIterator.next(); // The current player
					
					// If the current player is invalid (bad name), add it to the error list and remove it
					if (!currPlayer.getValid()) {
						playerErrors.add(currPlayer);
						playerIterator.remove(); // Remove the invalid player
					} else {
						// If we're at the first element, just add the experience and level
						if (playerIterator.nextIndex() == 1) {
							textAreaExperience.append(Long.toString(currPlayer.getExperience()));
							textAreaLevels.append(Integer.toString(currPlayer.getLevel()));
						} else {
							// Add the (valid) player's experience and level data with a comma.
							textAreaExperience.append(", " + currPlayer.getExperience());
							textAreaLevels.append(", " + currPlayer.getLevel());	
						}
					}
				}
				
				// We need a sorted ranking for the relative ranking
				for (int i = 0; i < players.size(); i++) {
					playersSorted.add(players.get(i));
				}
				// Sort players based on experience and level
				Collections.sort(playersSorted, new PlayerDataComparator().reversed());
				
				// Sorts and displays the relative rank of each player based on their experience and level
				ListIterator<Player> playerSortedIterator = playersSorted.listIterator(); // An iterator for the list of players
				while (playerSortedIterator.hasNext()) {
					Player currPlayer = playerSortedIterator.next(); // The current player
					
					// If the player is valid, collect their skill rank relative to the others
					if (currPlayer.getValid()) {
						int currentPlace = playerSortedIterator.nextIndex(); // The current number of players we've seen
						String currentSkill = Utility.skills[skillNumber]; // The skill we're collecting data for
						textAreaRelativeRanking.append(currentPlace + Utility.getPositionSuffix(currentPlace) + "\"" + 
								currPlayer.getName().trim()+ "\" " + currentSkill + " level: " + currPlayer.getLevel() + 
								" with " + NumberFormat.getNumberInstance().format(currPlayer.getExperience()) + " xp\n"); // The message to append to the text box
					}
				}
				
				// Handles printing the list of errors to the screen (if there are any)
				if (playerErrors.isEmpty()) {
					textAreaErrors.setText("None!");
				} else {
					// Go through the list of errors, and print out a specific response if it is known why the error
					// could have occurred, or a generic response otherwise
					for (int i = 0; i < playerErrors.size(); i++) {
						if (playerErrors.get(i).getName().length() > Utility.MAX_USERNAME_LENGTH) {
							textAreaErrors.append("\"" + playerErrors.get(i).getName().trim() + 
									"\"" + " ERROR! This name contains more than 12 characters.\n");
						} else {
							textAreaErrors.append("\"" + playerErrors.get(i).getName().trim() + "\"" + 
									" ERROR! Check for name change or spelling.\n");
						}
					}
				}
			}
			
			// If there were errors, let the user know
			if (!playerErrors.isEmpty()) {
				// Let the user that there was an error
				String errorMessage = "One or more errors occurred during data collection.\n"
									+ "Please fix these errors before using the results!";
				JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Saves the selected skill, list of players, list of experience, list of levels, and list of errors
	 * in a .txt file.
	 * 
	 * @throws InvalidSaveException when it's not safe to save the data to a file
	 */
	private void save() throws InvalidSaveException {
		// If it's not safe to save (text boxes empty, skill not selected, etc), throw an exception
    	if (!safeToSave()) {
    		throw new InvalidSaveException("Can't save.");
    	}
	    JFileChooser chooser = new JFileChooser();
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	    	// Create a File representation from the String path
		    File fileToSave = new File(Utility.addExtension(chooser.getSelectedFile().toString(), "txt"));
		    // If the file exists, confirm that the user would like to overwrite the existing file
		    if (fileToSave.exists()) {
		    	int response = JOptionPane.showConfirmDialog(frmClanSkillTracker, "This file already exists. Would you like to overwrite it?", 
		    			"Confirm", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		    	switch(response) {
		    	// If the user selectes 'yes', break from the switch statement and continue to writing the data to the file
		    	case JOptionPane.YES_OPTION:
		    		break;
		    	// If the user says 'no', bring up the save dialog again so they can choose a new path or filename
		    	case JOptionPane.NO_OPTION:
					save();
		    	default:
		    		return;
		    	}
		    }
		    // Write the data to the file
	        try (FileWriter fw = new FileWriter(Utility.addExtension(chooser.getSelectedFile().toString(), "txt"))) {
	        	Date date = new Date();
	        	fw.write("Data for: " + Utility.skills[verifySkillNumber] + " (" + String.valueOf(verifySkillNumber) + ")");
	        	fw.write("\nCurrent as of: " + date);
	        	
	        	fw.write("\n\nPLAYER USERNAME DATA:\n");
	        	fw.write(Utility.newLineGenerator(textAreaPlayers.getText()));
	        	
	        	fw.write("\nPLAYER EXPERIENCE DATA:\n");
	        	fw.write(Utility.newLineGenerator(textAreaExperience.getText()));
	        	
	        	fw.write("\nPLAYER LEVEL DATA:\n");
	            fw.write(Utility.newLineGenerator(textAreaLevels.getText()));
	            
	            fw.write("\nPLAYER SKILL DATA:\n");
	            fw.write(textAreaRelativeRanking.getText());
	            
	            fw.write("\nPLAYER ERROR DATA:\n");
	            fw.write(textAreaErrors.getText());
	        } catch (Exception e) {
	            e.printStackTrace();
				JOptionPane.showMessageDialog(frmClanSkillTracker, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	/**
	 * Loads a list of user data into the appropriate JTextAreas
	 * 
	 * @param box [0] Should ALWAYS be textAreaPlayers
	 * 			  [1] should ALWAYS be textAreaExperience
	 * 			  [2] should ALWAYS be textAreaLevels
	 */
	private void load() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Load");
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()));
    			try {
    				// If the file is valid, clear the JTextArea being used to write the data to, and
    				// write the data to it.
    				if (Utility.getFileExtension(chooser.getSelectedFile()).equals("txt")) {
    					// Clear any previous text on the application
    	        		Utility.clearAll(textAreaPlayers, textAreaExperience, textAreaLevels, 
    	        				textAreaRelativeRanking, textAreaErrors);
	    				// Retrieves the skillNumber from in between the set of parentheses on the first line
	    				skillNumber = Integer.parseInt(Utility.getMatch(br.readLine(), "\\((\\d+)\\)"));
	    				setSkillButton(skillNumber);
    	        		
    	        		String currLine = null; // The current line being read in the file
    	        		while ((currLine = br.readLine()) != null) {
    	        			// Read in the username data
    	        			if (currLine.equals("PLAYER USERNAME DATA:")) {
    	        				currLine = br.readLine();
    	        				// Read data until we reach the next section
    	        				while (!currLine.equals("PLAYER EXPERIENCE DATA:")) {
    	        					textAreaPlayers.append(currLine.trim());
    	        					currLine = br.readLine();
    	        				}
    	        			} 
    	        			// Read in the experience data
    	        			if (currLine.equals("PLAYER EXPERIENCE DATA:")) {
    	        				// Read the next line, which is the data. Print that to the textArea
    	        				currLine = br.readLine();
    	        				// Read data until we reach the next section
    	        				while (!currLine.equals("PLAYER LEVEL DATA:")) {
    	        					textAreaExperience.append(currLine);
    	        					currLine = br.readLine();
    	        				}
    	        			}
    	        			// Read in the level data
    	        			if (currLine.equals("PLAYER LEVEL DATA:")) {
    	        				currLine = br.readLine();
    	        				// Read data until we reach the next section
    	        				while (!currLine.equals("PLAYER SKILL DATA:")) {
    	        					textAreaLevels.append(currLine);
    	        					currLine = br.readLine();
    	        				}
    	        			}
    	        			// Read in the relative rank data
    	        			if (currLine.equals("PLAYER SKILL DATA:")) {
    	        				// Read and append data until an empty line is reached. That empty line
    	        				// if the end of the skill data
    	        				while (!currLine.equals("")) {
        	        				currLine = br.readLine();
    	        					textAreaRelativeRanking.append(currLine + "\n");	
    	        				}
    	        			}
    	        			// Read in the error data
    	        			if (currLine.equals("PLAYER ERROR DATA:")) {
	        					currLine = br.readLine();
	        					// Read and print the errors until the end of the file is reached
    	        				while (currLine != null) {
    	        					textAreaErrors.append(currLine + "\n");	
    	        					currLine = br.readLine();
    	        				}
    	        			}
    	        			
    	        		}
    	        		br.close();
    				}
    				// Throw an exception if the selected file does not have a .txt extension.
    				else {
    					br.close();
    					throw new FileFormatException("Invalid file type!\nThis program currently only supports .txt files.");
    				}
    			} catch (FileFormatException e) {
    				e.printStackTrace();
    				JOptionPane.showMessageDialog(frmClanSkillTracker, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    			}
	        } catch (Exception e) {
	            e.printStackTrace();
				JOptionPane.showMessageDialog(frmClanSkillTracker, "Error loading file.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	/**
	 * Used during the loading of a file. The file contains a skill number based on which skill was being used
	 * for calculations in that file, and when it is loaded, the button for that skill needs to be selected.
	 * 
	 * @param skillNumber The number corresponding to the skill (found in save files)
	 */
	private void setSkillButton(int skillNumber) {
		JRadioButton[] skillButton = { rdButtonTotal, rdButtonAttack, rdButtonDefence, rdButtonStrength,
				rdButtonHitpoints, rdButtonRanged, rdButtonPrayer, rdButtonMagic, rdButtonCooking, 
				rdButtonWoodcutting, rdButtonFletching, rdButtonFishing, rdButtonFiremaking, rdButtonCrafting, 
				rdButtonSmithing, rdButtonMining, rdButtonHerblore, rdButtonAgility, rdButtonThieving, rdButtonSlayer,
				rdButtonFarming, rdButtonRunecrafting, rdButtonHunter, rdButtonConstruction };
		
		skillButton[skillNumber].setSelected(true);
	}
	
	/**
	 * Determines if the the set options are valid to allow a file to be saved
	 * 
	 * @return true if the settings are such that it is valid to save the file, else false
	 */
	private boolean safeToSave() {
		if (skillNumber == -1 || verifySkillNumber == -1) {
			return false;
		} else if (textAreaPlayers.getText().isEmpty() || textAreaExperience.getText().isEmpty() || 
				   textAreaLevels.getText().isEmpty() || textAreaRelativeRanking.getText().isEmpty()) {
			return false;
		}
		return true;
	}
}
