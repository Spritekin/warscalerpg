package com.spritekin.warscale.currency;

//This class represents a pile of coins on a single type
// It should be subclassed with specific coin types
public abstract class Coins {
	private int amount = 0;

	public Coins(int amount) {
		set(amount);
	}
	
	//Set the coins of a certain type
	//type must be specified
	public void set(int amt) {
		this.amount = amt;
	}

	//Adds some money
	public void add(int amt) {
		this.amount += amt;		
	}
		
	public abstract String getType();
	
}
