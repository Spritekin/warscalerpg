package com.spritekin.warscale.material;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.Property;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.currency.Money;

import java.util.HashMap;
import java.util.ArrayList;

//A material is a modifier. As such it extends the warscle object
public class Material extends WarscaleObject {
	public static final String MATERIAL = "MATERIAL";

	public static final String MATERIALHARDNESS 	= "MaterialHardness";
	public static final String MATERIALQUALITY 	= "MaterialQuality";
	public static final String MATERIALMODIFIER 	= "MaterialModifier";

	public Material(String name, String category, int quality) {
		super(name, MATERIAL, "", "", "");
		addProperty(MaterialCategory.MATERIALCATEGORY, MaterialCategory.MATERIALCATEGORY, category);	//named modifier
		addProperty(MATERIALQUALITY, 	DataType.NUMBER, ""+quality);
		addProperty(MATERIALMODIFIER,	DataType.NUMBER, "["+MATERIALQUALITY+"]/5");
		addProperty(MATERIALHARDNESS,	DataType.NUMBER, "["+MaterialCategory.MATERIALCATEGORY+"."+MaterialCategory.BASEHARDNESS+"] + ["+MATERIALMODIFIER+"]");
	}
	
	public int getQuality() {
		return Integer.parseInt(getProperty(MATERIALQUALITY).getValue());
	}

	public int getHardness() {
		return Integer.parseInt(getProperty(MATERIALHARDNESS).getValue());
	}

	public boolean isHigherQualityThan(Material other) { 
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
