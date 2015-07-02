package com.sunshine.learn.designpattern.OCP;

public class NewTaxCalculator implements ITaxCalculate {

	@Override
	public float GetTax(float salary) {
		return salary * 0.02f;
	}

}
