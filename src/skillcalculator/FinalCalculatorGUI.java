package skillcalculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import skilltracker.FileFormatException;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import utils.Utility;
import utils.Utility.DataSelector;

/**
 * The GUI for the results menu
 * 
 * @author Dylan
 */
public class FinalCalculatorGUI {

	private JFrame frmCalculateResults;
	
	JLabel labelBeforeData = new JLabel("Before Data:");
	JLabel labelAfterData = new JLabel("After Data:");
	JLabel labelExperienceWinners = new JLabel("Experience Winners:");
	JLabel labelLevelWinners = new JLabel("Level Winners:");
	JLabel labelPlayersBefore = new JLabel("Player Count:");
	JLabel labelPlayersAfter = new JLabel("Player Count:");
	JLabel labelSkillBefore = new JLabel("Skill:");
	JLabel labelSkillAfter = new JLabel("Skill:");
	JLabel labelSkillNameBefore = new JLabel("None");
	JLabel labelSkillNameAfter = new JLabel("None");
	JLabel labelPlayerCountBefore = new JLabel("0");
	JLabel labelPlayerCountAfter = new JLabel("0");
	
	JButton buttonLoadBefore = new JButton("Load Data");
	JButton buttonLoadAfter = new JButton("Load Data");
	JButton buttonCalculateWinner = new JButton("Calculate Winners");
	JButton buttonInstructions = new JButton("Instructions");
	
	JScrollPane scrollPaneExperienceWinners = new JScrollPane();
	JScrollPane scrollPaneLevelWinners = new JScrollPane();
	
	JTextArea textAreaExperienceWinners = new JTextArea();
	JTextArea textAreaLevelWinners = new JTextArea();

	private List<PlayerResult> playerDataBefore = new ArrayList<>(); // Stores data from the initial file
	private List<PlayerResult> playerDataAfter = new ArrayList<>(); // Stores data from the final file

