package com.spritekin.warscale.core;

/*
 * grodriguez
 * 
 * Declares the operations accepted by warscale. This is just a reference list. There is nothing preventing the creation
 * of fancy operators for special cases.
 * The standard operators are:
* <no sign> : Same as +
* + : Expressions should be added if numbers or concatenated if strings
* - : Expressions should be subtracted. Numbers only.
* * : Expressions should be multiplied. Numbers only.
* / : Expressions should be divided if a number. If a string then the expression should be ignored and the original is maintained.
* = : The new expression replaces whatever came before. The sign of the original expression is maintained.
*/
public class Operator {
	public static final String ADD = "+";
	public static final String SUB = "-";
	public static final String MUL = "*";
	public static final String DIV = "/";
	public static final String EQ = "=";
}
