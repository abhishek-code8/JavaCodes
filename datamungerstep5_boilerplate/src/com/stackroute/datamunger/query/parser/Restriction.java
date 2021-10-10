package com.stackroute.datamunger.query.parser;

/*
 * This class is used for storing name of field, condition and value for 
 * each conditions
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

	public String getPropertyName() {
		// TODO Auto-generated method stub
		return field;
	}

	public String getPropertyValue() {
		// TODO Auto-generated method stub
		return value;
	}

	public String getCondition() {
		// TODO Auto-generated method stub
		return condition;
	}

	

}
