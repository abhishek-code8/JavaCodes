package com.stackroute.datamunger.query.parser;

/* This class is used for storing name of field, aggregate function for 
 * each aggregate function
 * generate getter and setter for this class,
 * Also override toString method
 * */

public class AggregateFunction {
	private String field;
	private String function;

	// Write logic for constructor
	public AggregateFunction(String field, String function) {
		this.field =field;
		this.function=function;
		//System.out.println("it ran");
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public String toString() {
		//System.out.println(getField()+" "+getFunction());
		String s = "AggregateFunction [field=" + getField() + ", function=" + getFunction() + "]";
		//System.out.println(s);
		return s;
	}
	
	/*
	@Override
	public String toString() {
		String result = new String();
		//result = getField()+"("+getFunction
	}
	*/
	

}