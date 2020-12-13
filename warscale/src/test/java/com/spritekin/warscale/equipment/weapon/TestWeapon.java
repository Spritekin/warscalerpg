package com.spritekin.warscale.equipment.weapon;

import junit.framework.Assert;
import com.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.item.weapon.WeaponModifier;
import com.spritekin.warscale.item.weapon.Weaponry;
import com.spritekin.warscale.item.weapon.Weapon;

public class TestWeapon extends WarscaleTestCase {
/*		
	public void testWeaponCreation() {
		
		System.out.println("TestWeapon::testWeaponCreation");

		Weapon w = Weaponry.get("Sword");
		//System.out.println(w.toString());
		Assert.assertEquals("Invalid weapon info",	"Sword	+3	45bp	1	1	-	+5	S	+0	8	Iron	1.5	2", w.toString());
		
	}

	public void testWeaponModifierCreation() {
		
		System.out.println("TestWeapon::testWeaponModifierCreation");

		//Direct creation
		Weapon i = new Weapon("Additional lash","", Weapon.WEAPONSUBTRAIT_MELEE, "+2",	"-",	"-",	"-",	"+2",	"-",	"-",	"+1",	null,	"+1",	"-","-","-");
		Assert.assertEquals("Invalid weapon modifier ",	"Additional lash	+2	-	-	-	-	+2	-	-	+1	-	+1.0	-", i.toString());
		
		//Extract from the big list
		Weapon war = WeaponModifier.getModifier("War");
		Assert.assertEquals("Invalid weapon modifier ",	"War	+3	-	-	-	-	+2	-	-	-	-	+1.0	-", war.toString());

		Weapon light = new Weapon("Light",			"", Weapon.WEAPONSUBTRAIT_MELEE, 	"+2",	"-",	"-",	"-",	"-",	"-",	"-",	"-",	null,	"-0.2",	"-","-","-");
		Assert.assertEquals("Invalid weapon modifier ",	"Light	+2	-	-	-	-	-	-	-	-	-	-0.2	-", light.toString());

	}

	public void testWeaponConstruction() {
		//We are going to build a katana by building its base and components one by one
		System.out.println("TestWeapon::testWeaponConstruction");

		//A katana is a backsword with the War and Light modifiers applied
		//addWeaponTemplate("Katana",			"Backsword", 	"War;Light");

		//Get the backsword
		Weapon backsword = Weaponry.get("Backsword");
		//System.out.println(backsword.toString());

		Weapon war = WeaponModifier.getModifier("War");
		//System.out.println(war.toString());
		Weapon light = WeaponModifier.getModifier("Light");
		//System.out.println(light.toString());

		//Add the modifiers to the weapon
		//backsword.addModifier(war, "CONSTRUCTION");
		//backsword.addModifier(light, "CONSTRUCTION");
		//System.out.println(backsword.toString());

		Assert.assertEquals("Invalid weapon construction ",	"Backsword	+9	207bp	1	1	-	+8	S	+0	9	Iron	2.3	2", backsword.toString());
		
		//Extract from the big list
		Weapon katana = Weaponry.get("Katana");
		Assert.assertEquals("Invalid weapon ",	"Katana	+9	207bp	1	1	-	+8	S	+0	9	Iron	2.3	2", katana.toString());
				
	}

	public void testWeaponMaterialConstruction() {
		//We are going to build a katana by building its base and components one by one
		Weapon cestus = Weaponry.get("Cestus");
		//System.out.println(cestus);
		Assert.assertEquals("Invalid weapon ",	"Cestus	+5	75bp	1	0	-	+2	P/B	+0	6	Iron	1.5	1", cestus.toString());

	}
	
	public void testWeaponMaterial() {
		
		System.out.println("TestWeapon::testWeaponMaterial");

		Weapon katana = Weaponry.get("Katana");
		Assert.assertEquals("Invalid weapon ",	"Katana	+9	207bp	1	1	-	+8	S	+0	9	Iron	2.3	2", katana.toString());
		
		//katana.setBase(Material.MATERIAL, Material.get("Gold"));
		Assert.assertEquals("Invalid weapon ",	"Katana	+9	2070gp	1	1	-	+13	S	+5	9	Gold	2.3	2", katana.toString());
		
		//Assert.assertEquals("Invalid hardness",	"40", katana.getPropertyValue(MaterialCategory.PROPERTY_MATERIALHARDNESS));

	}
	*/

}
