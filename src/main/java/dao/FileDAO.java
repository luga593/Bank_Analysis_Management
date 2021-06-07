package dao;

import model.User;

import javax.xml.transform.Result;

import Parser.parser;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FileDAO {
	private Connection connection;

	public FileDAO() {
		String host = "bronto.ewi.utwente.nl";
		String dbname = "dab_di20212b_7";
		String jdbcURL = "jdbc:postgresql://" + host + ":5432/" + dbname + "?dab_di20212b_7";
		String dbUser = "dab_di20212b_7";
		String dbPass = "WHT7j8rVmsTZfH70";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addFileDetails(parser parser) {
		for (int i = 0; i < parser.getContentSize(); i++) {
			String insertFile = "Insert into transactionfile values " + "(?,?,?,?,?,?,?,?,?,?)";
			String insertTransaction = "Insert into process values " + "(?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				int index = 0;
				PreparedStatement statement = connection.prepareStatement(insertFile);
				statement.setString(1, parser.get20field(i));
				System.out.println(parser.get20field(i));
				statement.setString(2, parser.get21Field(i));
				statement.setString(3, parser.get25field(i));
				statement.setString(4, parser.get28field(i));
				SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);
				if(parser.get60field(i).containsKey("Last Statement Date")) {
					statement.setDate(5,java.sql.Date.valueOf("20"+parser.get60field(i).get("Last Statement Date")));
						//(Date) formatter.parse(parser.get60field(i).get("Last Statement Date")));
				} else {
					statement.setDate(5,java.sql.Date.valueOf("20"+parser.get60field(i).get("Current Statement Date")));
				}
				statement.setString(6, parser.get60field(i).get("Currency"));
				statement.setFloat(7, Float.valueOf(parser.get60field(i).get("Ammount").replace(",", ".")));
				statement.setInt(8, 2);
				statement.setFloat(9, Float.valueOf(parser.get62Ffield(i,0).get("Ammount").replace(",", ".")));
				statement.setDate(10,java.sql.Date.valueOf ("20" + parser.get62Ffield(i,0).get("Balance Date")));
				int res = statement.executeUpdate();
				System.out.println(res);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
	}
	public void addTransactionDetails() {
		
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
}
