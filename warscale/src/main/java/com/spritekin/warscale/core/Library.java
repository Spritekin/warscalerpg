package com.spritekin.warscale.core;

import java.util.HashMap;
import java.util.Iterator;

import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialTable;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * grodriguez
 * This is the full data library. It is similar to a database. It keeps singletons for all the reference tables in the system.
 * This means all tables should be registered here so they are visible to the world.
 */
public class Library {
	// All ReferenceTables are registered here
	protected static java.util.HashMap<String, ReferenceTable> library = new HashMap<String, ReferenceTable>();

	static {		
		fromWarscaleData("MaterialCategory.wsd");
		//library.put(MaterialCategory.MATERIALCATEGORY, new MaterialCategoryTable());
		library.put(Material.MATERIAL, new MaterialTable());
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
		ReferenceTable table = ReferenceTable.fromWarscaleData("MaterialCategory.wsd");
		library.put(table.getName(), table);
	}
}
