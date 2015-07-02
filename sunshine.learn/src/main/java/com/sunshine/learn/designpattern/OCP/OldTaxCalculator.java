package com.sunshine.learn.designpattern.OCP;

public class OldTaxCalculator implements ITaxCalculate {

	@Override
	public float GetTax(float salary) {
		return salary * 0.03f;
	}

}
