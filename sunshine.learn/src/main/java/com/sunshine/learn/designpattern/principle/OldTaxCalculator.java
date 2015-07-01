package com.sunshine.learn.designpattern.principle;

public class OldTaxCalculator implements ITaxCalculate {

	@Override
	public float GetTax(float salary) {
		return salary * 0.03f;
	}

}
