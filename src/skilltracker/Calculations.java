package skilltracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * A class used to collect data on a list of players, which is then sorted based on that data.
 * 
 * @author Dylan Weaver
 *
 */
public class Calculations {
	/**
	 * Grabs the level and experience data for each player, adding it to a player
	 * object and then adding the player object into an arraylist
	 * 
	 * @param list
	 * @param skillNumber
	 * @return
	 */
	public static List<Player> runScraper(String[] list, int skillNumber) {
		List<Player> players = new ArrayList<>();
		
		// Go through the entire list of players
		for (int i = 0; i < list.length; i++) {
			try {
				String currentLine = connect(i, list[i], skillNumber);
					
				// Connect to the hiscores for the current index
				if (currentLine != "ERROR") {
					// Break up the line into an array of rank, level, and experience respectively
					String[] skillBrokenUp = currentLine.replaceAll(" ", "").split(",");
					int rank = Integer.parseInt(skillBrokenUp[0]);
					int level = Integer.parseInt(skillBrokenUp[1]);
					long experience = Long.parseLong(skillBrokenUp[2]);
	
					// Create a Player object and add this object to the players arrayList.
					Player p = new Player(list[i], rank, level, experience, true);
					players.add(p);
				} else {
					// The player name is invalid, set valid variable to false
					Player p = new Player(list[i], -1, -1, -1, false);
					players.add(p);
				}
			} catch (ConnectionException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				return null;
			}
		}
		
		return players;
	}
	
	/**
	 * For the given position, connect to the hiscores page for the username and
	 * read the data
	 * 
	 * @param position
	 * @param user
	 * @param skillNumber
	 * @return
	 * @throws ConnectionException 
	 */
	private static String connect(int position, String user, int skillNumber) throws ConnectionException {
		// Check that the username length is valid.
		if (user.length() > Utility.MAX_USERNAME_LENGTH) {
			throw new ConnectionException("Username: " + user + " is too long!");
		}
		// Connect to the hiscores using the username found at the position index of
		// Utlity.playersList
		String stringURL = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + user;
		try {
			URL url = new URL(stringURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // Create a connection to the URL
			
			// Go through the URL content and skip to the line that contains the skill we want
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				for (int i = 0; i < skillNumber; i++) {
					br.readLine();
				}
				// Read the line of data (which contains level, rank, and experience for the selected skill)
				return br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectionException("Something went wrong! Check Username: " + user);
		}
	}
}
