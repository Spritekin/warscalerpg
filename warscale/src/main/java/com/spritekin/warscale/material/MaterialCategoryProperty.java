package com.spritekin.warscale.material;

import com.spritekin.warscale.core.Library;
import com.spritekin.warscale.core.Operator;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.material.MaterialCategory;
import com.spritekin.warscale.core.Property;

import java.util.HashMap;

// A material category property is just a declaration of a material (i.e. Gold)
// There is no operations in this property, the only accepted operation is the 
// The values expressions can have the following prefixes
// =       : The value is set to this text regardless of any past operations. This is the default operation.

public class MaterialCategoryProperty extends Property {	

	private static String defaultEmptyValue = "";

	public MaterialCategoryProperty(WarscaleObject parent, String name) {
		super(parent, name, MaterialCategory.MATERIALCATEGORY, defaultEmptyValue);
	}
	
	private String accumulator;
	
	protected void initAccummulator(HashMap<String, String> vars) {
		accumulator = base.replaceVariables(vars);		
	}
	
	protected void applyModifier(String modifier) {
		if(modifier.startsWith(Operator.EQ)) {
			if(!Library.contains(MaterialCategory.MATERIALCATEGORY, modifier.substring(1)))
				return;	//Ignore invalid materials				
			accumulator = modifier.substring(1);
		}
		else {
			if(!Library.contains(MaterialCategory.MATERIALCATEGORY, modifier))
				return;	//Ignore invalid materials				
			accumulator = modifier;
		}		
	}
	
	protected void saveValue() {
		setValue(accumulator);
	}
		
}
