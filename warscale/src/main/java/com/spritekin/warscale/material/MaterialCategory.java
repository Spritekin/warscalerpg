package com.spritekin.warscale.material;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.WarscaleObject;

/* The MaterialCategory class is more a kind of interface on top of a warscale object allowing to 
 * access the properties using functions. The same functionality can be achieved with a pure warscaleobject.
 * The second utility is to declare all the values and property names so applications and data files
 *  have a common reference point for what values are expected.
 */

public class MaterialCategory extends WarscaleObject {
	
	// Public constants
	public static final String MATERIALCATEGORY = "MaterialCategory";
	
	public static final String BASEHARDNESS 	= "BaseHardness";
	public static final String MATERIALUNITS = "MeasureUnits";

	public static final String GEMSTONE 		= "Gemstone";
	public static final String METAL 		= "Metal";
	public static final String STONE 		= "Stone";
	public static final String WOOD 			= "Wood";
	public static final String NATURE 		= "Nature";
	public static final String LEATHER 		= "Leather";
	public static final String FABRIC 		= "Fabric";

	// Units
	public static final String KG 			= "kg";
	public static final String SQM 			= "sqm";
	public static final String CT 			= "ct";

	// Material category object
	public MaterialCategory(String name, int hardness, String units) {
		super(name, MATERIALCATEGORY, "", "", "");
		addProperty(BASEHARDNESS, 	DataType.NUMBER, ""+hardness);
		addProperty(MATERIALUNITS, 	DataType.TEXT, units);
	}

	public int getBaseHardness() {
		return Integer.parseInt(getProperty(BASEHARDNESS).getValue());
	}

	public int getUnits() {
		return Integer.parseInt(getProperty(MATERIALUNITS).getValue());
	}
	
}
