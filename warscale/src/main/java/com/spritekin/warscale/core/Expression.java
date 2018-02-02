package com.spritekin.warscale.core;

import java.util.HashMap;
/*
 * grodriguez
 * 
 * Expressions
 * A expression is a mathematical formula. It may contain variables enclosed in brackets [] 
 * Example:
 *  +5 
 *  +[Air]
 *
 * The expression does not evaluate itself. It is just a formula. It has no context so the parameters have no meaning.
 * The expression carries a datatype parameter which allows to declare its type. The expression doesn't have a concept of the "type",
 *  the purpose is to declare what the expression means. This information can be used by expression operators to accept, reject or
 *  do alternate operations on the expressions.
 * For convenience and speed, a expression has a list of variables which are extracted from the expression for easy access, it also
 *  provides methods to replace the variables in the expression and return a cleaner expression. It does not, however, compute the
 *  expression.
 * Expressions may be prefixed by an operator which say how to combine this expression with other expressions. 
 *
 * All properties in Warscale use expressions. 
*/
public class Expression {
	
	private String datatype = "";
	private String expression = "";
	private HashMap<String, String> variables = new HashMap<String, String>();

	public Expression(String datatype, String expression) {
		setDatatype(datatype);
		setExpression(expression);
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getExpression() {
		return expression;
	}

	//Sets the expression and extracts the parameters
	public void setExpression(String expression) {
		this.expression = expression;
		variables = com.spritekin.warscale.utils.StringUtils.getVariables(this.expression);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		return result;
	}

	//The equals method only cares if it is the same object.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return false;
	}	
	
	public boolean isEmpty() {
		return expression == null || expression.length() == 0 || expression.equals("-");		
	}

	//This method will return a hashmap with the required parameter list
	//This is a copy of the original list so the original is preserved. Entries are null.
	// The parameter list should be filled by the caller and used for the evaluate function
	public HashMap<String, String> getVariableList() {
		HashMap<String, String> res = new HashMap<String, String>();
		for(String key : variables.keySet())
			res.put(key, null);
		return res;
	}
	
	// Replaces variables with the values given in the hashmap
	// Returns an expression with as many values replaced as possible.
	public String replaceVariables(HashMap<String, String> vars) {
		String exp = getExpression();
		
		//Go on and replace all possible parameters
		exp = com.spritekin.warscale.utils.StringUtils.replaceVariables(exp, vars);
		return exp;		
	}

}
