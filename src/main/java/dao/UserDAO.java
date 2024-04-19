package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbUtil;
import util.PasswordUtil;

/**
 * Class implementing a Data Acess Object pattern for the users of the web-application.
 * This class handles cookie's and user's data validation.
 *
 * @author Group 4 of the Data & Information project
 * @author Luis Hinojosa (maintainer)
 */
public class UserDAO {
	
	/**
	 * Checks whether a user is already logged in. If there is an existing user, it returns his/her personID
	 *  otherwise, return -1
	 * @param uname the username of the user.
	 * @return int personid of the user in the login table.
	 */
	public static int verifyCookie(String uname) {
		PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        boolean status = false;
        int result = -1;

        try {
            if (connection == null || connection.isClosed()) {
                connection = DbUtil.getConnection();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            //ps = connection.prepareStatement("SELECT username,personid FROM dab_di20212b_7.login WHERE username=?");
            ps = connection.prepareStatement("SELECT username, personid FROM login WHERE username=?");
        	ps.setString(1, uname);
            rs = ps.executeQuery();
            status = rs.next();
            if (status == true) {
            	String curUserId = rs.getString(2);
            	System.out.println("curUserId: " + curUserId);
            	result = Integer.parseInt(curUserId);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
	}

    /**
     * Valides the provided username against the password. When successful, it authenticates the user.
     *
     * @param userName the name of the user
     * @param password the password provided
     * @return a boolean, true if validated, false if not.
     */
    public static boolean validate(String userName, String password) {
    	String encryptedPwd = PasswordUtil.encrypt(password);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        boolean status = false;
        try {
            if (connection == null || connection.isClosed()) {
                connection = DbUtil.getConnection();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            ps = connection.prepareStatement("SELECT username,password FROM login WHERE username=? AND password=?");
            ps.setString(1, userName);
            ps.setString(2, encryptedPwd);

            rs = ps.executeQuery();
            status = rs.next();
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return status;
    }
}