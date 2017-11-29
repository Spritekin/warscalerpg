package com.spritekin.warscale.utils;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

//This is a wrapper that keeps a javascript session alive
//This util is used only to evaluate small expressions that can be assigned to a variable
public class JavascriptUtils {

	private static ScriptEngineManager factory = new ScriptEngineManager();
	private static ScriptEngine engine = null;

    static synchronized ScriptEngine getEngine() {
    	if(engine == null) {
    	    engine = factory.getEngineByName("JavaScript"); 
    	}
    	return engine;
    }
    
    public static Object eval(String expr) throws ScriptException, NullPointerException {
    	getEngine().eval("res = (String)(" + expr + ");");
    	return getEngine().get("res");
    }
    
}
