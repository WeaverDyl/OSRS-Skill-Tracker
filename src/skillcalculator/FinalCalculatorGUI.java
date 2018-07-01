package skillcalculator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import skilltracker.FileFormatException;
import skilltracker.Utility;

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

	List<PlayerResult> playerDataBefore = new ArrayList<>();
	List<PlayerResult> playerDataAfter = new ArrayList<>();
	
	List<PlayerResult> finalResults = new ArrayList<>();

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
				loadBefore();
			}
		});
		
		// Action Listener for the loadAfter button
		buttonLoadAfter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAfter();
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
	 * 
	 */
//TODO Shorten this
	void loadBefore() {
		List<String> playerUsernames = new ArrayList<>();
		List<Integer> playerExperience = new ArrayList<>();
		List<Integer> playerLevels = new ArrayList<>();
		
		
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
		    				// Retrieves the skillNumber from in between the set of parentheses on the first line
	    					String skillBefore = Utility.skills[Integer.parseInt(Utility.getMatch(br.readLine(), "\\((\\d+)\\)"))];
	    					labelSkillNameBefore.setText(skillBefore);
	    	        		
	    	        		String line = null;
	    	        		while ((line = br.readLine()) != null) {
	    	        			// Loads the username data, placing it in textAreaPlayers
		    	        		if (line.equals("PLAYER USERNAME DATA:")) {
		    	        			while ((line = br.readLine()) != null) {
		    	        				if (!line.equals("PLAYER EXPERIENCE DATA:")) {
		    	        					playerUsernames.addAll(Arrays.asList(line.replaceAll(", ", ",").split(",")));
		    	        				} else {
					    	        		break;
		    	        				}
		    	        			}
		    	        		}
		    	        		if (line.equals("PLAYER EXPERIENCE DATA:")) {
    	        					while ((line = br.readLine()) != null) {
		    	        				if (!line.equals("PLAYER LEVEL DATA:") && !line.equals("")) {
		    	        					List<String> playerExperienceString = Arrays.asList(line.replaceAll(", ", ",").split(","));
		    	        					for (int i = 0; i < playerExperienceString.size(); i++) {
		    	        						playerExperience.add(Integer.parseInt(playerExperienceString.get(i)));
		    	        					}
		    	        				} else {
		    	        					break;
		    	        				}
    	        					}
		    	        		}
		    	        		if (line.equals("PLAYER LEVEL DATA:")) {
	    	        					while ((line = br.readLine()) != null) {
			    	        				if (!line.equals("PLAYER ERROR DATA:") && !line.equals("")) {
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
	    	        		
	    	        		labelPlayerCountBefore.setText(String.valueOf(playerUsernames.size()));
	    	        		if (skilltracker.Utility.areSameLength(playerUsernames.toArray(), playerExperience.toArray(), playerLevels.toArray())) {
	    	        			for (int i = 0; i < playerUsernames.size(); i++) {
	    	        				playerDataBefore.add(new PlayerResult(playerUsernames.get(i), playerExperience.get(i), playerLevels.get(i)));
	    	        			}
	    	        		}
	    				}
	    				// Throw an exception if the selected file does not have a .txt extension.
	    				else {
	    					throw new FileFormatException("Invalid file type!\nThis program currently only supports .txt files.");
	    				}
	    			} catch (FileFormatException e) {
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
	 * 
	 */
//TODO Shorten this
	void loadAfter() {
		List<String> playerUsernames = new ArrayList<>();
		List<Integer> playerExperience = new ArrayList<>();
		List<Integer> playerLevels = new ArrayList<>();
		
		
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
		    				// Retrieves the skillNumber from in between the set of parentheses on the first line
	    					String skillAfter = Utility.skills[Integer.parseInt(Utility.getMatch(br.readLine(), "\\((\\d+)\\)"))];
	    					labelSkillNameAfter.setText(skillAfter);
	    	        		
	    	        		String line = null;
	    	        		while ((line = br.readLine()) != null) {
	    	        			// Loads the username data, placing it in textAreaPlayers
		    	        		if (line.equals("PLAYER USERNAME DATA:")) {
		    	        			while ((line = br.readLine()) != null) {
		    	        				if (!line.equals("PLAYER EXPERIENCE DATA:")) {
		    	        					playerUsernames.addAll(Arrays.asList(line.replaceAll(", ", ",").split(",")));
		    	        				} else {
					    	        		break;
		    	        				}
		    	        			}
		    	        		}
		    	        		if (line.equals("PLAYER EXPERIENCE DATA:")) {
    	        					while ((line = br.readLine()) != null) {
		    	        				if (!line.equals("PLAYER LEVEL DATA:") && !line.equals("")) {
		    	        					List<String> playerExperienceString = Arrays.asList(line.replaceAll(", ", ",").split(","));
		    	        					for (int i = 0; i < playerExperienceString.size(); i++) {
		    	        						playerExperience.add(Integer.parseInt(playerExperienceString.get(i)));
		    	        					}
		    	        				} else {
		    	        					break;
		    	        				}
    	        					}
		    	        		}
		    	        		if (line.equals("PLAYER LEVEL DATA:")) {
	    	        					while ((line = br.readLine()) != null) {
			    	        				if (!line.equals("PLAYER ERROR DATA:") && !line.equals("")) {
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
	    	        		
	    	        		labelPlayerCountAfter.setText(String.valueOf(playerUsernames.size()));
	    	        		if (skilltracker.Utility.areSameLength(playerUsernames.toArray(), playerExperience.toArray(), playerLevels.toArray())) {
	    	        			for (int i = 0; i < playerUsernames.size(); i++) {
	    	        				playerDataAfter.add(new PlayerResult(playerUsernames.get(i), playerExperience.get(i), playerLevels.get(i)));
	    	        			}
	    	        		} else {
	    	        			//throw exception
	    	        		}
	    				}
	    				// Throw an exception if the selected file does not have a .txt extension.
	    				else {
	    					throw new FileFormatException("Invalid file type!\nThis program currently only supports .txt files.");
	    				}
	    			} catch (FileFormatException e) {
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
	void calculateWinner() {
		// If the two files don't contain data for the same skills, don't continue.
		if(!labelSkillNameBefore.getText().equals(labelSkillNameAfter.getText())) {
			JOptionPane.showMessageDialog(frmCalculateResults, "Error - The loaded files are for different skills.", 
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!playerDataBefore.isEmpty() && !playerDataAfter.isEmpty()) {
			if (playerDataBefore.size() == playerDataAfter.size()) {
				for (int i = 0; i < playerDataBefore.size(); i++) {
					// If the two files don't have the players in the same order, don't continue. 
					// This could lead to inaccurate results.
					if(!playerDataBefore.get(i).getUsername().equals(playerDataAfter.get(i).getUsername())) {
						JOptionPane.showMessageDialog(frmCalculateResults, 
								"Error - The loaded files have different lists of names.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					finalResults.add(new PlayerResult(playerDataBefore.get(i).getUsername(), 
							playerDataAfter.get(i).getExperienceGained() - playerDataBefore.get(i).getExperienceGained(),
							playerDataAfter.get(i).getLevelsGained() - playerDataBefore.get(i).getLevelsGained()));
				}
			}
		}
		
		// Finally, print the results
		printExperienceWinners();
		printLevelWinners();
	}
	
	/**
	 * 
	 */
	void printExperienceWinners() {
		Collections.sort(finalResults, new PlayerResultExperienceComparator().reversed());
		int positionExperience = 1;
		for (int i = 0; i < finalResults.size(); i++) {
			textAreaExperienceWinners.append(positionExperience +skilltracker.Utility.getPositionSuffix(positionExperience)+ " " + 
					finalResults.get(i).getUsername() + " with " + finalResults.get(i).getExperienceGained() + " experience.\n");
			positionExperience++;
		}
	}
	
	/**
	 * 
	 */
	void printLevelWinners() {
		Collections.sort(finalResults, new PlayerResultLevelComparator().reversed());
		int positionLevel = 1;
		for (int i = 0; i < finalResults.size(); i++) {
			textAreaLevelWinners.append(positionLevel + skilltracker.Utility.getPositionSuffix(positionLevel)+ " " + 
					finalResults.get(i).getUsername() + " with " + finalResults.get(i).getLevelsGained() + " levels.\n");
			positionLevel++;
		}
	}
	
	public JFrame getFrame() {
		return frmCalculateResults;
	}
}
