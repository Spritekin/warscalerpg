package com.spritekin.warscale.utils;

//Generates a unique id
//Starts at 0. Each time a new uid is requested a counter is incremented by 1
public class UIDGenerator {

	private static long uid = 0;
	
	public static long getUID() {
		uid++;
		return uid;
	}
	
}
