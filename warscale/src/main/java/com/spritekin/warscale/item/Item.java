package com.spritekin.warscale.item;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.Property;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.currency.Money;
import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialCategory;

public class Item extends WarscaleObject {

	public static String ITEMSUBTYPE_WEARABLE	= "Wearable";
	public static String ITEMSUBTYPE_IMPLEMENT 	= "Implement";
	public static String ITEMSUBTYPE_CONSUMABLE 	= "Consumable";

	public static String ITEMSUBTYPE_ARMOR 		= "Armor";
	public static String ITEMSUBTYPE_ARTIFACT 	= "Artifact";
	public static String ITEMSUBTYPE_EQUIPMENT 	= "Equipment";
	public static String ITEMSUBTYPE_JEWELLERY 	= "Jewellery";
	public static String ITEMSUBTYPE_CLOTHING 	= "Clothing";
	public static String ITEMSUBTYPE_TOOLS 		= "Tools";
	public static String ITEMSUBTYPE_WEAPON 		= "Weapon";
	
	public static final String PROPERTY_DR 		= "DR";
	public static final String PROPERTY_PRICE 	= "Price";
	public static final String PROPERTY_WEIGHT 	= "Weight";
	
	public Item(String name, String subtype, String trait, String subtrait, String dr, Material material, String weight) {
		super(name, OBJECTTYPE_ITEM, subtype, trait, subtrait);
		
		if(material!=null)	//Modifiers can have this property set to null
			setBase(Material.MATERIAL, material);	//this one is special so I name the modifier
		
		addProperty(PROPERTY_DR, 		DataType.NUMBER, dr);
		addProperty(PROPERTY_WEIGHT,		DataType.NUMBER, weight);
		
	}

	//Returns the price of an object
	public Money getPrice() {
		Money m = new Money();
		
		int quality = Integer.parseInt(getPropertyValue(Material.PROPERTY_MATERIALQUALITY));
		String categoryName = getPropertyValue(MaterialCategory.PROPERTY_MATERIALCATEGORY);
		Material mat = Material.get(MaterialCategory.get(categoryName), quality);

		if(mat != null) {
			m = mat.getPrice();
			m.scale(Integer.parseInt(getProperty(PROPERTY_DR).getValue()));
			m.scale(Double.parseDouble(getProperty(PROPERTY_WEIGHT).getValue()));
		}
		return m;
	}
	
	//Returns a price for one unit of this material
	public Material getMaterial() {
		int quality = Integer.parseInt(getPropertyValue(Material.PROPERTY_MATERIALQUALITY).substring(1));
		String categoryName = getPropertyValue(MaterialCategory.PROPERTY_MATERIALCATEGORY);
		return Material.get(MaterialCategory.get(categoryName), quality);
	}

	//Returns a material object from its 
	public int getMaterialBonus() {
		String squality = getPropertyValue(Material.PROPERTY_MATERIALQUALITY);
		int quality = 0;
		if(squality.startsWith("="))
			quality = Integer.parseInt(squality.substring(1));
		else
			quality = Integer.parseInt(squality);
			
		return (quality - 5) / 5;
	}

}
