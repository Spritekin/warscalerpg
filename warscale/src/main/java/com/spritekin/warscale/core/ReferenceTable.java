package com.spritekin.warscale.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.ArrayUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.yaml.snakeyaml.Yaml;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * grodriguez
 * The ReferenceTable class is an interface for any class that needs to group and return data.
 * Warscale doesn't really care if the data comes from a formula, table or whatever as long as it
 *  can be queried for a result value.
 *  Example of reference tables are the material category or the material table.
 * The reference table is a hashmap so every entry in the reference table must be uniquely identifiable by a key or id.
 *  This id is used as the key for the hashmap.
 *  A referenced object is a WarscaleObject itself so it can contain any number of properties. The reference table subclass
 *   is responsible of properly constructing all the objects and add them to the table.
 *  The reference table provides functions to both recover the object or to quickly access the values of the objects.
 */

public class ReferenceTable {
	
	// All ReferenceTable subclasses should initialize with their own specific object types
	protected java.util.Map<String, WarscaleObject> referenceTable = new HashMap<String, WarscaleObject>();
	protected String name;

	public ReferenceTable(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public java.util.Set<String> getKeys() {
		return referenceTable.keySet();
	}

	public WarscaleObject getObject(String key) {
		return referenceTable.get(key);
	}

	// Check if a key is registered
	public WarscaleObject add(WarscaleObject object) {
		String name = object.getName();
		return referenceTable.put(name, object);
	}	

	
	// Check if a key is registered
	public boolean contains(String key) {
		return referenceTable.containsKey(key);
	}	

	// Check if a key is registered
	public boolean hasProperty(String key, String propertyName) {
		return referenceTable.get(key).hasProperty(propertyName);
	}	

	// This is the preferred way to get a value from the table
	// Retrieves a property from an object in a table
	public String getValue(String key, String propertyName) {
		return referenceTable.get(key).getPropertyValue(propertyName);
	}
		
	// Merges the contents of the target table into this table. Do not overwrite.
	public void append(ReferenceTable other) {
		append(other, false);
	}

	// Merges the contents of the target table into this table
	// If overwrite is set, new entries will replace old ones
	public void append(ReferenceTable other, boolean overwrite) {
		for(String key: other.getKeys()) {
			if(!referenceTable.containsKey(key) || overwrite)
				referenceTable.put(key, other.getObject(key));
		}
	}
	
	// Create a new empty object, must belong to someone and have a unique name under its library
	// At least the type and name are required
	public String createNew(String owner, String name, String type) {	
		
		//the name must be unique
		if(contains(name))
			return "ERROR: Object already exists";

		WarscaleObject res = new WarscaleObject(name, type, null);
		res.setOwner(owner);
		add(res);

		return "ok";
	}

	// Set multiple properties in an object
	// If a property fails to set, return the id and stop
	public String setObjectProperties(String owner, String name, List<String> properties) {	

		//the object must exist
		if(!contains(name))
			return "ERROR: Object " + name + " does not exist in library " + this.name + ".";
		
		WarscaleObject obj = getObject(name);
		if(!obj.belongsTo(owner))
			return "ERROR: Object doesn't belong to " + owner;
		
		for(String fqpe : properties) {
			Property prop = PropertyFactory.newPropertyFromExpression(null, fqpe);
			// If the object has this property, 
			if(obj.hasProperty(prop.getName()))
				obj.setProperty(prop.getName(), prop.getValue());					
			else
				obj.addProperty(fqpe);
		}

		return "ok";
	}

	/*
	public static void generateTable() throws RuntimeException {
		if(Library.contains(MATERIALCATEGORY))
			throw new RuntimeException("Can't generate table " + MATERIALCATEGORY + " because it already exists on the library.");
    */
}
