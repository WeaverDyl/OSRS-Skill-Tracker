package skillcalculator;

/**
 * Used to compare player's experience and level gains after collecting an initial
 * and final set of data
 * @author Dylan Weaver
 *
 */
public class PlayerResult {
	private String username; //
	private long experienceGained; //
	private int levelsGained; //
	
	/**
	 * Creates a PlayerResult object, used to compare everybody's experience and level
	 * gains
	 * @param username The player's username
	 * @param experienceGained The player's experience gained
	 * @param levelsGained The player's level gains
	 */
	public PlayerResult(String username, long experienceGained, int levelsGained) {
		this.username = username;
		this.experienceGained = experienceGained;
		this.levelsGained = levelsGained;
	}
	
	/**
	 * @return The player's username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return The player's experience gain
	 */
	public long getExperienceGained() {
		return experienceGained;
	}
	
	/**
	 * @return The player's level gain
	 */
	public int getLevelsGained() {
		return levelsGained;
	}

}
