package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;

public class SimonsGraph {

	DbUtil newdb = new DbUtil();
	int rowCount = 0;

	// Statement statement2 = newdb.getConnection().createStatement();
//    	Statement statement3 = newdb.getConnection().createStatement();
//    	Statement statement4 = newdb.getConnection().createStatement();


	private static final String query = "SELECT t.startingamount,p.entrydate,p.creditdebit, p.transactionammount ,t.time \n"
			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS t \n"
			+ "WHERE t.fileid = p.fileid AND t.time = (SELECT max(t.time) FROM dab_di20212b_7.transactionfile AS t) \n"
			+ "ORDER BY p.entrydate ";
	private static final String queryrep = "SELECT t.startingamount,p.entrydate,p.creditdebit, p.transactionammount ,t.time \n"
			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS t \n"
			+ "WHERE t.fileid = p.fileid AND t.time = (SELECT max(t.time) FROM dab_di20212b_7.transactionfile AS t) \n"
			+ "ORDER BY p.entrydate ";

    	
//    	String query2 = "CREATE MATERIALIZED VIEW transactiondifer3 AS "
//    			+ "SELECT t.fileid, t.startingamount - t.closingammount AS transferredmoney \n"
//    			+ "FROM dab_di20212b_7.transactionfile AS t \n"
//    			+ "WHERE date_part('month',t.date) = 3 \n";
//    	String query3 = "SELECT SUM(tr.transferredmoney) AS transferedFrom \n"
//    			+ "FROM dab_di20212b_7.transactiondifer3 as tr \n"
//    			+ "WHERE tr.transferredmoney>0 AND tr.fileid <> 1 \n";
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
		List<List<String>> result = new ArrayList<List<String>>();
		List<List<String>> finalresult = new ArrayList<List<String>>();
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
		for (int i = 0; i < result.get(2).size(); i++) {
			if (result.get(2).get(i).equals("D")) {
				double res = amounttransfered.get(i) * (-1);
				amounttransfered.set(i, res);
			}
		}

		for (int i = 0; i < amounttransfered.size(); i++) {
			if (i == 0) {
				finalammounts.add(initialamount + amounttransfered.get(0));
			} else {
				finalammounts.add(finalammounts.get(i - 1) + amounttransfered.get(i));
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

    public static String toJavascriptArray(List<List<String>> arr){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=0; i<arr.get(0).size(); i++){
            sb.append("\"").append(arr.get(0)).append("\"");
            if(i+1 < arr.get(0).size()){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public static String toJavascriptArray1(List<List<String>> arr){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=0; i<arr.get(0).size(); i++){
            sb.append("\"").append(arr.get(0)).append("\"");
            if(i+1 < arr.get(0).size()){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public static void main(String[] args) throws SQLException {
    	SimonsGraph newggraph = new SimonsGraph();
    	List<List<String>> result1 = new ArrayList<List<String>>();
    	result1 = newggraph.getqueryResult();
    	 for(int i = 0;i<result1.size();i++) {
      		 for(int j=0;j<result1.get(i).size();j++) {
      			 System.out.println(result1.get(i).get(j));
      		 }
      	 }
    }

}
