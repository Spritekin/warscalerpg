package com.spritekin.warscale.creature;

import com.spritekin.warscale.core.WarscaleObject;

// A creature is an entity that interacts with the characters.
// All characters and living things are considered creatures.
public class Creature extends WarscaleObject {
	public static final String CREATURE = "Creature";

	// Subtypes
	public static final String HUMANOID 	= "Humanoid";
	public static final String BEAST 	= "Beast";
	public static final String CONSTRUCT = "Construct";
	public static final String PLANT 	= "Plant";
	public static final String ELEMENTAL	= "Elemental";

	/*	This are modifiers to the whole creature
	 *   for example, a human with the high-level modifier will have some specific attributes.
	 * 	Undead
	 *  Ghost
	 *  Giant 
	 *  Expert
	 *  HighLevel
	 */
	
	// Properties a creature defines
	public static final String CHARACTERLEVEL = "CharacterLevel"; 			// the current level of the creature, a creature can be read on lower levels but not higher
	public static final String CHARACTERPOINTSPERLEVEL = "CharacterPointsPerLevel"; // How many CPs the creature gains every time it gains a level
	public static final String CHARACTERPOINTS = "CharacterPoints"; 			// How many CPs the creature has in total
	public static final String CHARACTERPOINTSUSED = "CharacterPointsUsed"; 	// How many CPs the creature has used

	
	// A simple contructor. It is not recommended to create a creature using this constructor
	public Creature(String name) {
		super(name, CREATURE, "", "", "", null);
	}
	
}
