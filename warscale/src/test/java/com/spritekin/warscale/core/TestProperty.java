package com.spritekin.warscale.core;

import com.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.NumberProperty;
import com.spritekin.warscale.core.TextProperty;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.core.PropertyFactory;

public class TestProperty extends WarscaleTestCase {

	public static void testSetPropertyBase() {
		//Testing properties	
		System.out.println("TestProperty::testSetPropertyBase");
		WarscaleObject wo = new WarscaleObject("Dummy Object")
			.setProperty(WarscaleObject.TYPE, "Dummy type");

		// Create three properties with the same name
		TextProperty na = new TextProperty(wo, "TextAttribute");
		na.setBase("This is the base.");
		
		assertEquals("Invalid base", "This is the base.", na.getValue());
	}
		
	public static void testPropertyAddition() {
		//Testing properties	
		System.out.println("TestProperty::testPropertyAddition");
		WarscaleObject wo = new WarscaleObject("Dummy Object", "Dummy type", null);

		// Create three properties with the same name
		TextProperty na = new TextProperty(wo, "TextAttribute");
		na.setBase("This is the base.");
		 
		TextProperty nm = new TextProperty(wo, "TextAttribute");
		nm.setBase("The object name is ["+WarscaleObject.NAME+"].\n");

		TextProperty tm = new TextProperty(wo, "TextAttribute");
		tm.setBase("The object type is ["+WarscaleObject.TYPE+"].\n");

		// Now I will add the modifier nm to na so I should have a concatenation
		na.addModifier(nm);		
		assertEquals("Invalid base", "This is the base.The object name is Dummy Object.\n", na.getValue());

		// Now I will add even more modifiers to na so the description should be longer
		na.addModifier(tm);
		na.addModifier(tm);
		na.addModifier(tm);

		assertEquals("Invalid base", "This is the base.The object name is Dummy Object.\nThe object type is Dummy type.\nThe object type is Dummy type.\nThe object type is Dummy type.\n", na.getValue());
		
		TextProperty rm = new TextProperty(wo, "TextAttribute");
		rm.setBase("=This is a replacement");
		na.addModifier(rm);
		
		assertEquals("Invalid base", "This is a replacement", na.getValue());
		
		na.removeModifier(rm);
		na.removeModifier(tm);

		assertEquals("Invalid base", "This is the base.The object name is Dummy Object.\nThe object type is Dummy type.\nThe object type is Dummy type.\n", na.getValue());

		na.removeModifier(nm);
		assertEquals("Invalid base", "This is the base.The object type is Dummy type.\nThe object type is Dummy type.\n", na.getValue());

		na.removeModifier(rm);
		assertEquals("Invalid base", "This is the base.The object type is Dummy type.\nThe object type is Dummy type.\n", na.getValue());

	}
	
	public static void testNumberProperty() {
		System.out.println("TestProperty::testNumberProperty");
		WarscaleObject parent = new WarscaleObject("Parent Object");
		NumberProperty number = (NumberProperty)PropertyFactory.newPropertyOfType(DataType.NUMBER, parent, "TestNumberProperty");
		number.setBase("1");
		assert number.getValue().equals("1") : "Invalid value: expected 1, found" + number.getValue();
		
		number.addModifier(new NumberProperty(parent, "TestNumberProperty").setBase("-1234.5"));
		assert number.getValue().equals("-1233.5") : "Invalid value: expected 1, found" + number.getValue();

		number.addModifier(new NumberProperty(parent, "TestNumberProperty").setBase("+1234.5"));
		assert number.getValue().equals("1") : "Invalid value: expected 1, found" + number.getValue();

		number.addModifier(new NumberProperty(parent, "TestNumberProperty").setBase("=2"));
		assert number.getValue().equals("2") : "Invalid value: expected 2, found" + number.getValue();

		number.addModifier(new NumberProperty(parent, "TestNumberProperty").setBase("This is an error"));
		assert number.getValue().equals("2+This is an error") : "Invalid value " + number.getValue();
		
	}

}
