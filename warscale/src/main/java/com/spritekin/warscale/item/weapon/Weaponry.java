package com.spritekin.warscale.item.weapon;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import com.spritekin.warscale.material.Material;

public class Weaponry {
	
	//Definitions of templates
	private static HashMap <String, String> templateBase = new HashMap<String, String>();
	private static HashMap <String, String> templateModifiers = new HashMap<String, String>();
/*
	private static void addWeaponTemplate(String name, String base, String modifiers) {		
		templateBase.put(name, base);
		templateModifiers.put(name, modifiers);
	}
	
	static {
		
		//Axes
		addWeaponTemplate("Hand Axe",		"Hand Axe", 	"");
		addWeaponTemplate("Medium Axe",		"Medium Axe", 	"");
		addWeaponTemplate("Broad Axe", 		"Broad Axe", 	"");
		addWeaponTemplate("Pick", 			"Hand Axe", 	"Pick head");
		addWeaponTemplate("Pick Axe", 		"Medium Axe", 	"Double;Pick head");
		addWeaponTemplate("Military Pick", 	"Broad Axe", 	"Double;Pick head;Pick head;War");
		addWeaponTemplate("Double Axe", 	"Broad Axe", 	"Double");
		addWeaponTemplate("Battle Axe", 	"Medium Axe", 	"Double;Pick head;War");
		addWeaponTemplate("War Axe", 		"Broad Axe", 	"Double;War");
		addWeaponTemplate("Tomahawk", 		"Hand Axe", 	"Throwing");
		addWeaponTemplate("Francisca", 		"Medium Axe", 	"Throwing");
		addWeaponTemplate("Hurlbat", 		"Medium Axe", 	"Double;War;Throwing");

		addWeaponTemplate("Poleaxe", 		"Medium Axe", 	"Double;Pick head;Long shaft");
		
		addWeaponTemplate("Club",			"Club", 		"");
		addWeaponTemplate("Hammer", 		"Hammer", 		"");
		addWeaponTemplate("Mace", 			"Mace", 		"");
		addWeaponTemplate("Warhammer", 		"Hammer", 		"War");
		addWeaponTemplate("Flanged mace", 	"Mace", 		"War");
		addWeaponTemplate("Spiked club", 	"Club", 		"Spiked");
		addWeaponTemplate("Spiked mace", 	"Mace", 		"Spiked");

		addWeaponTemplate("Morning star", 	"Mace", 		"Spiked;Long shaft");
		addWeaponTemplate("Bec de corbin", 	"Hammer", 		"Pick head;Long shaft;Long shaft");
		
		addWeaponTemplate("Chain",			"Chain", 		"");
		addWeaponTemplate("Rope",			"Rope", 		"");
		addWeaponTemplate("Short flail", 	"Short flail", 	"");
		addWeaponTemplate("Whip", 			"Whip",			"");
		addWeaponTemplate("Nunchaku", 		"Short flail",	"");		
		addWeaponTemplate("Bolas", 			"Rope", 		"Ball;Throwing");
		addWeaponTemplate("Meteor hammer", 	"Chain", 		"Additional lash;Ball");
		addWeaponTemplate("Three section staff", "Short flail", "Three section");
		addWeaponTemplate("Military flail", "Chain", 		"Ball;War");
		addWeaponTemplate("Kusarigama", 	"Chain", 		"Long lash;Bladed;Ball");
		addWeaponTemplate("Spiked chain", 	"Chain", 		"Spiked");
		addWeaponTemplate("Bullwhip", 		"Whip", 		"Long lash");
		addWeaponTemplate("Flying claw", 	"Rope", 		"Claw");
		addWeaponTemplate("Dragon claw", 	"Chain", 		"War;Claw");
		addWeaponTemplate("Flying hook", 	"Rope", 		"Long lash;Hook");
		addWeaponTemplate("Scourge(5)", 	"Whip", 		"Additional lash;Additional lash;Additional lash;Additional lash");

		//Gauntlet		
		addWeaponTemplate("Soft Glove",		"Soft Glove", 	"");
		addWeaponTemplate("Hard Glove",		"Hard Glove", 	"");
		addWeaponTemplate("Metal Glove",	"Metal Glove", 	"");
		addWeaponTemplate("Knuckles", 		"Knuckles", 	"");
		addWeaponTemplate("Boxing Gloves", 	"Soft Glove", 	"Padded");
		addWeaponTemplate("Cestus", 		"Hard Glove", 	"Spiked");
		addWeaponTemplate("Locking Glove",	"Metal Glove", 	"Locking");
		addWeaponTemplate("Pata", 			"Metal Glove", 	"Dagger");
		addWeaponTemplate("Tiger Claws", 	"Knuckles", 	"Claw");
		addWeaponTemplate("War Gauntlet", 	"Metal Glove", 	"War;Spiked");

		//Knifes
		addWeaponTemplate("Dagger",			"Dagger", 	"");
		addWeaponTemplate("Knife",			"Knife", 	"");
		addWeaponTemplate("Sickle", 		"Sickle", 	"");
		addWeaponTemplate("Sai", 			"Dagger", 	"Foil;Weaponcatch");
		addWeaponTemplate("Wakisashi", 		"Knife", 	"Long blade;War;Light");
		addWeaponTemplate("Tanto", 			"Knife", 	"War;Light");
		addWeaponTemplate("Throwing dagger","Dagger", 	"Throwing");
		addWeaponTemplate("Dart",			"Dagger", 	"Light;Foil;Throwing");
		addWeaponTemplate("Shuriken",		"Knife", 	"Light;Throwing");
		addWeaponTemplate("Cutlass",		"Dagger", 	"War;Long blade;Handguard");
		addWeaponTemplate("Machete",		"Knife", 	"War;Long blade");

		addWeaponTemplate("Javelin",		"Dagger", 	"Foil;Throwing;Long shaft");
		addWeaponTemplate("Short spear",	"Dagger", 	"Long shaft");
		addWeaponTemplate("Spear",			"Dagger", 	"Long shaft;Long shaft");
		addWeaponTemplate("Spetum",			"Dagger", 	"Foil;Long blade;Long shaft");
		addWeaponTemplate("Lance",			"Dagger", 	"War;Long shaft;Long shaft");

		//Sword
		addWeaponTemplate("Sword",			"Sword",		"");
		addWeaponTemplate("Backsword",		"Backsword",	"");
		addWeaponTemplate("Scythe", 		"Scythe", 		"");
		addWeaponTemplate("Stiletto",		"Sword", 		"Foil");
		addWeaponTemplate("Rapier",			"Sword", 		"Handguard;Light");
		addWeaponTemplate("Broad sword",	"Sword", 		"War");
		addWeaponTemplate("Falchion",		"Backsword", 	"War;Handguard");
		addWeaponTemplate("Sabre",			"Backsword", 	"Handguard");
		addWeaponTemplate("Cavalry sabre",	"Backsword", 	"Handguard;Long blade");
		addWeaponTemplate("Katana",			"Backsword", 	"War;Light");
		addWeaponTemplate("Odachi",			"Backsword", 	"Long blade;War;Light");
		addWeaponTemplate("Two-handed sword","Sword", 		"Long blade");
		addWeaponTemplate("Claymore",		"Sword", 		"Long blade;Long blade;Weaponcatch;War");
		addWeaponTemplate("War scythe",		"Scythe", 		"War;Long shaft");

		addWeaponTemplate("Trident",		"Sword", 		"Weaponcatch;War;Long shaft");
		addWeaponTemplate("Naginata",		"Backsword", 	"War;Light;Long shaft");

		
		//Polearms
		addWeaponTemplate("Staff",			"Staff", 	"");
		addWeaponTemplate("Quarterstaff",	"Staff", 	"Long shaft");
		addWeaponTemplate("Long staff",		"Staff", 	"Long shaft;Long shaft");
		addWeaponTemplate("Pike",			"Staff", 	"Spiked;Long shaft");

		/*
		Ahlspeiss	+4	10sp	2	2	-	+8	Slash	0	+5	Metal	3.5	B
		Bardiche	+8	10sp	2	2	-	+12	Slash	0	+9	Metal	9	B
		Flail staff	+4	30bp	2	3	-	+9	Blunt	0	+7	Metal	5	B
		Glaive	+4	2sp	2	2	-	+9	Slash	0	+6	Metal	3.5	B
		Halberd	+4	3sp	2	2	-	+7	Slash	0	+7	Metal	5	B
		Partisan	+6	2sp	2	2	-	+6	Pierce	+2	+3	Metal	2.5	B
		Ranseur	+3	3sp	2	3	-	+8	Pierce	+2	+5	Metal	4	B
		Voulge	+11	30sp	2	3	-	+11	Slash	0	+5	Metal	4.5	B
		*/

/*
		//Bows
		addWeaponTemplate("Short bow",		"Short bow",	"");
		addWeaponTemplate("Hunter bow",		"Hunter bow", 	"");
		addWeaponTemplate("Long bow",		"Long bow", 	"");
		addWeaponTemplate("Strong bow",		"Long bow", 	"Composite");
		addWeaponTemplate("War bow",		"Long bow", 	"Recurve;Composite");

		//Arrow
		addWeaponTemplate("Arrow",			"Arrow",	"");
		addWeaponTemplate("Piercing arrow",	"Arrow", 	"Armor piercing");
		addWeaponTemplate("Blunt arrow",	"Arrow", 	"Blunt");
		addWeaponTemplate("Broad arrow",	"Arrow", 	"Broad");
		addWeaponTemplate("Incendiary arrow","Arrow", 	"Incendiary");
		addWeaponTemplate("Expansive arrow","Arrow", 	"Expansive");
		addWeaponTemplate("Explosive arrow","Arrow", 	"Explosive");
		addWeaponTemplate("Tracer arrow",	"Arrow", 	"Tracer");


		//Crossbows
		addWeaponTemplate("Hand crossbow",		"Hand crossbow",	"");
		addWeaponTemplate("Light crossbow",		"Light crossbow", 	"");
		addWeaponTemplate("Heavy crossbow",		"Heavy crossbow", 	"");
		addWeaponTemplate("Repeating crossbow",	"Light crossbow", 	"Chamber;Additional shot;Additional shot;Additional shot;Additional shot;Additional shot");
		addWeaponTemplate("War crossbow",		"Heavy crossbow", 	"Recurve;Composite;Targetting;Targetting");

		//Bolt
		addWeaponTemplate("Bolt",			"Bolt",	"");
		addWeaponTemplate("Piercing bolt",	"Bolt",	"Armor piercing");
		addWeaponTemplate("Blunt bolt",		"Bolt", "Blunt");
		addWeaponTemplate("Broad bolt",		"Bolt", "Broad");
		addWeaponTemplate("Incendiary bolt","Bolt", "Incendiary");
		addWeaponTemplate("Expansive bolt",	"Bolt", "Expansive");
		addWeaponTemplate("Explosive bolt",	"Bolt", "Explosive");
		addWeaponTemplate("Tracer bolt",	"Bolt", "Tracer");

		//Shield
		addWeaponTemplate("Buckler",		"Buckler",		"");
		addWeaponTemplate("Medium shield",	"Medium shield","");
		addWeaponTemplate("Body shield",	"Body shield",	"");
		addWeaponTemplate("Tower shield",	"Tower shield",	"");
		addWeaponTemplate("Lantern shield",	"Buckler",		"Lantern");
		addWeaponTemplate("Battle shield",	"Medium shield","Reinforced");
		addWeaponTemplate("Scutum",			"Body shield",	"Reinforced");
		addWeaponTemplate("Spiked shield",	"Medium shield","Reinforced");
		addWeaponTemplate("War shield",		"Medium shield","Reinforced;Reinforced;Spiked shield");
		
	}
		
	public static Set<String> getTemplateList() {
		return templateBase.keySet();
	}

	protected static Weapon newBaseWeapon(String name) {
		if(name.equalsIgnoreCase("Hand Axe")) 	return new Weapon("Hand Axe", 			Weapon.WEAPONTRAIT_AXE, 		Weapon.WEAPONSUBTRAIT_MELEE, 		"1",	"1","1",	"1",	"3",	"Slash","0",	"2",	Material.get("Iron"), "1",	"1", "10000", "0");		
		if(name.equalsIgnoreCase("Medium Axe")) return new Weapon("Medium Axe",			Weapon.WEAPONTRAIT_AXE, 		Weapon.WEAPONSUBTRAIT_MELEE, 		"3",	"1","1",	"0.5",	"6",	"Slash","0",	"4",	Material.get("Iron"),"2",	"2", "10000", "0");
		if(name.equalsIgnoreCase("Broad Axe")) 	return new Weapon("Broad Axe",			Weapon.WEAPONTRAIT_AXE, 		Weapon.WEAPONSUBTRAIT_MELEE, 		"6",	"1","1",	"0.25",	"9",	"Slash","0",	"6",	Material.get("Iron"),"4",	"3", "10000", "0");
	
		//Blunt
		if(name.equalsIgnoreCase("Club")) 		return new Weapon("Club", 				Weapon.WEAPONTRAIT_BLUNT, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"0",	"1","1","-","2","Blunt","0","2",Material.get("Pine"),"1","2", "10000", "0");
		if(name.equalsIgnoreCase("Hammer")) 	return new Weapon("Hammer",				Weapon.WEAPONTRAIT_BLUNT, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"6",	"1","1","0.5","6","Blunt","0","4",Material.get("Iron"),"5","2", "10000", "0");
		if(name.equalsIgnoreCase("Mace")) 		return new Weapon("Mace",				Weapon.WEAPONTRAIT_BLUNT, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"4",	"1","1","-","4","Blunt","0","3",Material.get("Iron"),"3","2", "10000", "0");
	
		//Flexible
		if(name.equalsIgnoreCase("Chain")) 		return new Weapon("Chain", 				Weapon.WEAPONTRAIT_FLEXIBLE, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"2",	"1","2","0.25","2","Blunt","0","4",Material.get("Iron"),"3","3", "10000", "0");
		if(name.equalsIgnoreCase("Rope")) 		return new Weapon("Rope",				Weapon.WEAPONTRAIT_FLEXIBLE, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"0",	"1","2","0.25","0","Blunt","0","4",Material.get("Jute"),"1","3", "10000", "0");
		if(name.equalsIgnoreCase("Short flail"))return new Weapon("Short flail",		Weapon.WEAPONTRAIT_FLEXIBLE, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"2",	"1","1","1","+2","Blunt","0","2",Material.get("Pine"),"0.5","1", "10000", "0");
		if(name.equalsIgnoreCase("Whip")) 		return new Weapon("Whip", 				Weapon.WEAPONTRAIT_FLEXIBLE, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"2",	"1","1","-","2","Slash","0","4",Material.get("Sheep"),"2","2", "10000", "0");
	
		//Gauntlet
		if(name.equalsIgnoreCase("Soft Glove")) return new Weapon("Soft Glove", 		Weapon.WEAPONTRAIT_GAUNTLET, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"1",	"1","0","0","0","Blunt","0","0",Material.get("Sheep"),"0","1", "10000", "0");
		if(name.equalsIgnoreCase("Hard Glove")) return new Weapon("Hard Glove", 		Weapon.WEAPONTRAIT_GAUNTLET, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"3",	"1","0","0","1","Blunt","0","0",Material.get("Sheep"),"1","1", "10000", "0");
		if(name.equalsIgnoreCase("Metal Glove"))return new Weapon("Metal Glove",		Weapon.WEAPONTRAIT_GAUNTLET, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"5",	"1","0","0","2","Blunt","0","0",Material.get("Iron")  ,"2","1", "10000", "0");
		if(name.equalsIgnoreCase("Knuckles")) 	return new Weapon("Knuckles",			Weapon.WEAPONTRAIT_GAUNTLET, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"2",	"1","0","0","1","Blunt","0","0",Material.get("Iron")  ,"0","1", "10000", "0");
	
		//Knifes
		if(name.equalsIgnoreCase("Dagger")) 	return new Weapon("Dagger", 			Weapon.WEAPONTRAIT_KNIFE, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"1",	"1",	"0",	"1",	"3",	"Pierce",	"0",	"0",	Material.get("Iron"),	"0.5",	"1", "10000", "0");
		if(name.equalsIgnoreCase("Knife")) 		return new Weapon("Knife",				Weapon.WEAPONTRAIT_KNIFE, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"2",	"1",	"0",	"1",	"4",	"Slash",	"0",	"1",	Material.get("Iron"),	"0.5",	"1", "10000", "0");
		if(name.equalsIgnoreCase("Sickle")) 	return new Weapon("Sickle",				Weapon.WEAPONTRAIT_KNIFE, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"2",	"1",	"0",	"-",	"5",	"Slash",	"0",	"2",	Material.get("Iron"),	"1",	"2", "10000", "0");
	
		//Sword
		if(name.equalsIgnoreCase("Sword")) 		return new Weapon("Sword", 				Weapon.WEAPONTRAIT_SWORD, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"3",	"1",	"1",	"-",	"5",	"Slash",	"0",	"2",	Material.get("Iron"),	"1.5",	"2", "10000", "0");
		if(name.equalsIgnoreCase("Backsword")) 	return new Weapon("Backsword",			Weapon.WEAPONTRAIT_SWORD, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"4",	"1",	"1",	"-",	"6",	"Slash",	"0",	"3",	Material.get("Iron"),	"1.5",	"2", "10000", "0");
		if(name.equalsIgnoreCase("Scythe")) 	return new Weapon("Scythe",				Weapon.WEAPONTRAIT_SWORD, 		Weapon.WEAPONSUBTRAIT_MELEE, 	"4",	"1",	"1",	"-",	"7",	"Slash",	"0",	"4",	Material.get("Iron"),	"1.5",	"2", "10000", "0");
	
		//Polearms
		if(name.equalsIgnoreCase("Staff")) 		return new Weapon("Staff", 				Weapon.WEAPONTRAIT_POLEARM, 	Weapon.WEAPONSUBTRAIT_MELEE, 	"1",	"2",	"1",	"-",	"3",	"Blunt",	"0",	"3",	Material.get("Pine"),	"2",	"2", "10000", "0");
	
		//Bows
		if(name.equalsIgnoreCase("Short bow")) 	return new Weapon("Short bow", 			Weapon.WEAPONTRAIT_BOW, 		Weapon.WEAPONSUBTRAIT_RANGED, 	"2",	"2",	"-",	"10",	"-",	"-",	"0",	"4",	Material.get("Pine"),		"1",	"1", "10000", "1");
		if(name.equalsIgnoreCase("Hunter bow")) return new Weapon("Hunter bow", 		Weapon.WEAPONTRAIT_BOW, 		Weapon.WEAPONSUBTRAIT_RANGED, 	"3",	"2",	"-",	"30",	"-",	"-",	"0",	"6",	Material.get("Pine"),		"2",	"2", "10000", "1");
		if(name.equalsIgnoreCase("Long bow")) 	return new Weapon("Long bow", 			Weapon.WEAPONTRAIT_BOW, 		Weapon.WEAPONSUBTRAIT_RANGED, 	"4",	"2",	"-",	"50",	"-",	"-",	"0",	"8",	Material.get("Pine"),		"3",	"3", "10000", "1");
	
		//Arrow
		if(name.equalsIgnoreCase("Arrow")) 		return new Weapon("Arrow", 				Weapon.WEAPONTRAIT_ARROW, 		Weapon.WEAPONSUBTRAIT_RANGED, 	"1",	"-",	"-",	"-",	"3",	"P",	"-",	"-",	Material.get("Pine"),		"0.1",	"1", "1", "0");

		//Crossbows
		if(name.equalsIgnoreCase("Hand crossbow")) return new Weapon("Hand crossbow",	Weapon.WEAPONTRAIT_CROSSBOW, 	Weapon.WEAPONSUBTRAIT_RANGED, 	"5",	"1",	"-",	"20",	"-",	"-",	"0",	"0/10",	Material.get("Iron"),	"1",	"1", "10000", "1");
		if(name.equalsIgnoreCase("Light crossbow"))return new Weapon("Light crossbow",	Weapon.WEAPONTRAIT_CROSSBOW, 	Weapon.WEAPONSUBTRAIT_RANGED, 	"4",	"2",	"-",	"60",	"-",	"-",	"0",	"0/15",	Material.get("Iron"),	"3",	"2", "10000", "1");
		if(name.equalsIgnoreCase("Heavy crossbow"))return new Weapon("Heavy crossbow",	Weapon.WEAPONTRAIT_CROSSBOW, 	Weapon.WEAPONSUBTRAIT_RANGED, 	"6",	"2",	"-",	"180",	"-",	"-",	"0",	"0/20",	Material.get("Iron"),	"8",	"3", "10000", "1");
	
		//Bolt
		if(name.equalsIgnoreCase("Bolt")) 		return new Weapon("Bolt", 				Weapon.WEAPONTRAIT_BOLT, 		Weapon.WEAPONSUBTRAIT_RANGED, 	"2",	"-",	"-",	"-",	"6",	"P",	"-",	"-",	Material.get("Pine"),		"0.2",	"1", "1", "0");

		//Shield
		if(name.equalsIgnoreCase("Buckler")) 	return new Weapon("Buckler",			Weapon.WEAPONTRAIT_SHIELD, 	Weapon.WEAPONSUBTRAIT_SHIELD, 	"0",	"1",	"0",	"0",	"2",	"B",	"+2",	"0",	Material.get("Pine"),	"2",	"1", "10000", "1");
		if(name.equalsIgnoreCase("Medium shield"))return new Weapon("Medium shield",	Weapon.WEAPONTRAIT_SHIELD, 	Weapon.WEAPONSUBTRAIT_SHIELD, 	"2",	"1",	"0",	"0",	"1",	"B",	"+4",	"1",	Material.get("Pine"),	"4",	"2", "10000", "1");
		if(name.equalsIgnoreCase("Body shield"))return new Weapon("Body shield",		Weapon.WEAPONTRAIT_SHIELD, 	Weapon.WEAPONSUBTRAIT_SHIELD, 	"5",	"1",	"0",	"0",	"0",	"B",	"+6",	"3",	Material.get("Pine"),	"8",	"3", "10000", "1");
		if(name.equalsIgnoreCase("Tower shield"))return new Weapon("Tower shield",		Weapon.WEAPONTRAIT_SHIELD, 	Weapon.WEAPONSUBTRAIT_SHIELD, 	"8",	"1",	"0",	"0",	"0",	"-",	"+10",	"6",	Material.get("Pine"),	"20",	"4", "10000", "1");

		return null;
	}

	
	//This method will return a weapon, properly initialized and with appropriate modifiers ny requesting it by name
	//Additional modifiers can be applied later
	public static Weapon get(String name) {
		Weapon res = null;

		//Look for the weapon inside each subclass
		if(getTemplateList().contains(name)) {
			String base = templateBase.get(name);
			if(base == null)
				return null;

			res = newBaseWeapon(base);
			res.setProperty(Weapon.PROPERTY_NAME, name);

			String modifiers = templateModifiers.get(name);
			if(modifiers.length() > 0) 
				res.applyModifiers(modifiers);
		} 
		else {
			System.out.println("Weapon::getWeapon - Unable to find the weapon " + name + " in the weapon classes");
		}

		return res;
	}

	//Table print assistance
	public static String headerString() {
		return Weapon.PROPERTY_NAME + "\t" + 
				//PROPERTY_TRAIT + "\t" + 
				Weapon.PROPERTY_DR + "\t" +  
				Weapon.PROPERTY_PRICE + "\t" +  
				Weapon.PROPERTY_HANDS + "\t" +  
				Weapon.PROPERTY_REACH + "\t" +  
				Weapon.PROPERTY_RANGE + "\t" +  
				Weapon.PROPERTY_ATTACK + "\t" +  
				Weapon.PROPERTY_DMGTYPE + "\t" + 
				Weapon.PROPERTY_PARRY + "\t" +  
				Weapon.PROPERTY_AP + "\t" +  
				"Material" + "\t" + 
				Weapon.PROPERTY_WEIGHT + "\t" + 
				Weapon.PROPERTY_SIZE;
	}

	public static String getDocumentFormatTable(String trait) {
		String res = trait + "\n" + headerString() + "\n";

		java.util.ArrayList<String> allBaseTable = new java.util.ArrayList<String>();
		java.util.HashSet<String> thisBaseWeapons = new java.util.HashSet<String>();
		
		java.util.HashSet<String> allBaseList = new java.util.HashSet<String>();
		for(String s : getTemplateList()) {
			if(templateBase.get(s).equals(s) && templateModifiers.get(s).length() == 0)
				allBaseList.add(s);
		}
		
		for(String s : allBaseList) {
			Weapon wd = Weaponry.get(s);
			if(wd.getProperty(Weapon.PROPERTY_TRAIT).getValue().equals(trait)) {
				allBaseTable.add(wd.toString());
				thisBaseWeapons.add(s);
			}
		}
		
		java.util.ArrayList<String> allSpecificTable = new java.util.ArrayList<String>();
		java.util.ArrayList<String> weaponModifiers = new java.util.ArrayList<String>();
		for(String s : templateBase.keySet()) {
			if(!allBaseList.contains(s)) {	//Ignore the weapons in the base list
				Weapon weapon = Weaponry.get(s);
				if(weapon.getProperty(Weapon.PROPERTY_TRAIT).getValue().equals(trait)) {	//Allow weapons of the required trait only
					//Add the weapon to the list os specifics
					allSpecificTable.add(weapon.toString());
					
					//Add the weapon to the description
					HashMap<String, Integer> modList = weapon.getModifierList();
					String mList = "";
					if(modList.size() > 0) {
						for(String mod : modList.keySet()) {
							if(mList.length() > 0) mList += ", ";
							mList += mod;
							if(modList.get(mod) > 1)
								mList += "(x" + modList.get(mod) + ")";
						}
					}
					
					String wm = s + ": " + templateBase.get(s);
					if(mList.length() > 0) 
						wm += "; " + mList + ".";
					else 
						wm += ".";
					weaponModifiers.add(wm);
				}
			}
		}

		//Sort and output the base table
		Collections.sort(allBaseTable);
		for(String s:allBaseTable)
			res += s + "\n";

		res += 	"\nSpecialized\n";
		Collections.sort(allSpecificTable);
		for(String s:allSpecificTable)
			res += s + "\n";

		res += 	"\nSpecialized\n";
		Collections.sort(weaponModifiers);
		for(String s:weaponModifiers)
			res += s + "\n";
		
		return res;
	}
*/

}


