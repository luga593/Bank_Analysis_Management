package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbUtil;
import util.PasswordUtil;

public class UserDAO {
	
	/**
	 * Check whether a user is already login. If there is an existing user, return his/her personid;
	 *  otherwise, return -1
	 * @param uname
	 * @return personid in dab_di20212b_7.login
	 */
	public int verifyCookie(String uname) {
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
            ps = connection.prepareStatement("SELECT username,personid FROM dab_di20212b_7.login WHERE username=?");
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