package com.spritekin.warscale.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.spritekin.warscale.utils.StringUtils;

// grodriguez
// A property is an expression plus a collection of other properties called modifiers.
// The modifiers are operated in order against the base expression in order to get a final expression that is then calculated.
//
// Both the Property and the modifiers have names. In order to add a modifier to the property both must share the same name.
//  The name works like a filter that only allow mixing properties of the same name thus providing a logical constraint
//  This is done so properties mix on the same conceptual level, like you may add apples with apples or oranges with oranges.
//
// Properties have a data type. Property subclasses should be coded to correctly operate against other expressions. 
// For example:
//  A number property is assigned a base expression "3" and a number modifier "+4", when operated will result in "7".  
//  A text property is assigned a base expression "3" and a text modifier of "+4", then it will result in "34".
//
// A property always has a base expression which is the start of the operation. Any modifier is operated against the base.
// So if the base = "3" and the modifiers are ["+4", "-5"] then the evaluation (as a number) will be 3+4-5 = 2.
// Modifiers are applied in order.
//
// A parent object is required so if any expression depends on a parameter, then the value is requested to the parent.
// If a parameter can't be replaced then the property is unresolvable and returns empty.
// Each time a modifier is added or removed, the property is marked as dirty and will be recomputed the next time the getValue function
//  is invoked. The result is kept in the value attribute so it doesn't need to be recomputed all the time.
// The property keeps any operation sign as returned by its modifiers so it is possible for a expression to return "+5"
// A expression can be described in a single string as "PropertyName PropertyType PropertyExpression"
//  so the two first words become the property name and datatype and the remainder is the expression
//
// A property can be marked as "shared" which means it will be transmitted to the parent mount of this parent object.
//  For example, if a sword has a shared property "Melee/Slash +5" the sword is mounted on a character, then the
//  character receives the Melee/Slash +5 property. However the sword material or its hardness or its age are not passed to the
//  character because these are not shared properties.
// Properties are not shared by default.

public abstract class Property {
	
	protected WarscaleObject parent = null;
	protected String name = "";
	protected Expression base = null;
	private boolean dirty = true;
	protected String value = null;
	protected ArrayList<Property> modifiers = new ArrayList<Property>();
	protected boolean shared = false;

	protected Property(WarscaleObject parent, String name, String datatype, String expression) {
		setName(name);
		setParent(parent);
		base = new Expression(datatype, expression);
	}

	public WarscaleObject getParent() {
		return parent;
	}

	private void setParent(WarscaleObject parent) {
		this.parent = parent;
	}

	private void invalidate() {
		dirty = true;
	}
	
	public String getType() {
		return base.getDatatype();
	}

	public String getValue() {
		return getValue("");
	}

	// This method is used to dig deeper into the properties of the value
	// Not all properties (like number of text) support a deeper digging, only properties
	// of types that are inside a library.
	// For example, if the property type is "Material" and the value is "Gold" and the expression is "MaterialHardness" then the
	//  property will dig the Material library for the MaterialHardness of Gold.
	// Note if the value is not properly evaluated the query of the expression might fail.
	//
	// If no expression is sent then the value is returned.
	public String getValue(String expression) {		
		if(dirty) recomputePropertyValue();
		if (expression.length() > 0) {
			// Use the expression data type to find the reference table in the library
			return Library.getValue(base.getDatatype(), value, expression);
		}
		return value;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
		dirty = true;
	}

	protected Expression getBase() {
		return base;
	}

	public Property setBase(String expression) {
		this.base.setExpression(expression);
		invalidate();
		return this;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public boolean isShared() {
		return shared;
	}
	
	protected void setValue(String value) {
		this.value = value;
		dirty = false;
	}

	public ArrayList<Property> getPropertyModifiers() {
		return modifiers;
	}
		
	public void addModifier(Property property) {
		if(property == null) {
			System.out.println("Attribute::addModifier - Improper modifier 'null' added to property " + getName());
			return;
		}

		//Properties are very strict, names must match
		if(!property.getName().equals(getName())) {
			System.out.println("Attribute::addModifier - The property name " + getName() + " don't match the modifier name " + property.getName());
			return;
		}
		
		if(!property.getType().equals(getType())) {
			System.out.println("Attribute::addModifier - The property types don't match " + getName());
			return;
		}

		modifiers.add(property);
		invalidate();
	}

	// Removes a modifier from the list
	public void removeModifier(Property property) {
		modifiers.remove(property);
		invalidate();
	}

	// Removes all modifiers from the list
	public void clearModifiers() {
		modifiers.clear();
		invalidate();
	}
	
	// Allow the subclass to build its own operating object
	protected abstract void initAccummulator(HashMap<String, String> vars);
	protected abstract void applyModifier(String modifier);
	protected abstract void saveValue();

	//Recomputes the total and places the result in the result attribute
	private void recomputePropertyValue() {
		HashMap<String, String> vars = base.getVariableList();
		vars = gatherVariableData(vars);
		
		// Create an object of the property type that will handle the requests
		initAccummulator(vars);
		
		for(Property mod : getPropertyModifiers()) {
			//Ignore expressions with empty base values
			if(mod.getBase().isEmpty())
				continue;
			
			// Try getting the value of the modifier
			// This will use the modifier parent if any
			String modValue = mod.getValue();
			
			// Any remaining variables, use this object parent as data source
			vars = com.spritekin.warscale.utils.StringUtils.getVariables(modValue);
			vars = gatherVariableData(vars);
			modValue = com.spritekin.warscale.utils.StringUtils.replaceVariables(modValue, vars);

			applyModifier(modValue);
		}
		saveValue();
	}
							
	
	protected HashMap<String, String> gatherVariableData(HashMap<String, String> vars) {
		//Fill in from parent attributes if it exists
		if( getParent() != null ) {
			for(String key:vars.keySet()) {
				String data = parent.getPropertyValue(key);
				vars.put(key, data);
			}
		}
		return vars;
	}
}
