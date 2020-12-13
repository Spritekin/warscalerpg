package com.spritekin.warscale.core;

import java.util.HashMap;

//A text attribute treats the values as text. It will try to concatenate the values
// Text expressions can have the following prefixes
// =       : The value is set to this text regardless of any past operations
// +       : The value is added to the text. This is the default operation.

public class TextProperty extends Property {	
	
	private static String defaultEmptyValue = "";
		
	public TextProperty(WarscaleObject parent, String name) {
		super(parent, name, DataType.TEXT, defaultEmptyValue);
	}

	private String accumulator;
	
	protected void initAccummulator(HashMap<String, String> vars) {
		accumulator = base.replaceVariables(vars);		
	}
	
	protected void applyModifier(String modifier) {
		if(modifier.startsWith("="))
			accumulator = modifier.substring(1);
		else if(modifier.startsWith("+"))
			accumulator = accumulator + modifier.substring(1);
		else
			// Text modifiers just add the strings
			accumulator = accumulator + modifier;
	}
	
	protected void saveValue() {
		setValue(accumulator);
	}
	
}
