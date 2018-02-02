package com.spritekin.warscale.skills;

import com.spritekin.warscale.core.WarscaleObject;

//A power is an object that can be added to other objects. 
public class Power extends WarscaleObject {
	public static final String POWER = "Power";
	
	// Properties
	// Powers does not define new properties, instead it uses
	//  properties available for creatures or items

	// Very simple constructor
	public Power(String name) {
		super(name, POWER, "", "", "", null);
	}
}
