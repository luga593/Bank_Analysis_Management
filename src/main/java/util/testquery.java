package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class testquery {
	private final static String query = "SELECT t.startingamount,p.entrydate,p.creditdebit, p.transactionammount ,t.time \n"
			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS t \n"
			+ "WHERE t.fileid = p.fileid AND t.time = (SELECT max(t.time) FROM dab_di20212b_7.transactionfile AS t) \n"
			+ "ORDER BY p.entrydate ";
	private static final String queryrep = "SELECT t.startingamount,p.entrydate,p.creditdebit, p.transactionammount ,t.time \n"
			+ "FROM dab_di20212b_7.process AS p, dab_di20212b_7.transactionfile AS t \n"
			+ "WHERE t.fileid = p.fileid AND t.time = (SELECT max(t.time) FROM dab_di20212b_7.transactionfile AS t) \n"
			+ "ORDER BY p.entrydate ";

	public static void main(String[] args) throws SQLException {
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
		for (int i = 0; i < finalresult.size(); i++) {
			for (int j = 0; j < finalresult.get(i).size(); j++) {
				System.out.println(finalresult.get(i).get(j));
			}
		}

		// Statement statement2 = newdb.getConnection().createStatement();
//	Statement statement3 = newdb.getConnection().createStatement();
//	Statement statement4 = newdb.getConnection().createStatement();

	}
}
