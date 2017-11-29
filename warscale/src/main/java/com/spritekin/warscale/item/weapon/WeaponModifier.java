package com.spritekin.warscale.item.weapon;

import java.util.Collections;
import java.util.HashMap;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.material.Material;

//Convenience class to list all modifiers. Each modifier is of weapon type
public class WeaponModifier {
	
	protected static HashMap<String, Weapon> allModifierList = new HashMap<String, Weapon>();

	static {

		//Generic
		allModifierList.put("Gemstone", 	new Weapon("Gemstone",		"", Weapon.WEAPONSUBTRAIT_GENERIC, 	"+5",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"-",	"-","-","-"));

		//Melee weapon modifiers
		allModifierList.put("Additional lash", new Weapon("Additional lash","", Weapon.WEAPONSUBTRAIT_MELEE, "+2",	"-",	"-",	"-",	"+2",	"-",	"-",	"+1",	null,	"+1",	"-","-","-"));
		allModifierList.put("Ball", 		new Weapon("Ball",			"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+3",	"-",	"-",	"-",	"+3",	"Blunt","-",	"+2",	Material.get("Iron"),"+0.5",	"-","-","-"));
		allModifierList.put("Bladed", 		new Weapon("Bladed",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+5",	"-",	"-",	"-",	"+2",	"Slash","-",	"+1",	Material.get("Iron"),"+0.5",	"-","-","-"));
		allModifierList.put("Claw", 		new Weapon("Claw",			"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+3",	"-",	"-",	"-",	"+3",	"Slash","-",	"+2",	Material.get("Iron"),"+0.3",	"-","-","-"));
		allModifierList.put("Dagger", 		new Weapon("Dagger",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+4",	"-",	"-",	"-",	"+3",	"Pierce","-",	"-",	Material.get("Iron"),"+0.5",	"-","-","-"));
		allModifierList.put("Double", 		new Weapon("Double",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+5", 	"-", 	"-",	"-", 	"+2", 	"-", 	"-", 	"+1", 	null, 	"+1", "-","-","-"));
		allModifierList.put("Extending", 	new Weapon("Extending",		"", Weapon.WEAPONSUBTRAIT_MELEE,	"+2",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"+0.5",	"-","-","-"));
		allModifierList.put("Foil", 		new Weapon("Foil",			"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+3",	"-",	"-",	"-",	"+1",	"Pierce","-",	"-1",	null,	"-0.2",	"-","-","-"));
		allModifierList.put("Handguard",	new Weapon("Handguard",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+1",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"+0.1",	"-","-","-"));
		allModifierList.put("Hook", 		new Weapon("Hook",			"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+2",	"-",	"-",	"-",	"+1",	"Pierce","-",	"+2",	Material.get("Iron"),"+0.3",	"-","-","-"));
		allModifierList.put("Light", 		new Weapon("Light",			"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+2",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"-0.2",	"-","-","-"));
		allModifierList.put("Locking", 		new Weapon("Locking",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+2",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"+0.1",	"-","-","-"));
		allModifierList.put("Long blade",	new Weapon("Long blade",	"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+3",	"-",	"+0.5",	"-",	"+2",	"-",	"-",	"+1",	null,	"+1",	"+0.5","-","-"));
		allModifierList.put("Long lash",	new Weapon("Long lash",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+2",	"-",	"+1",	"-",	"-",	"-",	"-",	"+2",	null,	"+0.5",	"-","-","-"));
		allModifierList.put("Long shaft",	new Weapon("Long shaft",	"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+1",	"=2",	"+1",	"-",	"+2",	"-",	"-",	"+2",	null,	"+1",	"-","-","-"));
		allModifierList.put("Padded",		new Weapon("Padded",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+0",	"-",	"-",	"-",	"+1",	"-",	"-",	"-",	Material.get("Sheep"),"+0.2","-","-","-"));
		allModifierList.put("Pick head",	new Weapon("Pick head",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+1",	"-",	"-",	"-",	"+1",	"Pierce","-",	"-",	null,	"-",	"-","-","-"));
		allModifierList.put("Retracting",	new Weapon("Retracting",	"", Weapon.WEAPONSUBTRAIT_MELEE,	"+2",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"+0.5",	"-","-","-"));
		allModifierList.put("Spiked",		new Weapon("Spiked",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+2",	"-",	"-",	"-",	"+1",	"Pierce","-",	"+0",	Material.get("Iron"),"+0.5",	"-","-","-"));
		allModifierList.put("Three section", new Weapon("Three section","", Weapon.WEAPONSUBTRAIT_MELEE, 	"+5",	"+1",	"+1",	"-1",	"+2",	"-",	"-",	"+2",	null,	"+1",	"-","-","-"));
		allModifierList.put("Throwing",		new Weapon("Throwing",		"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+4",	"-",	"-",	"*2",	"-1",	"-",	"-",	"-1",	null,	"-0.2",	"-","-","-"));
		allModifierList.put("War",			new Weapon("War",			"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+3",	"-",	"-",	"-",	"+2",	"-",	"-",	"-",	null,	"+1",	"-","-","-"));
		allModifierList.put("Weaponcatch",	new Weapon("Weaponcatch",	"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+1",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"+0.1",	"-","-","-"));
		allModifierList.put("Weighted", 	new Weapon("Weighted",  	"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+2",	"-",	"-",	"-",	"+1",	"-",	"-",	"+1",	null,	"+0.5",	"-","-","-"));

		//Ranged weapon modifiers
		allModifierList.put("Additional shot",new Weapon("Additional shot","", Weapon.WEAPONSUBTRAIT_RANGED,"+1",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"+0.1",	"-","-","+1"));
		allModifierList.put("Chamber",		new Weapon("Chamber", 		"", Weapon.WEAPONSUBTRAIT_RANGED, 	"+3",	"-",	"-",	"-",	"-",	"-",	"-",	"-5",	null,	"+0.5",	"-","-","+1"));
		allModifierList.put("Composite",	new Weapon("Composite", 	"", Weapon.WEAPONSUBTRAIT_RANGED, 	"+5",	"-",	"-",	"*2",	"-",	"-",	"-",	"-",	null,	"-",	"-","-","-"));
		allModifierList.put("Magazine",		new Weapon("Magazine", 		"", Weapon.WEAPONSUBTRAIT_RANGED, 	"+3",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"-",	"-","-","-"));
		allModifierList.put("Quick shot",	new Weapon("Quick shot", 	"", Weapon.WEAPONSUBTRAIT_RANGED, 	"+1",	"-",	"-",	"-",	"-",	"-",	"-",	"-1",	null,	"-",	"-","-","-"));
		allModifierList.put("Recurve",		new Weapon("Recurve",  		"", Weapon.WEAPONSUBTRAIT_RANGED, 	"+3",	"-",	"-",	"*1.5",	"-",	"-",	"-",	"-",	null,	"-",	"-","-","-"));
		allModifierList.put("Targetting",	new Weapon("Targetting",  	"", Weapon.WEAPONSUBTRAIT_RANGED, 	"+2",	"-",	"-",	"-",	"+1",	"-",	"-",	"-",	null,	"-",	"-","-","-"));
		
		//Missile modifiers
		allModifierList.put("Armor piercing",new Weapon("Armor piercing","", Weapon.WEAPONSUBTRAIT_RANGED,	"+5",	"-",	"-",	"-",	"+1",	"P",	"-",	"-",	Material.get("Iron"),"-",	"-","-","-"));
		allModifierList.put("Blunt",		new Weapon("Blunt",			"", Weapon.WEAPONSUBTRAIT_RANGED,	"+4",	"-",	"-",	"-",	"-1",	"B",	"-",	"-",	Material.get("Iron"),"-",	"-","-","-"));		
		allModifierList.put("Broad",		new Weapon("Broad",			"", Weapon.WEAPONSUBTRAIT_RANGED,	"+4",	"-",	"-",	"-",	"+2",	"S",	"-",	"-",	Material.get("Iron"),"-",	"-","-","-"));		
		allModifierList.put("Incendiary",	new Weapon("Incendiary",	"", Weapon.WEAPONSUBTRAIT_RANGED,	"+5",	"-",	"-",	"-",	"+2",	"Fire",	"-",	"-",	Material.get("Iron"),"-",	"-","-","-"));
		allModifierList.put("Expansive",	new Weapon("Expansive",		"", Weapon.WEAPONSUBTRAIT_RANGED,	"+10",	"-",	"-",	"-",	"+5",	"P",	"-",	"-",	Material.get("Iron"),"-",	"-","-","-"));		
		allModifierList.put("Explosive",	new Weapon("Explosive",		"", Weapon.WEAPONSUBTRAIT_RANGED,	"+15",	"-",	"-",	"-",	"+10",	"Fire",	"-",	"-",	Material.get("Iron"),"-",	"-","-","-"));
		allModifierList.put("Tracer",		new Weapon("Tracer",		"", Weapon.WEAPONSUBTRAIT_RANGED,	"+2",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	Material.get("Iron"),"-",	"-","-","-"));

		//Shield modifiers
		allModifierList.put("Lantern",		new Weapon("Lantern",		"", Weapon.WEAPONSUBTRAIT_SHIELD,	"+2",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"+0.5",	"-","-","-"));
		allModifierList.put("Reinforced",	new Weapon("Reinforced",	"", Weapon.WEAPONSUBTRAIT_SHIELD,	"+2",	"-",	"-",	"-",	"-",	"-",	"+1",	"-",	null,	"+0.5",	"-","-","-"));
		allModifierList.put("Spiked shield",new Weapon("Spiked shield",	"", Weapon.WEAPONSUBTRAIT_SHIELD,	"+2",	"-",	"-",	"-",	"+1",	"P",	"-",	"-",	null,	"+0.5",	"-","-","-"));
		
	}
	
	public static HashMap <String, Weapon> getAllModifierList() {
		return allModifierList;
	}

	public static Weapon getModifier(String name) {
		return allModifierList.get(name);
	}

	public static boolean isModifier(String name) {
		return allModifierList.containsKey(name);
	}

	public static String getDocumentFormatTable(String subtrait) {
		String res = subtrait + "\n" + Weaponry.headerString() + "\n";

		java.util.ArrayList<String> allBaseTable = new java.util.ArrayList<String>();
		for(String s : allModifierList.keySet()) {
			Weapon wd = allModifierList.get(s);
			String wmtrait = wd.getProperty(WarscaleObject.PROPERTY_SUBTRAIT).getValue();
			if(wmtrait.equals(subtrait) || wmtrait.equals(Weapon.WEAPONSUBTRAIT_GENERIC)) {
				allBaseTable.add(wd.toString());
			}
		}
		
		//Sort and output
		Collections.sort(allBaseTable);
		for(String s:allBaseTable)
			res += s + "\n";
		
		return res;
	}

}
