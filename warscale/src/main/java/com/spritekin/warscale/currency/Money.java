package com.spritekin.warscale.currency;

import java.util.HashMap;

//This class tracks warscale money
// Money is a collection of coins on different types
// Money is maintained this way so that the coins on each type are never mixed
// Money can be expressed as a comma separated string, for example "3gp,4bp" means 3 gold and 4 bronze.
public class Money {
	
	public static String MONEY = "Money";
	
	public static String BRONZE_PIECE 	= "bp";
	public static String SILVER_PIECE 	= "sp";
	public static String GOLD_PIECE 		= "gp";
	public static String TITANIUM_PIECE	= "tp";

	protected static HashMap<String, Double> conversion = new HashMap<String, Double>();
	static {
		conversion.put(BRONZE_PIECE, 1d);
		conversion.put(SILVER_PIECE, 100d);
		conversion.put(GOLD_PIECE, 10000d);
		conversion.put(TITANIUM_PIECE, 1000000d);
	}

	// A hash with different types of coins
	protected HashMap<String, Double> coins = new HashMap<String, Double>();

	// By default, we initialize with an empty purse
	public Money() {
		coins.put(TITANIUM_PIECE, 0d);
		coins.put(GOLD_PIECE, 0d);
		coins.put(SILVER_PIECE, 0d);
		coins.put(BRONZE_PIECE, 0d);
	}

	//Initialize using a comma separated string with coin values
	//
	public Money(String icoins) {
		coins.put(TITANIUM_PIECE, 0d);
		coins.put(GOLD_PIECE, 0d);
		coins.put(SILVER_PIECE, 0d);
		coins.put(BRONZE_PIECE, 0d);
		set(icoins);
	}

	// Zeroes all money in this object
	private void clear() {
		for(String s : coins.keySet()) {
			coins.put(s, 0d);
		}
	}

	//Set the coins of a certain type
	//type must be specified
	public void set(String coins) {
		clear();
		while(coins.length() > 0) {
			String c = com.spritekin.warscale.utils.StringUtils.getValueBefore(coins, ",");
			coins = com.spritekin.warscale.utils.StringUtils.getValueAfter(coins, ",");
			if(isValidMoneyExpr(c))
				add(c);
		}		
	}

	//Truncates the excess of each expression and moves excesses to the next level
	private void truncate() {
		Double excess = coins.get(TITANIUM_PIECE) - Math.floor(coins.get(TITANIUM_PIECE));
		coins.put(TITANIUM_PIECE, Math.floor(coins.get(TITANIUM_PIECE)));
		coins.put(GOLD_PIECE, coins.get(GOLD_PIECE) + excess * 100);

		
		excess = coins.get(GOLD_PIECE) - Math.floor(coins.get(GOLD_PIECE));
		coins.put(GOLD_PIECE, Math.floor(coins.get(GOLD_PIECE)));
		coins.put(SILVER_PIECE, coins.get(SILVER_PIECE) + excess * 100);

		excess = coins.get(SILVER_PIECE) - Math.floor(coins.get(SILVER_PIECE));
		coins.put(SILVER_PIECE, Math.floor(coins.get(SILVER_PIECE)));
		coins.put(BRONZE_PIECE, Math.floor(coins.get(BRONZE_PIECE) + excess * 100));
		
	}

	public void scale(double scale) {
		coins.put(TITANIUM_PIECE, Math.round(coins.get(TITANIUM_PIECE) * scale * 100d)/100d);
		coins.put(GOLD_PIECE, Math.round(coins.get(GOLD_PIECE) * scale * 100d)/100d);
		coins.put(SILVER_PIECE, Math.round(coins.get(SILVER_PIECE) * scale * 100d)/100d);
		coins.put(BRONZE_PIECE, Math.round(coins.get(BRONZE_PIECE) * scale * 100d)/100d);

		System.out.println("Check scale" + toStringBase());
		truncate();
		
	}
	
	// Adds money, keeps coin types separate 
	public void add(String moneyExpr) {
		if(!isValidMoneyExpr(moneyExpr))
			return;

		while(moneyExpr.length() > 0) {
			String c = com.spritekin.warscale.utils.StringUtils.getValueBefore(moneyExpr, ",");
			moneyExpr = com.spritekin.warscale.utils.StringUtils.getValueAfter(moneyExpr, ",");

			String type = getMoneyExprType(c);
			Double amt = getMoneyExprValue(c);
		
			coins.put(type, coins.get(type) + amt);
		}
	}

