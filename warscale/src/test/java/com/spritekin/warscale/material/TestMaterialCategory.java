package com.spritekin.warscale.material;

import com.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.core.PropertyFactory;
import com.spritekin.warscale.core.ReferenceTable;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.material.MaterialCategoryProperty;

public class TestMaterialCategory extends WarscaleTestCase {

	public void testMaterialCategory() {
		System.out.println("TestMaterialCategory::testMaterialCategory");
		MaterialCategory mc = new MaterialCategory(MaterialCategory.METAL, 12, MaterialCategory.KG);

		// Test methods
		assert mc.getName() == MaterialCategory.METAL : "Invalid name: " + MaterialCategory.METAL;
		assert mc.getBaseHardness() == 12 : "Invalid material hardness: " + 12;

		// Test properties
		assert mc.getProperty(MaterialCategory.PROPERTY_NAME).getValue() == MaterialCategory.METAL : "Invalid name: " + MaterialCategory.METAL;
		assert mc.getBaseHardness() == 12 : "Invalid material hardness: " + 12;

	}

	public void testMaterialCategoryTable() {
		System.out.println("TestMaterialCategory::testMaterialCategoryTable");
		ReferenceTable mct = ReferenceTable.fromWarscaleData("MaterialCategory.wsd");

		// Test direct value access
		assert mct.getValue(MaterialCategory.METAL, MaterialCategory.BASEHARDNESS).equals("12") : "Invalid material hardness: expected 12, found " + mct.getValue(MaterialCategory.METAL, MaterialCategory.BASEHARDNESS);

	}

	public void testMaterialCategoryProperty() {
		System.out.println("TestMaterialCategory::testMaterialCategoryProperty");
		WarscaleObject parent = new WarscaleObject("Parent Object");
		MaterialCategoryProperty mcp = (MaterialCategoryProperty)PropertyFactory.newPropertyOfType(MaterialCategory.MATERIALCATEGORY, parent, "TestMaterialCategoryProperty");
		mcp.setBase(MaterialCategory.METAL);
		assert mcp.getValue().equals(MaterialCategory.METAL) :
			"Invalid value: expected " + MaterialCategory.METAL + ", found" + mcp.getValue();
		
		mcp.addModifier(new MaterialCategoryProperty(parent, "TestMaterialCategoryProperty").setBase("="+MaterialCategory.WOOD));
		assert mcp.getValue().equals(MaterialCategory.WOOD) :
			"Invalid value: expected "+MaterialCategory.WOOD+", found " + mcp.getValue();

		mcp.addModifier(new MaterialCategoryProperty(parent, "TestMaterialCategoryProperty").setBase(MaterialCategory.LEATHER));
		assert mcp.getValue().equals(MaterialCategory.LEATHER) :
			"Invalid value: expected "+MaterialCategory.LEATHER+", found " + mcp.getValue();
		
		assert mcp.getValue(MaterialCategory.BASEHARDNESS).equals("4") :
			"Invalid value: expected 4, found " + mcp.getValue(MaterialCategory.BASEHARDNESS);
		
	}

}
