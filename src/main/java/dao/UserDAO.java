package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbUtil;
import util.PasswordUtil;

public class UserDAO {
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