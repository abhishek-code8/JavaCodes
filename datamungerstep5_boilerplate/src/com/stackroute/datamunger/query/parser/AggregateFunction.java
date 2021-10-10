package com.stackroute.datamunger.query.parser;

/* This class is used for storing name of field, aggregate function for 
 * each aggregate function
 * */
public class AggregateFunction {
	private String field;
	private String function;

	public AggregateFunction(String field, String function) {
		this.field =field;
		this.function=function;
		//System.out.println("it ran");
	}

	public String getFunction() {
		// TODO Auto-generated method stub
		return function;
	}

	public String getField() {
		// TODO Auto-generated method stub
		return field;
	}
	
	

}
