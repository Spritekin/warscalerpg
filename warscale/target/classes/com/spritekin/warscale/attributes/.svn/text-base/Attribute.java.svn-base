package com.spritekin.warscale.attributes;

import java.util.HashMap;

//Base class describing a generic attribute. This class will be subclasses in specialized attributes each one with its own stat list
public abstract class Attribute {
	private String name = "";
	private int value = 0;
	private HashMap<String, Integer> stat; 			//The default maximum stats
	private HashMap<String, Integer> currentStat; 	//Current stat values (affected by some effect)

	public Attribute(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setValue(int val) {
		value = val;
		updateStats();
	}

	public int getValue() {
		return value;
	}

	//Returns a stat value. If not found returns null
	public int getStat(String statName) {
		return stat.get(statName);
	}

	//Returns the current stat value. If not found returns null
	public int getCurrentStat(String statName) {
		return currentStat.get(statName);
	}

	//This method is invokes in response of a value update. 
	protected abstract void updateStats();
}
