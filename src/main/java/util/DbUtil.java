package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Utility Class for connecting to the postgres SQL database.
 * @author Group 7 of the Data & information project.
 * @author Luis Hinojosa (maintainer)
 */

public class DbUtil {

    private static Properties properties = new Properties();

    static {
        try {
            // Load properties from file using ClassLoader
            ClassLoader classLoader = DbUtil.class.getClassLoader();
            InputStream fis = classLoader.getResourceAsStream("db.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDriver(){
        return properties.getProperty("driverName");
    }
    public static String getJdbcURL(){
        return properties.getProperty("jdbcURL");
    }
    public static String getDbUser() {
        return properties.getProperty("dbUser");
    }

    public static String getDbPass() {
        return properties.getProperty("dbPass");
    }
    public static String getDBName(){
        return properties.getProperty("dbname");
    }

    static Connection connection = null;
    static {
        try {
            try {
                Class.forName(getDriver());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            connection = DriverManager.getConnection(getJdbcURL(), getDbUser(), getDbPass());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns the connection attribute.
     * @return The connection object
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * closes the connection.
     */
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