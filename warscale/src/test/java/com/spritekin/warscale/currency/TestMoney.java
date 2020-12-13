package com.spritekin.warscale.currency;

import com.spritekin.warscale.WarscaleTestCase;
import com.spritekin.warscale.core.DataType;
import com.spritekin.warscale.core.PropertyFactory;
import com.spritekin.warscale.core.WarscaleObject;
import com.spritekin.warscale.currency.Money;

public class TestMoney extends WarscaleTestCase {
		
	public void testMoneyCreation() {
		
		class Local {};
		System.out.println(Local.class.getName() + "::" + Local.class.getEnclosingMethod().getName());

		Money m = new Money("2tp,3gp,4sp,5bp");

		// Test properties
		assert m.toString().equals("2tp,3gp,4sp,5bp") : 
			"Invalid money expression: " + m.toString() + " should be 2tp,3gp,4sp,5bp";

	}

	public void testMoneyAdd() {
		
		class Local {};
		System.out.println(Local.class.getName() + "::" + Local.class.getEnclosingMethod().getName());

		Money m = new Money();
		m.set("2tp,4sp,3gp,5bp");
		m.add("20tp,50bp,30gp,40sp"); //Notice different order

		assert m.toString().equals("22tp,33gp,44sp,55bp") :
			"Invalid money expression: " + m.toString() + " should be 22tp,33gp,44sp,55bp";

	}

	public void testMoneyScale() {
		
		class Local {};
		System.out.println(Local.class.getName() + "::" + Local.class.getEnclosingMethod().getName());

		Money m = new Money("2tp,3gp,4sp,5bp");
		m.scale(2);
		assert m.toString().equals("4tp,6gp,8sp,10bp") :
			"Invalid money expression: " + m.toString() + " should be 4tp,6gp,8sp,10bp";
	
	}

	public void testMoneyScaleDouble() {
		
		class Local {};
		System.out.println(Local.class.getName() + "::" + Local.class.getEnclosingMethod().getName());

		// This test is particular because we know we can't multiply 2tp by 1.7
		// The class moves any excess to the next coin level, so 2tp * 1.7 = 3.4tp
		// As I can't have 0.4tp it is converted to gp which is the next coin type.
		Money m = new Money("2tp,3gp,4sp,5bp");		
		m.scale(1.7);
		assert m.toString().equals("3tp,45gp,16sp,88bp") :
			"Invalid money expression: " + m.toString() + " should be 3tp,45gp,16sp,88bp";

	}

	public void testSimpleMoneyProperty() {
		
		class Local {};
		System.out.println(Local.class.getName() + "::" + Local.class.getEnclosingMethod().getName());

		// This test will make 		
		WarscaleObject parent = new WarscaleObject("Parent Object");
		MoneyProperty mp = (MoneyProperty)PropertyFactory.newPropertyOfType(Money.MONEY, parent, "TestMoneyProperty");
		String moneyExpr = "2gp,1sp";
		mp.setBase(moneyExpr);
		assert mp.getValue().equals(moneyExpr) :
			"Invalid value: expected " + moneyExpr + ", found" + mp.getValue();
		
	}

	public void testSimpleMoneyPropertyOperation() {
		
		class Local {};
		System.out.println(Local.class.getName() + "::" + Local.class.getEnclosingMethod().getName());

		// This test will make 		
		WarscaleObject parent = new WarscaleObject("Parent Object");
		MoneyProperty mp = (MoneyProperty)PropertyFactory.newPropertyOfType(Money.MONEY, parent, "TestMoneyProperty");
		String moneyExpr = "1+1gp,1sp";
		String moneyResult = "2gp,1sp";
		mp.setBase(moneyExpr);
		assert mp.getValue().equals(moneyResult) :
			"Invalid value: expected " + moneyResult + ", found" + mp.getValue();
		
	}

	public void testMoneyPropertyOperationOnOtherProperty() {
		
		class Local {};
		System.out.println(Local.class.getName() + "::" + Local.class.getEnclosingMethod().getName());

		// This test will make 		
		WarscaleObject parent = new WarscaleObject("Parent Object")
			.addProperty("TEST", DataType.NUMBER, "2");
		MoneyProperty mp = (MoneyProperty)PropertyFactory.newPropertyOfType(Money.MONEY, parent, "TestMoneyProperty");
		String moneyExpr = "2+[TEST]gp,1+[TEST]sp";
		String moneyResult = "4gp,3sp";
		mp.setBase(moneyExpr);
		assert mp.getValue().equals(moneyResult) :
			"Invalid value: expected " + moneyResult + ", found" + mp.getValue();		
	}

}
