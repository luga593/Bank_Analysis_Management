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

import servlets.SimonsChartServlet;
import servlets.getGraphServlet;

public class SimonsGraph{
    	
    	static DbUtil newdb = new DbUtil();
    	int rowCount = 0;
    	private List<List<String>> result = new ArrayList<List<String>>();
		private List<List<String>> finalresult = new ArrayList<List<String>>();
    	private static final String query ="SELECT f.startingamount, p.entrydate,p.creditdebit, p.transactionammount ,f.time "
    			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS f, "
    			+ "(Select t.filename as filename, max(t.time) as time from transactionfile t  , (Select r.filename as filename, r.time as time from request r ) as lastRequest , "
    			+ "(Select max(r.time) as time from request r) as l  "
    			+ "where lastrequest.time = l.time and t.filename = lastRequest.filename "
    			+ "group by t.filename) as lastFile "
    			+ "where f.fileid = p.fileid and f.filename = lastFile.filename and f.time = lastFile.time and EXTRACT(YEAR FROM p.entrydate) = ? and EXTRACT(MONTH FROM p.entrydate) = ? "
    			+ "ORDER BY p.entrydate";
    	private static final String query3 = "SELECT p.entrydate, p.creditdebit, p.transactionammount , f.time\n"
    			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS f , \n"
    			+ "(Select t.filename as filename, max(t.time) as time from transactionfile t  , (Select r.filename as filename, r.time as time from request r ) as lastRequest, "
    			+ "(Select max(r.time) as time from request r) as l  "
    			+ "where lastrequest.time = l.time and t.filename = lastRequest.filename "
    			+ "group by t.filename) as lastFile "
    			+ "where f.fileid = p.fileid and f.filename = lastFile.filename and f.time = lastFile.time and EXTRACT(YEAR FROM p.entrydate) = ? and EXTRACT(MONTH FROM p.entrydate) = ? ";

