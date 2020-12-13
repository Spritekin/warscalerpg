package com.spritekin.warscale.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.utils.WarscaleConf;


/*
 * grodriguez
 * This is the full data library. It is similar to a database. It keeps singletons for all the reference tables in the system.
 */
public class Library {
	// All ReferenceTables are registered here
	protected static java.util.Map<String, ReferenceTable> library = new HashMap<String, ReferenceTable>();

	static {	
		// On first call to the Library, build all tables
		// Start with the core tables, no user should have access to these
		MaterialCategory.generateTable(createTable(MaterialCategory.MATERIALCATEGORY));
		Material.generateTable(createTable(Material.MATERIAL));
		
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.info("Library::static - Loading library files");
	    try {
	    		String librarypath = WarscaleConf.getProperty("LIBRARY", "");
	        Path path = Paths.get(librarypath);
	
		    FileVisitor<Path> fileProcessor = new ProcessFile();
		    Files.walkFileTree(path, fileProcessor);
	    }
	    catch(IOException e) {
	        logger.info(e.toString());
	        e.printStackTrace();
	    		System.exit(1); // Critical exception during startup, just quit
	    }
	}
	
	private static final class ProcessFile extends SimpleFileVisitor<Path> {
	    @Override public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) throws IOException {
		    if(aFile.toString().endsWith("yml")) {
				Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	            logger.info("Library::ProcessFile - Processing file:" + aFile);
		    		deserialize(aFile.toString());
		    }
		    return FileVisitResult.CONTINUE;
	    }
	}
	
	// Get a reference table by name
	public static ReferenceTable get(String table) {
		return library.get(table);
	}

	// Lists all the reference tables
	public static Set<String> listReferenceTables() {
		return library.keySet();
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
	public static boolean hasProperty(String table, String key, String propertyName) {
		return library.get(table).hasProperty(key, propertyName);
	}	

	// This is the preferred way to get a value form the table
	public static String getValue(String table, String key, String propertyName) {
		return library.get(table).getValue(key, propertyName);
	}	
		
	// Load a data file as a table
	public static void deserialize(String filename) {

        try
        {
		    WarscaleObject object = WarscaleObject.deserialize(filename);
			String tablename = object.getLibrary();
		
			if(!library.containsKey(tablename))
				library.put(tablename, new ReferenceTable(tablename));
		
			library.get(tablename).add(object);	
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
            System.exit(-1);
        }
        catch(InvalidWarscaleDataException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
        
	}

	// Save all not serialized objects to files, objects should belong to an owner
	public static void serialize(String owner) {	
		// Scan the library tables for dirty objects
	    for (Map.Entry<String, ReferenceTable> entry : library.entrySet()) {
	        String key = entry.getKey();
	        ReferenceTable table = entry.getValue();
	        
	        for(String objectid: table.getKeys()) {
	        		WarscaleObject object = table.getObject(objectid);
	        		if(!object.getOwner().equals(owner))
	        			continue;
	        		
	        		try {
	        			object.serialize();
	        		}
	        		catch (IOException e) {
						// TODO: handle exception
	        			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
                    logger.info("Library::serialize - Exception while serializing object " + key + ":" + objectid );	        			
                    logger.severe(e.getMessage());
                    e.printStackTrace();
				}
	        		
	        }
	    }
	}

	// Create a new empty table, must have a unique name
	public static ReferenceTable createTable(String table) throws RuntimeException {
		
		// The table must not exist
		if(library.containsKey(table))
			throw new RuntimeException("Table "+table+" already exists.");
		
		ReferenceTable res = new ReferenceTable(table);
		library.put(table, res);
		return res;
	}

	// Create a new empty object, must belong to someone and have a unique name under its library
	// At least the type and name are required
	public static String createObject(String owner, String table, String name, String type) {	
		
		// The library must exist
		if(!library.containsKey(table))
			return "Table does not exist";

		//the name must be unique
		return get(table).createNew(owner, name, type);
	}
	
	// Set object properties, properties set this way are destroyed, then recreated
	public static String setObjectProperties(String owner, String table, String name, List<String> fqpes) {
		
		// The library must exist
		if(!library.containsKey(table))
			return "Table " + table + " does not exist";

		return library.get(table).setObjectProperties(owner, name, fqpes);
	}

}
