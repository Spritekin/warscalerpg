package com.spritekin.warscale.ability;

import java.util.List;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.WarscaleObject;

public class Ability extends WarscaleObject {

	public static final String ABILITY = "Ability";
	
	// Subtype
	public static String SKILL 		= "Skill";
	public static String PERK 		= "Perk";
	public static String POWER 		= "Power";
	public static String SPELL 		= "Spell";
	
	public Ability(String name, String subtype, List<String> properties) {
		super(name, ABILITY, properties);
		addProperty(SUBTYPE, 	DataType.TEXT, subtype);
	}

}
