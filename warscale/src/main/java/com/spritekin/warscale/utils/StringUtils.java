package com.spritekin.warscale.utils;

//Different functions to work with strings
public class StringUtils {
	
	//String parsing
	public static String getValueBefore(String s, String sep) {
		int idx = s.indexOf(sep);
		if(idx < 0) 
			return s;
		return s.substring(0, idx);
	}

	public static String getValueAfter(String s, String sep) {
		int idx = s.indexOf(sep);
		if(idx < 0) 
			return "";
		return s.substring(idx + sep.length());
	}

	
	//-----------------------------------------------------------------------------------------------
	//Variable analysis
	// Warscale considers all variables as text enclosed by brackets []

	//replaces a parameter on a expression
	public static String replaceVariable(String exp, String key, String value) {
		if(value == null)
			return exp;	//null values are not replaced
		String res = "";
		while(exp.length() > 0) {
			res += getValueBefore(exp, "[");
			exp = getValueAfter(exp, "[");
			if(exp.length() == 0)
				continue;
			String var = getValueBefore(exp, "]");
			exp = getValueAfter(exp, "]");
			if(var.trim().equals(key))
				res += value;
			else
				res += "[" + var + "]";
		}
		return res;
	}

	public static boolean hasVariables(String exp) {
		if(exp.contains("[")) {
			return true;
		}
		return false;
	}

	public static java.util.HashMap<String, String> getVariables(String exp) {
		java.util.HashMap<String, String> res = new java.util.HashMap<String, String>();
		while(hasVariables(exp)) {
			exp = StringUtils.getValueAfter(exp, "[");
			res.put(StringUtils.getValueBefore(exp, "]").trim(), null);
			exp = StringUtils.getValueAfter(exp, "]");
		}
		return res;
	}
	
	public static String replaceVariables(String exp, java.util.HashMap<String, String> vars) {
		String res = exp;
		for(String key : vars.keySet()) {
			res = replaceVariable(res, key, vars.get(key));
		}		
		return res;
	}

}
