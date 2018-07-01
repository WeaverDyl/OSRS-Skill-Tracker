package skilltracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to collect data on a list of players, which is then sorted based on that data.
 * 
 * @author Dylan Weaver
 *
 */
public class Calculations {
	// An arraylist of player objects which will later be sorted and printed
	static List<Player> players = new ArrayList<>();
	// Any excluded players (due to errors) will br place in here
	public static List<String> playerErrors = new ArrayList<>();
	// The current line of data - null before any data is read
	private static String currentLine = null;
	
	/**
	 * For the given position, connect to the hiscores page for the username and
	 * read the data
	 * 
	 * @param position The current index of the array
	 */
	private static boolean connect(int position, String user, int skillNumber) {
		// Check that the username length is valid, and if it isn't, don't process it, and add it to playerErrors.
		if (user.length() > Utility.MAX_USERNAME_LENGTH) {
			playerErrors.add(user);
			return false;
		}
		// Connect to the hiscores using the username found at the position index of
		// Utlity.playersList
		String stringURL = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + user;
		try {
			// Open a connection to StringUrl
			URL url = new URL(stringURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				for (int i = 0; i < skillNumber; i++) {
					br.readLine();
				}
				// Read the line of data (which contains level, rank, and experience for the selected skill)
				currentLine = br.readLine();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			playerErrors.add(user);
			return false;
		}
	}

	/**
	 * Grabs the level and experience data for each player, adding it to a player
	 * object and then adding the player object into an arraylist
	 */
	public static List<Player> runScraper(String[] list, int skillNumber) {
		// Clear the players and playerErrors lists so that data isn't added multiple times through consecutive runs
		players.clear();
		playerErrors.clear();
		// Go through the entire list of players
		for (int i = 0; i < list.length; i++) {
			// Connect to the hiscores for the current index
			if (connect(i, list[i], skillNumber)) {
				// Break up the line into an array of rank, level, and experience respectively
				String[] skillBrokenUp = currentLine.replaceAll(" ", "").split(",");
				int rank = Integer.parseInt(skillBrokenUp[0]);
				int level = Integer.parseInt(skillBrokenUp[1]);
				long experience = Long.parseLong(skillBrokenUp[2]);

				// Create a Player object and add this object to the players arrayList.
				Player p = new Player(list[i], rank, level, experience);
				players.add(p);
			}
		}
		return players;
	}

}
