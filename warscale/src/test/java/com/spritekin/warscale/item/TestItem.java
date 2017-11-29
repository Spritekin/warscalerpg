package test.spritekin.warscale.item;

import junit.framework.Assert;
import test.spritekin.warscale.WarscaleTestCase;

import com.spritekin.warscale.item.Item;
import com.spritekin.warscale.item.weapon.Weapon;
import com.spritekin.warscale.material.Material;
import com.spritekin.warscale.material.MaterialCategory;

public class TestItem extends WarscaleTestCase {
		
	public void testItemCreation() {
		
		System.out.println("TestItem::testItemCreation");

		Item i = new Item("TestItem", Item.ITEMSUBTYPE_WEAPON, Weapon.WEAPONTRAIT_SWORD, Weapon.WEAPONSUBTRAIT_MELEE, "1", Material.get(MaterialCategory.get(MaterialCategory.MATERIALCATEGORY_METAL), 5), "1.5");
		Assert.assertEquals("Invalid hardness",	"1", i.getPropertyValue(Item.PROPERTY_DR));

	}
	

}
