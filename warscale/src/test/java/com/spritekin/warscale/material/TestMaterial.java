package com.spritekin.warscale.material;

import com.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.core.Library;
import com.spritekin.warscale.core.PropertyFactory;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.material.MaterialProperty;
import com.spritekin.warscale.material.MaterialTable;

public class TestMaterial extends WarscaleTestCase {

	public void testMaterial() {
		System.out.println("TestMaterial::testMaterial");
		int testQuality = 25;
		Material m = new Material("Gold", MaterialCategory.METAL, testQuality);
		
		int quality = m.getQuality();
		int expectedQuality = testQuality;
		assert quality == expectedQuality :
			"Invalid quality: expected" + expectedQuality + ", found " + quality;

		
		int modifier = m.getModifier();
		int expectedModifier = testQuality/5;
		assert modifier == expectedModifier :
			"Invalid modifier: expected" + expectedModifier + ", found " + modifier;
		
		int hardness = m.getHardness();
		int expectedHardness = Integer.parseInt(Library.getValue(MaterialCategory.MATERIALCATEGORY, MaterialCategory.METAL, MaterialCategory.BASEHARDNESS)) + testQuality / 5;
		assert hardness == expectedHardness :
			"Invalid hardness: expected" + expectedHardness + ", found " + hardness;

		String hard = m.getPropertyValue(MaterialCategory.MATERIALCATEGORY+"."+MaterialCategory.MATERIALUNITS);
		assert hard.equals(MaterialCategory.KG) :
			"Invalid hardness: expected" + MaterialCategory.KG + ", found " + hard;
		
	}
	
	public void testMaterialTable() {
		System.out.println("TestMaterial::testMaterialTable");
		MaterialTable mt = new MaterialTable();

		// Test direct value access
		assert mt.getValue("Gold", Material.MATERIALHARDNESS).equals("17") : "Invalid material hardness: expected 17, found " + mt.getValue("Gold", Material.MATERIALHARDNESS);

	}

	public void testMaterialProperty() {
		System.out.println("TestMaterial::testMaterialProperty");
		WarscaleObject parent = new WarscaleObject("Parent");
		MaterialProperty mp = (MaterialProperty)PropertyFactory.newPropertyOfType(Material.MATERIAL, parent, "TestMaterialProperty");
		String matName = "Gold";
		mp.setBase(matName);
		assert mp.getValue().equals(matName) :
			"Invalid value: expected " +  matName + ", found" + mp.getValue();
		
		mp.addModifier(new MaterialProperty(parent, "TestMaterialProperty").setBase("=Steel"));
		assert mp.getValue().equals("Steel") :
			"Invalid value: expected Steel, found " + mp.getValue();

		mp.addModifier(new MaterialProperty(parent, "TestMaterialProperty").setBase("Silver"));
		assert mp.getValue().equals("Silver") :
			"Invalid value: expected Silver, found " + mp.getValue();
		
		// Check Silver hardness
		assert mp.getValue(Material.MATERIALHARDNESS).equals("15") :
			"Invalid value: expected 15, found " + mp.getValue(Material.MATERIALHARDNESS);

		// Check metal basehardness
		assert mp.getValue(MaterialCategory.MATERIALCATEGORY+"."+MaterialCategory.BASEHARDNESS).equals("12") :
			"Invalid value: expected 12, found " + mp.getValue(MaterialCategory.MATERIALCATEGORY+"."+MaterialCategory.BASEHARDNESS);

	}
}
