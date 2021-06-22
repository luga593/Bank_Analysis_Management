package util;
import java.sql.*;
import java.util.ArrayList;

import javax.ws.rs.core.Application;

import org.knowm.xchart.*;

public class ShowCharts extends Application{


    public static void main(String args[]) throws SQLException {



    	DbUtil DB = new DbUtil();
    	
    	Connection connection =  DB.getConnection(); //this is being made static, so we might aswell declare it once
    	
    	
    	String query = "SELECT p.*, t.fileid FROM TransactionFile t, process p\r\n"
    			+ "WHERE p.fileid = t.fileid AND t.fileid = ?;"; //getting all the processes that match an ID
    	
    	String query1 = "SELECT COUNT(p.*) FROM TransactionFile t, process p\r\n" //getting count of processes with null iban (in a day statement mt940)
    			+ "WHERE p.fileid = t.fileid AND p.iban is NULL AND t.fileid = ?;";
    	
    	String query2 = "SELECT COUNT(p.*) FROM TransactionFile t, process p\r\n"
    				+ "WHERE p.fileid = t.fileid AND p.description is NULL AND t.fileid = ?;";
    	
    	//count how many have null description
    	//--------
    	

    	PreparedStatement st = connection.prepareStatement(query);
    	st.setLong(1,255);
    	ResultSet resultSet = st.executeQuery();
    	
    	int processCount =  0; //countss the process for a file ID
    	while(resultSet.next()){
    		System.out.println(resultSet.getString(4));
    		processCount++;
    		
    	}
    	System.out.println(processCount);
    	
    	//now check for the process that have a null recipient for your chart
/*    	PreparedStatement st1 = connection.prepareStatement(query1);
    	st1.setLong(1,255);
    	ResultSet resultSet1 = st1.executeQuery();
    	resultSet1.next();
    	System.out.println(resultSet1.getInt(1));
    		
    	
    	
    	//check for null description
    	PreparedStatement st2 = connection.prepareStatement(query1);
    	st2.setLong(1, 255);
    	ResultSet resultSet2 = st2.executeQuery();
    	resultSet2.next();
    	System.out.println(resultSet2.getInt(1));*/

    	/*
    	List<List<String>> result = new ArrayList<List<String>>();
    	DbUtil newdb = new DbUtil();
    	int rowCount = 0;
    	Statement statement = newdb.getConnection().createStatement();
    	Statement statement2 = newdb.getConnection().createStatement();
    	Statement statement3 = newdb.getConnection().createStatement();
    	Statement statement4 = newdb.getConnection().createStatement();
    	
    	//
    	String query = "SELECT t.fileid, t.date,t.startingamount,t.closingdate,t.closingammount \n"
    			+ "FROM dab_di20212b_7.transactionfile AS t \n"                              
    			+ "ORDER BY t.date";
    	String query2 = "CREATE MATERIALIZED VIEW transactiondifer3 AS "  // materialized view TRANSACTIONDIFER displays fileID with transferred money for month 3??
    			+ "SELECT t.fileid, t.startingamount - t.closingammount AS transferredmoney \n"
    			+ "FROM dab_di20212b_7.transactionfile AS t \n"
    			+ "WHERE date_part('month',t.date) = 3 \n";
    	
    	
    	//sum of all transferred RECEIVED money from M VIEW
    	String query3 = "SELECT SUM(tr.transferredmoney) AS transferedFrom \n"
    			+ "FROM dab_di20212b_7.transactiondifer3 as tr \n"
    			+ "WHERE tr.transferredmoney>0 AND tr.fileid <> 1 \n";
    	//sum of all transferred SENT  money from M VIEW
    	String query4 = "SELECT SUM(tr.transferredmoney) AS transferedTo \n"
    			+ "FROM dab_di20212b_7.transactiondifer3 as tr \n"
    			+ "WHERE tr.transferredmoney<0 AND tr.fileid <> 1 \n";
    	//the min per month
    	String query5 = "SELECT MIN(t.closingammount)"
    			+ "FROM dab_di20212b_7.transactionfile as t "
    			+ "WHERE EXTRACT(MONTH FROM t.date) = ?";
    	
    	ResultSet rs = statement.executeQuery(query);
    	try {
        	int rs2 = statement2.executeUpdate(query2);
        	}
        	catch(Exception e) {
        		//System.out.println("Materialized view was already executed once");
        	}
    	ResultSet rs3 = statement3.executeQuery(query3);
    	PreparedStatement statement5 =newdb.getConnection().prepareStatement(query5);
    	statement5.setInt(1, 3);
    	ResultSet rs5 = statement5.executeQuery();
    	ResultSet rs4 = statement4.executeQuery(query4);
    	while (rs3.next())
    	{ 
    		System.out.println("Sum of transfered money from the account"+rs3.getString(1));
    	}
    	while (rs5.next())
    	{ 
    		System.out.println("minimum ammount of money in the account for mounth march "+rs5.getString(1));
    	}
    	while (rs4.next())
    	{ 
    	if(rs4.getString(1) == null) {
    		System.out.println("No money were transfered to the account");
    	}
    	else {
    		System.out.println("Sum of transfered money to the account"+rs5.getString(1));
    	}
    	
    	}
    	 for(int i=0;i<11;i++) {
    		 result.add(new ArrayList<String>());
    	 }
    	 
    	while (rs.next())
    	{ 
    		result.get(0).add(rs.getString(1));
    		result.get(1).add(rs.getString(2));
    		result.get(2).add(rs.getString(3));
    		result.get(3).add(rs.getString(4));
    		result.get(4).add(rs.getString(5));

    	}
    	
    	
       	 for(int i = 0;i<result.size();i++) {
       		 for(int j=0;j<result.get(i).size();j++) {
       			 System.out.print(result.get(i).get(j) + " " );
       			 
       		 }
       		 System.out.println(" \n");
       	 }
       	 
    	System.out.println();
    	rs.close();
    	rs3.close();
    	rs4.close();
    	statement.close();
    	statement2.close();
    	statement3.close();
    	statement4.close();
    	statement5.close();
    	*/

}

    	}