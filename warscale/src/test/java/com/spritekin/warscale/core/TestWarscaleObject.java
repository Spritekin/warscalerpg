package com.spritekin.warscale.core;

import com.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.item.Item;
import com.spritekin.warscale.item.weapon.Weapon;

public class TestWarscaleObject extends WarscaleTestCase {
		
	public void testObjectCreation() {
		
		System.out.println("TestWarscaleObject::testObjectCreation");

		WarscaleObject o = new WarscaleObject("TestItem", Item.ITEM, Item.WEAPON, Weapon.WEAPONTRAIT_SWORD, Weapon.WEAPONSUBTRAIT_MELEE, null);
		assert 	Weapon.WEAPONTRAIT_SWORD == o.getPropertyValue(Item.TRAIT): "Invalid trait";

	}
	
	// Tests if a property can access the values from another property in an object
	public static void testPropertyReferences() {
		System.out.println("TestWarscaleObject::testPropertyReferences");
		WarscaleObject parent = new WarscaleObject("Parent", null);
		parent.addProperty("a", DataType.NUMBER, "1");
		parent.addProperty("b", DataType.NUMBER, "[a]");
		
		assert parent.getPropertyValue("b").equals("1") : "Invalid value: expected 1, found" + parent.getPropertyValue("b");		
	}

	
	// Tests if a property can access the values from another property which in is an object in turn
	public static void testComplexPropertyReferences() {
		System.out.println("TestWarscaleObject::testComplexPropertyReferences");
		WarscaleObject parent = new WarscaleObject("Parent", null);
		parent.addProperty("a", DataType.NUMBER, "1");
		parent.addProperty("b", DataType.NUMBER, "[a]");
		
		assert parent.getPropertyValue("b").equals("1") : "Invalid value: expected 1, found" + parent.getPropertyValue("b");		
	}
}