    public List<List<String>> getqueryResult(String monthyear) throws SQLException {
    	System.out.println("Am ajuns aici");
    	DbUtil newdb = new DbUtil();
		int rowCount = 0;
		System.out.println("Aproape de getgraphservlet");
		if(monthyear==null) {
			return null;
		}
		System.out.println("The month+year"+monthyear);
		String[] arrOfStr = monthyear.split("-");
		String year = arrOfStr[0];
    	String month = arrOfStr[1];
		List<Double> amounttransfered = new ArrayList<Double>();
		List<Double> finalammounts = new ArrayList<Double>();
		PreparedStatement statement =newdb.getConnection().prepareStatement(query);
		PreparedStatement statement5 =newdb.getConnection().prepareStatement(query);
		
    	statement5.setInt(1, Integer.parseInt(year));
    	statement5.setInt(2, Integer.parseInt(month));
    	statement.setInt(1, Integer.parseInt(year));
    	statement.setInt(2, Integer.parseInt(month));
		double initialamount = 0;
		ResultSet rs = statement.executeQuery();
		ResultSet rsrep = statement5.executeQuery();
		System.out.println("Am ajuns mai jos de statements");
		while (rsrep.next()) {
			result.add(new ArrayList<String>());
		}
		System.out.println("Am ajuns aici"+year);
		while (rs.next()) {
			result.get(0).add(rs.getString(1));
			result.get(1).add(rs.getString(2));
			result.get(2).add(rs.getString(3));
			amounttransfered.add(rs.getDouble(4));
		}
		System.out.println("Am ajuns aici"+month);
		if (result.size()==0) {
			return null;
		}
		System.out.println("Am ajuns aici");
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
		finalammounts.add(initialamount);
		for (int j = 0; j < 2; j++) {
			finalresult.add(new ArrayList<String>());
		}
		for (int j = 0; j < finalammounts.size(); j++) {
			if(j==finalammounts.size()-1) {
				finalresult.get(1).add((finalammounts.get(j).toString()));
				finalresult.get(0).add(result.get(1).get(0));
			}
			else {
			finalresult.get(0).add(result.get(1).get(j));
			finalresult.get(1).add((finalammounts.get(j).toString()));
			}
		}
		rs.close();
		rsrep.close();
		System.out.println("Am ajuns aproape la final"+finalresult.get(0).get(0));
		return finalresult;
    	
    }
    
public double getminimumammount() throws SQLException {
	List<Double> doublelist = new ArrayList<Double>();
	double minvalue = 0;
	if (finalresult.size()==0)
		return 0.0;
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
	return minvalue;	
}
public boolean getCreditResponse(String monthyear) throws SQLException {
	SimonsChartServlet getparam = new SimonsChartServlet();
	double credit = 0;
	if (credit == 0) {
		return false;
	}
	double creditmonth = credit/12;
	SimonsGraph newggraph = new SimonsGraph();
	double minammount = newggraph.getminimumammount();
	if(creditmonth>minammount)
		return false;
	else return true;
		
}
public String getTransferedToAcc() throws SQLException {
	PreparedStatement statement =newdb.getConnection().prepareStatement(query3);
	PreparedStatement statement5 =newdb.getConnection().prepareStatement(query3);
	String year="";
	String month="";
	if(month==null || year==null) {
		return "0.0";
	}
	statement5.setInt(1, Integer.parseInt(year));
	statement5.setInt(2, Integer.parseInt(month));
	statement.setInt(1, Integer.parseInt(year));
	statement.setInt(2, Integer.parseInt(month));
	ResultSet rs = statement.executeQuery();
	ResultSet rsrep = statement5.executeQuery();
	List<List<String>> result1 = new ArrayList<List<String>>();
	double transferedMoney = 0;
	while (rsrep.next()) {
		result1.add(new ArrayList<String>());
	}
	if(result1.size()==0) {
		return "0.0";
	}
	while (rs.next()) {
		result1.get(0).add(rs.getString(1));
		result1.get(1).add(rs.getString(2));
		result1.get(2).add(rs.getString(3));
	}
	for(int i=0;i<result1.get(1).size();i++) {
		if(result1.get(1).get(i).equals("C")) {
			transferedMoney = transferedMoney+Math.abs(Double.parseDouble(result1.get(2).get(i)));
		}
	}
	rs.close();
	rsrep.close();
	return String.valueOf(transferedMoney);
	
}
public String getTransferedFromAcc(String monthyear) throws SQLException {
	PreparedStatement statement =newdb.getConnection().prepareStatement(query3);
	PreparedStatement statement5 =newdb.getConnection().prepareStatement(query3);
	String year="";
	String month="";
	if(month==null || year==null) {
		return "0.0";
	}
	statement5.setInt(1, Integer.parseInt(year));
	statement5.setInt(2, Integer.parseInt(month));
	statement.setInt(1, Integer.parseInt(year));
	statement.setInt(2, Integer.parseInt(month));
	ResultSet rs = statement.executeQuery();
	ResultSet rsrep = statement5.executeQuery();
	List<List<String>> result1 = new ArrayList<List<String>>();
	double transferedMoney = 0;
	while (rsrep.next()) {
		result1.add(new ArrayList<String>());
	}
	if(result1.size()==0) {
		return "0.0";
	}
	while (rs.next()) {
		result1.get(0).add(rs.getString(1));
		result1.get(1).add(rs.getString(2));
		result1.get(2).add(rs.getString(3));
	}
	for(int i=0;i<result1.get(1).size();i++) {
		if(result1.get(1).get(i).equals("D")) {
			transferedMoney = transferedMoney + Math.abs(Double.parseDouble(result1.get(2).get(i)));
		}
	}
	rs.close();
	rsrep.close();
	return String.valueOf(transferedMoney);
	
}

    public static void main(String[] args) throws SQLException {
    	SimonsGraph newggraph = new SimonsGraph();
    	List<List<String>> result1 = new ArrayList<List<String>>();
    	result1 = newggraph.getqueryResult("2021-03");
//    	PreparedStatement statement =newdb.getConnection().prepareStatement(query);
//		PreparedStatement statement5 =newdb.getConnection().prepareStatement(query);
//		statement5.setInt(1, Integer.parseInt("2021"));
//    	statement5.setInt(2, Integer.parseInt("3"));
//    	statement.setInt(1, Integer.parseInt("2021"));
//    	statement.setInt(2, Integer.parseInt("3"));
//    	ResultSet rs = statement.executeQuery();
//		ResultSet rsrep = statement5.executeQuery();
//		System.out.println("Am ajuns mai jos de statements");
//		while (rsrep.next()) {
//			System.out.println("Sunt aici"+rsrep.getString(1));
//		}
//    	String result3;
//    	double result4;
//    	result3 = newggraph.getTransferedFromAcc();
//    	System.out.println("transfered from"+result3);
//    	result3 = newggraph.getTransferedToAcc();
//    	System.out.println("transfered to"+result3);
//    	result4 = newggraph.getminimumammount();
//    	System.out.println("minimum ammount"+result4);
    	 for(int i = 0;i<result1.size();i++) {
      		 for(int j=0;j<result1.get(i).size();j++) {
      			 System.out.println(result1.get(i).get(j));
      		 }
      	 }
    }
}

