package com.spritekin.warscale.dice;

import com.spritekin.warscale.utils.StringUtils;
import java.util.Random;

public class DiceRoller {

	private static Random dice = new Random(System.nanoTime());
	private static String CONCATENATOR = "+";
	private static String MULTICONCATENATOR = ",";

	//Dice expressions
	private static String SEPARATOR = "d";				//Standard dice separator
	
	//This is a more multi roll dice expresion parser. It will recognize ',' as a concatenation symbol for multiple dice expression
	//The results will be returned in a string also separated by ','. ie. 2,3,5
	//Each expression is independent. If one fails it will return 0 but the others may survive
	public static String rollDiceMulti(String expr) {
		expr = expr.trim();
		String res = "";
		while(expr.length() > 0) {
			String currentExpr = StringUtils.getValueBefore(expr, MULTICONCATENATOR).trim();
			expr = StringUtils.getValueAfter(expr, MULTICONCATENATOR).trim();
			
			int val = DiceRoller.rollDiceEx(currentExpr);
			if(res.length() == 0)
				res += val;
			else
				res += "," + val;
		}
		return res;
	}
	
	//This is a more advanced dice expresion parser. It will recognize + as a concatenation symbol for multiple dice
	//It will also determine if some expresions are constant like in the case of 1d6 + 2 it will roll a d6 and add 2
	//Expressions will be trimmed where possible.
	//Empty expresions will be ignored, for example in 1d10 + + 1d6 + 1, the second expression will be ignored. This means an expression like "+" will return 0
	//There is no limit to the number of concatenated expresions but if one of them fails then all the expression fails
	private static int rollDiceEx(String expr) {
		expr = expr.toLowerCase().trim();	//To lowercase and trim spaces
		
		//Break the expression using + and execute each one
		int res = 0;
		while(expr.length() > 0) {
			String currentExpr = StringUtils.getValueBefore(expr, CONCATENATOR).trim();
			expr = StringUtils.getValueAfter(expr, CONCATENATOR).trim();
			//System.out.println("Evaluating " + currentExpr);
			if(currentExpr.length() == 0)
				continue;	//Ignore empty expressions
			
			//Check if the expression has a d separator
			int val = 0;
			if(currentExpr.indexOf(SEPARATOR) < 0) {
				//System.out.println(currentExpr + " - No separator. If it can't be transformed to an integer then all the expression fails");
				try {
					val = Integer.parseInt(currentExpr);
				}
				catch (NumberFormatException e) {
					return 0;	//If unable to get a value, terminate the roll
				}
			}
			else {
				val = rollDice(currentExpr);
				//System.out.println(currentExpr + " = " + val);
				if(val == 0)
					return 0;	//If there is an error in the dice expression, return 0
			}
			res += val;
		}

		return res;
		
	}
	
	//This method will receive a expresion like d20, 1d20, 3d6 and run the appropriate results
	//The dice will return a 0 if the expresion does not evaluate
	//The expresion will trim spaces where appropriate, so expresions like " 3 d 6 " will still be accepted
	//A d1 will return 1.
	private static int rollDice(String expr) {
		expr = expr.toLowerCase().trim();	//To lowercase and trim spaces

		//If there is no 'd' then it is an invalid expresion
		if(expr.indexOf(SEPARATOR) < 0)
			return 0;

		//Break and trim
		String numberExpr = StringUtils.getValueBefore(expr, SEPARATOR).trim();
		String diceExpr = StringUtils.getValueAfter(expr, SEPARATOR).trim();

		//Try transfoming the dice expression into an integer value, if there is an exception return 0
		int diceSides = 0;
		try {
			diceSides = Integer.parseInt(diceExpr);			
		}
		catch (NumberFormatException e) {
			return 0;	//If unable to get a value, terminate the roll
		}

		//If we are here then the dice was recognized as some kind of valid number. Reject quickly to save time.
		if(diceSides < 1) 
			return 0;

		//Now parse the dice number
		int diceNumber = 0;
		if(numberExpr.length() == 0)
			diceNumber = 1;		//If no dice number assume 1
		else {
			try {
				diceNumber = Integer.parseInt(numberExpr);			
			}
			catch (NumberFormatException e) {
				return 0;	//If unable to get a value, terminate the roll
			}
		}		

		//So we have a number of dice and dice type, shake them!!!
		return rollDice(diceNumber, diceSides);
	}

	public static int rollDice(int number, int sides) {
		int res = 0;
		//System.out.println(number + " - " + sides);
		for(int i = 0; i<number; i++) {
			int roll = rollDice(sides);
			if(roll == 0)
				return 0;	//On any error break and exit
			res += roll;
		}
		return res;

	}
	
	//This method rolls a dice. The number of sides should be provided
	//The dice will return a 0 if the expresion does not evaluate
	private static int rollDice(int sides) {
		if(sides < 1) 
			return 0;
		if(sides == 1) 
			return 1;
		return dice.nextInt(sides) + 1;
	}

	//Just to test some dice
	public static void main(String[] args) {
		for(int i = 1; i<=20; i++) 
			System.out.println(rollDiceEx("2d10 + d4 + 2"));
		for(int i = 1; i<=20; i++) 
			System.out.println(rollDiceMulti("3d6,2d3+1,2d10 + d4 + 2"));

	}

}
