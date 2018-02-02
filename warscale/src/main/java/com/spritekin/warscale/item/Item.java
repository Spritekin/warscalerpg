package com.spritekin.warscale.item;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.material.Material;

public class Item extends WarscaleObject {

	public static final String ITEM = "Item";
	
	// Extra
	public static String WEARABLE	= "Wearable";
	public static String IMPLEMENT 	= "Implement";
	public static String CONSUMABLE 	= "Consumable";

	// Type
	public static String ARMOR 		= "Armor";
	public static String ARTIFACT 	= "Artifact";
	public static String EQUIPMENT 	= "Equipment";
	public static String JEWELLERY 	= "Jewellery";
	public static String CLOTHING 	= "Clothing";
	public static String TOOLS 		= "Tools";
	public static String WEAPON 		= "Weapon";

	// Properties
	public static final String DR 		= "DR";
	public static final String PRICE 	= "Price";
	public static final String WEIGHT 	= "Weight";
	
	public Item(String name, String subtype, String trait, String subtrait, String dr, Material material, String weight) {
		super(name, ITEM, subtype, trait, subtrait, null);
				
		addProperty(Material.MATERIAL, 	Material.MATERIAL, material.getName());
		addProperty(DR, 			DataType.NUMBER, dr);
		addProperty(WEIGHT,		DataType.NUMBER, weight);
		addProperty(PRICE,		DataType.NUMBER, "["+Material.MATERIAL+"."+Material.MATERIALQUALITY+"]");
		
	}
	

}
