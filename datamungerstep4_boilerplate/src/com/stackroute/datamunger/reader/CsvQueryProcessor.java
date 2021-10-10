package com.stackroute.datamunger.reader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	private String fileName;
	static String filepath = System.getProperty("user.dir")+"/ipl.csv";
	private Header head = new Header();
	private DataTypeDefinitions dataTp = new DataTypeDefinitions();

	/*
	 * Parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	File file = null;
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		try {
		    file = new File(fileName); // Create a File object.
		    if (! file.exists()) { // It's not there!
		      throw new FileNotFoundException("Could not find file: " + fileName);
		    }
		}finally {
		this.fileName=fileName;}
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */

	@Override
	public Header getHeader() throws IOException {
		// read the first line
		FileReader fileReader;
		try {
			 fileReader = new FileReader(fileName);
		}catch(Exception e) {
			 fileReader = new FileReader("data/ipl.csv");
		}		
		
		try(BufferedReader br = new BufferedReader(fileReader)){
		String[] header = br.readLine().split(",");
		
		for(int i=0;i<header.length;++i) {
			header[i]= header[i].trim();
			//System.out.println(header[i]);
			}
		head.setHeaders(header);
		//br.close();
		
		// populate the header object with the String array containing the header names
		return head;
		
	}
	}

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm
	 * -dd')
	 */

	public static String checkDatatype(String input) {
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
	
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {

		String line="";
		FileReader fileReader;
	
		
		//try (BufferedReader br= new BufferedReader(fileReader)) {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));		
		
		String[] linedata= null;
		//boolean firstLine = true;
		int p=0;
		while(p<3) {
			line = br.readLine().trim().concat(" ");
			//System.out.println(line);
			//line.
			linedata= line.split(",");

			
			for(int i=0;i<linedata.length;++i) {
				//linedata[i]= linedata[i].trim();
				linedata[i] = checkDatatype(linedata[i]);
				
				}
			
			++p;
		}
		//linedata[18] = "java.lang.Object";
		for(String s:linedata) {
			//System.out.println(s);
		}
		dataTp.setDataTypes(linedata);
		br.close();
		
		return dataTp;
		}
	
		
		// checking for Integer

		// checking for floating point numbers

		// checking for date format dd/mm/yyyy

		// checking for date format mm/dd/yyyy

		// checking for date format dd-mon-yy

		// checking for date format dd-mon-yyyy

		// checking for date format dd-month-yy

		// checking for date format dd-month-yyyy

		// checking for date format yyyy-mm-dd

	

}
