package com.spritekin.warscale.core;

import com.spritekin.warscale.core.DataType;

import com.spritekin.warscale.WarscaleTestCase;

public class ExpressionTest extends WarscaleTestCase {

	public static void testExpression() {
		com.spritekin.warscale.core.Expression exp =  new com.spritekin.warscale.core.Expression(DataType.NUMBER, "2 + [Fire  ]/[ Water ] * 5 / [ Fire  ]");
		java.util.HashMap<String, String> vars = exp.getVariableList();
		if(vars.containsKey("Fire"))
			vars.put("Fire", "8");
		//This should return the expression with fire replaced		
		assertEquals("testExpression", "2 + 8/[ Water ] * 5 / 8", exp.replaceVariables(vars));
		
		if(vars.containsKey("Water"))
			vars.put("Water", "bad value");
		//This should fully evaluate, fail and return 0 as it is numeric
		assertEquals("testExpression", "2 + 8/bad value * 5 / 8", exp.replaceVariables(vars));

		if(vars.containsKey("Water"))
			vars.put("Water", "9");

		//This should fully evaluate and return the result as it is numeric
		assertEquals("testExpression", "2 + 8/9 * 5 / 8", exp.replaceVariables(vars));

	}
	

}
