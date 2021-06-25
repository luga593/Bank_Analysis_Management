package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Application;

public class SimonsGraph extends Application{
    	
    	DbUtil newdb = new DbUtil();
    	int rowCount = 0;
    	
    	//Statement statement2 = newdb.getConnection().createStatement();
//    	Statement statement3 = newdb.getConnection().createStatement();
//    	Statement statement4 = newdb.getConnection().createStatement();
    	
    	private List<List<String>> result = new ArrayList<List<String>>();
		private List<List<String>> finalresult = new ArrayList<List<String>>();
    	private static final String query ="SELECT f.startingamount, p.entrydate,p.creditdebit, p.transactionammount ,f.time "
    			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS f, "
    			+ "(Select t.filename as filename, max(t.time) as time from transactionfile t  , (Select r.filename as filename, r.time as time from request r ) as lastRequest , "
    			+ "(Select max(r.time) as time from request r) as l  "
    			+ "where lastrequest.time = l.time and t.filename = lastRequest.filename "
    			+ "group by t.filename) as lastFile "
    			+ "where f.fileid = p.fileid and f.filename = lastFile.filename and f.time = lastFile.time "
    			+ "ORDER BY p.entrydate";
    	private static final String queryrep = "SELECT f.startingamount, p.entrydate,p.creditdebit, p.transactionammount ,f.time "
    			+ " FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS f, \n"
    			+ " (Select t.filename as filename, max(t.time) as time from transactionfile t  , (Select r.filename as filename, r.time as time from request r ) as lastRequest "
    			+ " , (Select max(r.time) as time from request r) as l  "
    			+ " where lastrequest.time = l.time and t.filename = lastRequest.filename "
    			+ " group by t.filename) as lastFile "
    			+ " where f.fileid = p.fileid and f.filename = lastFile.filename and f.time = lastFile.time \n"
    			+ " ORDER BY p.entrydate";
    	private static final String query3 = "SELECT p.creditdebit, p.transactionammount , f.time\n"
    			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS f , \n"
    			+ "(Select t.filename as filename, max(t.time) as time from transactionfile t  , (Select r.filename as filename, r.time as time from request r ) as lastRequest ,\n"
    			+ "(Select max(r.time) as time from request r) as l  \n"
    			+ "where lastrequest.time = l.time and t.filename = lastRequest.filename \n"
    			+ "group by t.filename) as lastFile \n"
    			+ "where f.fileid = p.fileid and f.filename = lastFile.filename and f.time = lastFile.time";
    	private static final String query3rep = "SELECT p.creditdebit, p.transactionammount , f.time\n"
    			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS f , \n"
    			+ "(Select t.filename as filename, max(t.time) as time from transactionfile t  , (Select r.filename as filename, r.time as time from request r ) as lastRequest ,\n"
    			+ "(Select max(r.time) as time from request r) as l  \n"
    			+ "where lastrequest.time = l.time and t.filename = lastRequest.filename \n"
    			+ "group by t.filename) as lastFile \n"
    			+ "where f.fileid = p.fileid and f.filename = lastFile.filename and f.time = lastFile.time";

//    	String query4 = "SELECT SUM(tr.transferredmoney) AS transferedTo \n"
//    			+ "FROM dab_di20212b_7.transactiondifer3 as tr \n"
//    			+ "WHERE tr.transferredmoney<0 AND tr.fileid <> 1 \n";
//    	String query5 = "SELECT MIN(t.closingammount)"
//    			+ "FROM dab_di20212b_7.transactionfile as t "
//    			+ "WHERE EXTRACT(MONTH FROM t.date) = ?";
    	
//    	try {
//        	int rs2 = statement2.executeUpdate(query2);
//        	}
//        	catch(Exception e) {
//        		//System.out.println("Materialized view was already executed once");
//        	}
//    	ResultSet rs3 = statement3.executeQuery(query3);
//    	PreparedStatement statement5 =newdb.getConnection().prepareStatement(query5);
//    	statement5.setInt(1, 3);
//    	ResultSet rs5 = statement5.executeQuery();
//    	ResultSet rs4 = statement4.executeQuery(query4);
//    	while (rs3.next())
//    	{ 
//    		System.out.println("Sum of transfered money from the account"+rs3.getString(1));
//    	}
//    	while (rs5.next())
//    	{ 
//    		System.out.println("minimum ammount of money in the account for mounth march "+rs5.getString(1));
//    	}
//    	while (rs4.next())
//    	{ 
//    	if(rs4.getString(1) == null) {
//    		System.out.println("No money were transfered to the account");
//    	}
//    	else {
//    		System.out.println("Sum of transfered money to the account"+rs5.getString(1));
//    	}
//    	}
    	
//    	rs3.close();
//    	rs4.close();
//    	statement.close();
//    	statement2.close();
//    	statement3.close();
//    	statement4.close();
//    	statement5.close();
    	


