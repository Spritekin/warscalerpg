package com.spritekin.warscale.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	
	// All ReferenceTable subclasses should initialise with their own specific object types
	protected java.util.HashMap<String, WarscaleObject> referenceTable = new HashMap<String, WarscaleObject>();
	protected String name;

	public ReferenceTable(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
	public boolean contains(String key, String propertyName) {
		return referenceTable.get(key).containsProperty(propertyName);
	}	

	// THis is the preferred way to get a value form the table
	public String getValue(String key, String propertyName) {
		return referenceTable.get(key).getPropertyValue(propertyName);
	}
	
	// Load the table from a warscale data file
	public static ReferenceTable fromWarscaleData(String filename) {
        JSONParser parser = new JSONParser();
        try
        {
            Object object = parser.parse(new FileReader(filename));
            
            //convert Object to JSONObject
            JSONObject jsonObject = (JSONObject)object;
            
            //Fetch the name, this is the reference table name in the library
            String name = (String) jsonObject.get("Name");
            if(name == null)
            		throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleData - Unable to load the file " + filename + ". Name not specified.");
            
            ReferenceTable table = new ReferenceTable(name);
            
            // Common for all objects, some may be null.
            String type = (String) jsonObject.get("Type");
            if(type==null) type = "";
            String subtype = (String) jsonObject.get("Subtype");
            if(subtype==null) subtype = "";
            String trait = (String) jsonObject.get("Trait");
            if(trait==null) trait = "";
            String subtrait = (String) jsonObject.get("Subtrait");
            if(subtrait==null) subtrait = "";
            //Printing all the values
            Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.finest("Type: " + type);
            logger.finest("Subtype: " + subtype);
            logger.finest("Trait: " + trait);
            logger.finest("Subtrait: " + subtrait);
            
            // Specialised
            JSONArray data = (JSONArray)jsonObject.get("Data");
            for(Object row : data)
            {	
            		//In this data, each entry has a name followed by an array, get the name 
                JSONObject jsonRow = (JSONObject)row;         
                Iterator<String> iterator = jsonRow.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    JSONArray properties = (JSONArray)jsonRow.get(key);
                    String[] stringProperties = new String[properties.size()];
                    int i = 0;
                    for(Object property : properties) {
                    		stringProperties[i++] = (String)property;
                    }
                    table.add(new WarscaleObject(key, type, subtype, trait, subtrait, stringProperties));
                }
            }
            return table;
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        // If it fails loading throw an exception
        throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleData - Failed to load the data file" + filename);
	}
}
