package com.spritekin.warscale.world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.yaml.snakeyaml.Yaml;

import com.spritekin.warscale.core.InvalidWarscaleDataException;
import com.spritekin.warscale.core.ReferenceTable;
import com.spritekin.warscale.core.WarscaleObject;

/*
 * The world is the simulation. Its the class instantiation.
*/

public class World {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		Yaml yaml = new Yaml();
		String filename = "MaterialCategory.yml";
		
		System.out.println(yaml.dump(yaml.load(new FileInputStream(new File(
				filename)))));

		Map<String, Object> values = (Map<String, Object>) yaml
				.load(new FileInputStream(new File(filename)));

        //Fetch the name, this is the reference table name in the library
		String name = (String) values.get("Name");
        if(name == null)
        		throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleData - Unable to load the file " + filename + ". Name not specified.");
        
        ReferenceTable table = new ReferenceTable(name);

        // Get common attributes. THis are added to all classes defined in this class
        String[] commonProperties = null ;        
        Map<String, Object> commons = (Map<String, Object>)values.get("Common");
        if( commons != null )
	        for(String commonkey : commons.keySet()) {
	        		System.out.println(commonkey);
	
	            Map<String, Object> wsclass = (Map<String, Object>)commons.get(commonkey);
	
	            	//A warscale common may define a list of properties like
	            // Properties:
	            //     - "PropertyName type value"
	        		//       ...
	        		// Other...
	            java.util.ArrayList<String> properties = (java.util.ArrayList<String>)wsclass.get("Properties");
	            commonProperties  = new String[properties.size()];
	            properties.toArray(commonProperties);
	        }
            
        // Get all classes        
        Map<String, Object> classlist = (Map<String, Object>)values.get("Class");
        for(String classkey : classlist.keySet()) {
        		System.out.println(classkey);

            Map<String, Object> wsclass = (Map<String, Object>)classlist.get(classkey);

            	//A warscale class is defined as
            // Type: MaterialCategory
            // Properties:
            //     - "PropertyName type value"
        		//       ...
            // Powers:
            //     PowerName: "value"
            // Skills:
            //     SkillName: "value"
        		// Other...

            String type = (String) wsclass.get("Type");
                if(type == null)
                		throw new InvalidWarscaleDataException("ReferenceTable::fromWarscaleData - Unable to load class " + classkey + " on file " + filename + ". Type not specified.");

            java.util.ArrayList<String> properties = (java.util.ArrayList<String>)wsclass.get("Properties");
            String[] classProperties  = new String[properties.size()];
            properties.toArray(classProperties);

            String[] allProperties = (String[]) ArrayUtils.addAll(classProperties, commonProperties);
            
            table.add(new WarscaleObject(classkey, type, "", "", "", allProperties));
        }

//            return table;
	}

}
