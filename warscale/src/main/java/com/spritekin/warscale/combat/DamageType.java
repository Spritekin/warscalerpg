package com.spritekin.warscale.combat;

public class DamageType {
	public static final String DAMAGETYPELONG_BLUNT = "Blunt";
	public static final String DAMAGETYPELONG_SLASH = "Slash";
	public static final String DAMAGETYPELONG_PIERCE = "Pierce";

	public static final String DAMAGETYPE_BLUNT = "B";
	public static final String DAMAGETYPE_SLASH = "S";
	public static final String DAMAGETYPE_PIERCE = "P";

	private java.util.HashSet<String> damageType = new java.util.HashSet<String>();

	public DamageType(String damageType) {
		while(damageType.length() > 0) {
			String dT = com.spritekin.warscale.utils.TestStringUtils.getValueBefore(damageType, "/");
			damageType = com.spritekin.warscale.utils.TestStringUtils.getValueAfter(damageType, "/");
			addDamageType(dT);
		}
	}

	//Checks if a damage type is valid
	//Single type only
	public static boolean isValidDamageType(String damageType) {
		if(damageType.equals(DAMAGETYPE_SLASH) || damageType.equals(DAMAGETYPELONG_SLASH) ) return true;
		if(damageType.equals(DAMAGETYPE_BLUNT) || damageType.equals(DAMAGETYPELONG_BLUNT) ) return true;
		if(damageType.equals(DAMAGETYPE_PIERCE) || damageType.equals(DAMAGETYPELONG_PIERCE) ) return true;
		return false;
	}

	//Sets the damage type to a specific value
	public void setDamageType(String damageType) {		
		if(!isValidDamageType(damageType))
			return;
		this.damageType = new java.util.HashSet<String>();
		String dT = toOneChar(damageType);
		this.damageType.add(dT);
	}

	//Adds a damage type to this damage type object, this allows multiple types
	public void addDamageType(String damageType) {
		if(!isValidDamageType(damageType))
			return;
		String dT = toOneChar(damageType);
		if(!this.damageType.contains(dT))
			this.damageType.add(dT);
	}

	//Returns a string representation of this damage type as S/P/B
	@Override
	public String toString() {
		String res = "";
		if(damageType.contains(DAMAGETYPE_SLASH)) {
			res += DAMAGETYPE_SLASH;
		}
		if(damageType.contains(DAMAGETYPE_PIERCE)) {
			if(res.length() > 0) res += "/";
			res += DAMAGETYPE_PIERCE;
		}			
		if(damageType.contains(DAMAGETYPE_BLUNT)) {
			if(res.length() > 0) res += "/";
			res += DAMAGETYPE_BLUNT;
		}
		if(res == "")
			res = "-";
		return res;
	}

	public String toLongString() {
		String res = "";
		if(damageType.contains(DAMAGETYPE_SLASH)) {
			res += DAMAGETYPELONG_SLASH;
		}
		if(damageType.contains(DAMAGETYPE_PIERCE)) {
			if(res.length() > 0) res += "/";
			res += DAMAGETYPELONG_PIERCE;
		}			
		if(damageType.contains(DAMAGETYPE_BLUNT)) {
			if(res.length() > 0) res += "/";
			res += DAMAGETYPELONG_BLUNT;
		}
		return res;
	}

	private static String toOneChar(String damageType) {
		if(!isValidDamageType(damageType))
			return null;
		return damageType.substring(0,1);
	}

}
