package skillcalculator;

import java.util.Comparator;

import skilltracker.Player;

/**
 * Used for sorting players based on their gained levels and experience.
 * 
 * @author Dylan Weaver
 *
 */
public class PlayerDataComparator implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		// Compare the player's levels
		if (p1.getLevel() > p2.getLevel()) {
			return 1;
		} else if (p2.getLevel() > p1.getLevel()) {
			return -1;
		} else {
			// Else their levels are the same. All that's left to check is their
			// experience!
			if (p1.getExperience() > p2.getExperience()) { 
				return 1;
			} else if (p2.getExperience() > p1.getExperience()) {
				return -1;
			} else {
				// Else the players have the same level and same experience (how coincidental!)
				return 0;
			}
		}
	}
}