package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Application;

public class ShowCharts extends Application{
    public static void main(String args[]) throws SQLException {
    	List<List<String>> result = new ArrayList<List<String>>();
    	DbUtil newdb = new DbUtil();
    	int rowCount = 0;
    	Statement statement = newdb.getConnection().createStatement();
    	Statement statement2 = newdb.getConnection().createStatement();
    	Statement statement3 = newdb.getConnection().createStatement();
    	Statement statement4 = newdb.getConnection().createStatement();
    	String query = "SELECT t.fileid, t.date,t.startingamount,t.closingdate,t.closingammount \n"
    			+ "FROM dab_di20212b_7.transactionfile AS t \n"
    			+ "ORDER BY t.date";
    	String query2 = "CREATE MATERIALIZED VIEW transactiondifer3 AS "
    			+ "SELECT t.fileid, t.startingamount - t.closingammount AS transferredmoney \n"
    			+ "FROM dab_di20212b_7.transactionfile AS t \n"
    			+ "WHERE date_part('month',t.date) = 3 \n";
    	String query3 = "SELECT SUM(tr.transferredmoney) AS transferedFrom \n"
    			+ "FROM dab_di20212b_7.transactiondifer3 as tr \n"
    			+ "WHERE tr.transferredmoney>0 AND tr.fileid <> 1 \n";
    	String query4 = "SELECT SUM(tr.transferredmoney) AS transferedTo \n"
    			+ "FROM dab_di20212b_7.transactiondifer3 as tr \n"
    			+ "WHERE tr.transferredmoney<0 AND tr.fileid <> 1 \n";
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
       			 System.out.println(result.get(i).get(j));
       		 }
       	 }
    	rs.close();
    	rs3.close();
    	rs4.close();
    	statement.close();
    	statement2.close();
    	statement3.close();
    	statement4.close();
    	statement5.close();
    	
}
    	}