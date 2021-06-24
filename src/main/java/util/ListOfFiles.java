package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ListOfFiles {
	private Connection connection;
	public ListOfFiles() {
		 DbUtil DB = new DbUtil();
		 this.connection =  DB.getConnection();
	}
	public LinkedHashMap<String,java.sql.Timestamp > getFiles() {
		LinkedHashMap<String,java.sql.Timestamp > map = new LinkedHashMap<String,java.sql.Timestamp >();
		String query = "Select distinct t.filename,min(t.time) from transactionFile t \r\n"
				+ "group by t.filename";
		try {
			Statement st = connection.createStatement();
			ResultSet set = st.executeQuery(query);
			while(set.next()) {
				map.put(set.getString(1), set.getTimestamp(2));
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	public static void main(String args[]) {
		ListOfFiles list = new ListOfFiles();
		list.getFiles();		
	}

}
