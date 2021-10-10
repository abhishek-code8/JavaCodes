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

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	private String fileName;
	static String filepath = System.getProperty("user.dir")+"/ipl.csv";
	
	private Header head = new Header();
	private DataTypeDefinitions dataTp = new DataTypeDefinitions();

	// Parameterized constructor to initialize filename
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
	 * Note: Return type of the method will be Header
	 */
	
	@Override
	public Header getHeader() throws IOException {
		

		// read the first line
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
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

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */
	
	 public static String checkDatatype(String input) {
	        String datatype = "";
	        
	        if (input.matches("\\d+")) {
	            datatype = "java.lang.Integer";
	        }
	 
	        else if (input.matches("\\d*[.]\\d+")) {
	            datatype = "java.lang.Double";
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
		try {
			 fileReader = new FileReader(filepath);
		}catch(Exception e) {
			 fileReader = new FileReader("data/ipl.csv");
		}
		try (BufferedReader br= new BufferedReader(fileReader)) {
		
		//BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));		
		
		String[] linedata= null;
		boolean firstLine = true;
		int p=0;
		while(p<3) {
			line = br.readLine().concat(" ");
			System.out.println(line);
			//line.
			linedata= line.split(",");

			
			for(int i=0;i<linedata.length;++i) {
				//linedata[i]= linedata[i].trim();
				linedata[i] = checkDatatype(linedata[i]);
				
				}
			
			++p;
		}
		for(String s:linedata) {
			System.out.println(s);
		}
		dataTp.setDataTypes(linedata);
		br.close();
		
		return dataTp;
		}
	}
	
}