	/**
	 * Launch the application.
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
					FinalCalculatorGUI window = new FinalCalculatorGUI();
					window.frmCalculateResults.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FinalCalculatorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalculateResults = new JFrame();
		frmCalculateResults.setIconImage(Toolkit.getDefaultToolkit().getImage(FinalCalculatorGUI.class.getResource("/media/overall.gif")));
		frmCalculateResults.setTitle("Calculate Results");
		frmCalculateResults.setResizable(false);
		frmCalculateResults.setBounds(100, 100, 425, 416);
		frmCalculateResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculateResults.getContentPane().setLayout(null);
		
		// A list of all the JComponents used for the result menu
		JComponent[] listOfComponents = new JComponent[] { labelBeforeData, buttonLoadBefore, buttonInstructions,
				labelPlayersBefore, labelAfterData, buttonLoadAfter, labelPlayersAfter, labelSkillBefore,
				labelSkillAfter, scrollPaneExperienceWinners, labelExperienceWinners, scrollPaneLevelWinners,
				textAreaExperienceWinners, labelLevelWinners, labelSkillNameBefore, labelPlayerCountBefore,
				labelPlayerCountAfter, labelSkillNameAfter, buttonCalculateWinner, textAreaLevelWinners };
		
		// Add the components to the frame
		for (JComponent components : listOfComponents) {
			frmCalculateResults.getContentPane().add(components);
		}
		
		// Set the bounds for the various components
		labelBeforeData.setBounds(10, 11, 81, 14);
		labelAfterData.setBounds(206, 11, 69, 14);
		labelExperienceWinners.setBounds(10, 86, 122, 14);
		labelLevelWinners.setBounds(10, 220, 89, 14);
		labelPlayersBefore.setBounds(10, 36, 75, 14);
		labelPlayersAfter.setBounds(200, 36, 75, 14);
		labelSkillBefore.setBounds(57, 61, 28, 14);
		labelSkillAfter.setBounds(248, 61, 28, 14);
		labelSkillNameBefore.setBounds(95, 61, 95, 14);
		labelSkillNameAfter.setBounds(285, 61, 95, 14);
		labelPlayerCountBefore.setBounds(95, 36, 95, 14);
		labelPlayerCountAfter.setBounds(285, 36, 95, 14);
		
		buttonLoadBefore.setBounds(95, 8, 89, 23);
		buttonLoadAfter.setBounds(285, 8, 89, 23);
		buttonCalculateWinner.setBounds(10, 353, 119, 23);
		buttonInstructions.setBounds(139, 353, 89, 23);
		
		scrollPaneExperienceWinners.setBounds(10, 111, 400, 98);
		scrollPaneLevelWinners.setBounds(10, 245, 400, 98);
		
		// Set the font settings for the various labels
		labelBeforeData.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelPlayersBefore.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelAfterData.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelPlayersAfter.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSkillBefore.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSkillAfter.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelExperienceWinners.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelLevelWinners.setFont(new Font("Tahoma", Font.BOLD, 12));

		// Set the miscellaneous settings for components
		scrollPaneExperienceWinners.setViewportView(textAreaExperienceWinners);
		scrollPaneLevelWinners.setViewportView(textAreaLevelWinners);
		textAreaExperienceWinners.setEditable(false);
		textAreaLevelWinners.setEditable(false);
		
		// Action Listener for the loadBefore button
		buttonLoadBefore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerDataBefore.clear();
				load(Utility.DataSelector.INITIAL);
			}
		});
		
		// Action Listener for the loadAfter button
		buttonLoadAfter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerDataAfter.clear();
				load(Utility.DataSelector.FINAL);
			}
		});
		
		// Action Listener for the calculate button
		buttonCalculateWinner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateWinner();
			}
		});
		
		// Action Listener for the instructions button
		buttonInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String instructionMessage = "Simply load a file of data from the beginning of the event and one from the end of the event"
						+ "\nand click calculate.\n\nIf the files are incompatible, which can be caused by the player lists within"
						+ " the files \nbeing different, or the two files containing data for different skills, then you will be alerted."
						+ "\n\nOtherwise, the results will be automatically added to the two text boxes.";
				JOptionPane.showMessageDialog(frmCalculateResults, instructionMessage, 
		    			"Instructions", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	/**
	 * Loads player data into arraylists to be processed
	 * @param data Indicates if the data being loaded is initial data or final data
	 */
	private void load(DataSelector data) {
		List<String> playerUsernames = new ArrayList<>();
		List<Long> playerExperience = new ArrayList<>();
		List<Integer> playerLevels = new ArrayList<>();
		
		String initialSkill = null;
		String finalSkill = null;
		
		JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Load");
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	// Create a new FileReader wrapped in a BufferedReader that will read the selected file.
	        	try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
	    			try {
	    				// If the file is valid, clear the JTextArea being used to write the data to, and
	    				// write the data to it.
	    				if (Utility.getFileExtension(chooser.getSelectedFile()).equals("txt")) {
	    					// Set the initial skill
	    					if (data == DataSelector.INITIAL) {
	    						initialSkill = br.readLine();
	    					} else {
	    						// Set the final skill
	    						finalSkill = br.readLine();
	    					}
	    	        		
	    	        		String line = null;
	    	        		while ((line = br.readLine()) != null) {
	    	        			// Loads the username data into playerUsernames
		    	        		if (line.equals("PLAYER USERNAME DATA:")) {
		    	        			while ((line = br.readLine()) != null) {
		    	        				if (!line.equals("PLAYER EXPERIENCE DATA:")) {
		    	        					playerUsernames.addAll(Arrays.asList(line.replaceAll(", ", ",").split(",")));
		    	        				} else {
					    	        		break;
		    	        				}
		    	        			}
		    	        		}
		    	        		
	    	        			// Loads the experience data into playerExperience
		    	        		if (line.equals("PLAYER EXPERIENCE DATA:")) {
    	        					while ((line = br.readLine()) != null) {
		    	        				if (!line.equals("PLAYER LEVEL DATA:") && !line.equals("")) {
		    	        					List<String> playerExperienceString = Arrays.asList(line.replaceAll(", ", ",").split(","));
		    	        					for (int i = 0; i < playerExperienceString.size(); i++) {
		    	        						playerExperience.add(Long.parseLong(playerExperienceString.get(i)));
		    	        					}
		    	        				} else {
		    	        					break;
		    	        				}
    	        					}
		    	        		}
		    	        		
	    	        			// Loads the level data into playerLevels
		    	        		if (line.equals("PLAYER LEVEL DATA:")) {
	    	        					while ((line = br.readLine()) != null) {
			    	        				if (!line.equals("PLAYER SKILL DATA:") && !line.equals("")) {
			    	        					List<String> playerLevelsString = Arrays.asList(line.replaceAll(", ", ",").split(","));
			    	        					for (int i = 0; i < playerLevelsString.size(); i++) {
			    	        						playerLevels.add(Integer.parseInt(playerLevelsString.get(i)));
			    	        					}
			    	        				} else {
			    	        					break;
			    	        				}
	    	        					}
	    	        				}
	    	        			}
	    	        		// Ensure the file has equal numbers of players, experience, and levels. Otherwise, we can't load properly
	    	        		if (Utility.areSameLength(playerUsernames.toArray(), playerExperience.toArray(), playerLevels.toArray())) {
	    	        			for (int i = 0; i < playerUsernames.size(); i++) {
	    	        				// Set the correct labels for initial data versus final data
	    	    					if (data == DataSelector.INITIAL) {
	    	    						// Get the initial skill from the utility array and set the text to match
	    		    					String skillBefore = Utility.skills[Integer.parseInt(Utility.getMatch(initialSkill, "\\((\\d+)\\)"))];
	    		    					labelSkillNameBefore.setText(skillBefore);
	    	    						
	    		    					// Set the initial player count
	    		    	        		labelPlayerCountBefore.setText(String.valueOf(playerUsernames.size()));
	    	    						playerDataBefore.add(new PlayerResult(playerUsernames.get(i), playerExperience.get(i), playerLevels.get(i)));
	    	    					} else {
	    	    						// Get the final skill from the utility array and set the text to match
	    		    					String skillAfter = Utility.skills[Integer.parseInt(Utility.getMatch(finalSkill, "\\((\\d+)\\)"))];
	    		    					labelSkillNameAfter.setText(skillAfter);
	    		    					
	    		    					// Set the final player count
	    		    	        		labelPlayerCountAfter.setText(String.valueOf(playerUsernames.size()));
	    	    						playerDataAfter.add(new PlayerResult(playerUsernames.get(i), playerExperience.get(i), playerLevels.get(i)));
	    	    					}
	    	        			}
	    	        		} else {
	    	        			throw new LoadException("There is an issue loading this file!\nMake sure there are no errors with any players within the file!");
	    	        		}
	    				}
	    				// Throw an exception if the selected file does not have a .txt extension.
	    				else {
	    					throw new FileFormatException("Invalid file type!\nThis program currently only supports .txt files.");
	    				}
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    				JOptionPane.showMessageDialog(frmCalculateResults, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    			}
	    		}
	        } catch (Exception e) {
	            e.printStackTrace();
				JOptionPane.showMessageDialog(frmCalculateResults, "Error loading file.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	/**
	 * Calculates the winner by comparing the data between the two files
	 */
	private void calculateWinner() {
		List<PlayerResult> finalResults = new ArrayList<>();
		Utility.clearAll(textAreaExperienceWinners, textAreaLevelWinners); // Clear any previous winner calculations
		
		// If the two files don't contain data for the same skills, don't continue.
		if(!labelSkillNameBefore.getText().equals(labelSkillNameAfter.getText())) {
			JOptionPane.showMessageDialog(frmCalculateResults, "Error - The loaded files are for different skills.", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!playerDataBefore.isEmpty() && !playerDataAfter.isEmpty()) {
			// Check that the lists both have data for the same number of players
			if (playerDataBefore.size() == playerDataAfter.size()) {
				for (int i = 0; i < playerDataBefore.size(); i++) {
					// If the two files don't have the players in the same order, don't continue. 
					// This could lead to inaccurate results.
					if(!playerDataBefore.get(i).getUsername().equals(playerDataAfter.get(i).getUsername())) {
						JOptionPane.showMessageDialog(frmCalculateResults, 
								"Error - The loaded files have different lists of names.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					// Add the experience and level gains data to the final results for each player
					finalResults.add(new PlayerResult(playerDataBefore.get(i).getUsername(), 
							playerDataAfter.get(i).getExperienceGained() - playerDataBefore.get(i).getExperienceGained(),
							playerDataAfter.get(i).getLevelsGained() - playerDataBefore.get(i).getLevelsGained()));
				}
			} else {
				// The two files have different numbers of players
				JOptionPane.showMessageDialog(frmCalculateResults, "Error - The loaded files have different numbers of players.", 
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		// Finally, print the results
		printExperienceWinners(finalResults);
		printLevelWinners(finalResults);
	}
	
	/**
	 * Prints an ordered list of all player's experience gains
	 * @param finalResults The unordered list of results
	 */
	private void printExperienceWinners(List<PlayerResult> finalResults) {
		// Sort the players based on their experience gains (in descending order)
		Collections.sort(finalResults, new PlayerResultExperienceComparator().reversed());
		int positionExperience = 1; // Keeps track of current rank
		// Append the text to the experience textArea
		for (int i = 0; i < finalResults.size(); i++) {
			textAreaExperienceWinners.append(positionExperience + Utility.getPositionSuffix(positionExperience) +
					"\"" + finalResults.get(i).getUsername() + "\" with " + finalResults.get(i).getExperienceGained() + " experience.\n");
			positionExperience++;
		}
	}
	
	/**
	 * Prints an ordered list of all player's level gains
	 * @param finalResults The unordered list of results
	 */
	private void printLevelWinners(List<PlayerResult> finalResults) {
		// Sort the players based on their level gains (in descending order)
		Collections.sort(finalResults, new PlayerResultLevelComparator().reversed());
		int positionLevel = 1; // Keeps track of current rank
		// Append the text to the level textArea
		for (int i = 0; i < finalResults.size(); i++) {
			textAreaLevelWinners.append(positionLevel + Utility.getPositionSuffix(positionLevel) +
					"\"" + finalResults.get(i).getUsername() + "\" with " + finalResults.get(i).getLevelsGained() + " levels.\n");
			positionLevel++;
		}
	}
	
	public JFrame getFrame() {
		return frmCalculateResults;
	}
}
