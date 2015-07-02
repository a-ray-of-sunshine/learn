package com.sunshine.learn.designpattern.principle.OCP;

public class NewTaxCalculator implements ITaxCalculate {

	@Override
	public float GetTax(float salary) {
		return salary * 0.02f;
	}

}
