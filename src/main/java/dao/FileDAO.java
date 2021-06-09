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
			String insertTransaction = "Insert into process(valuedate,entrydate,creditdebit,transactionammount,cusomter_reference,details,iban,incasantid,description,partyname,fileid) values "
					+ "(?,?,?,?,?,?,?,?,?,?,?)";
			String query = "Select f.fileid from dab_di20212b_7.transactionfile f " + "where transactionreferenceno = ? and "
					+ "relatedreference	=? and " + "accid = ? and " + "statementno= ? and " + "date = ? and "
					+ "currency = ? and " + "startingamount = ? and " + "userid = ? and " + "closingammount = ? and "
					+ "closingdate = ?";

			try {
				int index = 0;
				PreparedStatement statement1 = connection.prepareStatement(insertFile);
				PreparedStatement statement2 = connection.prepareStatement(insertTransaction);
				PreparedStatement statement3 = connection.prepareStatement(query);
				statement1.setString(1, parser.get20field(i));
				statement3.setString(1, parser.get20field(i));
				statement1.setString(2, parser.get21Field(i));
				statement3.setString(2, parser.get21Field(i));
				statement1.setString(3, parser.get25field(i));
				statement3.setString(3, parser.get25field(i));
				statement1.setString(4, parser.get28field(i));
				statement3.setString(4, parser.get28field(i));
				if (parser.get60field(i).containsKey("Last Statement Date")) {
					statement1.setDate(5,
							java.sql.Date.valueOf("20" + parser.get60field(i).get("Last Statement Date")));
					statement3.setDate(5,
							java.sql.Date.valueOf("20" + parser.get60field(i).get("Last Statement Date")));
					// (Date) formatter.parse(parser.get60field(i).get("Last Statement Date")));
				} else {
					statement1.setDate(5,
							java.sql.Date.valueOf("20" + parser.get60field(i).get("Current Statement Date")));
					statement3.setDate(5,
							java.sql.Date.valueOf("20" + parser.get60field(i).get("Current Statement Date")));
				}
				statement1.setString(6, parser.get60field(i).get("Currency"));
				statement3.setString(6, parser.get60field(i).get("Currency"));
				statement1.setFloat(7, Float.valueOf(parser.get60field(i).get("Ammount").replace(",", ".")));
				statement3.setFloat(7, Float.valueOf(parser.get60field(i).get("Ammount").replace(",", ".")));
				statement1.setInt(8, 2);
				statement3.setInt(8, 2);
				statement1.setFloat(9, Float.valueOf(parser.get62Ffield(i, 0).get("Ammount").replace(",", ".")));
				statement3.setFloat(9, Float.valueOf(parser.get62Ffield(i, 0).get("Ammount").replace(",", ".")));
				statement1.setDate(10, java.sql.Date.valueOf("20" + parser.get62Ffield(i, 0).get("Balance Date")));
				statement3.setDate(10, java.sql.Date.valueOf("20" + parser.get62Ffield(i, 0).get("Balance Date")));
				statement1.executeUpdate();
				int j = 0;
				while (parser.get61field(i, j) != null) {
					statement2.setDate(1, java.sql.Date.valueOf("20" + parser.get61field(i, j).get("Value Date")));

					statement2.setDate(2, java.sql.Date.valueOf("2021-" + parser.get61field(i, j).get("Entry Date")));
					statement2.setString(3, parser.get61field(i, j).get("Credit/Debit"));
					statement2.setFloat(4,
							Float.valueOf(parser.get61field(i, j).get("Transaction Amount").replace(",", ".")));
					statement2.setString(5, parser.get61field(i, j).get("Customer Reference"));
					if (parser.get86field(i, j).containsKey("Details")) {
						statement2.setString(6, parser.get86field(i, j).get("Details"));
					} else {
						statement2.setString(6, null);
					}
					if (parser.get86field(i, j).containsKey("TransactionIBAN")) {
						statement2.setString(7, parser.get86field(i, j).get("TransactionIBAN"));
					} else {
						statement2.setString(7, null);
					}
					if (parser.get86field(i, j).containsKey("IncassantID")) {
						statement2.setString(8, parser.get86field(i, j).get("IncassantID"));
					} else {
						statement2.setString(8, null);
					}
					if (parser.get86field(i, j).containsKey("description")) {
						statement2.setString(9, parser.get86field(i, j).get("description"));
					} else {
						statement2.setString(9, null);
					}
					if (parser.get86field(i, j).containsKey("partyName")) {
						statement2.setString(10, parser.get86field(i, j).get("partyName"));
					} else {
						statement2.setString(10, null);
					}
					ResultSet set = statement3.executeQuery();
					while(set.next()) {
						statement2.setInt(11, set.getInt(1));
					}
					statement2.executeUpdate();
					j++;
				}

				// System.out.println(res);
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
