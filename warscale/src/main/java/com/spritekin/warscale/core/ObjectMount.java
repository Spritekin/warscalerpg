package com.spritekin.warscale.core;

// On object mount Links a parent object with a slave object and adds some additional information to the link
//  like a level or a relation name.
// An object mount can be anything, like a power, a skill, an armour, a sword or an item. 
//  for example, if a character has a sword and the sword has slashing damage, the character can do slashing damage.
//  or if the character is mounted a "Fast Learner power" (which is an object itself) then the property of the
//  power is transferred to the parent.
// The relation name is used to identify the mount.

public class ObjectMount {
	private WarscaleObject object = null;
	private String relation = "";
	
	public ObjectMount(WarscaleObject object, String relation) {
		this.object = object;
		this.relation = relation;
	}
	
	//The equals method only cares if it is the same object.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return false;
	}	

	public WarscaleObject getObject() {
		return object;
	}

	public void setObject(WarscaleObject object) {
		this.object = object;
	}

	public String getRelation() {
		return relation;
	}	
}
