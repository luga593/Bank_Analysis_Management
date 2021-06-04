package dao;

import model.User;

import java.sql.*;

public class UserDAO {

    public User loginCheck(String username, String password) throws ClassNotFoundException, SQLException {
        String host = "bronto.ewi.utwente.nl";
        String dbname = "dab_di20212b_7";
        String jdbcURL = "jdbc:postgresql://" + host + ":5432/" + dbname + "?dab_di20212b_7";
        String dbUser = "dab_di20212b_7";
        String dbPass = "WHT7j8rVmsTZfH70";

        Class.forName("com.postgresql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);

        String sql = "SELECT * FROM logIn WHERE username = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setUsername(username);
        }

        connection.close();

        return user;
    }
}
