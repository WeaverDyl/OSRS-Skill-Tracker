package skillcalculator;

/**
 * 
 * @author Dylan
 *
 */
public class PlayerResult {
	private String username;
	private int experienceGained;
	private int levelsGained;
	
	/**
	 * 
	 * @param username
	 * @param experienceGained
	 * @param levelsGained
	 */
	public PlayerResult(String username, int experienceGained, int levelsGained) {
		this.username = username;
		this.experienceGained = experienceGained;
		this.levelsGained = levelsGained;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getExperienceGained() {
		return experienceGained;
	}
	
	public int getLevelsGained() {
		return levelsGained;
	}

}
