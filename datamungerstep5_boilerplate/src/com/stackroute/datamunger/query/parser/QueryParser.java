package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryParser {

	private QueryParameter queryParameter = new QueryParameter();
	
	/*
	 * This method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {

		queryParameter.setFileName(getFileName(queryString)); //done
		queryParameter.setBaseQuery(getBaseQuery(queryString)); //done
		queryParameter.setRestrictions(getRestrictions(queryString));
		queryParameter.setLogicalOperators(getLogicalOperators(queryString)); //done
		queryParameter.setFields(getFields(queryString)); //done

		queryParameter.setAggregateFunctions(getAggregateFunctionsAlgo(queryString));
		queryParameter.setGroupByFields(getGroupByFields(queryString)); //done
		queryParameter.setOrderByFields(getOrderByFields(queryString)); //done


		return queryParameter;
	
		/*
		 * extract the name of the file from the query. File name can be found after the
		 * "from" clause.
		 */
		
		
		/*
		 * extract the order by fields from the query string. Please note that we will
		 * need to extract the field(s) after "order by" clause in the query, if at all
		 * the order by clause exists. For eg: select city,winner,team1,team2 from
		 * data/ipl.csv order by city from the query mentioned above, we need to extract
		 * "city". Please note that we can have more than one order by fields.
		 */
		
		
		/*
		 * extract the group by fields from the query string. Please note that we will
		 * need to extract the field(s) after "group by" clause in the query, if at all
		 * the group by clause exists. For eg: select city,max(win_by_runs) from
		 * data/ipl.csv group by city from the query mentioned above, we need to extract
		 * "city". Please note that we can have more than one group by fields.
		 */
		
		
		/*
		 * extract the selected fields from the query string. Please note that we will
		 * need to extract the field(s) after "select" clause followed by a space from
		 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
		 * query mentioned above, we need to extract "city" and "win_by_runs". Please
		 * note that we might have a field containing name "from_date" or "from_hrs".
		 * Hence, consider this while parsing.
		 */
		
		
		
		
		/*
		 * extract the conditions from the query string(if exists). for each condition,
		 * we need to capture the following: 
		 * 1. Name of field 
		 * 2. condition 
		 * 3. value
		 * 
		 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
		 * where season >= 2008 or toss_decision != bat
		 * 
		 * here, for the first condition, "season>=2008" we need to capture: 
		 * 1. Name of field: season 
		 * 2. condition: >= 
		 * 3. value: 2008
		 * 
		 * the query might contain multiple conditions separated by OR/AND operators.
		 * Please consider this while parsing the conditions.
		 * 
		 */
		
		
		/*
		 * extract the logical operators(AND/OR) from the query, if at all it is
		 * present. For eg: select city,winner,team1,team2,player_of_match from
		 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
		 * bangalore
		 * 
		 * the query mentioned above in the example should return a List of Strings
		 * containing [or,and]
		 */
		
		
		/*
		 * extract the aggregate functions from the query. The presence of the aggregate
		 * functions can determined if we have either "min" or "max" or "sum" or "count"
		 * or "avg" followed by opening braces"(" after "select" clause in the query
		 * string. in case it is present, then we will have to extract the same. For
		 * each aggregate functions, we need to know the following: 
		 * 1. type of aggregate function(min/max/count/sum/avg) 
		 * 2. field on which the aggregate function is being applied
		 * 
		 * Please note that more than one aggregate function can be present in a query
		 * 
		 * 
		 */

	}
	public String getFileName(String queryString) {
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
	 *
	 * Extract the baseQuery from the query.This method is used to extract the
	 * baseQuery from the query string. BaseQuery contains from the beginning of the
	 * query till the where clause
	 */
	public String getBaseQuery(String queryString) {
		String result = new String();
		if(queryString.indexOf("where")!=-1)
			result = queryString.substring(0,queryString.indexOf("where")-1);
		else if(queryString.indexOf("group")!=-1) {
			result = queryString.substring(0,queryString.indexOf("group")-1);
		}
		//else
		//return null;

		return result.toLowerCase();
	}

	/*
	 * extract the order by fields from the query string. Please note that we will
	 * need to extract the field(s) after "order by" clause in the query, if at all
	 * the order by clause exists. For eg: select city,winner,team1,team2 from
	 * data/ipl.csv order by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one order by fields.
	 */
	public List<String> getOrderByFields(String queryString) {
		List<String> finalResult =new ArrayList<String>();
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
		finalResult.add(result[idx+2].trim());


		return finalResult;
	}

	/*
	 * Extract the group by fields from the query string. Please note that we will
	 * need to extract the field(s) after "group by" clause in the query, if at all
	 * the group by clause exists. For eg: select city,max(win_by_runs) from
	 * data/ipl.csv group by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one group by fields.
	 */
	public List<String> getGroupByFields(String queryString) {
		List<String> finalResult = new ArrayList<String>();
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
		finalResult.add(result[idx+2].trim());


		return finalResult;
	}


	/*
	 * Extract the selected fields from the query string. Please note that we will
	 * need to extract the field(s) after "select" clause followed by a space from
	 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
	 * query mentioned above, we need to extract "city" and "win_by_runs". Please
	 * note that we might have a field containing name "from_date" or "from_hrs".
	 * Hence, consider this while parsing.
	 */
	public List<String> getFields(String queryString){
		List<String> result =  new ArrayList<String>();


		for(String itr:queryString.split(" ")){
			if(itr.indexOf(",")!=-1){
				result= Arrays.asList(itr.split(","));

			}
		}

		if(result.size()==0) {
			List<String> news = new ArrayList<String>();
			news.add("*");
			return news;}

		else
			return result;
	}
	/*
	 * Extract the conditions from the query string(if exists). for each condition,
	 * we need to capture the following: 1. Name of field 2. condition 3. value
	 *
	 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
	 * where season >= 2008 or toss_decision != bat
	 *
	 * here, for the first condition, "season>=2008" we need to capture: 1. Name of
	 * field: season 2. condition: >= 3. value: 2008
	 *
	 * the query might contain multiple conditions separated by OR/AND operators.
	 * Please consider this while parsing the conditions.
	 *
	 */
	public List <Restriction> getRestrictions(String queryString){
		//System.out.println(queryString);
		List <Restriction> returnRestriction = new ArrayList<Restriction>();
		String result=new String();
		if(queryString.indexOf("where")==-1) {
			return null;
		}
		int endPoint = queryString.length() ;
		if(queryString.indexOf("order")!=-1) {
			endPoint=queryString.indexOf("order");
		}
		if (queryString.indexOf("group")!=-1){
			endPoint= queryString.indexOf("group");
		}

		result = queryString.substring(queryString.indexOf("where")+6, endPoint);
		System.out.println(result);
		String[] finalResult= {};
		//String [] newStr = new String[1];
		if(result.indexOf("and")!=-1|| result.indexOf("or")!=-1) {
			finalResult=result.trim().split(" and | or ");
		}
		else
			finalResult= result.trim().split(",");
		for(int i=0;i<finalResult.length;++i) {
			finalResult[i]= finalResult[i].trim();
			System.out.println(finalResult[i]);
		}
		for(String s:finalResult) {
			String t[] = {};
			t=s.split(" ");
			//System.out.println(t.length);
			if(t.length<10000) {
				String value= new String();
				String condition= new String();
				String name= new String();
				String [] tempList = s.split("[=>< ']");
				List <String> trp = new ArrayList<String>();
				for(String k : tempList) {

					if(k!=null && !k.equals("")) {

						trp.add(k);
					}
				}// end of for
				name =trp.get(0);
				value = trp.get(1);
				System.out.println(name+" "+value);
				for(int i=0;i<s.length();++i) {
					if(s.charAt(i)=='<'||s.charAt(i)=='>'||s.charAt(i)=='!'||s.charAt(i)=='=') {
						condition=condition+s.charAt(i);
						if(s.charAt(i+1)=='<'||s.charAt(i+1)=='>'||s.charAt(i+1)=='!'||s.charAt(i+1)=='=') {
							condition = condition+s.charAt(i+1);
						}
						break;
					}
				}
				Restriction newObj = new Restriction(name,value,condition);
				returnRestriction.add(newObj);

			}
			else {
				Restriction newObj = new Restriction(t[0],t[2],t[1]);
				returnRestriction.add(newObj);
			}

		}
		return returnRestriction;
	}


	/*
	 * Extract the logical operators(AND/OR) from the query, if at all it is
	 * present. For eg: select city,winner,team1,team2,player_of_match from
	 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
	 * bangalore
	 *
	 * The query mentioned above in the example should return a List of Strings
	 * containing [or,and]
	 */
	public List <String> getLogicalOperators(String queryString) {
		String [] stringList=queryString.split(" ");
		List<String> result = new ArrayList<String>();


		for(String s:stringList){
			if(s.equals("and")||s.equals("or")||s.equals("not")){
				result.add(s);
			}
		}
		//if(queryString.indexOf("and")==-1||queryString.indexOf("or")==-1||queryString.indexOf("not")==-1) {
		//	return null;
		//}
		if(result.size()==0) {
			return null;
		}


		//result.trim();
		return result;
	}

	/*
	 * Extract the aggregate functions from the query. The presence of the aggregate
	 * functions can determined if we have either "min" or "max" or "sum" or "count"
	 * or "avg" followed by opening braces"(" after "select" clause in the query
	 * string. in case it is present, then we will have to extract the same. For
	 * each aggregate functions, we need to know the following: 1. type of aggregate
	 * function(min/max/count/sum/avg) 2. field on which the aggregate function is
	 * being applied.
	 *
	 * Please note that more than one aggregate function can be present in a query.
	 *
	 *
	 */
	public List<AggregateFunction> getAggregateFunctionsAlgo(String queryString){
		//System.out.println(queryString);
		List<AggregateFunction> returnAggregate = new ArrayList<AggregateFunction>();

		String [] result= {};
		//String finalString= new String();

		for(String s:queryString.split(" ")){
			if(s.length()>=5) {
				if(s.substring(0,3).equals("max")||s.substring(0,3).equals("min")||
						s.substring(0,3).equals("avg")||s.substring(0,5).equals("count")||s.substring(0,3).equals("sum")) {
					if(s.indexOf(",")!=-1) {
						result=s.split(",");
					}
					else
						result = s.split("'");
					//finalString=finalString+" "+s;
				}


			}

		}
		// result is a list
		for(String s:result) {
			if(s.substring(0,5).equals("count")) {

				AggregateFunction newObj = new AggregateFunction(s.substring(6,s.length()-1),s.substring(0,5));
				//System.out.println(s.substring(6,s.length()-2)+" "+s.substring(0,5));
				returnAggregate.add(newObj);
			}
			else if(s.substring(0,3).equals("max")||s.substring(0,3).equals("min")||
					s.substring(0,3).equals("avg")||s.substring(0,3).equals("sum")) {
				AggregateFunction newObj2 = new AggregateFunction(s.substring(4,s.length()-1),s.substring(0,3));
				//System.out.println(s.substring(4,s.length()-2)+" "+s.substring(0,3));
				returnAggregate.add(newObj2);
			}
		}
		//System.out.println(returnAggregate.get(0).toString());
		return returnAggregate;
	}
	
	
}
