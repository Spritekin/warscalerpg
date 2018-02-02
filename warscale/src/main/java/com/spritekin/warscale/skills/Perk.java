package com.spritekin.warscale.skills;

import com.spritekin.warscale.core.WarscaleObject;

//A material is a modifier. As such it extends the warscle object
public class Perk extends WarscaleObject {
	public static final String PERK = "Perk";
	
	// Properties
	// Varies but normally uses creature properties

	// Very simple constructor
	public Perk(String name) {
		super(name, PERK, "", "", "", null);
	}
	
}
