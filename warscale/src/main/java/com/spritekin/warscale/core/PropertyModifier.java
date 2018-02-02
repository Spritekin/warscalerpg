package com.spritekin.warscale.core;

//The property modifier adds some information to the property relations.
//A property modifier is a relation between properties same as the relation between the parent objects
//When a relation between parents is created, a relation between childs is also created. That way if a base object or modifier
// needs to be replaced then the properties can be quickly swapped in the child object as well.
public class PropertyModifier {
	private ObjectMount relation = null;
	private Property modifier = null;
	
	public PropertyModifier(ObjectMount relation, Property modifier) {
		this.relation = relation;
		this.modifier = modifier;
	}
	
	//The equals method only cares if it is the same object.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return false;
	}	

	public Property getModifier() {
		return modifier;
	}

	public void setModifier(Property modifier) {
		this.modifier = modifier;
	}

	public ObjectMount getRelation() {
		return relation;
	}

}
