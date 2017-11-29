package com.spritekin.warscale.material;

import com.spritekin.warscale.core.ReferenceTable;

//A material is a modifier. As such it extends the warscle object
public class MaterialTable extends ReferenceTable {

	public MaterialTable() {
		super("MaterialTable");
		
		referenceTable.put("Agathe",			new Material("Agathe",			MaterialCategory.GEMSTONE, 5));
		referenceTable.put("Amethist",		new Material("Amethist",			MaterialCategory.GEMSTONE, 10));
		referenceTable.put("Jade",			new Material("Jade",				MaterialCategory.GEMSTONE, 15));
		referenceTable.put("Turquoise",		new Material("Turquoise",		MaterialCategory.GEMSTONE, 20));
		referenceTable.put("Sapphire",		new Material("Sapphire",			MaterialCategory.GEMSTONE, 25));
		referenceTable.put("Ruby",			new Material("Ruby",				MaterialCategory.GEMSTONE, 30));
		referenceTable.put("Amber",			new Material("Amber",			MaterialCategory.GEMSTONE, 35));
		referenceTable.put("Topaz",			new Material("Topaz",			MaterialCategory.GEMSTONE, 40));
		referenceTable.put("Emerald",		new Material("Emerald",			MaterialCategory.GEMSTONE, 45));
		referenceTable.put("Obsidian",		new Material("Obsidian",			MaterialCategory.GEMSTONE, 50));
		referenceTable.put("Diamond",		new Material("Diamond",			MaterialCategory.GEMSTONE, 55));
		referenceTable.put("Wurzite",		new Material("Wurzite",			MaterialCategory.GEMSTONE, 60));
		referenceTable.put("Carbon nitride",	new Material("Carbon nitride",	MaterialCategory.GEMSTONE, 70));
		referenceTable.put("Lonsdaleite",	new Material("Lonsdaleite",		MaterialCategory.GEMSTONE, 80));
		referenceTable.put("Heterodiamond",	new Material("Heterodiamond",	MaterialCategory.GEMSTONE, 90));
		referenceTable.put("Dilithium",		new Material("Dilithium",		MaterialCategory.GEMSTONE, 110));
		referenceTable.put("Graphene",		new Material("Graphene",			MaterialCategory.GEMSTONE, 120));

		referenceTable.put("Copper",			new Material("Copper",			MaterialCategory.METAL, 0));
		referenceTable.put("Iron",			new Material("Iron",				MaterialCategory.METAL, 2));
		referenceTable.put("Bronze",			new Material("Bronze",			MaterialCategory.METAL, 5));
		referenceTable.put("Steel",			new Material("Steel",			MaterialCategory.METAL, 10));
		referenceTable.put("Silver",			new Material("Silver",			MaterialCategory.METAL, 15));
		referenceTable.put("Palladium",		new Material("Palladium",		MaterialCategory.METAL, 20));
		referenceTable.put("Gold",			new Material("Gold",				MaterialCategory.METAL, 25));
		referenceTable.put("Titanium",		new Material("Titanium",			MaterialCategory.METAL, 30));
		referenceTable.put("Platinum",		new Material("Platinum",			MaterialCategory.METAL, 35));
		referenceTable.put("Thallium",		new Material("Thallium",			MaterialCategory.METAL, 40));
		referenceTable.put("Iridium",		new Material("Iridium",			MaterialCategory.METAL, 45));
		referenceTable.put("Adamantium",		new Material("Adamantium",		MaterialCategory.METAL, 50));
		referenceTable.put("Meteoric",		new Material("Meteoric",			MaterialCategory.METAL, 55));
		referenceTable.put("Luthanum",		new Material("Luthanum",			MaterialCategory.METAL, 60));
		referenceTable.put("Illanium",		new Material("Illanium",			MaterialCategory.METAL, 65));
		referenceTable.put("Arcanium",		new Material("Arcanium",			MaterialCategory.METAL, 70));
		referenceTable.put("Eonium",			new Material("Eonium",			MaterialCategory.METAL, 80));
		referenceTable.put("Eternium",		new Material("Eternium",			MaterialCategory.METAL, 90));
		referenceTable.put("Star",			new Material("Star",				MaterialCategory.METAL, 100));
		

		referenceTable.put("Pine",			new Material("Pine",				MaterialCategory.WOOD, 5));
		referenceTable.put("Cedar",			new Material("Cedar",			MaterialCategory.WOOD, 10));
		referenceTable.put("Cypress",		new Material("Cypress",			MaterialCategory.WOOD, 15));
		referenceTable.put("Redwood",		new Material("Redwood",			MaterialCategory.WOOD, 20));
		referenceTable.put("Yew",			new Material("Yew",				MaterialCategory.WOOD, 25));
		referenceTable.put("Walnut",			new Material("Walnut",			MaterialCategory.WOOD, 30));
		referenceTable.put("Ash",			new Material("Ash",				MaterialCategory.WOOD, 35));
		referenceTable.put("Bamboo",			new Material("Bamboo",			MaterialCategory.WOOD, 40));
		referenceTable.put("Balsa",			new Material("Balsa",			MaterialCategory.WOOD, 45));
		referenceTable.put("Birch",			new Material("Birch",			MaterialCategory.WOOD, 50));
		referenceTable.put("Oak",			new Material("Oak",				MaterialCategory.WOOD, 55));
		referenceTable.put("Ebony",			new Material("Ebony",			MaterialCategory.WOOD, 60));
		referenceTable.put("Elm",			new Material("Elm",				MaterialCategory.WOOD, 65));
		referenceTable.put("Marblewood",		new Material("Marblewood",		MaterialCategory.WOOD, 70));
		referenceTable.put("Mahogany",		new Material("Mahogany",			MaterialCategory.WOOD, 75));
		referenceTable.put("Kingwood",		new Material("Kingwood",			MaterialCategory.WOOD, 80));
		referenceTable.put("Ironwood",		new Material("Ironwood",			MaterialCategory.WOOD, 85));
				
		referenceTable.put("Jute",			new Material("Jute",				MaterialCategory.FABRIC, 5));
		referenceTable.put("Wool",			new Material("Wool",				MaterialCategory.FABRIC, 10));
		referenceTable.put("Cotton",			new Material("Cotton",			MaterialCategory.FABRIC, 15));
		referenceTable.put("Flannel",		new Material("Flannel",			MaterialCategory.FABRIC, 20));
		referenceTable.put("Gauze",			new Material("Gauze",			MaterialCategory.FABRIC, 25));
		referenceTable.put("Corduroy",		new Material("Corduroy",			MaterialCategory.FABRIC, 30));
		referenceTable.put("Denim",			new Material("Denim",			MaterialCategory.FABRIC, 35));
		referenceTable.put("Polyester",		new Material("Polyester",		MaterialCategory.FABRIC, 40));
		referenceTable.put("Silk",			new Material("Silk",				MaterialCategory.FABRIC, 45));
		referenceTable.put("Spandex",		new Material("Spandex",			MaterialCategory.FABRIC, 50));
		referenceTable.put("Kevlar",			new Material("Kevlar",			MaterialCategory.FABRIC, 55));
		referenceTable.put("Carbon fibre",	new Material("Carbon fibre",		MaterialCategory.FABRIC, 65));
		referenceTable.put("Spider silk",	new Material("Spider silk",		MaterialCategory.FABRIC, 80));
		referenceTable.put("Graphene fibre",	new Material("Graphene fibre",	MaterialCategory.FABRIC, 100));
		
		referenceTable.put("Sheep",			new Material("Sheep",			MaterialCategory.LEATHER, 5));
		referenceTable.put("Cow",			new Material("Cow",				MaterialCategory.LEATHER, 8));
		referenceTable.put("Wolf",			new Material("Wolf",				MaterialCategory.LEATHER, 10));
		referenceTable.put("Bear",			new Material("Bear",				MaterialCategory.LEATHER, 15));
		referenceTable.put("Lioness",		new Material("Lioness",			MaterialCategory.LEATHER, 17));
		referenceTable.put("Hatchling Dragon", new Material("Hatchling Dragon", MaterialCategory.LEATHER, 20));
		referenceTable.put("Badger",			new Material("Badger",			MaterialCategory.LEATHER, 25));
		referenceTable.put("Lion",			new Material("Lion",				MaterialCategory.LEATHER, 30));
		referenceTable.put("Buffalo",		new Material("Buffalo",			MaterialCategory.LEATHER, 35));
		referenceTable.put("Crocodile",		new Material("Crocodile",		MaterialCategory.LEATHER, 36));
		referenceTable.put("Young Dragon",	new Material("Young Dragon",		MaterialCategory.LEATHER, 40));
		referenceTable.put("Tiger",			new Material("Tiger",			MaterialCategory.LEATHER, 45));
		referenceTable.put("Rhinoceros",		new Material("Rhinoceros",		MaterialCategory.LEATHER, 47));
		referenceTable.put("Panther",		new Material("Panther",			MaterialCategory.LEATHER, 50));
		referenceTable.put("Adult Dragon",	new Material("Adult Dragon",		MaterialCategory.LEATHER, 55));
		referenceTable.put("Shark",			new Material("Shark",			MaterialCategory.LEATHER, 60));
		referenceTable.put("Giant whale",	new Material("Giant whale",		MaterialCategory.LEATHER, 65));
		referenceTable.put("Ancient Dragon",	new Material("Ancient Dragon",	MaterialCategory.LEATHER, 70));
		referenceTable.put("Leviathan",		new Material("Leviathan",		MaterialCategory.LEATHER, 75));
		referenceTable.put("Kraken",			new Material("Kraken",			MaterialCategory.LEATHER, 80));
		referenceTable.put("Wyrm Dragon",	new Material("Wyrm Dragon",		MaterialCategory.LEATHER, 90));
	}
	
	public Material getMaterial(String key) {
		return (Material)referenceTable.get(key);
	}

	
}
