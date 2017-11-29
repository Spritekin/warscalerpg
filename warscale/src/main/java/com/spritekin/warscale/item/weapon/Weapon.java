package com.spritekin.warscale.item.weapon;

import java.util.HashMap;

import com.spritekin.utils.StringUtils;
import com.spritekin.warscale.core.Property;
import com.spritekin.warscale.currency.Money;
import com.spritekin.warscale.item.Item;
import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialCategory;

public class Weapon extends Item {

	public static final String PROPERTY_HANDS 		= "Hands";
	public static final String PROPERTY_REACH 		= "Reach";
	public static final String PROPERTY_RANGE 		= "Range";
	public static final String PROPERTY_ATTACK 		= "Attack";
	public static final String PROPERTY_DMGTYPE		= "DmgType";
	public static final String PROPERTY_PARRY 		= "Parry";
	public static final String PROPERTY_AP 			= "AP";
	public static final String PROPERTY_SIZE 		= "Size";
	public static final String PROPERTY_NATTACKS 	= "#Att";
	public static final String PROPERTY_CHAMBERCAP	= "Cap";

	public static String WEAPONSUBTRAIT_MELEE 		= "Melee";
	public static String WEAPONSUBTRAIT_RANGED		= "Ranged";
	public static String WEAPONSUBTRAIT_GENERIC		= "Generic";
		
	public static String WEAPONTRAIT_AXE				= "Axe";
	public static String WEAPONTRAIT_BLUNT = 	"Blunt";
	public static String WEAPONTRAIT_FLEXIBLE = "Flexible";
	public static String WEAPONTRAIT_GAUNTLET = "Gauntlet";
	public static String WEAPONTRAIT_KNIFE = 	"Knife";
	public static String WEAPONTRAIT_SWORD = 	"Sword";
	public static String WEAPONTRAIT_POLEARM = 	"Polearm";

	public static String WEAPONTRAIT_BOW = 		"Bow";
	public static String WEAPONTRAIT_ARROW = 	"Arrow";
	public static String WEAPONTRAIT_CROSSBOW = "Crossbow";
	public static String WEAPONTRAIT_BOLT = 	"Bolt";
	public static String WEAPONTRAIT_PISTOL = 	"Pistol";
	public static String WEAPONTRAIT_RIFLE = 	"Rifle";
	public static String WEAPONTRAIT_BULLET = 	"Bullet";

	public static String WEAPONTRAIT_SHIELD = "Shield";
	public static String WEAPONSUBTRAIT_SHIELD = "Shield";

	
	//Definitions of base axes and modifiers
	private static HashMap<String, HashMap<String, Integer>> allowedModifiers = new HashMap<String, HashMap<String, Integer>>();
	
