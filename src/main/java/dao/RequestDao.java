package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

import util.DbUtil;

public class RequestDao {
	private Connection connection;
	public RequestDao() {
		try {
            if (connection == null || connection.isClosed()) {
                connection = DbUtil.getConnection();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	public void addToDatabase(String filename) {
		String insertFile = "Insert into request values(?,?)";
		try {
			PreparedStatement statement1 = connection.prepareStatement(insertFile);
			statement1.setString(1,filename);
			Long dateTime = System.currentTimeMillis();
			statement1.setTimestamp(2,new java.sql.Timestamp(dateTime),Calendar.getInstance(TimeZone.getTimeZone("UTC")));
			statement1.executeUpdate();
			statement1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
