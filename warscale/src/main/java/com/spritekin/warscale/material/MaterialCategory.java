package com.spritekin.warscale.material;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.Library;
import com.spritekin.warscale.core.ReferenceTable;
import com.spritekin.warscale.core.WarscaleObject;

/* The MaterialCategory class is more a kind of interface on top of a warscale object allowing to 
 * access the properties using functions. The same functionality can be achieved with a pure warscaleobject.
 * The second utility is to declare all the values and property names so applications and data files
 *  have a common reference point for what values are expected.
 */

public class MaterialCategory extends WarscaleObject {
	
	// Public constants
	public static final String MATERIALCATEGORY = "MaterialCategory";
	
	// Properties
	public static final String BASEHARDNESS 	= "BaseHardness";	// The base hardness for all materials of this category
	public static final String MATERIALUNITS = "MeasureUnits";	// The units how this material is measured and sold

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
		super(name, MATERIALCATEGORY);
		addProperty(BASEHARDNESS, 	DataType.NUMBER, ""+hardness);
		addProperty(MATERIALUNITS, 	DataType.TEXT, units);
	}
	
	// Generate a table with all the available material categories
	public static void generateTable(ReferenceTable table) throws RuntimeException {
		table.add(new MaterialCategory(FABRIC, 	2, SQM));
		table.add(new MaterialCategory(LEATHER, 	4, SQM));
		table.add(new MaterialCategory(NATURE, 	6, KG));
		table.add(new MaterialCategory(WOOD, 	8, KG));
		table.add(new MaterialCategory(STONE, 	10, KG));
		table.add(new MaterialCategory(METAL, 	12, KG));
		table.add(new MaterialCategory(GEMSTONE, 14, CT));		
	}
}
