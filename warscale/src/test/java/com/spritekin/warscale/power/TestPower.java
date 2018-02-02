package com.spritekin.warscale.power;

import com.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.core.PropertyFactory;
import com.spritekin.warscale.core.ReferenceTable;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.item.Item;
import com.spritekin.warscale.item.weapon.Weapon;
import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.material.MaterialCategoryProperty;

public class TestPower extends WarscaleTestCase {

	public void testPower() {
		System.out.println("TestPower::testPower");
		// A power is just a compound object. The only thing to test is if its really passing the
		// properties to the parent when used and if properties are modified

		// I will create a random object, based on a material
		String testQuality = "25";
		Material m = new Material("Gold", MaterialCategory.METAL, testQuality);

		// Now I will create another object, this time with a couple attributes, one that the material has
		// and another the material doesn't.
		String[] properties = {
				"shared MaterialQuality number +5",  // Shared, material quality should increase to 30
				"shared Dummy text testing"  // Shared, but this property doesn't exist
		};
		
		WarscaleObject power = new WarscaleObject("TestPower", properties);
		m.mount(power, "power", 0);

		// Check properties
		assert m.getPropertyValue(Material.NAME).equals("Gold") : "Invalid name: " +  m.getPropertyValue(MaterialCategory.NAME);
		
		assert m.getPropertyValue(Material.MATERIALQUALITY).equals("30") : "Invalid material quality: " + m.getPropertyValue(Material.MATERIALQUALITY);

		assert m.getPropertyValue("Dummy").equals("testing") : "Invalid dummy property: " + m.getPropertyValue("Dummy");

	}

	public void testPowerTable() {
		System.out.println("TestPower::testPowerTable");
		ReferenceTable pt = ReferenceTable.fromWarscaleYAML("resources/powers/Power.yml");

		// Test direct value access
		assert pt.getValue("Experienced", "CharacterPoints").equals("6") : "Invalid power: expected 6, found " + pt.getValue("Experienced", "CharacterPoints");

	}
	
}
