import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;

public class MainGui {

	private JFrame frmOsrsSkillTracker;
	
	static JRadioButton rdbtnOverall = new JRadioButton("Overall");
	static JRadioButton rdbtnAttack = new JRadioButton("Attack");
	static JRadioButton rdbtnDefence = new JRadioButton("Defence");
	static JRadioButton rdbtnStrength = new JRadioButton("Strength");
	static JRadioButton rdbtnHitpoints = new JRadioButton("Hitpoints");
	static JRadioButton rdbtnRanged = new JRadioButton("Ranged");
	static JRadioButton rdbtnPrayer = new JRadioButton("Prayer");
	static JRadioButton rdbtnMagic = new JRadioButton("Magic");
	static JRadioButton rdbtnCooking = new JRadioButton("Cooking");
	static JRadioButton rdbtnWoodcutting = new JRadioButton("Woodcutting");
	static JRadioButton rdbtnFletching = new JRadioButton("Fletching");
	static JRadioButton rdbtnFishing = new JRadioButton("Fishing");
	static JRadioButton rdbtnFiremaking = new JRadioButton("Firemaking");
	static JRadioButton rdbtnCrafting = new JRadioButton("Crafting");
	static JRadioButton rdbtnSmithing = new JRadioButton("Smithing");
	static JRadioButton rdbtnMining = new JRadioButton("Mining");
	static JRadioButton rdbtnHerblore = new JRadioButton("Herblore");
	static JRadioButton rdbtnAgility = new JRadioButton("Agility");
	static JRadioButton rdbtnThieving = new JRadioButton("Thieving");
	static JRadioButton rdbtnSlayer = new JRadioButton("Slayer");
	static JRadioButton rdbtnFarming = new JRadioButton("Farming");
	static JRadioButton rdbtnRunecrafting = new JRadioButton("Runecrafting");
	static JRadioButton rdbtnHunter = new JRadioButton("Hunter");
	static JRadioButton rdbtnConstruction = new JRadioButton("Construction");
	
	// A list of all of the skill radio buttons. Used to create button group
	static JRadioButton[] skillButtons = new JRadioButton[] 
			{ rdbtnOverall, rdbtnAttack, rdbtnDefence, rdbtnStrength, rdbtnHitpoints,
			  rdbtnRanged, rdbtnPrayer, rdbtnMagic, rdbtnCooking, rdbtnWoodcutting,
			  rdbtnFletching, rdbtnFishing, rdbtnFiremaking, rdbtnCrafting,
			  rdbtnSmithing, rdbtnMining, rdbtnHerblore, rdbtnAgility, rdbtnThieving, 
			  rdbtnSlayer, rdbtnFarming, rdbtnRunecrafting, rdbtnHunter, rdbtnConstruction};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frmOsrsSkillTracker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		createSkillButtonGroup(skillButtons);
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOsrsSkillTracker = new JFrame();
		frmOsrsSkillTracker.setTitle("OSRS Skill Tracker");
		frmOsrsSkillTracker.setBounds(100, 100, 685, 486);
		frmOsrsSkillTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOsrsSkillTracker.getContentPane().setLayout(null);
		
		JLabel lblSelectSkill = new JLabel("Select Skill:");
		lblSelectSkill.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectSkill.setBounds(10, 11, 68, 14);
		frmOsrsSkillTracker.getContentPane().add(lblSelectSkill);
		
		
		rdbtnAttack.setBounds(10, 32, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnAttack);
		
		rdbtnStrength.setBounds(10, 58, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnStrength);
		
		rdbtnDefence.setBounds(10, 84, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnDefence);
		
		rdbtnRanged.setBounds(10, 110, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnRanged);
		
		rdbtnPrayer.setBounds(10, 136, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnPrayer);
		
		rdbtnMagic.setBounds(10, 162, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnMagic);
		
		rdbtnRunecrafting.setBounds(10, 188, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnRunecrafting);
		
		rdbtnConstruction.setBounds(10, 214, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnConstruction);
		
		rdbtnHitpoints.setBounds(112, 32, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnHitpoints);
		
		rdbtnAgility.setBounds(112, 58, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnAgility);
		
		rdbtnHerblore.setBounds(112, 84, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnHerblore);
		
		rdbtnThieving.setBounds(112, 110, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnThieving);
		
		rdbtnCrafting.setBounds(112, 136, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnCrafting);
		
		rdbtnFletching.setBounds(112, 162, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnFletching);
		
		rdbtnSlayer.setBounds(112, 188, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnSlayer);
		
		rdbtnHunter.setBounds(112, 214, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnHunter);
		
		rdbtnMining.setBounds(214, 32, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnMining);
		
		rdbtnSmithing.setBounds(214, 58, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnSmithing);
		
		rdbtnFishing.setBounds(214, 84, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnFishing);
		
		rdbtnCooking.setBounds(214, 110, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnCooking);
		
		rdbtnFiremaking.setBounds(214, 136, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnFiremaking);
		
		rdbtnWoodcutting.setBounds(214, 162, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnWoodcutting);
		
		rdbtnFarming.setBounds(214, 188, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnFarming);
		
		rdbtnOverall.setBounds(214, 214, 100, 23);
		frmOsrsSkillTracker.getContentPane().add(rdbtnOverall);
	}
	
	public static void createSkillButtonGroup(JRadioButton[] buttons) {
		ButtonGroup skillButtonGroup = new ButtonGroup();
		for (JRadioButton button : buttons) {
			skillButtonGroup.add(button);
		}
	}
}
