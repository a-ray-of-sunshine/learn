package com.sunshine.learn.designpattern.principle;

public class NewTaxCalculator implements ITaxCalculate {

	@Override
	public float GetTax(float salary) {
		return salary * 0.02f;
	}

}
