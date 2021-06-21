package dao;

import model.User;
import util.DbUtil;

import javax.sound.sampled.Line;
import javax.xml.transform.Result;

import com.prowidesoftware.swift.model.field.Field60F;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import Parser.Line86Parsed;
import Parser.parser;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class FileDAO {
	int fileid;
	private Connection connection;

	public FileDAO() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DbUtil.getConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addFileDetails(MT940 mt940,String tmp,Long dateTime,String filename) {
		String insertFile = "Insert into transactionfile(transactionreferenceno,relatedreference,accid,statementno,date,currency,startingamount,userid,closingammount,closingdate,time,filename)" + 
				"values " + "(?,?,?,?,?,?,?,?,?,?,?,?)";
		String insertTransaction = "Insert into process(valuedate,entrydate,creditdebit,transactionammount,cusomter_reference,details,iban,incasantid,description,partyname,fileid) values "
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		String query = "Select f.fileid from dab_di20212b_7.transactionfile f "
				+ "where transactionreferenceno = ? and " + "relatedreference	=? and " + "accid = ? and "
				+ "statementno= ? and " + "date = ? and " + "currency = ? and " + "startingamount = ? and "
				+ "userid = ? and " + "closingammount = ? and " + "closingdate = ? and time = ? and filename = ?";

		try {
			int index = 0;
			PreparedStatement statement1 = connection.prepareStatement(insertFile);
			PreparedStatement statement2 = connection.prepareStatement(insertTransaction);
			PreparedStatement statement3 = connection.prepareStatement(query);
			statement1.setString(1, mt940.getField20().getValue());
			statement3.setString(1, mt940.getField20().getValue());
			if(mt940.getField21()!=null) {
				statement1.setString(2, mt940.getField21().getValue());
				statement3.setString(2, mt940.getField21().getValue());
			} else {
				statement1.setString(2,"No info available");
				statement3.setString(2,"No info available");
				
			}
			statement1.setString(3, mt940.getField25().getAccount());
			statement3.setString(3, mt940.getField25().getAccount());
			statement1.setString(4, mt940.getField28C().getStatementNumber());
			statement3.setString(4, mt940.getField28C().getStatementNumber());
			String date;
			String currency;
			Float amount;
			if (mt940.getField60F() != null) {
				date = mt940.getField60F().getDate();
				currency = mt940.getField60F().getCurrency();
				amount = Float.valueOf(mt940.getField60F().getAmount().replace(",", "."));

			} else {
				date = mt940.getField60M().getDate();
				currency = mt940.getField60M().getCurrency();
				amount = Float.valueOf(mt940.getField60M().getAmount().replace(",", "."));
			}
			statement1.setDate(5, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			statement3.setDate(5, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			// (Date) formatter.parse(parser.get60field(i).get("Last Statement Date")));
			statement1.setString(6, currency);
			statement3.setString(6, currency);
			statement1.setFloat(7, amount);
			statement3.setFloat(7, amount);
			statement1.setInt(8, 2);
			statement3.setInt(8, 2);
			if (mt940.getField62F() != null) {
				date = mt940.getField62F().getDate();
				amount = Float.valueOf(mt940.getField62F().getAmount().replace(",", ".").replace("-", ""));

			} else {
				date = mt940.getField62M().getDate();
				amount = Float.valueOf(mt940.getField62M().getAmount().replace(",", "."));
			}
			statement1.setFloat(9, amount);
			statement3.setFloat(9, amount);
			statement1.setDate(10, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			statement3.setDate(10, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			java.sql.Timestamp timestamp = new Timestamp(dateTime);
			statement1.setTimestamp(11,timestamp,Calendar.getInstance(TimeZone.getTimeZone("UTC")));
			statement3.setTimestamp(11,timestamp,Calendar.getInstance(TimeZone.getTimeZone("UTC")));
			
			statement1.setString(12, filename);
			statement3.setString(12, filename);
			statement1.executeUpdate();
			
			int j = 0;
			int cnt = 0;
			for (int k = 0; k < mt940.getField61().size(); k++) {
				date = mt940.getField61().get(k).getEntryDate();
				statement2.setDate(1, java.sql.Date.valueOf("2021-" + date.substring(0, 2) + "-" + date.substring(2, 4)));
				date =  mt940.getField61().get(k).getValueDate();
				statement2.setDate(2, java.sql.Date.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
				statement2.setString(3, mt940.getField61().get(k).getDCMark());
				statement2.setFloat(4, Float.valueOf(mt940.getField61().get(k).getAmount().replace(",", ".")));
				statement2.setString(5, mt940.getField61().get(k).getReferenceForTheAccountOwner());
				int t = tmp.indexOf(mt940.getField61().get(k).getValue());
				int val = t + mt940.getField61().get(k).getValue().length() + 2;
				if (tmp.substring(val, val + 4) .equals(":86:")) {
					Line86Parsed line = new Line86Parsed(mt940.getField86().get(cnt).getValue());
					line.setDetails();
					cnt++;
					statement2.setString(6, null);
					// if (parser.get86field(i, j).containsKey("Details")) {
					// statement2.setString(6, parser.get86field(i, j).get("Details"));
					// } else {
					// statement2.setString(6, null);
					// }
					// if (parser.get86field(i, j).containsKey("TransactionIBAN")) {
					statement2.setString(7, line.getAccount());
					// } else {
					// statement2.setString(7, null);
					// }
					// if (parser.get86field(i, j).containsKey("IncassantID")) {
					// statement2.setString(8, parser.get86field(i, j).get("IncassantID"));
					// } else {
					statement2.setString(8, null);
					// }
					// if (parser.get86field(i, j).containsKey("description")) {
					// statement2.setString(9, parser.get86field(i, j).get("description"));
					// } else {
					statement2.setString(9, line.getDescription());
					// }
					// if (parser.get86field(i, j).containsKey("partyName")) {
					statement2.setString(10, line.getNameParty());
					// } else {
					// statement2.setString(10, null);
					// }
					ResultSet set = statement3.executeQuery();
					while (set.next()) {
						statement2.setInt(11, set.getInt(1));
						this.fileid = set.getInt(1);
					}
					statement2.executeUpdate();
					// j++;
				}
			}

			// System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// }

		}
		//DbUtil.closeConnection();
	}
	public int getFileID() {
		return this.fileid;
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
