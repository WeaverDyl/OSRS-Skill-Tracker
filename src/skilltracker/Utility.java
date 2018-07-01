package skilltracker;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/*
 * Contains many useful methods for the Skill Tracker Tool
 * 
 * @author Dylan Weaver
 */
public class Utility {
	// The maximum username length for Oldschool Runescape is 12 characters
	public static final int MAX_USERNAME_LENGTH = 12;
	
	// An array of every skill in old school runescape
	public static final String[] skills = { "overall", "attack", "defence", "strength", "hitpoints", "ranged", "prayer",
			"magic", "cooking", "woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining",
			"herblore", "agility", "thieving", "slayer", "farming", "runecrafting", "hunter", "construction" };
	
	/**
	 * Determines which suffix to use at the end of a given position (st, th, nd, rd)
	 * 
	 * @param position The current position in an array being looped through
	 * @return A suffix to be appended to the position
	 */
	public final static String getPositionSuffix(int position) {
		if (position >= 11 && position <= 13) {
			return "th: ";
		} else {
			switch (position % 10) {
			case 1:
				return "st: ";
			case 2:
				return "nd: ";
			case 3:
				return "rd: ";
			default:
				return "th: ";
			}
		}
	}
	
	/**
	 * Determines the extension of a file
	 * 
	 * @param file The file to return its extension
	 * @return A string representation of a specific file's extension
	 */
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return "Error retrieving file extension!";
	    }
	}
	
	/**
	 * Adds an extension to a file
	 * 
	 * @param file The file which needs an extension
	 * @param extension The extension to add to the file
	 * @return The file with the extension, as a String
	 */
	public static String addExtension(String file, String extension) {
		if (!file.toString().endsWith("." + extension)) {
			file += ("." + extension);
		}
		
		return file;
	}
	
	/**
	 * Resets a progress bar and text areas
	 * 
	 * @param bar A JProgressBar which needs to be reset
	 * @param toClear A list of JTextAreas which need to be cleared
	 */
	public static void clearAll(JProgressBar bar, JTextArea... toClear) {
		if (bar != null) {
			bar.setValue(0);
		}
		
		for (JTextArea textAreas : toClear) {
			textAreas.setText("");
		}
	}

	/**
	 * Generates new lines every 10 elements
	 * Used in file saving to make the data more readable
	 * 
	 * @param str The string to process
	 * @return The string with new lines every 10 elements
	 */
	public static String newLineGenerator(String str) {
		String result = "";
		String[] individualData = str.split(",");
		
		for (int i = 0; i < individualData.length; i++) {
			result += (individualData[i] + (i == individualData.length - 1 ? "" :","));
			if ((i > 0) && (i % 10 == 0)) {
				result += "\n";
			}
		}
		
		return result;
	}
	
	/**
	 * Finds a substring that matches a specified regex
	 * 
	 * @param str A string to find matches in
	 * @param regex A pattern to use to find a match
	 * @return the match from the regex
	 */
	public static String getMatch(String str, String regex) {
		Matcher m = Pattern.compile(regex).matcher(str);
		while (m.find()) {
			return m.group(1);
		}
		
		return "";
	}
	
	/**
	 * Determines if the arrays passed in are the same length
	 * @param arrays The arrays to check
	 * @return true if every array is the same length, false otherwise
	 */
	public static boolean areSameLength(Object[]... arrays) {
		int firstLength = arrays[0].length;
		for (Object[] playerData : arrays) {
			if (playerData.length != firstLength) {
				return false;
			}
		}
		return true;
	}
}
