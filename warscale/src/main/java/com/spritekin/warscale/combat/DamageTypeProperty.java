package com.spritekin.warscale.combat;

import com.spritekin.warscale.combat.DamageType;
import com.spritekin.warscale.core.ExpressionTest;
import java.util.HashMap;

//A text attribute treats the values as text. It will try to concatenate the values
// The values expressions can have the following prefixes
// ==       : The value is set to this text regardless of any past operations

public class DamageTypeProperty extends Property {	

	public DamageTypeProperty(WarscaleObject parent, String name) {
		super(parent, name);
		setType(PROPERTYTYPE_DAMAGETYPE);
	}
	
	public void recomputePropertyTotal() {
		HashMap<String, String> vars = base.getVariableList();
		vars = gatherVariableData(vars);
		String res = base.replaceVariables(vars);

		DamageType dtS = new DamageType(res);
		dtS.addDamageType(res);

		for(Property mod : getPropertyModifiers()) {
			//Let the mod evaluate itself as much as it can
			mod.recomputePropertyTotal();
			
			String modResult = mod.getResult();
			vars = com.spritekin.utils.TestStringUtils.getVariables(mod.getResult());
			vars = gatherVariableData(vars);
			modResult = com.spritekin.utils.TestStringUtils.replaceVariables(modResult, vars);
			
			if(modResult.startsWith("="))
				dtS.setDamageType(modResult.substring(1));
			else
				dtS.addDamageType(modResult);
		}
		
		setResult(dtS.toString());
	}
	
}
