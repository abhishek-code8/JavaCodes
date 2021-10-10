package com.stackroute.datamunger.query.parser;

/*
 * This class is used for storing name of field, condition and value for 
 * each conditions
 * generate getter and setter for this class,
 * Also override toString method
 * */

public class Restriction {
	private String field;
	private String condition;
	private String value;

	// Write logic for constructor
	public Restriction(String name, String value, String condition) {
		this.field =name;
		this.value=value;
		this.condition=condition;

	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Restriction [field=" + field + ", condition=" + condition + ", value=" + value + "]";
	}
	

}