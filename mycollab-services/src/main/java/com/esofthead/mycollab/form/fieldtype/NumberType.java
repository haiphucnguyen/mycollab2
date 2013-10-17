package com.esofthead.mycollab.form.fieldtype;

public class NumberType implements Type {
	private int length;

	private int decimal;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDecimal() {
		return decimal;
	}

	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}
}
