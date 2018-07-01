package skillcalculator;

import java.util.Comparator;

/**
 * Used for sorting players based on their gained levels
 * 
 * @author Dylan Weaver
 *
 */
public class PlayerResultLevelComparator implements Comparator<PlayerResult> {

	@Override
	public int compare(PlayerResult pr1, PlayerResult pr2) {
		return pr1.getLevelsGained() < pr2.getLevelsGained() ? -1 : pr1.getLevelsGained() > pr2.getLevelsGained() ? 1 : 0;
	}
}
