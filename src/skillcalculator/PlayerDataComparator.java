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
		return p1.getLevel() < p2.getLevel() ? -1 : p1.getExperience() > p2.getExperience() ? 1 : 0;
	}
}
