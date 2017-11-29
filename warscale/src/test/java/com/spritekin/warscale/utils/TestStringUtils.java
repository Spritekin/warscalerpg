package com.spritekin.warscale.utils;

public class TestStringUtils {
	
	public static void main(String[] args) {
		System.out.println(com.spritekin.warscale.utils.StringUtils.replaceVariable("2 + [ Fire] * 5", "Fire", "4"));
		System.out.println(com.spritekin.warscale.utils.StringUtils.replaceVariable("2 + [Fire ] * 5", "Water", "4"));
		System.out.println(com.spritekin.warscale.utils.StringUtils.replaceVariable("2 + [Fire]/[ Water ] * 5", "Water", "4"));
		System.out.println(com.spritekin.warscale.utils.StringUtils.replaceVariable("2 + [Fire  ]/[ Water ] * 5 / [ Fire  ]", "Fire", "4"));
		
		String exp = "2 + [Fire  ]/[ Water ] * 5 / [ Fire  ]";
		java.util.HashMap<String, String> vars = com.spritekin.warscale.utils.StringUtils.getVariables(exp);
		for(String key : vars.keySet())
			System.out.println("Variables in expression : " + key);
			
		if(vars.containsKey("Fire"))
			vars.put("Fire", "8");
		if(vars.containsKey("Water"))
			vars.put("Water", "9");		
		System.out.println("Multiple variable replace = " + com.spritekin.warscale.utils.StringUtils.replaceVariables(exp, vars));
	}
}