	public Double toBronze() {
		return coins.get(TITANIUM_PIECE) * conversion.get(TITANIUM_PIECE) +
			coins.get(GOLD_PIECE) * conversion.get(GOLD_PIECE) +
			coins.get(SILVER_PIECE) * conversion.get(SILVER_PIECE) +
			coins.get(BRONZE_PIECE) * conversion.get(BRONZE_PIECE);
		
	}
	
	//Returns all converted to this coin type
	public String getAs(String type) {
		if(!isValidType(type))
			return "";
		
		//Transform everything to the minimum common type bp, then scale to the 
		Double toType = toBronze() / conversion.get(type);
		return ""+(int)Math.floor(toType) + type;
		
	}

	public String get(String type) {
		if(!isValidType(type))
			return "";
		return coins.get(type) + type;
	}
	
	//Returns the money type, basically cuts the last 2 characters from the expression
	//This works with a single expression, non comma separated
	private static String getMoneyExprType(String moneyExpr) {
		return moneyExpr.substring(moneyExpr.length()-2);		
	}

	//Returns the money type, basically cuts the last 2 characters from the expression
	private static Double getMoneyExprValue(String moneyExpr) throws NumberFormatException {
		return Double.parseDouble(moneyExpr.substring(0, moneyExpr.length() - 2));
	}

	//Checks if this is a valid money expression
	public static boolean isValidMoneyExpr(String moneyExpr) {
		while(moneyExpr.length() > 0) {
			String c = com.spritekin.warscale.utils.StringUtils.getValueBefore(moneyExpr, ",");
			moneyExpr = com.spritekin.warscale.utils.StringUtils.getValueAfter(moneyExpr, ",");
			//Separate the sections on the expression
			try {
				Double value = getMoneyExprValue(c);
			}
			catch(NumberFormatException e) { //System.out.println("Money::isValidMoneyExpr - Not a valid value " + c); 
				return false; 
			}
			String type = getMoneyExprType(c);
			if(!isValidType(type)) { 
				//System.out.println("Money::isValidMoneyExpr - Not a valid type " + c); 
				return false; 
			}
		}
		return true;
	}

	public static boolean isValidType(String currency) {
		if(currency.equals(BRONZE_PIECE) || currency.equals(SILVER_PIECE) || currency.equals(GOLD_PIECE) || currency.equals(TITANIUM_PIECE))
			return true;
		//System.out.println("Money::isValidType - Not a valid value " + currency);
		return false;
	}
	
	// Get the canonical expression, drop decimals
	public String toString() {
		String res = "";
		if(coins.containsKey(TITANIUM_PIECE) && coins.get(TITANIUM_PIECE) > 1)
			res = (coins.get(TITANIUM_PIECE).intValue()) + TITANIUM_PIECE;
		
		if(coins.containsKey(GOLD_PIECE) && coins.get(GOLD_PIECE) > 1) {
			if(res.length() > 0) res += ",";
			res += (coins.get(GOLD_PIECE).intValue()) + GOLD_PIECE;
		}

		if(coins.containsKey(SILVER_PIECE) && coins.get(SILVER_PIECE) > 1) {
			if(res.length() > 0) res += ",";
			res += (coins.get(SILVER_PIECE).intValue()) + SILVER_PIECE;
		}

		if(coins.containsKey(BRONZE_PIECE) && coins.get(BRONZE_PIECE) > 1) {
			if(res.length() > 0) res += ",";
			res += (coins.get(BRONZE_PIECE).intValue()) + BRONZE_PIECE;
		}
		return res;
	}

	// Get the canonical expression, do not drop decimals
	public String toStringBase() {
		String res = "";
		if(coins.containsKey(TITANIUM_PIECE) && coins.get(TITANIUM_PIECE) > 1)
			res = coins.get(TITANIUM_PIECE) + TITANIUM_PIECE;
		
		if(coins.containsKey(GOLD_PIECE) && coins.get(GOLD_PIECE) > 1) {
			if(res.length() > 0) res += ",";
			res += coins.get(GOLD_PIECE) + GOLD_PIECE;
		}

		if(coins.containsKey(SILVER_PIECE) && coins.get(SILVER_PIECE) > 1) {
			if(res.length() > 0) res += ",";
			res += coins.get(SILVER_PIECE) + SILVER_PIECE;
		}

		if(coins.containsKey(BRONZE_PIECE) && coins.get(BRONZE_PIECE) > 1) {
			if(res.length() > 0) res += ",";
			res += coins.get(BRONZE_PIECE) + BRONZE_PIECE;
		}
		return res;
	}

	//Returns positive is this money is greater, 0 if it is the same, -1 if it is less
	public Double compareTo(Money m) {
		Double thisBP = toBronze();
		Double otherBP = m.toBronze();
		return thisBP - otherBP;
	}
	
}