	static {
		
		//Axes
		allowedModifiers.put(WEAPONTRAIT_AXE, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_AXE).put("Double", 1);
		allowedModifiers.get(WEAPONTRAIT_AXE).put("Gemstone", 3);
		allowedModifiers.get(WEAPONTRAIT_AXE).put("Light", 1);
		allowedModifiers.get(WEAPONTRAIT_AXE).put("Long shaft", 3);
		allowedModifiers.get(WEAPONTRAIT_AXE).put("Pick head", 2);
		allowedModifiers.get(WEAPONTRAIT_AXE).put("Throwing", 1);
		allowedModifiers.get(WEAPONTRAIT_AXE).put("War", 1);

		//Blunt
		allowedModifiers.put(WEAPONTRAIT_BLUNT, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_BLUNT).put("Gemstone", 3);
		allowedModifiers.get(WEAPONTRAIT_BLUNT).put("Light", 1);
		allowedModifiers.get(WEAPONTRAIT_BLUNT).put("Long shaft", 3);
		allowedModifiers.get(WEAPONTRAIT_BLUNT).put("Pick head", 2);
		allowedModifiers.get(WEAPONTRAIT_BLUNT).put("Spiked", 1);
		allowedModifiers.get(WEAPONTRAIT_BLUNT).put("War", 1);
		
		//Flexible
		allowedModifiers.put(WEAPONTRAIT_FLEXIBLE, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Additional lash", 4);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Ball", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Bladed", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Claw", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Gemstone", 3);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Hook", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Long lash", 1);		
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Spiked", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Three section", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Throwing", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("Weighted", 1);
		allowedModifiers.get(WEAPONTRAIT_FLEXIBLE).put("War", 1);
		
		//Gauntlet
		allowedModifiers.put(WEAPONTRAIT_GAUNTLET, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("Claw", 3);
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("Dagger", 3);
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("Gemstone", 3);
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("Locking", 1);
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("Padded", 1);
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("Retractile", 1);
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("Spiked", 1);
		allowedModifiers.get(WEAPONTRAIT_GAUNTLET).put("War", 1);
		
		//Knifes
		allowedModifiers.put(WEAPONTRAIT_KNIFE, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Foil", 1);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Gemstone", 3);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Handguard", 1);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Weaponcatch", 1);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Long blade", 1);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Throwing", 1);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("War", 1);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Light", 1);
		allowedModifiers.get(WEAPONTRAIT_KNIFE).put("Long shaft", 3);

		//Sword
		allowedModifiers.put(WEAPONTRAIT_SWORD, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("Foil", 1);
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("Gemstone", 3);
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("Light", 1);
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("Long blade", 2);
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("Long shaft", 3);
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("Handguard", 1);
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("Weaponcatch", 1);
		allowedModifiers.get(WEAPONTRAIT_SWORD).put("War", 1);
		
		//Polearms
		allowedModifiers.put(WEAPONTRAIT_POLEARM, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_POLEARM).put("Gemstone", 3);
		allowedModifiers.get(WEAPONTRAIT_POLEARM).put("Throwing", 1);
		allowedModifiers.get(WEAPONTRAIT_POLEARM).put("War", 1);
		allowedModifiers.get(WEAPONTRAIT_POLEARM).put("Light", 1);
		allowedModifiers.get(WEAPONTRAIT_POLEARM).put("Long shaft", 3);
		allowedModifiers.get(WEAPONTRAIT_POLEARM).put("Spiked", 1);

		//Bows
		allowedModifiers.put(WEAPONTRAIT_BOW, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_BOW).put("Gemstone", 2);
		allowedModifiers.get(WEAPONTRAIT_BOW).put("Composite", 1);
		allowedModifiers.get(WEAPONTRAIT_BOW).put("Recurve", 1);

		//Arrow
		allowedModifiers.put(WEAPONTRAIT_ARROW, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_ARROW).put("Armor piercing", 1);
		allowedModifiers.get(WEAPONTRAIT_ARROW).put("Blunt", 1);
		allowedModifiers.get(WEAPONTRAIT_ARROW).put("Broad", 1);
		allowedModifiers.get(WEAPONTRAIT_ARROW).put("Incendiary", 1);
		allowedModifiers.get(WEAPONTRAIT_ARROW).put("Expansive", 1);
		allowedModifiers.get(WEAPONTRAIT_ARROW).put("Explosive", 1);
		allowedModifiers.get(WEAPONTRAIT_ARROW).put("Tracer", 1);

		//Crossbows
		allowedModifiers.put(WEAPONTRAIT_CROSSBOW, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Additional shot", 20);
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Gemstone", 2);
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Chamber", 1);
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Composite", 1);
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Magazine", 1);
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Quick shot", 1);
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Recurve", 1);
		allowedModifiers.get(WEAPONTRAIT_CROSSBOW).put("Targetting", 20);

		//Bolt
		allowedModifiers.put(WEAPONTRAIT_BOLT, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_BOLT).put("Armor piercing", 1);
		allowedModifiers.get(WEAPONTRAIT_BOLT).put("Blunt", 1);
		allowedModifiers.get(WEAPONTRAIT_BOLT).put("Broad", 1);
		allowedModifiers.get(WEAPONTRAIT_BOLT).put("Incendiary", 1);
		allowedModifiers.get(WEAPONTRAIT_BOLT).put("Expansive", 1);
		allowedModifiers.get(WEAPONTRAIT_BOLT).put("Explosive", 1);
		allowedModifiers.get(WEAPONTRAIT_BOLT).put("Tracer", 1);

		//Shield
		allowedModifiers.put(WEAPONTRAIT_SHIELD, new HashMap<String, Integer>());
		allowedModifiers.get(WEAPONTRAIT_SHIELD).put("Lantern", 1);
		allowedModifiers.get(WEAPONTRAIT_SHIELD).put("Reinforced", 20);
		allowedModifiers.get(WEAPONTRAIT_SHIELD).put("Spiked shield", 20);
		
	}

	//A list with the current weapon modifiers and how many
	private HashMap<String, Integer> modifierList = new HashMap<String, Integer>();
	
	public Weapon(String name, String trait, String subtrait, String dr, String hands,
			String reach, String range, String attack, String dmgType,
			String parry, String ap, Material material, String weight, String size, String nAttacks, String chamberCap) {
		super(name, ITEMSUBTYPE_WEAPON, trait, subtrait, dr, material, weight);
		
		addProperty(PROPERTY_HANDS, 	Property.PROPERTYTYPE_NUMBER, hands);
		addProperty(PROPERTY_REACH,	Property.PROPERTYTYPE_NUMBER, reach);
		addProperty(PROPERTY_RANGE,	Property.PROPERTYTYPE_NUMBER, range);
		addProperty(PROPERTY_ATTACK,	Property.PROPERTYTYPE_NUMBER, attack);
		addProperty(PROPERTY_DMGTYPE, Property.PROPERTYTYPE_DAMAGETYPE, dmgType);
		addProperty(PROPERTY_PARRY,	Property.PROPERTYTYPE_NUMBER, parry);
		addProperty(PROPERTY_AP,		Property.PROPERTYTYPE_NUMBER, ap);
		addProperty(PROPERTY_SIZE,	Property.PROPERTYTYPE_NUMBER, size);
		addProperty(PROPERTY_NATTACKS,Property.PROPERTYTYPE_NUMBER, nAttacks);
		addProperty(PROPERTY_CHAMBERCAP,Property.PROPERTYTYPE_NUMBER, chamberCap);
		
	}

	protected HashMap<String, Integer> getModifierList() {
		return modifierList;
	}

	public HashMap <String, Integer> getAllowedModifiers(String trait) {
		return allowedModifiers.get(trait);
	}
	
	//Tests if this modifier can be adjusted by the specified modifier (
	private Weapon modify(Weapon m) {
		String modName = m.getProperty(PROPERTY_NAME).getValue();
		String weaponTrait = getProperty(PROPERTY_TRAIT).getValue();
		String weaponName = getProperty(PROPERTY_NAME).getValue();
		
		if(!getAllowedModifiers(weaponTrait).containsKey(modName)) {
			System.out.println("Weapon::modify - Unable to apply the modifier " + modName + " on weapon " + weaponName + ". Modifier not allowed.");
			return this;			

		}

		if(!modifierList.containsKey(modName)) {
			modifierList.put(modName, 0);
		}
		
		if(modifierList.get(modName) >= getAllowedModifiers(weaponTrait).get(modName)) {
			System.out.println("Weapon::modify - Unable to apply the modifier " + modName + " on weapon " + weaponName + ". Maximum limit reached.");
			return this;
		}
		
		addModifier(m, "CONSTRUCTION");
		
		//Increase the modifier count
		modifierList.put(modName, modifierList.get(modName) + 1);
		
		//Unique condition, if the modifier is Long shaft then the trait changes to polearm
		//PROBLEM: If the weapon changes traits, then the allowed modifiers will change and may not be compatible.
		// The long shaft modifier must go last in the list
		if(m.getProperty(PROPERTY_NAME).getValue().equals("Long shaft"))
			setProperty(PROPERTY_TRAIT, Weapon.WEAPONTRAIT_POLEARM);
		
		return this;
	}

	//Apply the requested modifiers to the weapon. The modifiers are separated by semicolons
	public Weapon applyModifiers(String modifiers) {
		
		while(modifiers.length() > 0) {
			String modifier = TestStringUtils.getValueBefore(modifiers, ";");
			modifiers = TestStringUtils.getValueAfter(modifiers, ";");
			
			Weapon mod = WeaponModifier.getModifier(modifier);
			if(mod != null) {
				modify(mod);
			}
			else {
				System.out.println("Weapon::applyModifiers - Modifier " + modifier + " not found for weapon " + getProperty(PROPERTY_NAME).getValue());
			}
		}
		return this;
	}
	
	//Returns the current property value
	public String getPropertyValue(String attributeName) {
		if(attributeName.equals(PROPERTY_ATTACK) && !WeaponModifier.isModifier(getName())) {
			int attack = Integer.parseInt(super.getPropertyValue(PROPERTY_ATTACK)) + getMaterialBonus();
			if(attack > 0)
				return "+" + attack;
			return ""+attack;
		}
		
		if(attributeName.equals(PROPERTY_PARRY) && !WeaponModifier.isModifier(getName())) {
			int attack = Integer.parseInt(super.getPropertyValue(PROPERTY_PARRY)) + getMaterialBonus();
			if(attack > 0)
				return "+" + attack;
			return ""+attack;
		}

		return super.getPropertyValue(attributeName);
	}


	public String toString() {
		
		String s = getProperty(PROPERTY_SIZE).getValue();
		if(!s.equals("-")) {
			Double dS = Double.parseDouble(s);
			dS = Math.floor(dS*100) / 100;
			s = ""+dS.intValue();
		}

		String ap = getProperty(PROPERTY_AP).getValue();
		ap = ((ap.startsWith("+") || ap.startsWith("-"))?"":"+") + ap; 
		if(this instanceof Weapon) {
			String rap = "";
			while(ap.length() > 0) {
				String tap = com.spritekin.utils.TestStringUtils.getValueBefore(ap, "/");
				ap = com.spritekin.utils.TestStringUtils.getValueAfter(ap, "/");

				//Adjust times only if this is not a modifier
				if( !tap.equals("-") && !WeaponModifier.isModifier(getName())) {
					if(tap.startsWith("+")) 
						tap = "" + (6 + Integer.parseInt(tap.substring(1)));
					else if(tap.startsWith("-")) 
						tap = "" + (6 - Integer.parseInt(tap.substring(1)));						
					else
						tap = "" + (6 + Integer.parseInt(tap));
				}
				if(rap.length() > 0) rap += "/";
				rap += tap;	
			}
			ap = rap;
		}

		String dr = getPropertyValue(PROPERTY_DR);
		dr = (dr.startsWith("+")?"":"+") + dr; 

		String attack = getPropertyValue(PROPERTY_ATTACK);
		attack = ((attack.startsWith("+") || attack.startsWith("-")) ?"":"+") + attack; 

		String parry = getPropertyValue(PROPERTY_PARRY);
		parry = ((parry.startsWith("+") || parry.startsWith("-")) ?"":"+") + parry; 

		String range = getPropertyValue(PROPERTY_RANGE);
		if(!range.equals("-") && !range.startsWith("*") && !range.startsWith("+")) {
			Double dRange = Double.parseDouble(range);
			range = "Fire";
			if(dRange == 0) {
				range = "-";
			}
			else if(dRange < 1) 
				range += "/" + (int)(1/dRange); 
			else if (dRange > 1) {
				range += "*" + dRange.intValue();
			}			
		}

		String material = "-";
		String matQuality = getPropertyValue(Material.PROPERTY_MATERIALQUALITY);
		String matCategory = getPropertyValue(MaterialCategory.PROPERTY_MATERIALCATEGORY);
		//System.out.println(matQuality);
		//System.out.println(matCategory);
		if(!WeaponModifier.isModifier(getName())) {
			if(matQuality != null) {
				Material mat = Material.get(MaterialCategory.get(matCategory), Integer.parseInt(matQuality));			
				material = mat.getName();
			}
		}
		else {
			if(matCategory != null)
				material = matCategory;
		}

		String price = "-";
		if( !WeaponModifier.isModifier(getName())) {
			Money mprize = getPrice();
			Double bronze = mprize.toBronze();
			if(bronze > 100000000)
				price = mprize.getAs(Money.TITANIUM_PIECE);
			else if(bronze > 1000000)
				price = mprize.getAs(Money.GOLD_PIECE);
			else if(bronze > 10000)
				price = mprize.getAs(Money.SILVER_PIECE);
			else
				price = mprize.getAs(Money.BRONZE_PIECE);
		
			if(price.length() == 0)
				price = "-";
		}
		
		String w = getProperty(PROPERTY_WEIGHT).getValue();
		if(!w.equals("-")) {
			Double dW = Double.parseDouble(w);
			if( WeaponModifier.isModifier(getName())) {
				dW = Math.round(dW*100) / 100d;
				w = ""+dW;
				if(!w.startsWith("-") && !w.startsWith("+"))
					w = "+" + w;
			}
			else {
				if(dW < 0.1) dW = 0.1;	//Min weight for an item
				//System.out.println(dW);
				//System.out.println(dW*10);
				//System.out.println(Math.floor(dW*10));
				//System.out.println(Math.floor(dW*10)/10);
				dW = Math.round(dW*100) / 100d;
				w = ""+dW;
			}
		}
		
		return getProperty(PROPERTY_NAME).getValue() + 
				"\t" + dr + 
				"\t" + price + 
				"\t" + getProperty(PROPERTY_HANDS).getValue() + 
				"\t" + getProperty(PROPERTY_REACH).getValue() + 
				"\t" + range + 
				"\t" + attack + 
				"\t" + getProperty(PROPERTY_DMGTYPE).getValue() + 
				"\t" + parry + 
				"\t" + ap + 
				"\t" + material + 
				"\t" + w + 
				"\t" + s;
	}

}


