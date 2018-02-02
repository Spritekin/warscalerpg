package com.spritekin.warscale.material;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.WarscaleObject;

//A material is a modifier. As such it extends the warscle object
public class Material extends WarscaleObject {
	public static final String MATERIAL = "Material";

	// Properties
	public static final String MATERIALHARDNESS 	= "MaterialHardness";	// The hardness of the material
	public static final String MATERIALQUALITY 	= "MaterialQuality";		// The material quality
	public static final String MATERIALMODIFIER 	= "MaterialModifier";	// Modifiers for using this material

	public Material(String name, String category, String quality) {
		super(name, MATERIAL, "", "", "", null);
		addProperty(MaterialCategory.MATERIALCATEGORY, MaterialCategory.MATERIALCATEGORY, category);	//named modifier
		addProperty(MATERIALQUALITY, 	DataType.NUMBER, quality);
		addProperty(MATERIALMODIFIER,	DataType.NUMBER, "["+MATERIALQUALITY+"]/5");
		addProperty(MATERIALHARDNESS,	DataType.NUMBER, "["+MaterialCategory.MATERIALCATEGORY+"."+MaterialCategory.BASEHARDNESS+"] + ["+MATERIALMODIFIER+"]");
	}
	
	//Returns a price for one unit of this material
	/*
	public Money getPrice() {
		Money m = new Money();
		MaterialCategory category = (MaterialCategory)(getBase(MaterialCategory.MATERIALCATEGORY).getModifier());
		int quality = getQuality();

		if(category.getName().equals(MaterialCategory.MATERIALCATEGORY_FABRIC)) {
			m.set("" + Math.pow(category.getHardness(), quality/5.0f) + "bp");
		}
		else if(category.getName().equals(MaterialCategory.MATERIALCATEGORY_LEATHER)) {
			m.set("" + Math.pow(category.getHardness(), quality/5.0f) + "bp");			
		}
		else if(category.getName().equals(MaterialCategory.MATERIALCATEGORY_WOOD)) {
			m.set("" + Math.pow(category.getHardness(), quality/5.0f) + "bp");
		}
		else if(category.getName().equals(MaterialCategory.MATERIALCATEGORY_METAL)) {
			m.set("" + Math.pow(category.getHardness(), quality/5.0f) + "bp");
		}
		else if(category.getName().equals(MaterialCategory.MATERIALCATEGORY_GEMSTONE)) {
			m.set("" + (50 * Math.pow(2,quality/5 - 1)) + "bp");
		}
		return m;
	}
	*/
		
}
