package com.spritekin.warscale.core;

import java.util.HashMap;

/*
 * grodriguez
 * This is the full data library. It is similar to a database. It keeps singletons for all the reference tables in the system.
 * This means all tables should be registered here so they are visible to the world.
 */
public class Library {
	// All ReferenceTables are registered here
	protected static java.util.Map<String, ReferenceTable> library = new HashMap<String, ReferenceTable>();

	static {	
		// Materials
		fromWarscaleYAML("resources/items/MaterialCategory.yml");
		fromWarscaleYAML("resources/items/Material.yml");

		// Skills and specialisations
		//fromWarscaleData("Skill.wsd");
		
		// Perks and powers
		//fromWarscaleData("Perk.wsd");
		fromWarscaleYAML("resources/powers/Power.yml");

		// Items
		//fromWarscaleData("Item.wsd");

		// Creatures
		//fromWarscaleYAML("creatures/Creature.wsd");
	}
	
	public static ReferenceTable get(String table) {
		return library.get(table);
	}

	// Check if a table is registered
	public static boolean contains(String table) {
		return library.containsKey(table);
	}	

	// Check if a key is registered in a table
	public static boolean contains(String table, String key) {
		return library.get(table).contains(key);
	}	

	// Check if a key is registered in a table
	public static boolean contains(String table, String key, String propertyName) {
		return library.get(table).contains(key, propertyName);
	}	

	// This is the preferred way to get a value form the table
	public static String getValue(String table, String key, String propertyName) {
		return library.get(table).getValue(key, propertyName);
	}	
	
	// Load a data file as a table
	public static void fromWarscaleData(String filename) {
		ReferenceTable table = ReferenceTable.fromWarscaleData(filename);
		library.put(table.getName(), table);
	}
	
	// Load a data file as a table
	public static void fromWarscaleYAML(String filename) {
		ReferenceTable table = ReferenceTable.fromWarscaleYAML(filename);
		
		if(library.containsKey(table.getName())) 
			library.get(table.getName()).append(table);
		else 
			library.put(table.getName(), table);
	}
	
}
