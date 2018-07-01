package skillcalculator;

/**
 * 
 * @author Dylan Weaver
 *
 */
public class PlayerResult {
	private String username; //
	private long experienceGained; //
	private int levelsGained; //
	
	/**
	 * 
	 * @param username
	 * @param experienceGained
	 * @param levelsGained
	 */
	public PlayerResult(String username, long experienceGained, int levelsGained) {
		this.username = username;
		this.experienceGained = experienceGained;
		this.levelsGained = levelsGained;
	}
	
	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return
	 */
	public long getExperienceGained() {
		return experienceGained;
	}
	
	/**
	 * @return
	 */
	public int getLevelsGained() {
		return levelsGained;
	}

}
