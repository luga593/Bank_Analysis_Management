package util;

import servlets.logInServlet;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ListOfFiles {
	private Connection connection;
	public ListOfFiles() {
		 DbUtil DB = new DbUtil();
		 this.connection =  DB.getConnection();
	}
	public LinkedHashMap<String,java.sql.Timestamp > getFiles() {
		int currentUID = logInServlet.getUser().getUserID();

		LinkedHashMap<String,java.sql.Timestamp > map = new LinkedHashMap<String,java.sql.Timestamp >();
		String query = "Select distinct t.filename,min(t.time) from transactionFile t \r\n"
				+ "WHERE t.userid = ?"
				+ "GROUP BY t.filename, t.userid; \r\n";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, currentUID);
			ResultSet set = ps.executeQuery();
			while(set.next()) {
				map.put(set.getString(1), set.getTimestamp(2));
			}
			ps.close();
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
