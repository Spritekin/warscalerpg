package com.spritekin.warscale.core;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import com.spritekin.warscale.core.DuplicatePropertyException;

import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.utils.StringUtils;

//A Warscale object is the base class for all the objects in the game, be it a character, an item, a spell, etc
public class WarscaleObject {
	//Types
	public static final String OBJECTTYPE_CREATURE 	= "Creature";
	public static final String OBJECTTYPE_ITEM  		= "Item";
	public static final String OBJECTTYPE_SPELL 		= "Spell";

	public static final String PROPERTY_NAME 		= "Name";
	public static final String PROPERTY_TYPE 		= "Type";
	public static final String PROPERTY_SUBTYPE		= "Subtype";
	public static final String PROPERTY_TRAIT 		= "Trait";
	public static final String PROPERTY_SUBTRAIT 	= "Subtrait";
	public static final String PROPERTY_DESCRIPTION 	= "Description";
	
	private HashMap<String, Property> properties = new HashMap<String, Property>();
	protected HashMap<String, ObjectModifier> base = new HashMap<String, ObjectModifier>();
	protected ArrayList<ObjectModifier> modifiers = new ArrayList<ObjectModifier>();

	protected void init(String name, String type, String subtype, String trait, String subtrait) {
		addProperty(PROPERTY_NAME, 			DataType.TEXT, name);
		addProperty(PROPERTY_TYPE, 			DataType.TEXT, type);
		addProperty(PROPERTY_SUBTYPE, 		DataType.TEXT, subtype);
		addProperty(PROPERTY_TRAIT, 			DataType.TEXT, trait);
		addProperty(PROPERTY_SUBTRAIT,		DataType.TEXT, subtrait);
		addProperty(PROPERTY_DESCRIPTION,	DataType.TEXT, "");
	
	}

	//Warscale objects have some base methods to get
	public WarscaleObject(String name) {
		init(name, "", "", "", "");
	}

	public WarscaleObject(String name, String type, String subtype, String trait, String subtrait) {		
		init(name, type, subtype, trait, subtrait);
	}

	public WarscaleObject(String name, String type, String subtype, String trait, String subtrait, String[] properties) {
		init(name, type, subtype, trait, subtrait);
        System.out.println(name+":"+type);
		for(String property : properties) {
	        String propertyName = StringUtils.getValueBefore(property, " ");
	        property = StringUtils.getValueAfter(property, " ");
	        String propertyType = StringUtils.getValueBefore(property, " ");
	        String propertyValue = StringUtils.getValueAfter(property, " ");
	        addProperty(propertyName, propertyType, propertyValue);
	        System.out.println(propertyName+":"+propertyType+":"+propertyValue);
		}
		
	}

	//Just a convenience function to get the name of the object
	public String getName() {
		return getProperty(PROPERTY_NAME).getValue();
	}

	public ArrayList<ObjectModifier> getModifiers() {
		return modifiers;
	}

	public HashMap<String, Property> getProperties() {
		return properties;
	}

	public void addProperty(String propertyName, String type, String value) {
		if(getProperties().containsKey(propertyName)) 
			throw new DuplicatePropertyException("The property " + propertyName + " already exists for object " + getName());
						
		Property att = PropertyFactory.newPropertyOfType(type, this, propertyName);
		att.setBase(value);
		getProperties().put(propertyName, att);

	}

	// Check if the property is registered
	public boolean containsProperty(String propertyName) {
		if(getProperties().containsKey(propertyName))
			return true;
		return false;
	}

	//The set property method will only change any local property of the object. It won't change any parent property
	//However, if a value is set for a non existing property, it will affect the final result anyway.
	public void setProperty(String attributeName, String value) {
		if(!getProperties().containsKey(attributeName)) {
			return;
		}
		
		Property att = getProperties().get(attributeName);
		att.setBase(value);
	
	}

	//Returns a property. This looks into local properties and won't dig into parents or aggregates
	public Property getProperty(String attributeName) {
		if(!getProperties().containsKey(attributeName)) {
			return null;
		}
		return getProperties().get(attributeName);
	}

	//Returns the current property value
	// This method supports a "dot" separator to access complex types
	// For example:
	// a property matcat : (materialcategory) metal
	// if we run: getPropertyValue("matcat") >>> metal
	// if we run: getPropertyValue("matcat.basehardness") >>> 12
	
	public String getPropertyValue(String expression) {
		String propertyName = com.spritekin.warscale.utils.StringUtils.getValueBefore(expression, ".");
		expression = com.spritekin.warscale.utils.StringUtils.getValueAfter(expression, ".");
		
		if(!getProperties().containsKey(propertyName))
			return null;

		return getProperties().get(propertyName).getValue(expression);
	}
	
	//Gather any parent or modifier which has the specified property name
	//Searches in parents first, then in modifiers
	private ArrayList<WarscaleObject> gatherModifiersWithProperty(String propertyName) {
		//Make a list of all the objects which have this property
		ArrayList<WarscaleObject> mods = new ArrayList<WarscaleObject>();

		//Start with the base modifiers
		for(Entry<String, ObjectModifier> e : base.entrySet()) {
			if(e.getValue().getModifier().getProperty(propertyName) != null) {
				mods.add(e.getValue().getModifier());
			}
		}
		
		//Now do the general modifiers
		for(ObjectModifier o : getModifiers()) {
			if(o.getModifier().getProperty(propertyName) != null) {
				mods.add(o.getModifier());
			}
		}
		
		return mods;
	}

