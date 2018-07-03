package skilltracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An actionListener for the skill buttons within SkillTrackerGui
 * 
 * @author Dylan Weaver
 *
 */
public class SkillButtonActionListener implements ActionListener {
	private int skillId; // The skill id that corresponds to a specific skill.
    
	/**
	 * A constructor for a SkillButtonActionListener
	 * 
	 * @param skillId The skill Id
	 */
    public SkillButtonActionListener(int skillId){
    	this.skillId = skillId;
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        SkillTrackerGui.skillNumber = skillId; // gross what the heck
    }
}
