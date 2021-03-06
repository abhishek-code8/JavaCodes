package com.stackroute.datamunger.query;

/*
 * Implementation of DataTypeDefinitions class. This class contains a method getDataTypes() 
 * which will contain the logic for getting the datatype for a given field value. This
 * method will be called from QueryProcessors.   
 * In this assignment, we are going to use Regular Expression to find the 
 * appropriate data type of a field. 
 * Integers: should contain only digits without decimal point 
 * Double: should contain digits as well as decimal point 
 * Date: Dates can be written in many formats in the CSV file. 
 * However, in this assignment,we will test for the following date formats('dd/mm/yyyy',
 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
 */
public class DataTypeDefinitions {

	//method stub
	public static Object getDataType(String input) {
	
		// checking for Integer
		
		// checking for floating point numbers
		
		// checking for date format dd/mm/yyyy
		
		// checking for date format mm/dd/yyyy
		
		// checking for date format dd-mon-yy
		
		// checking for date format dd-mon-yyyy
		
		// checking for date format dd-month-yy
		
		// checking for date format dd-month-yyyy
		
		// checking for date format yyyy-mm-dd

		String datatype = "";

		if (input.matches("\\d+")) {
			datatype = "java.lang.Integer";
		}

		else if (input.matches("\\d*[.]\\d+")) {
			datatype = "java.lang.Double";
		}
		else if(input.matches("\\d{2}[-]\\w+[-]\\d{2}")|| input.matches("\\d{2}[-]\\w+[-]\\d{4}")||input.matches("\\d{4}[-]\\w+[-]\\d{2}")){
			datatype = "java.util.Date";
		}
		else if(input.matches(" ")) {
			datatype = "java.lang.Object";
		}

		else {
			datatype = "java.lang.String";
		}

		return datatype;
		

	}
	

	
}