	//Recomputes all the properties which might be affected by this modifier
	private void recomputeModifierProperties(WarscaleObject modifier) {
		//Name and type is never modified
		for(String attName : modifier.getProperties().keySet()) {
			//Do not add names or types
			if(attName.equals(PROPERTY_NAME) || attName.equals(PROPERTY_TYPE))
				continue;

			//If this object does not have this property, create it
			//If the property does not exist, add an empty one
			if(!getProperties().containsKey(attName)) {
				Property p = modifier.getProperty(attName);
				if(p.getType().equals(DataType.NUMBER)) {
					addProperty(attName, DataType.NUMBER, "0");										
				} 
				else if(p.getType().equals(DataType.TEXT)) {
					addProperty(attName, DataType.TEXT, "");										
				}
				else if(p.getType().equals(MaterialCategory.MATERIALCATEGORY)) {
					addProperty(attName, MaterialCategory.MATERIALCATEGORY, MaterialCategory.FABRIC);
				}
				else if(p.getType().equals(DataType.DAMAGETYPE)) {
					addProperty(attName, DataType.DAMAGETYPE, "-");
				}
			}

			//Find the property in this class
			Property currentProperty = getProperty(attName);
			
			//Make a list of all the objects which have this property
			ArrayList<WarscaleObject> mods = gatherModifiersWithProperty(attName);			
			for(WarscaleObject o : mods) {
				currentProperty.addModifier(o.getProperty(attName));
			}
			
			currentProperty.clearModifiers();

		}
		
	}
	
	//Adds this modifier to the modifier list
	// then goes through all the attributes in the modifier and adds them to the attributes of the main object
	public void addModifier(WarscaleObject modifier, String relation) {
		modifiers.add(new ObjectModifier(modifier, relation));
		recomputeModifierProperties(modifier);		
	}

	
	//Goes through the list of modifiers and returns the first container that references this particular modifier
	public ObjectModifier getObjectModifier(WarscaleObject modifier) {
		for(ObjectModifier m : modifiers) {
			if(m.getModifier().equals(modifier)) {
				return m;
			}
		}
		return null;
	}

	//Goes through the list of modifiers and returns the first container that references this particular modifier
	public ObjectModifier getObjectModifier(String relation) {
		for(ObjectModifier m : modifiers) {
			if(m.getRelation().equals(relation)) {
				return m;
			}
		}
		return null;
	}

	//Remove a modifier by object
	// then goes through all the attributes of the object and removes the properties added by this modifier
	// resulting values will be recomputed the moment the modifier is removed
	public void removeModifier(WarscaleObject modifier) {
		ObjectModifier m = getObjectModifier(modifier);
		if(m!=null) {
			modifiers.remove(m);
			recomputeModifierProperties(modifier);
		}
	}


	//Remove a modifier by object
	// then goes through all the attributes of the object and removes the properties added by this modifier
	// resulting values will be recomputed the moment the modifier is removed
	public void removeModifier(String relation) {
		ObjectModifier m = getObjectModifier(relation);
		if(m!=null) {
			WarscaleObject modifier = m.getModifier();
			modifiers.remove(m);
			recomputeModifierProperties(modifier);
		}
	}

	//Replaces a modifier
	//The relation parameter is used to lookup an ObjectModifier link
	//This does not destroys the ObjectModifier link. It just replaces the object inside
	// The related modifiers in the properties are relinked and recomputed
	public void replaceModifier(WarscaleObject modifier, String relation) {
		ObjectModifier m = getObjectModifier(relation);
		if(m!=null) {
			//The modifier was found, remove properties affecting the main object
			WarscaleObject wo = m.getModifier();
			recomputeModifierProperties(wo);

			//Now relink the new properties
			m.setModifier(modifier);
			recomputeModifierProperties(modifier);
		}
	}

	public ObjectModifier getBase(String baseName) {
		return base.get(baseName);
	}

	//Sets a base object, if the base object exists then it is replaced
	//Only an object of the same base type can be replaced by an object of another same base type (i.e. Material with Material, Race with Race).
	//A base cannot be removed! It is a constituent part of the object. It would be like having a character without race or an item with no material.
	public void setBase(String baseName, WarscaleObject baseObject) {
		ObjectModifier m = getBase(baseName);
		if(m!=null) {
			//The modifier was found, remove properties affecting the main object
			WarscaleObject wo = m.getModifier();
			
			//If not the same class, do not accept
			if(!wo.getClass().equals(baseObject.getClass())) {
				return;
			}
			
			//Now relink the new properties
			m.setModifier(baseObject);
		}
		else {
			//No modifier found, then add this as a base
			base.put(baseName, new ObjectModifier(baseObject, baseName));
			base.get(baseName).resetTimeStamp();	//parents have a timestamp of 0
		}
		
		recomputeModifierProperties(baseObject);

	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof WarscaleObject)
			if(this.getProperty(PROPERTY_NAME).getValue().equals(((WarscaleObject)obj).getProperty(PROPERTY_NAME).getValue())) 
				return true;
		return false;
	}

}
