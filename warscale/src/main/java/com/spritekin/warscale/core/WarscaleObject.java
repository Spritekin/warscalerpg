package com.spritekin.warscale.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.spritekin.warscale.core.DuplicatePropertyException;

import com.spritekin.warscale.utils.StringUtils;
import com.spritekin.warscale.utils.WarscaleConf;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

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
	public static final String TRAITS = "Trait";
	public static final String DESCRIPTION = "Description";

	private HashMap<String, Property> properties = new HashMap<String, Property>();
	protected List<ObjectMount> mounted = new ArrayList<ObjectMount>();

	private boolean consistent = false;	// Object is in an inconsistent state and needs to be serialized

	private String owner = "root";	// root owns all by default
	private String library = "";	// library where the object belongs
	
	// Create a warscale object, only name required
	public WarscaleObject(String name) {
		addProperty(NAME, DataType.TEXT, name, false);
	}

	// Create a warscale object, only name required
	// Properties are expected to arrive as list of fqpe
	public WarscaleObject(String name, List<String> properties) {
		addProperty(NAME, DataType.TEXT, name, false);
		if (properties != null)
			for (String property : properties) {
				addProperty(property);
			}
	}

	// Create a warscale object, only name and type required
	public WarscaleObject(String name, String type) {
		addProperty(NAME, DataType.TEXT, name, false);
		addProperty(TYPE, DataType.TEXT, type, false);
	}

	// Open constructor.
	// Properties are expected to arrive as a list of fqpe
	public WarscaleObject(String name, String type, List<String> properties) {
		addProperty(NAME, DataType.TEXT, name, false);
		addProperty(TYPE, DataType.TEXT, type, false);
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
	public List<ObjectMount> getMounted(int level) {
		return mounted.stream()
			.filter(m -> { return m.getLevel() == level; } )
			.collect(Collectors.toList());			
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
	public WarscaleObject addProperty(String propertyName, String type, String value) throws RuntimeException, DuplicatePropertyException {
		return addProperty(propertyName, type, value, false);
	}

	public WarscaleObject addProperty(String propertyName, String type, String value, boolean shared) throws RuntimeException, DuplicatePropertyException {
		if (getProperties().containsKey(propertyName))
			throw new DuplicatePropertyException(
					"The property " + propertyName + " already exists for object " + getName());

		Property att = PropertyFactory.newPropertyOfType(type, this, propertyName);
		att.setBase(value);
		getProperties().put(propertyName, att);
		att.setShared(shared);
		return this;
	}

	public WarscaleObject addProperty(String fqpe) throws RuntimeException, DuplicatePropertyException {

		String propertyName = StringUtils.getValueBefore(fqpe, " ");
        if(propertyName.equals("shared")) {
	        propertyName = StringUtils.getValueBefore(StringUtils.getValueAfter(fqpe, " "), " ");
	    }

		if (getProperties().containsKey(propertyName))
			throw new DuplicatePropertyException(
					"The property " + propertyName + " already exists for object " + getName());

		Property att = PropertyFactory.newPropertyFromExpression(this, fqpe);
		getProperties().put(propertyName, att);
		
		return this;
	}

	// Adds multiple properties using a list of FQPE
	// If a property already exists raise an exception
	public WarscaleObject addProperties(List<String> fqpes) throws RuntimeException, DuplicatePropertyException {
		if (fqpes != null)
			for (String fqpe : fqpes) {
				addProperty(fqpe);
			}
		return this;
	}

	// Check if the property is registered
	public boolean hasProperty(String propertyName) {
		if (getProperties().containsKey(propertyName))
			return true;
		return false;
	}

	// Set the value of a property of the object.
	// Only properties that exist can be affected, if the property doesn't
	// exist then the method will return.
	public WarscaleObject setProperty(String propertyName, String value) {
		if (!getProperties().containsKey(propertyName)) {
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.info("WarscaleObject::setProperty - Unable to set the value for property " + propertyName + ". Property doesn't exist");
			return this;
		}

		Property att = getProperties().get(propertyName);
		att.setBase(value);
		return this;
	}

	// Returns a property. This looks into local properties and won't dig into
	// parents or mounts
	public Property getProperty(String propertyName) {
		if (!getProperties().containsKey(propertyName)) {
			return null;
		}
		return getProperties().get(propertyName);
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

	// Returns the base value of a property
	// It doesn't compute any modifiers, just the base
	// If the property doesn't exist, it will raise an exception so test the property exists before
	public String getPropertyBaseValue(String propertyName) {
		return getProperties().get(propertyName).getBase().getExpression();
	}

	// Adds this object to the mounted list
	// then goes through all the shared properties in the mounted and adds them to the
	// properties of the main object
	public WarscaleObject mount(ObjectMount mountinfo) {

		// Remove all mounted objects
		unapplyMounts();
		// Add to the list
		mounted.add(mountinfo);
		mounted.sort((o1, o2)->{
			if(o2.getLevel() == o1.getLevel())
				return o1.getObject().getName().compareTo(o2.getObject().getName());
			return o2.getLevel() - o1.getLevel();
		});
		
		// Mount all again
		applyMounts();
		return this;
	}

	// Adds this object to the mounted list
	// then goes through all the shared properties in the mounted and adds them to the
	// properties of the main object
	public WarscaleObject unmount(WarscaleObject object, int level) {

		// Locate this object in the mounted list
		// Note that if more than one exists on that level, it will still remove one
		ObjectMount found = null;
		for(ObjectMount m : mounted) {
			if(m.getObject().equals(object) && m.getLevel() == level) {
				found = m;
				break;
			}
		}

		// If an object is found on that level, remove it, have to reapply all mounts
		if(found != null) {
			unapplyMounts();
			mounted.remove(found);
			applyMounts();
		}	
		
		return this;
	}

	// Remount all mounted objects, this is only needed if the object properties have been
	// cleared. So all properties need to be rebuilt.
	public void applyMounts() {
		for(ObjectMount mount : mounted) {
			// Now, for each shared property of the mounted object, add the property as a modifier
			//  of the property of the same name.
			// If the property does not exist, create an empty property
			for(Property property: mount.getObject().getSharedProperties()) {
				if(!getProperties().containsKey(property.getName())) {
					// Create a placeholder property with default values
					Property att = PropertyFactory.newPropertyOfType(property.getType(), this, property.getName());
					att.setPlaceholder(true);
					getProperties().put(property.getName(), att);
				}				
				getProperties().get(property.getName()).addModifier(property);			
			}			
		}
	}

	// unmount all mounted objects, this is needed if a new object is mounted because mounts need to be applied
	// in level order. So if we mount an object at level 3 then at level 6 then another at level 1, all mounts needs to be
	// reset because the object mounted at level 1 needs to come before the ones mounted at level 3 and 6.
	public void unapplyMounts() {
		for(ObjectMount mount : mounted) {
			// For each shared property of the mounted object, remove the modifier
			//  from property of the same name.
			for(Property property: mount.getObject().getSharedProperties()) {
				getProperties().get(property.getName()).removeModifier(property);

				// If the property is left empty, drop it
				if(getProperties().get(property.getName()).isEmpty())
					getProperties().remove(property.getName());
			}
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

	public boolean isConsistent() {
		return consistent;
	}

	public void setConsistent(boolean consistent) {
		this.consistent = consistent;
	}

	// Query if the object is serialized, if false then its not consistent
	public String getOwner() {
		return owner;
	}
	
	// Set the object serialization state
	public void setOwner(String owner) {
		this.owner = owner;
	}

	// Convenience for getOwner
	public boolean belongsTo(String owner) {
		return this.owner.equals(owner);
	}

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	// Serialises the object, uses the global library path configured in the conf
	// Objects are serialised in a path relative to its type and subtype, for example
	// /var/warscale/library/creature/humanoid/objectfile.yml	
	public void serialize() throws IOException {
		// If in consistent state, do nothing
		if(isConsistent()) {
			Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
			logger.info("WarscaleObject::serialize - No changes found for object " + this.getPropertyValue(NAME));
			return;
		}
		
		String filepath = WarscaleConf.getProperty("LIBRARY", ".");
		
		// Add the type to the path
		filepath += "/" + getPropertyValue(TYPE).toLowerCase();

		// Add the subtype if meaningful
		if(getPropertyValue(SUBTYPE) != null && getPropertyValue(SUBTYPE).length() > 0) 
			filepath += "/" + getPropertyValue(SUBTYPE).toLowerCase();
		
		// Add the object name, removing any spaces
		filepath += "/" + getPropertyValue(NAME).toLowerCase() + ".yml";
		
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.info("WarscaleObject::serialize - Serializing " + filepath);
				
		java.nio.file.Path path = Paths.get(filepath);

		StringBuilder content = new StringBuilder();
		String nl = System.getProperty("line.separator");
		String tab = "  ";
		
		// Start with the library where this object will be added
		content.append("Library: " + getPropertyValue(TYPE) + nl);
		
		// Continue with the class entry
		content.append("Class:" + nl);
		content.append(tab + getPropertyValue(NAME) + ":" + nl);
		content.append(tab + tab + "Type: " + getPropertyValue(TYPE) + nl);
		content.append(tab + tab + "Owner: " + getOwner() + nl);
		
		// Save the properties, skip Name and Type as they are specific
		content.append(tab + tab + "Properties:" + nl);
		
		for(String key: properties.keySet()) {
			if(key.equals(TYPE) || key.equals(NAME))
				continue;
			Property prop = properties.get(key);
			String propertyString = String.format("\"%s %s %s\"",
			         prop.getName(),
			         prop.getBase().getDatatype(),
			         prop.getBase().getExpression()
			);			
			content.append(tab + tab + tab + "- " + propertyString + nl);
		}
		
		Files.createDirectories(path.getParent());
		Files.write(path, content.toString().getBytes());
				
		// All serialized, set the state
		//setSerialized(true);
	}
	
	// Build a new object from a warscale yaml file
	// A file describes a single object
	public static WarscaleObject deserialize(String filename) throws FileNotFoundException, InvalidWarscaleDataException {
    		Yaml yaml = new Yaml();
    			
    		Map<String, Object> values = (Map<String, Object>) yaml
    				.load(new FileInputStream(new File(filename)));

        //Fetch the Library, this is the reference table name in the library
    		// Where the object will be allocated
    		String library = (String) values.get("Library");
        if(library == null || library.length() == 0)
        		throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleYAML - Unable to load the file " + filename + ". Library not specified.");
        
        // Only one object will be loaded
        Map<String, Object> classlist = (Map<String, Object>)values.get("Class");
        for(String classkey : classlist.keySet()) {
            Map<String, Object> wsclass = (Map<String, Object>)classlist.get(classkey);

            	//A warscale class is defined as
            // Type: MaterialCategory
            // Owner: theowneruid
            // Properties:
            //     PropertyName: value
        		//     ...
            // Powers:
            //     PowerName: "value"
            // Skills:
            //     SkillName: "value"
        		// Other...

            String type = (String) wsclass.get("Type");
                if(type == null || type.length() == 0 )
                		throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleYAML - Unable to load class " + classkey + " on file " + filename + ". Type not specified.");

            String owner = (String) wsclass.get("Owner");
                if(owner == null || owner.length() == 0)
                		throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleYAML - Unable to load class " + classkey + " on file " + filename + ". Owner not specified.");

            java.util.ArrayList<String> properties = (java.util.ArrayList<String>)wsclass.get("Properties");
            
            // build the object and mark the library and owner
            WarscaleObject res = new WarscaleObject(classkey, type, properties);
            res.setLibrary(library);
            res.setOwner(owner);
            res.setConsistent(true);
            return res;
        }
        
        // If it fails loading throw an exception
        throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleYAML - Failed to load the data file" + filename);
	}
	
}
