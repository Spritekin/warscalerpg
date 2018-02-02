package com.spritekin.warscape.skills;

import com.spritekin.warscale.WarscaleTestCase;
import com.spritekin.warscale.core.Library;
import com.spritekin.warscale.skills.Power;

public class TestPower extends WarscaleTestCase {

	// Powers present no new functions, the only thing to test is if the library loaded correctly
	public static void testLibraryLoad() {

		String characterPointsPerLevel = Library.getValue(Power.POWER, "Fast_learner", "CharacterPointsPerLevel");
		// Test direct value access
		assert characterPointsPerLevel.equals("1") : 
			"Invalid material hardness: expected 1, found " + characterPointsPerLevel;

	}
}
