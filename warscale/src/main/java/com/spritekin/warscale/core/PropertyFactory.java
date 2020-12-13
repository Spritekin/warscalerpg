package com.spritekin.warscale.core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.material.MaterialCategoryProperty;
import com.spritekin.warscale.currency.Money;
import com.spritekin.warscale.currency.MoneyProperty;
import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialProperty;
import com.spritekin.warscale.utils.StringUtils;

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
		propertyRegistry.put(Money.MONEY, MoneyProperty.class);
		//propertyRegistry.put(DataType.DAMAGETYPE, DamageTypeProperty.class);
	}

	// A factory builder
	public static Property newPropertyOfType(String type, WarscaleObject parent, String name) {
		// Create an object of the required class, of not defined then it will raise an exception which is ok
		Class<? extends Property> property = propertyRegistry.get(type);
		try {
			return property.getConstructor(WarscaleObject.class, String.class).newInstance(parent, name);
		} catch (InstantiationException ex) {
			throw new PropertyInstantiationException("newPropertyOfType::InstantiationException");
		} catch (InvocationTargetException ex) {
			throw new PropertyInstantiationException("newPropertyOfType::InvocationTargetException");
		} catch (IllegalAccessException ex) {
			throw new PropertyInstantiationException("newPropertyOfType::IllegalAccessException");
		} catch (NoSuchMethodException ex) {
			throw new PropertyInstantiationException("newPropertyOfType::NoSuchMethodException");
		}
	}
	
	// Builds a property from an FQPE, still needs a parent
	public static Property newPropertyFromExpression(WarscaleObject parent, String fqpe) {
		boolean shared = false;
		String name = StringUtils.getValueBefore(fqpe, " ");
        if(name.equals("shared")) {
    			shared = true;
	        fqpe = StringUtils.getValueAfter(fqpe, " ");
	        name = StringUtils.getValueBefore(fqpe, " ");        		
	    }
		
        fqpe = StringUtils.getValueAfter(fqpe, " ");
		String type = StringUtils.getValueBefore(fqpe, " ");
		String value = StringUtils.getValueAfter(fqpe, " ");
		
		Property res = newPropertyOfType(type, parent, name);		
		res.setShared(shared);
		res.setBase(value);
		return res;
	}

	
}