    public List<List<String>> getqueryResult() throws SQLException {
    	DbUtil newdb = new DbUtil();
		int rowCount = 0;
		
		List<Double> amounttransfered = new ArrayList<Double>();
		List<Double> finalammounts = new ArrayList<Double>();
		Statement statement = newdb.getConnection().createStatement();
		Statement statement5 = newdb.getConnection().createStatement();
		double initialamount = 0;
		ResultSet rs = statement.executeQuery(query);
		ResultSet rsrep = statement5.executeQuery(queryrep);
		while (rsrep.next()) {
			result.add(new ArrayList<String>());
		}

		while (rs.next()) {
			result.get(0).add(rs.getString(1));
			result.get(1).add(rs.getString(2));
			result.get(2).add(rs.getString(3));
			amounttransfered.add(rs.getDouble(4));
		}
		initialamount = Double.parseDouble(result.get(0).get(0));
	for(int i=0;i<result.get(2).size();i++) {
		if (result.get(2).get(i).equals("D")) {
			double res = amounttransfered.get(i)*(-1);
			amounttransfered.set(i, res);
		}
	}
		
		for (int i = 0; i < amounttransfered.size(); i++) {
			if(i==0) {
				finalammounts.add(initialamount + amounttransfered.get(0));
			}
			else {
			finalammounts.add(finalammounts.get(i-1) + amounttransfered.get(i));
			}
		}
		for (int j = 0; j < 2; j++) {
			finalresult.add(new ArrayList<String>());
		}
		for (int j = 0; j < finalammounts.size(); j++) {
			finalresult.get(0).add(result.get(1).get(j));
			finalresult.get(1).add((finalammounts.get(j).toString()));
		}
		rs.close();
		rsrep.close();
		return finalresult;
    	
    }
    
public String getminimumammount() throws SQLException {
	List<Double> doublelist = new ArrayList<Double>();
	double minvalue;
	if (finalresult.get(1).equals(null))
		return null;
	else
		for(int i=0;i<finalresult.get(1).size();i++) {
			doublelist.add(Double.parseDouble(finalresult.get(1).get(i)));
		}
	minvalue = doublelist.get(0);
	for(int i=0;i<doublelist.size();i++) {
		if (doublelist.get(i)<minvalue) {
			minvalue = doublelist.get(i);
		}
	}
	return String.valueOf(minvalue);
	
	
}
public String getTransferedToAcc() throws SQLException {
	Statement statement = newdb.getConnection().createStatement();
	Statement statement2 = newdb.getConnection().createStatement();
	ResultSet rs = statement.executeQuery(query3);
	ResultSet rsrep = statement2.executeQuery(query3rep);
	List<List<String>> result1 = new ArrayList<List<String>>();
	double transferedMoney = 0;
	while (rsrep.next()) {
		result1.add(new ArrayList<String>());
	}
	while (rs.next()) {
		result1.get(0).add(rs.getString(1));
		result1.get(1).add(rs.getString(2));
		result1.get(2).add(rs.getString(3));
	}
	for(int i=0;i<result1.get(1).size();i++) {
		if(result1.get(0).get(i).equals("C")) {
			transferedMoney = transferedMoney+Double.parseDouble(result1.get(1).get(i));
		}
	}
	rs.close();
	rsrep.close();
	return String.valueOf(transferedMoney);
	
}
public String getTransferedFromAcc() throws SQLException {
	Statement statement = newdb.getConnection().createStatement();
	Statement statement2 = newdb.getConnection().createStatement();
	ResultSet rs = statement.executeQuery(query3);
	ResultSet rsrep = statement2.executeQuery(query3rep);
	List<List<String>> result1 = new ArrayList<List<String>>();
	double transferedMoney = 0;
	while (rsrep.next()) {
		result1.add(new ArrayList<String>());
	}
	while (rs.next()) {
		result1.get(0).add(rs.getString(1));
		result1.get(1).add(rs.getString(2));
		result1.get(2).add(rs.getString(3));
	}
	for(int i=0;i<result1.get(1).size();i++) {
		if(result1.get(0).get(i).equals("D")) {
			transferedMoney = transferedMoney + Double.parseDouble(result1.get(1).get(i));
		}
	}
	rs.close();
	rsrep.close();
	return String.valueOf(transferedMoney);
	
}
    public static void main(String[] args) throws SQLException {
    	SimonsGraph newggraph = new SimonsGraph();
    	List<List<String>> result1 = new ArrayList<List<String>>();
    	result1 = newggraph.getqueryResult();
    	String result3;
    	String result4;
    	result3 = newggraph.getTransferedFromAcc();
    	System.out.println("transfered from"+result3);
    	result3 = newggraph.getTransferedToAcc();
    	System.out.println("transfered to"+result3);
    	result4 = newggraph.getminimumammount();
    	System.out.println("minimum ammount"+result4);
    	 for(int i = 0;i<result1.size();i++) {
      		 for(int j=0;j<result1.get(i).size();j++) {
      			 System.out.println(result1.get(i).get(j));
      		 }
      	 }
    }
}

