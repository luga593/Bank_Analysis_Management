package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//Class to connect to the database.
public class DbUtil {
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String host = "bronto.ewi.utwente.nl";
    private static final String dbname = "dab_di20212b_7";
    private static final String jdbcURL = "jdbc:postgresql://" + host + ":5432/" + dbname + "?dab_di20212b_7";
    private static final String dbUser = "dab_di20212b_7";
    private static final String dbPass = "WHT7j8rVmsTZfH70";
    static Connection connection = null;
    static {
        try {
            try {
                Class.forName(DRIVER_NAME);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(jdbcURL, dbname, dbPass);
			/*
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/dab_di20212b_110");
			connection = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		*/
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
    public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static void main(String args[]) throws SQLException {
    	DbUtil newdb = new DbUtil();
    	Statement statement = newdb.getConnection().createStatement();
    	String query = "SELECT t.fileid, t.date,t.startingamount,t.closingdate,t.closingammount\n"
    			+ "FROM dab_di20212b_7.transactionfile AS t\n"
    			+ "ORDER BY t.date";

    	ResultSet rs = statement.executeQuery(query);
    	while (rs.next())
    	{ 
    		System.out.println(rs.getString(2));
    	}
    	rs.close();
    	statement.close();
            
}
}