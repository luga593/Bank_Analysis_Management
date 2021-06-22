package dao;

import model.File;
import model.Process;
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
			File file = new File();
			file.setTransactionReference(mt940.getField20().getValue());
			statement1.setString(1, file.getTransactionReference());
			statement3.setString(1, file.getTransactionReference());
			if(mt940.getField21()!=null) {
				statement1.setString(2, mt940.getField21().getValue());
				statement3.setString(2, mt940.getField21().getValue());
			} else {
				statement1.setString(2,"No info available");
				statement3.setString(2,"No info available");
				
			}
			file.setAccid(mt940.getField25().getAccount());
			statement1.setString(3, file.getAccid());
			statement3.setString(3, file.getAccid());
			file.setStatementNo(mt940.getField28C().getStatementNumber());
			statement1.setString(4,file.getStatementNo());
			statement3.setString(4, file.getStatementNo());
			String date;
			String currency;
			Float amount;
			if (mt940.getField60F() != null) {
				file.setDate(mt940.getField60F().getDate());
				file.setCurrency(mt940.getField60F().getCurrency());
				file.setStartingAmount(Float.valueOf(mt940.getField60F().getAmount().replace(",", ".")));
				date = file.getDate();

			} else {
				file.setDate(mt940.getField60M().getDate());
				file.setCurrency(mt940.getField60M().getCurrency());
				file.setStartingAmount(Float.valueOf(mt940.getField60M().getAmount().replace(",", ".")));
				date = file.getDate();
			}
			statement1.setDate(5, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			statement3.setDate(5, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			// (Date) formatter.parse(parser.get60field(i).get("Last Statement Date")));
			statement1.setString(6, file.getCurrency());
			statement3.setString(6, file.getCurrency());
			statement1.setFloat(7, file.getStartingAmount());
			statement3.setFloat(7, file.getStartingAmount());
			statement1.setInt(8, 2); 
			statement3.setInt(8, 2); // userid should be dynamic
			if (mt940.getField62F() != null) {
				file.setClosingDate(mt940.getField62F().getDate());
				date = file.getClosingDate();
				file.setClosingAmount(Float.valueOf(mt940.getField62F().getAmount().replace(",", ".").replace("-", "")));

			} else {
				file.setClosingDate(mt940.getField62M().getDate());
				date = file.getClosingDate();
				file.setClosingAmount(Float.valueOf(mt940.getField62M().getAmount().replace(",", ".").replace("-", "")));
			}
			statement1.setFloat(9, file.getClosingAmount());
			statement3.setFloat(9, file.getClosingAmount());
			statement1.setDate(10, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			statement3.setDate(10, java.sql.Date
					.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
			java.sql.Timestamp timestamp = new Timestamp(dateTime);
			file.setTime(timestamp);
			statement1.setTimestamp(11,file.getTime(),Calendar.getInstance(TimeZone.getTimeZone("UTC")));
			statement3.setTimestamp(11,file.getTime(),Calendar.getInstance(TimeZone.getTimeZone("UTC")));
			file.setFilename(filename);
			statement1.setString(12, file.getFilename());
			statement3.setString(12, file.getFilename());
			statement1.executeUpdate();
			
			int j = 0;
			int cnt = 0;
			for (int k = 0; k < mt940.getField61().size(); k++) {
				Process process =  new Process();
				process.setEntryDate(mt940.getField61().get(k).getEntryDate());
				date = process.getEntryDate();
				statement2.setDate(1, java.sql.Date.valueOf("2021-" + date.substring(0, 2) + "-" + date.substring(2, 4)));
				process.setValueDate(mt940.getField61().get(k).getValueDate());
				date =  process.getValueDate();
				statement2.setDate(2, java.sql.Date.valueOf("20" + date.substring(0, 2) + "-" + date.substring(2, 4) + "-" + date.substring(4, 6)));
				process.setCreditDebit(mt940.getField61().get(k).getDCMark());
				statement2.setString(3, process.getCreditDebit());
				process.setTransactionAmount(Float.valueOf(mt940.getField61().get(k).getAmount().replace(",", ".")));
				statement2.setFloat(4, process.getTransactionAmount());
				process.setCustomerReference(mt940.getField61().get(k).getReferenceForTheAccountOwner());
				statement2.setString(5, process.getCustomerReference());
				int t = tmp.indexOf(mt940.getField61().get(k).getValue());
				int val = t + mt940.getField61().get(k).getValue().length() + 2;
				if (tmp.substring(val, val + 4) .equals(":86:")) {
					Line86Parsed line = new Line86Parsed(mt940.getField86().get(cnt).getValue());
					line.setDetails();
					cnt++;
					statement2.setString(6, null);
					process.setIban(line.getAccount());
					statement2.setString(7, process.getIban());
					statement2.setString(8, null);
					process.setDescription(line.getDescription());
					statement2.setString(9, process.getDescription());
					process.setPartyname(line.getNameParty());
					statement2.setString(10, process.getPartyname());
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
