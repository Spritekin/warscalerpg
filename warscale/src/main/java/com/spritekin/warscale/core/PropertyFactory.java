package com.spritekin.warscale.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.material.MaterialCategoryProperty;
import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialProperty;

// grodriguez
// A simple factory for property objects.
public abstract class PropertyFactory {
	
	protected static HashMap<String, Class<? extends Property>> propertyRegistry = new HashMap<String, Class<? extends Property>>();

	static {
		// Register the handlers for each property type
		propertyRegistry.put(DataType.TEXT, TextProperty.class);
		propertyRegistry.put(DataType.NUMBER, NumberProperty.class);
		propertyRegistry.put(MaterialCategory.MATERIALCATEGORY, MaterialCategoryProperty.class);
		propertyRegistry.put(Material.MATERIAL, MaterialProperty.class);
		//propertyRegistry.put(DataType.DAMAGETYPE, DamageTypeProperty.class);
	}

	// A factory builder
	public static Property newPropertyOfType(String type, WarscaleObject parent, String name) {
		// Create an object of the required class, of not defined then it will raise an exception which is ok
		Class<? extends Property> property = propertyRegistry.get(type);
		try {
			return property.getConstructor(WarscaleObject.class, String.class).newInstance(parent, name);
		} catch (InstantiationException ex) {
			return null;
		} catch (InvocationTargetException ex) {
			return null;
		} catch (IllegalAccessException ex) {
			return null;
		} catch (NoSuchMethodException ex) {
			return null;
		}
	}
}
