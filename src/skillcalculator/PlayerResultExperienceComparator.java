package skillcalculator;

import java.util.Comparator;

/**
 * Used for sorting players based on their gained experience.
 * 
 * @author Dylan Weaver
 *
 */
public class PlayerResultExperienceComparator implements Comparator<PlayerResult> {

	@Override
	public int compare(PlayerResult pr1, PlayerResult pr2) {
		return pr1.getExperienceGained() < pr2.getExperienceGained() ? -1 : pr1.getExperienceGained() > pr2.getExperienceGained() ? 1 : 0;
	}
}
