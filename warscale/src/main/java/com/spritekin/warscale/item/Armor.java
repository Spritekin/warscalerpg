package com.spritekin.warscale.item;

import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialCategory;

public class Armor extends Item {

	protected Armor(String name, String trait, String subtrait,
			String dr, String price, String materialCategory, String weight) {
		super(name, ITEMSUBTYPE_ARMOR, trait, subtrait, dr, Material.get(MaterialCategory.get(materialCategory), 5), weight);
		
		//modifiers = new ArrayList<Armor>();

	}

}
