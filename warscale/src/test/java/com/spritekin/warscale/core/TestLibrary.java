package com.spritekin.warscale.core;

import com.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.core.Library;
import com.spritekin.warscale.material.MaterialCategory;

public class TestLibrary extends WarscaleTestCase {
	
	public static void testTextAttribute() {

		String baseHardness = Library.getValue(MaterialCategory.MATERIALCATEGORY, MaterialCategory.METAL, MaterialCategory.BASEHARDNESS);
		// Test direct value access
		assert baseHardness.equals("12") : 
			"Invalid material hardness: expected 12, found " + baseHardness;

	}
}
