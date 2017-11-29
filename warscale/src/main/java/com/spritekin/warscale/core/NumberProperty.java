package com.spritekin.warscale.core;

import javax.script.ScriptException;
import java.util.HashMap;

//A number attribute treats the values as numbers, it will attempt to add or multiply them.
// The modifiers are applied in the order they come. So if a multiplier is added to the modifier list, then a sum, then they will be operated in that order
// The values expressions can have the following prefixes
// + - x / : Standard operators for example 4 +2 x2 will return (4+2)x2 = 12, but 4 x2 +2 will return 10.
// ==       : The value is set to this amount regardless of any past operations

public class NumberProperty extends Property {	

	private static String defaultEmptyValue = "0";

	public NumberProperty(WarscaleObject parent, String name) {
		super(parent, name, DataType.NUMBER, defaultEmptyValue);
	}
	
	//Returns a double value from the 
	private Double getDoubleValue(Expression v) {
		if(v.isEmpty())
			return 0d;
		String s = v.getExpression();
		
		try {
			return Double.parseDouble(s);
		}
		catch(NumberFormatException e) {
			System.out.println("NumberAttribute::getDoubleValue - Returning 0 on invalid expression : " + s );
		}
		return 0d;	//Any invalid format returns 0
	}
	
	private String accumulator;
	
	protected void initAccummulator(HashMap<String, String> vars) {
		accumulator = base.replaceVariables(vars);		
	}
	
	protected void applyModifier(String modifier) {
		String eval = "";
		try {
			if(modifier.startsWith(Operator.EQ))
				accumulator = modifier.substring(1);
			else if(modifier.startsWith(Operator.ADD) || modifier.startsWith(Operator.SUB) || modifier.startsWith(Operator.MUL) || modifier.startsWith(Operator.DIV)) {
				eval = accumulator + modifier;
				accumulator = (String)com.spritekin.warscale.utils.JavascriptUtils.eval(eval);
			}
			else {	//If it has no prefix, assume an addition
				eval = accumulator + "+" + modifier;
				accumulator = (String)com.spritekin.warscale.utils.JavascriptUtils.eval(eval);
			}
		}
		catch(ScriptException e) {
			accumulator = eval;
			System.out.println("NumberProperty::applyModifier - Unable to apply the modifier: " + modifier );
			//e.printStackTrace();
		}
	}
	
	protected void saveValue() {
		// For numbers, do a final computation then save
		try {
			accumulator = (String)com.spritekin.warscale.utils.JavascriptUtils.eval(accumulator);
		} catch (NullPointerException e) {
			System.out.println("NumberProperty::saveValue - Unable to do compute the final value for: " + accumulator );
		} catch (ScriptException e) {
			System.out.println("NumberProperty::saveValue - Unable to do compute the final value for: " + accumulator );
		}
		setValue(accumulator);
	}
					
}
