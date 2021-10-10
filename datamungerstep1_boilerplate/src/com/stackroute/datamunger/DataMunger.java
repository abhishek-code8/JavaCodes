package com.stackroute.datamunger;

import java.util.ArrayList;

/*There are total 5 DataMungertest files:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 3 methods
 * a)getSplitStrings()  b) getFileName()  c) getBaseQuery()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 3 methods
 * a)getFields() b) getConditionsPartQuery() c) getConditions()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getLogicalOperators() b) getOrderByFields()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * 4)DataMungerTestTask4.java file is for testing following 2 methods
 * a)getGroupByFields()  b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask4.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class DataMunger {

	/*
	 * This method will split the query string based on space into an array of words
	 * and display it on console
	 */

	public String[] getSplitStrings(String queryString) {
		String[] result = queryString.toLowerCase().split(" ");
		return result;
	}

	/*
	 * Extract the name of the file from the query. File name can be found after a
	 * space after "from" clause. Note: ----- CSV file can contain a field that
	 * contains from as a part of the column name. For eg: from_date,from_hrs etc.
	 * 
	 * Please consider this while extracting the file name in this method.
	 */

	public String getFileName(String queryString) {
	//String myStr = "select * from 435dfipl.csv where season > 2014 and city = 'Bangalore'";
    String[] result =  queryString.split(" ");
	String finalResult =new String();
    String testcase = new String(".csv");
		for(String s:result){
			if(s.length()>4){
				if(s.substring(s.length()-4,s.length()).equals(testcase) ){
					finalResult=finalResult+s;
					break;
				}
			}
		}
        		
		return finalResult;
	}

	/*
	 * This method is used to extract the baseQuery from the query string. BaseQuery
	 * contains from the beginning of the query till the where clause
	 * 
	 * Note: ------- 1. The query might not contain where clause but contain order
	 * by or group by clause 2. The query might not contain where, order by or group
	 * by clause 3. The query might not contain where, but can contain both group by
	 * and order by clause
	 */
	
	public String getBaseQuery(String queryString) {
		String result = new String();
		if(queryString.indexOf("where")!=-1) 
			result = queryString.substring(0,queryString.indexOf("where")-1);
			else if(queryString.indexOf("group")!=-1) {
				result = queryString.substring(0,queryString.indexOf("group")-1);
			}
			else
			return null;
				
		return result.toLowerCase();
	}

	/*
	 * This method will extract the fields to be selected from the query string. The
	 * query string can have multiple fields separated by comma. The extracted
	 * fields will be stored in a String array which is to be printed in console as
	 * well as to be returned by the method
	 * 
	 * Note: 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The field
	 * name can contain '*'
	 * 
	 */
	
	public String[] getFields(String queryString) {
		String [] result = {};
		
		
		for(String itr:queryString.split(" ")){
			if(itr.indexOf(",")!=-1){
				result=itr.split(",");
			}
		}
		
		if(result.length==0) {
			String[] news = {"*"};
			return news;}
		
			else
			return result;
	}

	/*
	 * This method is used to extract the conditions part from the query string. The
	 * conditions part contains starting from where keyword till the next keyword,
	 * which is either group by or order by clause. In case of absence of both group
	 * by and order by clause, it will contain till the end of the query string.
	 * Note:  1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
	 * might not contain where clause at all.
	 */
	
	public String getConditionsPartQuery(String queryString) {
		String result=new String();
		if(queryString.indexOf("where")==-1) {
			return null;
		}
		result = queryString.substring(queryString.indexOf("where")+5, queryString.indexOf("order")==-1?
				(queryString.indexOf("group")==-1?
						queryString.length():queryString.indexOf("group"))
				:queryString.indexOf("order"));
		
		return result.toLowerCase().trim();
	}

	/*
	 * This method will extract condition(s) from the query string. The query can
	 * contain one or multiple conditions. In case of multiple conditions, the
	 * conditions will be separated by AND/OR keywords. for eg: Input: select
	 * city,winner,player_match from ipl.csv where season > 2014 and city
	 * ='Bangalore'
	 * 
	 * This method will return a string array ["season > 2014","city ='bangalore'"]
	 * and print the array
	 * 
	 * Note: ----- 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
	 * might not contain where clause at all.
	 */

	public String[] getConditions(String queryString) {
		String result=new String();
		if(queryString.indexOf("where")==-1) {
			return null;
		}
		result = queryString.substring(queryString.indexOf("where")+6, queryString.indexOf("order")==-1?
				(queryString.indexOf("group")==-1?
						queryString.length():queryString.indexOf("group"))
				:queryString.indexOf("order"));
		String[] finalResult= {};
		//String [] newStr = new String[1];
		if(result.indexOf("and")!=-1|| result.indexOf("or")!=-1) {			
		finalResult=result.trim().toLowerCase().split(" and | or ");
		}		
		else
			finalResult= result.trim().split(",");
		for(int i=0;i<finalResult.length;++i) {
			finalResult[i]= finalResult[i].trim();
			}
		if(finalResult.length==0) {
			return null;
		}
		return finalResult;
	}

	/*
	 * This method will extract logical operators(AND/OR) from the query string. The
	 * extracted logical operators will be stored in a String array which will be
	 * returned by the method and the same will be printed Note:  1. AND/OR
	 * keyword will exist in the query only if where conditions exists and it
	 * contains multiple conditions. 2. AND/OR can exist as a substring in the
	 * conditions as well. For eg: name='Alexander',color='Red' etc. Please consider
	 * these as well when extracting the logical operators.
	 * 
	 */

	public String[] getLogicalOperators(String queryString) {
		String [] stringList=queryString.split(" ");
		String result = new String();
    	
		for(String s:stringList){
			if(s.equals("and")||s.equals("or")||s.equals("not")){
				result=result+" "+s;
			}
		}
		if(result.trim().length()==0) {
			return null;
		}
		//result.trim();
		return result.trim().split(" ");
	}

	/*
	 * This method extracts the order by fields from the query string. Note: 
	 * 1. The query string can contain more than one order by fields. 2. The query
	 * string might not contain order by clause at all. 3. The field names,condition
	 * values might contain "order" as a substring. For eg:order_number,job_order
	 * Consider this while extracting the order by fields
	 */

	public String[] getOrderByFields(String queryString) {
		String [] finalResult ={};
		String[] result = queryString.split(" ");
		if(queryString.indexOf("order")==-1) {
			return null;
		}
		
		int idx =0;
		int ctr =0;
		for(String s: result){
			if(s.equals("order")){
				idx=ctr;
			}
			++ctr;
		}
		finalResult= result[idx+2].trim().split("'");
		

		return finalResult;
	}

	/*
	 * This method extracts the group by fields from the query string. Note:
	 * 1. The query string can contain more than one group by fields. 2. The query
	 * string might not contain group by clause at all. 3. The field names,condition
	 * values might contain "group" as a substring. For eg: newsgroup_name
	 * 
	 * Consider this while extracting the group by fields
	 */

	public String[] getGroupByFields(String queryString) {
		String [] finalResult ={};
		String[] result = queryString.split(" ");
		if(queryString.indexOf("group")==-1) {
			return null;
		}
		
		int idx =0;
		int ctr =0;
		for(String s: result){
			if(s.equals("group")){
				idx=ctr;
			}
			++ctr;
		}
		finalResult= result[idx+2].trim().split("'");
		

		return finalResult;

		
	}

	/*
	 * This method extracts the aggregate functions from the query string. Note:
	 *  1. aggregate functions will start with "sum"/"count"/"min"/"max"/"avg"
	 * followed by "(" 2. The field names might
	 * contain"sum"/"count"/"min"/"max"/"avg" as a substring. For eg:
	 * account_number,consumed_qty,nominee_name
	 * 
	 * Consider this while extracting the aggregate functions
	 */

	public String[] getAggregateFunctions(String queryString) {
		String [] result= queryString.split(" ");
		String finalString= new String();
		
		for(String itr:queryString.split(" ")){
			if(itr.indexOf(",")!=-1){
				result=itr.split(",");
			}
		}
		if(queryString.indexOf("sum")==-1&&queryString.indexOf("count")==-1&&queryString.indexOf("min")==-1&&
				queryString.indexOf("max")==-1&&queryString.indexOf("avg")==-1)
			return null;
		for(String s:result){
			if(s.length()>=3) {
			if(s.substring(0,3).equals("max")||s.substring(0,3).equals("min")||
					s.substring(0,3).equals("avg")||s.substring(0,5).equals("count")||s.substring(0,3).equals("sum")){
				finalString=finalString+" "+s;
			}
		}
		}

		if(finalString.trim().length()==0){
			return null;
		}
		return finalString.trim().split(" ");
	}

}