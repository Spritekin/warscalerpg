package com.spritekin.warscale.skills;

import com.spritekin.warscale.core.WarscaleObject;

//A material is a modifier. As such it extends the warscle object
public class Skills extends WarscaleObject {
	public static final String MATERIAL = "Material";

	public static final String MATERIALHARDNESS 	= "MaterialHardness";
	public static final String MATERIALQUALITY 	= "MaterialQuality";
	public static final String MATERIALMODIFIER 	= "MaterialModifier";

	public Skills(String name, String category, int quality) {
		super(name, MATERIAL, "", "", "", null);
	}
	
	public int getQuality() {
		return Integer.parseInt(getProperty(MATERIALQUALITY).getValue());
	}

	public int getHardness() {
		return Integer.parseInt(getProperty(MATERIALHARDNESS).getValue());
	}

	public boolean isHigherQualityThan(Skills other) { 
		return this.getQuality() > other.getQuality(); 
	}; 

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
	
	//Returns a price for one unit of this material
	public int getModifier() {
		return Integer.parseInt(getProperty(MATERIALMODIFIER).getValue());
	}
	
}
