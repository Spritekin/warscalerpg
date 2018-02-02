package com.spritekin.warscale.core;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.spritekin.warscale.core.DuplicatePropertyException;

import com.spritekin.warscale.utils.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// A Warscale object is the base class for all the objects in the game, be it a character, an item, a spell, skills, powers, etc
// A Warscale object is basically formed by a name and a list of properties. This properties give the object some logical values
// For example, a Creature and an Item are both WarscaleObjects, but one has the property "Type text Creature" and the other has
//  a property "Type text Item". Furthermore a creature has special properties for attributes while an item has properties for 
//  material and quality. So an object properties provide specific information about the objects.
// Objects only set its properties at build time.
// In addition, an object also may have ObjectMounts. A mount is an object that provide its properties to the parent object.
//  for example, if a character has a sword and the sword has slashing damage, the character can do slashing damage.
//  or if the character is mounted a "increased fire attribute power" (which is an object itself) then the property of the
//  power is transferred to the parent.
// When an object is mounted, all its properties shared with the parent are assigned as property modifiers to the parent.
//  If the parent doesn't have the property the mounted object wants to assign, then the property is not shared.
//  For example, a sword has a MeleeModifier property, and the parent has a MeleeModifier property, then the property is
//   assigned as a modifier for the parent MeleeModifier. However the Sword MaterialCategory property does not exist in the
//   parent so it is not shared.
// When a mounted object is removed from the parent, all its property modifiers are removed from the parent.

public class WarscaleObject {

	// Common properties shared by all objects
	public static final String NAME = "Name";
	public static final String TYPE = "Type";
	public static final String SUBTYPE = "Subtype";
	public static final String TRAIT = "Trait";
	public static final String SUBTRAIT = "Subtrait";
	public static final String DESCRIPTION = "Description";

	private HashMap<String, Property> properties = new HashMap<String, Property>();
	protected HashMap<Integer, ArrayList<ObjectMount>> mounted = new HashMap<Integer, ArrayList<ObjectMount>>();

	// Open constructor. Properties are supposed to arrive as a string "PropertyName
	// PropertyType PropertyExpression"
	public WarscaleObject(String name, String type, String subtype, String trait, String subtrait,
			String[] properties) {
		addProperty(NAME, DataType.TEXT, name, false);
		addProperty(TYPE, DataType.TEXT, type, false);
		addProperty(SUBTYPE, DataType.TEXT, subtype, false);
		addProperty(TRAIT, DataType.TEXT, trait, false);
		addProperty(SUBTRAIT, DataType.TEXT, subtrait, false);
		addProperty(DESCRIPTION, DataType.TEXT, "", false);
		if (properties != null)
			for (String property : properties) {
				addProperty(property);
			}
	}

	// Create a warscale object, only name required
	public WarscaleObject(String name, String[] properties) {
		addProperty(NAME, DataType.TEXT, name, false);
		if (properties != null)
			for (String property : properties) {
				addProperty(property);
			}
	}

	// Just a convenience function to get the name of the object
	public String getName() {
		return getProperty(NAME).getValue();
	}

	// Returns the mounted objects on a level
	public ArrayList<ObjectMount> getMounted(int level) {
		return mounted.get(level);
	}

	public HashMap<String, Property> getProperties() {
		return properties;
	}

	public List<Property> getSharedProperties() {
		return properties.values().stream()
			.filter(property -> property.isShared())
			.collect(Collectors.toList());
	}

	// By default add a non shared property
	public void addProperty(String propertyName, String type, String value) {
		addProperty(propertyName, type, value, false);
	}

	public void addProperty(String propertyName, String type, String value, boolean shared) {
		if (getProperties().containsKey(propertyName))
			throw new DuplicatePropertyException(
					"The property " + propertyName + " already exists for object " + getName());

		Property att = PropertyFactory.newPropertyOfType(type, this, propertyName);
		att.setBase(value);
		getProperties().put(propertyName, att);
		att.setShared(shared);
	}

	public void addProperty(String fqpe) {
		boolean shared = false;
		String propertyName = StringUtils.getValueBefore(fqpe, " ");
        if(propertyName.equals("shared")) {
    			shared = true;
	        fqpe = StringUtils.getValueAfter(fqpe, " ");
	        propertyName = StringUtils.getValueBefore(fqpe, " ");        		
	    }
				
        fqpe = StringUtils.getValueAfter(fqpe, " ");
		String propertyType = StringUtils.getValueBefore(fqpe, " ");
		String propertyValue = StringUtils.getValueAfter(fqpe, " ");
		addProperty(propertyName, propertyType, propertyValue, shared);
	}

	// Check if the property is registered
	public boolean containsProperty(String propertyName) {
		if (getProperties().containsKey(propertyName))
			return true;
		return false;
	}

	// Set the base of a local property of the object.
	// Only properties that exist can be affected, if the property doesn't
	// exist then the method will return.
	public void setProperty(String propertyName, String value) {
		if (!getProperties().containsKey(propertyName)) {
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.info("WarscaleObject::setProperty - Unable to set the value for property " + propertyName + ". Property doesn't exist");
			return;
		}

		Property att = getProperties().get(propertyName);
		att.setBase(value);

	}

	// Returns a property. This looks into local properties and won't dig into
	// parents or mounts
	public Property getProperty(String attributeName) {
		if (!getProperties().containsKey(attributeName)) {
			return null;
		}
		return getProperties().get(attributeName);
	}

	// Returns the current value of a property
	// This method supports a "dot" separator to access complex types
	// For example:
	// a property matcat : (materialcategory) metal
	// if we run: getPropertyValue("matcat") >>> metal
	// if we run: getPropertyValue("matcat.basehardness") >>> 12
	// The getPropertyValue method will also dig into the mounts and search for
	//  the same properties in the mounts, then will operate all

	public String getPropertyValue(String expression) {
		String propertyName = com.spritekin.warscale.utils.StringUtils.getValueBefore(expression, ".");
		expression = com.spritekin.warscale.utils.StringUtils.getValueAfter(expression, ".");

		if (!getProperties().containsKey(propertyName))
			return null;

		return getProperties().get(propertyName).getValue(expression);
	}


	// Adds this object to the mounted list
	// then goes through all the shared properties in the mounted and adds them to the
	// properties of the main object
	public void mount(WarscaleObject object, String relation, int level) {
		if(!mounted.containsKey(level))
			mounted.put(level,new ArrayList<ObjectMount>());
		mounted.get(level).add(new ObjectMount(object, relation));
		
		// Now, for each shared property of the mounted object, add the property as a modifier
		//  of the property of the same name.
		// If the property does not exist, create an empty property
		for(Property property: object.getSharedProperties()) {
			if(!getProperties().containsKey(property.getName())) {
				// Create an empty property with default values
				Property att = PropertyFactory.newPropertyOfType(property.getType(), this, property.getName());
				getProperties().put(property.getName(), att);
			}
			
			getProperties().get(property.getName()).addModifier(property);			
		}
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WarscaleObject)
			if (this.getProperty(NAME).getValue()
					.equals(((WarscaleObject) obj).getProperty(NAME).getValue()))
				return true;
		return false;
	}

}
