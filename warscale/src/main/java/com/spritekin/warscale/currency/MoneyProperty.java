package com.spritekin.warscale.currency;

import javax.script.ScriptException;

import com.spritekin.warscale.core.Operator;
import com.spritekin.warscale.core.Property;
import com.spritekin.warscale.core.WarscaleObject;

import java.util.HashMap;

//A money property works with coin expressions like for example 10gp,5sp,3bp
//Each part of the expression is independent and will be operated as a number, the coin types are applied to the result, for example:
//  3+5gp will result in 8gp.
// The property modifiers are applied in-order.
// The modifier expression can have the following prefixes
// =       : The value is set to this amount regardless of any past operations

public class MoneyProperty extends Property {	

	private static String defaultEmptyValue = "";

	public MoneyProperty(WarscaleObject parent, String name) {
		super(parent, name, Money.MONEY, defaultEmptyValue);
	}
	
	// Evaluates the expression
	private String eval(String moneyexpr) throws ScriptException {
		// Initialize a hash with some coins
		HashMap<String, String> coins = new HashMap<String, String>();

		while(moneyexpr.length() > 0) {
			String c = com.spritekin.warscale.utils.StringUtils.getValueBefore(moneyexpr, ",").trim();
			moneyexpr = com.spritekin.warscale.utils.StringUtils.getValueAfter(moneyexpr, ",");

			String value = c.substring(0,c.length()-2);
			String type = c.substring(c.length()-2);
			if(!Money.isValidType(type))
				throw new ScriptException("Invalid money expression " + c);
			
			String evaluated = (String)com.spritekin.warscale.utils.JavascriptUtils.eval(value);
			coins.put(type, evaluated);
			
		}
		
		// Reintegrate in order of coin value
		String res = "";
		if(coins.containsKey(Money.TITANIUM_PIECE))
			res = coins.get(Money.TITANIUM_PIECE) + Money.TITANIUM_PIECE;
		
		if(coins.containsKey(Money.GOLD_PIECE)) {
			if(res.length() > 0) res += ",";
			res += coins.get(Money.GOLD_PIECE) + Money.GOLD_PIECE;
		}

		if(coins.containsKey(Money.SILVER_PIECE)) {
			if(res.length() > 0) res += ",";
			res += coins.get(Money.SILVER_PIECE) + Money.SILVER_PIECE;
		}

		if(coins.containsKey(Money.BRONZE_PIECE)) {
			if(res.length() > 0) res += ",";
			res += coins.get(Money.BRONZE_PIECE) + Money.BRONZE_PIECE;
		}
		return res;
		
	}
	
	private String accumulator;
	
	protected void initAccummulator(HashMap<String, String> vars) {
		accumulator = base.replaceVariables(vars);		
	}
	
	protected void applyModifier(String modifier) {
		String eval = "";
		try {
			if(modifier.startsWith(Operator.EQ)) {
				eval = modifier.substring(1);
				accumulator = eval(eval);
			}
			else {	//If it has no prefix
				eval = modifier;
				accumulator = eval(eval);
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
			accumulator = eval(accumulator);
		} catch (NullPointerException e) {
			System.out.println("NumberProperty::saveValue - Unable to do compute the final value for: " + accumulator );
		} catch (ScriptException e) {
			System.out.println("NumberProperty::saveValue - Unable to do compute the final value for: " + accumulator );
		}
		setValue(accumulator);
	}

	
}
